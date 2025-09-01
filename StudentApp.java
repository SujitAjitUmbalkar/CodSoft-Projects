import java.io.*;
import java.util.*;

class Student {
    private String name;
    private int rollNo;
    private String grade;

    public Student(String name, int rollNo, String grade) {
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty())
            this.name = name;
    }

    public void setGrade(String grade) {
        if (grade != null && !grade.trim().isEmpty())
            this.grade = grade;
    }

    @Override
    public String toString() {
        return rollNo + " | " + name + " | " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.txt";

    // Load data from file
    public void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    students.add(new Student(parts[0], Integer.parseInt(parts[1]), parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("No previous data found, starting fresh.");
        }
    }

    // Save data to file
    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.getName() + "," + s.getRollNo() + "," + s.getGrade());
            }
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    public void addStudent(Student s) {
        students.add(s);
        System.out.println(" Student added successfully.");
    }

    public void removeStudent(int rollNo) {
        Student toRemove = null;
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                toRemove = s;
                break;
            }
        }
        if (toRemove != null) {
            students.remove(toRemove);
            System.out.println(" Student removed.");
        } else {
            System.out.println(" No student found with roll " + rollNo);
        }
    }

    public void searchStudent(int rollNo) {
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                System.out.println("🔎 Found: " + s);
                return;
            }
        }
        System.out.println(" Student not found.");
    }

    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            System.out.println("\n--- Student List ---");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }
}

public class StudentApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.loadFromFile();

        boolean running = true;
        while (running) {
            System.out.println("\n===============================");
            System.out.println("   📘 Student Management Menu  ");
            System.out.println("===============================");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println(" Name cannot be empty.");
                        break;
                    }
                    System.out.print("Enter Roll Number: ");
                    int rollNo;
                    try {
                        rollNo = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println(" Roll number must be numeric.");
                        break;
                    }
                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine().trim();
                    if (grade.isEmpty()) {
                        System.out.println(" Grade cannot be empty.");
                        break;
                    }
                    sms.addStudent(new Student(name, rollNo, grade));
                    break;

                case 2:
                    System.out.print("Enter Roll Number to remove: ");
                    try {
                        int r = Integer.parseInt(sc.nextLine());
                        sms.removeStudent(r);
                    } catch (NumberFormatException e) {
                        System.out.println(" Invalid roll number.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Roll Number to search: ");
                    try {
                        int r = Integer.parseInt(sc.nextLine());
                        sms.searchStudent(r);
                    } catch (NumberFormatException e) {
                        System.out.println(" Invalid roll number.");
                    }
                    break;

                case 4:
                    sms.displayAll();
                    break;

                case 5:
                    sms.saveToFile();
                    running = false;
                    System.out.println(" Data saved. Exiting...");
                    break;

                default:
                    System.out.println(" Invalid option, try again.");
            }
        }
        sc.close();
    }
}
