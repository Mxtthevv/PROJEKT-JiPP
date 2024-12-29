package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Inicjalizacja menedżera studentów (klasa do zarządzania bazą danych)
        StudentManagerImpl manager = new StudentManagerImpl();

        // Tworzenie głównego okna aplikacji
        JFrame frame = new JFrame("Zaawansowany system zarządzania studentami");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        // Główny panel aplikacji, układ BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // Panel wejściowy z układem GridLayout (pola tekstowe do wprowadzania danych)
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Etykiety i pola tekstowe do wprowadzania danych studenta
        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        JLabel gradeLabel = new JLabel("Grade:");
        JTextField gradeField = new JTextField();

        // Dodanie etykiet i pól tekstowych do panelu wejściowego
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);

        // Panel z przyciskami, każdy przycisk w osobnym wierszu
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton updateButton = new JButton("Update Student");
        JButton displayButton = new JButton("Display All Students");
        JButton averageButton = new JButton("Calculate Average");

        // Dodanie przycisków do panelu
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(averageButton);

        // Obszar wyjściowy do wyświetlania komunikatów
        JTextArea outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);

        // Dodanie paneli (wejściowego, przycisków, wyjściowego) do głównego układu
        mainPanel.add(inputPanel, BorderLayout.WEST);      // Pola tekstowe po lewej stronie
        mainPanel.add(buttonPanel, BorderLayout.EAST);     // Przyciski po prawej stronie
        mainPanel.add(outputScrollPane, BorderLayout.SOUTH); // Obszar wyjściowy na dole

        // Dodanie głównego panelu do okna aplikacji
        frame.add(mainPanel);

        // Obsługa przycisku dodawania studenta
        addButton.addActionListener(e -> {
            try {
                // Pobranie danych z pól tekstowych
                String id = idField.getText();
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                double grade = Double.parseDouble(gradeField.getText());

                // Walidacja danych wejściowych
                if (!id.matches("\\d+")) throw new IllegalArgumentException("Student ID must contain only digits.");
                if (!name.matches("[a-zA-Z]+")) throw new IllegalArgumentException("Name must contain only letters.");
                if (age <= 0) throw new IllegalArgumentException("Age must be positive.");
                if (grade < 0.0 || grade > 100.0) throw new IllegalArgumentException("Grade must be between 0.0 and 100.0.");

                // Tworzenie obiektu studenta i dodanie go do bazy danych
                Student student = new Student(name, age, grade, id);
                manager.addStudent(student);

                // Wyświetlenie komunikatu o sukcesie
                outputArea.setText("Student added successfully!");
            } catch (Exception ex) {
                // Obsługa błędów (np. złe dane wejściowe)
                outputArea.setText("Error: " + ex.getMessage());
            }
        });

        // Obsługa przycisku usuwania studenta
        removeButton.addActionListener(e -> {
            try {
                // Pobranie ID studenta do usunięcia
                String id = idField.getText();
                manager.removeStudent(id);

                // Wyświetlenie komunikatu o sukcesie
                outputArea.setText("Student removed successfully!");
            } catch (Exception ex) {
                // Obsługa błędów (np. student o takim ID nie istnieje)
                outputArea.setText("Error: " + ex.getMessage());
            }
        });

        // Obsługa przycisku aktualizacji danych studenta
        updateButton.addActionListener(e -> {
            try {
                // Pobranie danych z formularza
                String id = idField.getText();
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                double grade = Double.parseDouble(gradeField.getText());

                // Walidacja danych wejściowych
                if (!id.matches("\\d+")) throw new IllegalArgumentException("Student ID must contain only digits.");
                if (!name.matches("[a-zA-Z]+")) throw new IllegalArgumentException("Name must contain only letters.");
                if (age <= 0) throw new IllegalArgumentException("Age must be positive.");
                if (grade < 0.0 || grade > 100.0) throw new IllegalArgumentException("Grade must be between 0.0 and 100.0.");

                // Tworzenie obiektu studenta i aktualizacja danych w bazie
                Student student = new Student(name, age, grade, id);
                manager.updateStudent(id, student);

                // Wyświetlenie komunikatu o sukcesie
                outputArea.setText("Student updated successfully!");
            } catch (Exception ex) {
                // Obsługa błędów (np. brak studenta o takim ID)
                outputArea.setText("Error: " + ex.getMessage());
            }
        });

        // Obsługa przycisku wyświetlania wszystkich studentów
        displayButton.addActionListener(e -> {
            try {
                // Pobranie wszystkich studentów z bazy danych
                ArrayList<Student> students = manager.displayAllStudents();
                if (students.isEmpty()) {
                    // Jeżeli brak studentów, wyświetl odpowiedni komunikat
                    outputArea.setText("No students found.");
                } else {
                    // Jeżeli są studenci, wyświetl ich dane
                    StringBuilder sb = new StringBuilder();
                    for (Student s : students) {
                        sb.append(s.getStudentID()).append(", ")
                                .append(s.getName()).append(", ")
                                .append(s.getAge()).append(", ")
                                .append(s.getGrade()).append("\n");
                    }
                    outputArea.setText(sb.toString());
                }
            } catch (Exception ex) {
                // Obsługa błędów (np. błąd połączenia z bazą)
                outputArea.setText("Error: " + ex.getMessage());
            }
        });

        // Obsługa przycisku obliczania średniej oceny
        averageButton.addActionListener(e -> {
            try {
                // Obliczanie średniej oceny
                double avg = manager.calculateAverageGrade();
                outputArea.setText("Average Grade: " + avg);
            } catch (Exception ex) {
                // Obsługa błędów (np. brak studentów w bazie)
                outputArea.setText("Error: " + ex.getMessage());
            }
        });

        // Wyświetlenie okna aplikacji
        frame.setVisible(true);
    }
}
