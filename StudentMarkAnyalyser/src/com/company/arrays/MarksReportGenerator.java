package com.company.arrays;

public class MarksReportGenerator {

    // Fields
    private String[] studentNames;
    private String[] subjectNames;
    private int[][] marks; // marks[studentIndex][subjectIndex]

    // Constructor
    public MarksReportGenerator(String[] studentNames, String[] subjectNames, int[][] marks) {
        this.studentNames = studentNames;
        this.subjectNames = subjectNames;
        this.marks = marks;
    }

    // 1. Display marks table
    public void printMarksTable() {
        System.out.println("\n===== MARKS TABLE =====");

        // Print header
        System.out.print("Student\t");
        for (String subject : subjectNames) {
            System.out.print(subject + "\t");
        }
        System.out.println();

        // Print marks for each student
        for (int i = 0; i < studentNames.length; i++) {
            System.out.print(studentNames[i] + "\t");
            for (int j = 0; j < subjectNames.length; j++) {
                System.out.print(marks[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // 2. Total marks per student
    public int[] getTotalMarksPerStudent() {
        int[] totals = new int[studentNames.length];

        for (int i = 0; i < studentNames.length; i++) {
            totals[i] = ArrayUtils.sum(marks[i]);
        }

        return totals;
    }

    // 3. Average marks per student
    public double[] getAverageMarksPerStudent() {
        double[] averages = new double[studentNames.length];
        int[] totals = getTotalMarksPerStudent();

        for (int i = 0; i < studentNames.length; i++) {
            averages[i] = (double) totals[i] / subjectNames.length;
        }

        return averages;
    }

    // 4. Total marks per subject
    public int[] getTotalMarksPerSubject() {
        int[] subjectTotals = new int[subjectNames.length];

        for (int j = 0; j < subjectNames.length; j++) {
            int total = 0;
            for (int i = 0; i < studentNames.length; i++) {
                total += marks[i][j];
            }
            subjectTotals[j] = total;
        }

        return subjectTotals;
    }

    // 5. Average marks per subject
    public double[] getAverageMarksPerSubject() {
        double[] subjectAverages = new double[subjectNames.length];
        int[] subjectTotals = getTotalMarksPerSubject();

        for (int j = 0; j < subjectNames.length; j++) {
            subjectAverages[j] = (double) subjectTotals[j] / studentNames.length;
        }

        return subjectAverages;
    }

    // 6. Find topper (highest total marks)
    public String getOverallTopperName() {
        int[] totals = getTotalMarksPerStudent();
        int maxIndex = 0;

        for (int i = 1; i < totals.length; i++) {
            if (totals[i] > totals[maxIndex]) {
                maxIndex = i;
            }
        }

        return studentNames[maxIndex];
    }

    // 7. Find subject-wise topper
    public String[] getSubjectToppers() {
        String[] toppers = new String[subjectNames.length];

        for (int j = 0; j < subjectNames.length; j++) {
            int maxMark = marks[0][j];
            int maxIndex = 0;

            for (int i = 1; i < studentNames.length; i++) {
                if (marks[i][j] > maxMark) {
                    maxMark = marks[i][j];
                    maxIndex = i;
                }
            }

            toppers[j] = studentNames[maxIndex];
        }

        return toppers;
    }

    // 8. Grade distribution per student
    public char[] getGradesPerStudent() {
        char[] grades = new char[studentNames.length];
        double[] averages = getAverageMarksPerStudent();

        for (int i = 0; i < studentNames.length; i++) {
            double avg = averages[i];

            if (avg >= 90) {
                grades[i] = 'A';
            } else if (avg >= 80) {
                grades[i] = 'B';
            } else if (avg >= 70) {
                grades[i] = 'C';
            } else if (avg >= 60) {
                grades[i] = 'D';
            } else {
                grades[i] = 'F';
            }
        }

        return grades;
    }

    // 9. Get students who failed in at least one subject
    public String[] getStudentsFailedInAnySubject(int passMark) {
        // First, count how many students failed in any subject
        int failCount = 0;
        boolean[] failed = new boolean[studentNames.length];

        for (int i = 0; i < studentNames.length; i++) {
            for (int j = 0; j < subjectNames.length; j++) {
                if (marks[i][j] < passMark) {
                    failed[i] = true;
                    failCount++;
                    break; // No need to check other subjects for this student
                }
            }
        }

        // Create array of exact size needed
        String[] failedStudents = new String[failCount];
        int index = 0;

        for (int i = 0; i < studentNames.length; i++) {
            if (failed[i]) {
                failedStudents[index++] = studentNames[i];
            }
        }

        return failedStudents;
    }

    // 10. Get class average
    public double getClassAverage() {
        int totalMarks = 0;
        int totalSubjects = 0;

        for (int i = 0; i < studentNames.length; i++) {
            for (int j = 0; j < subjectNames.length; j++) {
                totalMarks += marks[i][j];
                totalSubjects++;
            }
        }

        return (double) totalMarks / totalSubjects;
    }

    // 11. Find a student's marks by name
    public int[] getMarksForStudent(String name) {
        int studentIndex = -1;

        // Find student index
        for (int i = 0; i < studentNames.length; i++) {
            if (studentNames[i].equals(name)) {
                studentIndex = i;
                break;
            }
        }

        // Return null if student not found
        if (studentIndex == -1) {
            return null;
        }

        // Return copy of the marks array for this student
        return ArrayUtils.copy(marks[studentIndex]);
    }

    // 12. Find subject index by name (helper)
    public int getSubjectIndex(String subjectName) {
        for (int i = 0; i < subjectNames.length; i++) {
            if (subjectNames[i].equals(subjectName)) {
                return i;
            }
        }
        return -1;
    }

    // 13. Get pass percentage for a subject
    public double getPassPercentageForSubject(String subjectName, int passMark) {
        int subjectIndex = getSubjectIndex(subjectName);
        if (subjectIndex == -1)
            return 0.0;

        int passCount = 0;
        for (int i = 0; i < studentNames.length; i++) {
            if (marks[i][subjectIndex] >= passMark) {
                passCount++;
            }
        }

        return ((double) passCount / studentNames.length) * 100;
    }
}