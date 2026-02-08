package com.company.arrays;

public class StudentMarksApp {

    public static void main(String[] args) {
        // Initialize data
        String[] studentNames = { "Rahul", "Anjali", "Vikram", "Sneha", "Kiran" };
        String[] subjectNames = { "Maths", "Science", "English", "CS" };

        int[][] marks = {
                { 78, 85, 90, 88 }, // Rahul's marks
                { 92, 80, 76, 89 }, // Anjali's marks
                { 65, 70, 68, 72 }, // Vikram's marks
                { 55, 60, 58, 62 }, // Sneha's marks
                { 88, 75, 82, 91 } // Kiran's marks
        };

        // Create MarksReportGenerator instance
        MarksReportGenerator reportGenerator = new MarksReportGenerator(
                studentNames, subjectNames, marks);

        // 1. Display marks table
        reportGenerator.printMarksTable();

        // 2. Total marks per student
        System.out.println("\n===== TOTAL MARKS PER STUDENT =====");
        int[] studentTotals = reportGenerator.getTotalMarksPerStudent();
        for (int i = 0; i < studentNames.length; i++) {
            System.out.println(studentNames[i] + " -> " + studentTotals[i]);
        }

        // 3. Average marks per student
        System.out.println("\n===== AVERAGE MARKS PER STUDENT =====");
        double[] studentAverages = reportGenerator.getAverageMarksPerStudent();
        for (int i = 0; i < studentNames.length; i++) {
            System.out.printf("%s -> %.2f\n", studentNames[i], studentAverages[i]);
        }

        // 4. Total marks per subject
        System.out.println("\n===== TOTAL MARKS PER SUBJECT =====");
        int[] subjectTotals = reportGenerator.getTotalMarksPerSubject();
        for (int j = 0; j < subjectNames.length; j++) {
            System.out.println(subjectNames[j] + " -> " + subjectTotals[j]);
        }

        // 5. Average marks per subject
        System.out.println("\n===== AVERAGE MARKS PER SUBJECT =====");
        double[] subjectAverages = reportGenerator.getAverageMarksPerSubject();
        for (int j = 0; j < subjectNames.length; j++) {
            System.out.printf("%s -> %.1f\n", subjectNames[j], subjectAverages[j]);
        }

        // 6. Find topper
        System.out.println("\n===== OVERALL TOPPER =====");
        String topper = reportGenerator.getOverallTopperName();
        int[] allTotals = reportGenerator.getTotalMarksPerStudent();
        int topperIndex = -1;
        for (int i = 0; i < studentNames.length; i++) {
            if (studentNames[i].equals(topper)) {
                topperIndex = i;
                break;
            }
        }
        System.out.println(topper + " with " + allTotals[topperIndex] + " marks");

        // 7. Find subject-wise topper
        System.out.println("\n===== SUBJECT TOPPERS =====");
        String[] subjectToppers = reportGenerator.getSubjectToppers();
        for (int j = 0; j < subjectNames.length; j++) {
            // Find the mark of the topper
            int maxMark = 0;
            for (int i = 0; i < studentNames.length; i++) {
                if (studentNames[i].equals(subjectToppers[j])) {
                    maxMark = marks[i][j];
                    break;
                }
            }
            System.out.println(subjectNames[j] + " -> " + subjectToppers[j] + " (" + maxMark + ")");
        }

        // 8. Grade distribution per student
        System.out.println("\n===== GRADES PER STUDENT =====");
        char[] grades = reportGenerator.getGradesPerStudent();
        for (int i = 0; i < studentNames.length; i++) {
            System.out.println(studentNames[i] + " -> " + grades[i]);
        }

        // 9. Get students who failed in at least one subject
        System.out.println("\n===== STUDENTS FAILED IN ANY SUBJECT (PASS MARK = 40) =====");
        String[] failedStudents = reportGenerator.getStudentsFailedInAnySubject(40);
        if (failedStudents.length == 0) {
            System.out.println("None");
        } else {
            for (String student : failedStudents) {
                System.out.println(student);
            }
        }

        // 10. Get class average
        System.out.println("\n===== CLASS AVERAGE =====");
        double classAverage = reportGenerator.getClassAverage();
        System.out.printf("Overall class average: %.1f\n", classAverage);

        // 11. Find a student's marks by name
        System.out.println("\n===== MARKS FOR STUDENT \"Rahul\" =====");
        int[] rahulMarks = reportGenerator.getMarksForStudent("Rahul");
        if (rahulMarks != null) {
            System.out.print("Rahul's Marks: ");
            for (int j = 0; j < subjectNames.length; j++) {
                System.out.print(subjectNames[j] + ": " + rahulMarks[j]);
                if (j < subjectNames.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        // 12. Get pass percentage for a subject
        System.out.println("\n===== PASS PERCENTAGE FOR SUBJECT \"Maths\" (PASS MARK = 40) =====");
        double passPercentage = reportGenerator.getPassPercentageForSubject("Maths", 40);
        System.out.printf("Pass percentage: %.1f%%\n", passPercentage);

        // Additional: Demonstrate ArrayUtils methods
        System.out.println("\n===== DEMONSTRATING ARRAY UTILS METHODS =====");

        int[] testArray = { 1, 2, 3, 4, 5 };
        System.out.println("Original array sum: " + ArrayUtils.sum(testArray));
        System.out.println("Original array max: " + ArrayUtils.max(testArray));
        System.out.println("Original array min: " + ArrayUtils.min(testArray));
        System.out.println("Original array average: " + ArrayUtils.average(testArray));

        int[] copiedArray = ArrayUtils.copy(testArray);
        System.out.println("Copied array: " + java.util.Arrays.toString(copiedArray));

        ArrayUtils.reverse(testArray);
        System.out.println("Reversed array: " + java.util.Arrays.toString(testArray));
    }
}