package org.example.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import exceptions.RegistryException;
import org.example.Entities.Group;
import org.example.Entities.Student;
import org.example.Main.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class StudentFileService {
     private static final Logger log = LogManager.getLogger(StudentService.class);
     static Path dataDir = Paths.get("data");
     static Path students = dataDir.resolve("students.csv");

    static {
        try {
            if (!Files.exists(dataDir)) Files.createDirectories(dataDir);
        } catch (IOException e) {
            log.error("Error with file {}", e.getMessage());
        }
    }

    public static void saveAll() {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(students.toFile())))) {
            writer.printf("%-5s;%-10s;%-15s;%-15s;%-12s;%-25s;%-15s;%-12s;%-15s;%-10s;%-16s;%-15s;%-15s\n",
                    "ID", "LAST NAME", "FIRST NAME", "MIDDLE NAME","BIRTH DATE","EMAIL","PHONE", "STUDENT ID", "COURSE", "GROUP","ENTRY YEAR","STUDY FORM", "STATUS");

            for (Student s : Repository.getStudents()) {
                writer.printf("%-5d;%-10s;%-15s;%-15s;%-12s;%-25s;%-15s;%-12s;%-15d;%-10s;%-16d;%-15s;%-15s\n",
                        s.getId(),          // 0
                        s.getLastName(),    // 1
                        s.getFirstName(),   // 2
                        s.getMiddleName(),  // 3
                        s.getBirthDate(),   // 4
                        s.getEmail(),       // 5
                        s.getPhone(),       // 6
                        s.getStudentId(),   // 7
                        s.getCourse(),      // 8
                        s.getGroupName(),   // 9
                        s.getEntryYear(),   // 10
                        s.getStudyForm(),   // 11
                        s.getStatus()       // 12
                );
            }
        } catch (IOException e) {
            log.error("Error in saving {}", e.getMessage());
        }
    }

    public static void loadAll() {
        if (!Files.exists(students)) return;

        try {
            List<String> lines = Files.readAllLines(students);
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.trim().isEmpty()) continue;

                String[] p = line.split(";");
                if (p.length < 13) continue;

                Group group = Repository.findGroupByName(p[9].trim()).orElse(null);

                Student student = new Student(
                        Integer.parseInt(p[0].trim()), // id
                        p[1].trim(), p[2].trim(), p[3].trim(), //name
                        LocalDate.parse(p[4].trim()),
                        p[5].trim(), p[6].trim(),     // email, phone
                        p[7].trim(),                   // studentId
                        Integer.parseInt(p[8].trim()), // course
                        group,
                        Integer.parseInt(p[10].trim()),// entryYear
                        p[11].trim(),                  // studyForm
                        p[12].trim()                   // status
                );
                Repository.addStudent(student);
            }
        } catch (IOException | RegistryException e) {
            log.error("Error in loading {}", e.getMessage());
        }
    }
}
