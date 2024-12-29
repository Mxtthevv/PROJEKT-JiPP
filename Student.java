package org.example;

public class Student {
    private String name;      // Nazwa studenta
    private int age;          // Wiek studenta
    private double grade;     // Ocena studenta
    private String studentID; // ID studenta

    // Konstruktor klasy Student
    public Student(String name, int age, double grade, String studentID) {
        // Walidacja danych wejściowych
        if (!name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Name must contain only letters.");
        }
        if (!studentID.matches("\\d+")) {
            throw new IllegalArgumentException("Student ID must contain only digits.");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be positive.");
        }
        if (grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException("Grade must be between 0.0 and 100.0.");
        }

        // Inicjalizacja pól klasy
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.studentID = studentID;
    }

    // Gettery i settery dla atrybutów klasy Student

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Name must contain only letters.");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be positive.");
        }
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException("Grade must be between 0.0 and 100.0.");
        }
        this.grade = grade;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        if (!studentID.matches("\\d+")) {
            throw new IllegalArgumentException("Student ID must contain only digits.");
        }
        this.studentID = studentID;
    }

    // Metoda do wyświetlania informacji o studencie
    public void displayInfo() {
        System.out.println("Student ID: " + studentID); // Wyświetlenie ID studenta
        System.out.println("Name: " + name);            // Wyświetlenie imienia studenta
        System.out.println("Age: " + age);              // Wyświetlenie wieku studenta
        System.out.println("Grade: " + grade);          // Wyświetlenie oceny studenta
    }
}
