import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> studentNames = new ArrayList<>();
        ArrayList<Double> studentGrades = new ArrayList<>();

        System.out.println("--- Welcome to Student Grade Tracker ---");

        while (true) {
            System.out.print("Enter student name (or type 'exit' to finish): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("exit")) break;

            System.out.print("Enter grade for " + name + ": ");
            double grade = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            studentNames.add(name);
            studentGrades.add(grade);
        }

        if (studentGrades.isEmpty()) {
            System.out.println("No data entered.");
            scanner.close(); // Scanner closed here
            return;
        }

        double total = 0;
        for (double g : studentGrades) {
            total += g;
        }

        double average = total / studentGrades.size();
        double highest = Collections.max(studentGrades);
        double lowest = Collections.min(studentGrades);

        System.out.println("\n--- Grade Summary ---");
        System.out.println("Total Students: " + studentNames.size());
        for (int i = 0; i < studentNames.size(); i++) {
            System.out.println(studentNames.get(i) + ": " + studentGrades.get(i));
        }
        System.out.printf("Average Grade: %.2f\n", average);
        System.out.println("Highest Grade: " + highest);
        System.out.println("Lowest Grade: " + lowest);
        
        scanner.close(); // Scanner closed here
    }
}