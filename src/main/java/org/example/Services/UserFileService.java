package org.example.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.*;
import org.example.Annotations.Encrypted;
import org.example.Annotations.UserJsonField;
import org.example.Entities.User;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserFileService {
    private final String fileName;
    private static final Logger log = LogManager.getLogger(UserFileService.class);
    public UserFileService(String fileName) {
        this.fileName = fileName;
    }
    public void saveUsersToFile(List<User> users) {
        Path path = Paths.get(fileName);

        StringJoiner allUsersJson = new StringJoiner(",\n", "[\n", "\n]");

        try {
            for (User user : users) {
                String json = JsonUserSerializer.serialize(user);
                allUsersJson.add("  " + json);
            }
            Files.writeString(
                    path,
                    allUsersJson.toString(),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

        } catch (IllegalAccessException e) {
            log.warn("Failed field access: {}", e.getMessage());
        } catch (IOException e) {
            log.warn("Failed file writing: {}", e.getMessage());
        }
    }
    public void loadUsersFromFile() {
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            return;
        }
        try {
            String content = Files.readString(path, StandardCharsets.UTF_8);

            content = content.trim();
            if (content.startsWith("[") && content.endsWith("]")) {
                content = content.substring(1, content.length() - 1);
            }

            String[] objects = content.split("(?<=\\}),\\s*(?=\\{)");

            for (String objJson : objects) {
                if (!objJson.isBlank()) {
                    User user = JsonUserSerializer.deserialize(objJson.trim());
                    Repository.addUser(user);
                }
            }

        } catch (Exception e) {
            log.error("Loading error: {}", e.getMessage());
        }

    }
    private static class JsonUserSerializer {
        private static final String KEY="RealSecret";
        private static String encrypt(String text) {
            byte[] result = text.getBytes();
            byte[] keyBytes = KEY.getBytes();
            for (int i = 0; i < result.length; i++) {
                result[i] = (byte) (result[i] ^ keyBytes[i % keyBytes.length]);
            }
            return Base64.getEncoder().encodeToString(result);
        }
        private static String decrypt(String text) {
            byte[] decoded = Base64.getDecoder().decode(text);
            byte[] keyBytes = KEY.getBytes();
            for (int i = 0; i < decoded.length; i++) {
                decoded[i] = (byte) (decoded[i] ^ keyBytes[i % keyBytes.length]);
            }
            return new String(decoded);
        }
        public static String serialize(User user) throws IllegalAccessException {
            StringJoiner json = new StringJoiner(", ", "{", "}");
            for (Field field :  User.class.getDeclaredFields()) {

                if (field.isAnnotationPresent(UserJsonField.class)) {
                    field.setAccessible(true);

                    UserJsonField an = field.getAnnotation(UserJsonField.class);
                    String name = field.getName().isEmpty()? an.name() : field.getName();
                    Object value = field.get(user);
                    if (field.isAnnotationPresent(Encrypted.class) && value instanceof String) {
                        value = encrypt((String) value);
                    }
                    json.add("\"" +name+ "\": \"" +value+ "\"");
                }
            }
            return json.toString();
        }
        public static User deserialize(String json) throws Exception {

            User user = new User();
            for (Field field : User.class.getDeclaredFields()) {
                if (field.isAnnotationPresent(UserJsonField.class)) {
                    UserJsonField ann = field.getAnnotation(UserJsonField.class);
                    String jsonKey = ann.name().isEmpty() ? field.getName() : ann.name();

                    // Looking for "key": "value"
                    Pattern pattern = Pattern.compile("\"" + jsonKey + "\":\\s*\"([^\"]*)\"");
                    Matcher matcher = pattern.matcher(json);

                    if (matcher.find()) {
                        String value = matcher.group(1);
                        field.setAccessible(true);
                        if (field.isAnnotationPresent(Encrypted.class))
                            value = decrypt(value);
                        if (field.getType() == int.class || field.getType() == Integer.class) {
                            field.set(user, Integer.parseInt(value));
                        } else if (field.getType().isEnum()) {
                            @SuppressWarnings("unchecked")
                            Enum<?> enumValue = Enum.valueOf((Class<Enum>) field.getType(), value);
                            field.set(user, enumValue);}
                        else {
                            field.set(user, value);
                        }
                    }
                }
            }
            return user;
        }
    }
}
