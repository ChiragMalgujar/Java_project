import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class result {

    private final Scanner sc = new Scanner(System.in);

    public void studentAdd() {
        System.out.println("Give Student Detail to Add");
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Roll Number: ");
        int rollNo = sc.nextInt();

        System.out.print("Enter Class: ");
        int clas = sc.nextInt();

        double percentage = 0.0;

        if (clas >= 1 && clas <= 5) {
            percentage = getPercentage(4);
        } else if (clas >= 6 && clas <= 10) {
            percentage = getPercentage(6);
        } else if (clas == 11 || clas == 12) {
            percentage = getPercentage(5);
        } else {
            System.out.println("Invalid class entered!");
            sc.nextLine(); // clear buffer
            return;
        }

        String grade = calculateGrade(percentage);
        System.out.printf("Percentage: %.2f%%  Grade: %s%n", percentage, grade);

        try (FileWriter wr = new FileWriter("result.csv", true)) {
            // use 'grade' (lowercase) not 'Grade'
            wr.append(String.join(",", name,
                                   String.valueOf(rollNo),
                                   String.valueOf(clas),
                                   String.format("%.2f", percentage),
                                   grade));
            wr.append("\n");
            System.out.println("Record saved to result.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.nextLine(); // clear buffer before returning to menu
    }

    private double getPercentage(int subjectCount) {
        int total = 0;
        for (int i = 1; i <= subjectCount; i++) {
            System.out.printf("Enter Subject %d marks: ", i);
            total += sc.nextInt();
        }
        return total / (double) subjectCount;
    }

    private String calculateGrade(double pct) {
        if (pct >= 90) return "A+";
        else if (pct >= 80) return "A";
        else if (pct >= 70) return "B";
        else if (pct >= 60) return "C";
        else if (pct >= 50) return "D";
        else return "E";
    }

    public void reportCard() {
        System.out.println("\n---- Report Card ----");
        try (BufferedReader br = new BufferedReader(new FileReader("result.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No report found or file error.");
        }
    }

    public static void main(String[] args) {
        Scanner menuScanner = new Scanner(System.in);
        result app = new result();        // create an object to call instance methods

        System.out.println("Student Result Management System");

        while (true) {
            System.out.println("\n1. Add student details");
            System.out.println("2. Display report card");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = menuScanner.nextLine();

            switch (choice) {
                case "1" -> app.studentAdd();   // call through the object
                case "2" -> app.reportCard();   // call through the object
                case "3" -> {
                    System.out.println("Goodbye");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
