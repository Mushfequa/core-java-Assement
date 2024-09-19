package CoreAssesment;

import java.util.ArrayList;
import java.util.Scanner;



class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private ArrayList<String> subjects = new ArrayList<>();
    private ArrayList<Integer> marks = new ArrayList<>();
    private ArrayList<Integer> fees = new ArrayList<>();

    
    public Student(int id, String firstName, String lastName, String contactNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }

    
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public ArrayList<String> getSubjects() {
        return subjects;
    }

    public ArrayList<Integer> getMarks() {
        return marks;
    }

    public ArrayList<Integer> getFees() {
        return fees;
    }

   
    public void addSubject(String subject, int mark, int fee) {
        this.subjects.add(subject);
        this.marks.add(mark);
        this.fees.add(fee);
    }
}



public class StudentManagementSystem {

	 
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            // Role selection menu
            System.out.println("Select a role:");
            System.out.println("1. Counsellor");
            System.out.println("2. Faculty");
            System.out.println("3. Exit");
            System.out.print("Enter a role id: ");
            int role = scanner.nextInt();
            scanner.nextLine(); 

            switch (role) {
                case 1:
                    counsellorMenu();
                    break;
                case 2:
                    facultyMenu();
                    break;
                case 3:
                    System.out.println("Exiting system...");
                    System.exit(0);
                default:
                    System.out.println("Invalid role id! Please try again.");
            }
        }
    }

  
    public static void counsellorMenu() {
        while (true) {
            System.out.println("\nCounsellor Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. View All Students");
            System.out.println("4. View Specific Student");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter a choice by counsellor: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    viewAllStudents();
                    break;
                case 4:
                    viewSpecificStudent();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Faculty menu for adding marks and viewing students
    public static void facultyMenu() {
        while (true) {
            System.out.println("\nFaculty Menu:");
            System.out.println("1. Add Marks to Student");
            System.out.println("2. View All Students");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter a choice by faculty: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addMarksToStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void addStudent() {
        System.out.print("Enter Serial Number: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        String firstName;
        while (true) {
            System.out.print("Enter First Name: ");
            firstName = scanner.nextLine();
            if (firstName.matches("[a-zA-Z]+")) {
                break;
            } else {
                System.out.println("Invalid input! First Name must contain only alphabets.");
            }
        }

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        String contactNumber;
        while (true) {
            System.out.print("Enter Contact Number: ");
            contactNumber = scanner.nextLine();
            if (contactNumber.matches("\\d{10}")) {
                break;
            } else {
                System.out.println("Invalid input! Contact Number must be 10 digits.");
            }
        }

        Student student = new Student(id, firstName, lastName, contactNumber);
        addSubjectsToStudent(student);
        students.add(student);
        System.out.println("Student added successfully!");
    }

    public static void addSubjectsToStudent(Student student) {
        while (true) {
            System.out.print("Enter a Subject: ");
            String subject = scanner.nextLine();
            System.out.print("Enter Marks: ");
            int marks = scanner.nextInt();
            System.out.print("Enter Fees: ");
            int fees = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            student.addSubject(subject, marks, fees);

            System.out.print("Do you want to add another subject? (y/n): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    public static void removeStudent() {
        System.out.print("Enter Student ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Student studentToRemove = null;
        for (Student student : students) {
            if (student.getId() == id) {
                studentToRemove = student;
                break;
            }
        }

        if (studentToRemove != null) {
            System.out.print("Are you sure you want to delete this student? (y/n): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("y")) {
                students.remove(studentToRemove);
                System.out.println("Student removed successfully!");
            } else {
                System.out.println("Operation cancelled.");
            }
        } else {
            System.out.println("Student with ID " + id + " does not exist.");
        }
    }

    public static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            System.out.println("\nList of all students:");
            for (Student student : students) {
                System.out.println("ID: " + student.getId() + ", Name: " + student.getFirstName() + " " + student.getLastName() + ", Contact: " + student.getContactNumber());
            }
        }
    }

    public static void viewSpecificStudent() {
        System.out.print("Enter Student ID to view: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println("ID: " + student.getId() + ", Name: " + student.getFirstName() + " " + student.getLastName() + ", Contact: " + student.getContactNumber());
                return;
            }
        }
        System.out.println("Student with ID " + id + " does not exist.");
    }

    public static void addMarksToStudent() {
        System.out.print("Enter Student ID to add marks: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        for (Student student : students) {
            if (student.getId() == id) {
                addSubjectsToStudent(student);
                System.out.println("Marks added successfully!");
                return;
            }
        }
        System.out.println("Student with ID " + id + " does not exist.");
    }
}



