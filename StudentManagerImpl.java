package org.example;

import java.sql.*;
import java.util.ArrayList;

public class StudentManagerImpl implements StudentManager {
    // Ścieżka do bazy danych SQLite
    private static final String DB_URL = "jdbc:sqlite:students.db";

    // Konstruktor klasy, który tworzy tabelę w bazie danych, jeśli nie istnieje
    public StudentManagerImpl() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            // Zapytanie SQL do tworzenia tabeli
            String createTableQuery = """
                CREATE TABLE IF NOT EXISTS students (
                    studentID TEXT PRIMARY KEY,
                    name TEXT NOT NULL,
                    age INTEGER NOT NULL,
                    grade REAL NOT NULL
                );
            """;
            conn.createStatement().execute(createTableQuery);  // Wykonanie zapytania tworzącego tabelę
        } catch (SQLException e) {
            e.printStackTrace();  // Logowanie błędu połączenia z bazą danych
        }
    }

    @Override
    public void addStudent(Student student) {
        // Zapytanie do dodania studenta do bazy danych
        String query = "INSERT INTO students (studentID, name, age, grade) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            // Walidacja danych przed dodaniem
            if (student.getAge() <= 0) throw new IllegalArgumentException("Age must be positive.");
            if (student.getGrade() < 0.0 || student.getGrade() > 100.0) throw new IllegalArgumentException("Grade must be between 0.0 and 100.0.");

            // Ustawienie wartości w zapytaniu
            stmt.setString(1, student.getStudentID());
            stmt.setString(2, student.getName());
            stmt.setInt(3, student.getAge());
            stmt.setDouble(4, student.getGrade());
            stmt.executeUpdate();  // Wykonanie zapytania do bazy danych
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();  // Logowanie błędu, np. błędy SQL lub walidacji danych
        }
    }

    @Override
    public void removeStudent(String studentID) {
        // Zapytanie do usunięcia studenta z bazy
        String query = "DELETE FROM students WHERE studentID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentID);
            int rowsAffected = stmt.executeUpdate();  // Wykonanie zapytania

            // Sprawdzenie, czy usunięto jakikolwiek rekord
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("Student with ID " + studentID + " does not exist.");
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();  // Logowanie błędu
        }
    }

    @Override
    public void updateStudent(String studentID, Student updatedStudent) {
        // Zapytanie do aktualizacji danych studenta
        String query = "UPDATE students SET name = ?, age = ?, grade = ? WHERE studentID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            // Walidacja danych przed aktualizowaniem
            if (updatedStudent.getAge() <= 0) throw new IllegalArgumentException("Age must be positive.");
            if (updatedStudent.getGrade() < 0.0 || updatedStudent.getGrade() > 100.0) throw new IllegalArgumentException("Grade must be between 0.0 and 100.0.");

            stmt.setString(1, updatedStudent.getName());
            stmt.setInt(2, updatedStudent.getAge());
            stmt.setDouble(3, updatedStudent.getGrade());
            stmt.setString(4, studentID);
            int rowsAffected = stmt.executeUpdate();  // Wykonanie zapytania

            // Sprawdzenie, czy zaktualizowano jakikolwiek rekord
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("Student with ID " + studentID + " does not exist.");
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();  // Logowanie błędu, np. błędy SQL lub walidacji danych
        }
    }

    @Override
    public ArrayList<Student> displayAllStudents() {
        // Zapytanie do pobrania wszystkich studentów z bazy danych
        String query = "SELECT * FROM students";
        ArrayList<Student> students = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Przetwarzanie wyników zapytania
            while (rs.next()) {
                String id = rs.getString("studentID");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double grade = rs.getDouble("grade");
                students.add(new Student(name, age, grade, id));  // Dodanie studenta do listy
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Logowanie błędu połączenia z bazą danych
        }
        return students;  // Zwrócenie listy studentów
    }

    @Override
    public double calculateAverageGrade() {
        // Zapytanie do obliczenia średniej ocen
        String query = "SELECT AVG(grade) FROM students";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Zwrócenie średniej ocen
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Logowanie błędu
        }
        return 0.0;  // Zwrócenie 0.0, jeśli nie ma studentów
    }
}
