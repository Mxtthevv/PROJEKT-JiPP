package org.example;

import java.util.ArrayList;

public interface StudentManager {
    // Metoda dodająca nowego studenta do systemu
    void addStudent(Student student);

    // Metoda usuwająca studenta z systemu na podstawie jego unikalnego ID
    void removeStudent(String studentID);

    // Metoda aktualizująca dane istniejącego studenta
    void updateStudent(String studentID, Student updatedStudent);

    // Metoda zwracająca listę wszystkich studentów zapisanych w systemie
    ArrayList<Student> displayAllStudents();

    // Metoda obliczająca średnią ocen wszystkich studentów
    double calculateAverageGrade();
}
