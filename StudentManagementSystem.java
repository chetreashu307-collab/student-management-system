    
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentManagementSystem {

    private static final Scanner sc = new Scanner(System.in);

    // Stores students using their unique IDs
    private static final Map<Integer, Student> students =
            new LinkedHashMap<>();

    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("   STUDENT MANAGEMENT SYSTEM");
        System.out.println("==================================");

        while (true) {
            showMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println(
                        "Invalid choice. Enter a number from 1 to 6."
                    );
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n---------- MENU ----------");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.println("--------------------------");
    }

    private static void addStudent() {
        System.out.println("\n--- Add Student ---");

        int id = readInt("Enter student ID: ");

        if (students.containsKey(id)) {
            System.out.println("Student ID already exists!");
            return;
        }

        String name = readNonEmpty("Enter student name: ");
        int age = readInt("Enter age: ");

        while (age <= 0 || age > 120) {
            System.out.println("Age must be between 1 and 120.");
            age = readInt("Enter age again: ");
        }

        String course = readNonEmpty("Enter course: ");
        double marks = readMarks();

        Student student =
                new Student(id, name, age, course, marks);

        students.put(id, student);

        System.out.println("Student added successfully!");
    }

    private static void viewStudents() {
        System.out.println("\n--- All Students ---");

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        printHeader();

        for (Student student : students.values()) {
            student.displayDetails();
        }
    }

    private static void searchStudent() {
        System.out.println("\n--- Search Student ---");

        int id = readInt("Enter student ID to search: ");
        Student student = students.get(id);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            printHeader();
            student.displayDetails();
        }
    }

    private static void updateStudent() {
        System.out.println("\n--- Update Student ---");

        int id = readInt("Enter student ID to update: ");
        Student student = students.get(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println(
            "Enter the new details for student ID " + id
        );

        String name = readNonEmpty("Enter new name: ");
        int age = readInt("Enter new age: ");

        while (age <= 0 || age > 120) {
            System.out.println("Age must be between 1 and 120.");
            age = readInt("Enter age again: ");
        }

        String course = readNonEmpty("Enter new course: ");
        double marks = readMarks();

        student.setName(name);
        student.setAge(age);
        student.setCourse(course);
        student.setMarks(marks);

        System.out.println("Student updated successfully!");
    }

    private static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");

        int id = readInt("Enter student ID to delete: ");

        Student removed = students.remove(id);

        if (removed == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Student deleted successfully!");
        }
    }

    private static void printHeader() {
        System.out.printf(
            "%-6s %-20s %-6s %-15s %-8s %-5s%n",
            "ID", "Name", "Age", "Course", "Marks", "Grade"
        );

        System.out.println(
            "--------------------------------------------------------------"
        );
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readMarks() {
        while (true) {
            System.out.print("Enter marks (0-100): ");
            String input = sc.nextLine().trim();

            try {
                double marks = Double.parseDouble(input);

                if (Double.isFinite(marks)
                        && marks >= 0 && marks <= 100) {
                    return marks;
                }

                System.out.println(
                    "Marks must be between 0 and 100."
                );
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid marks.");
            }
        }
    }
}


