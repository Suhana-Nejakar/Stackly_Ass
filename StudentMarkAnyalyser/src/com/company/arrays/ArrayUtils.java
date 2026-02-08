package com.company.arrays;

public class ArrayUtils {

    // 1. Sum of elements in an int[]
    public static int sum(int[] arr) {
        int total = 0;
        for (int num : arr) {
            total += num;
        }
        return total;
    }

    // 2. Maximum element in an int[]
    public static int max(int[] arr) {
        if (arr.length == 0)
            return Integer.MIN_VALUE;

        int maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        return maxValue;
    }

    // 3. Minimum element in an int[]
    public static int min(int[] arr) {
        if (arr.length == 0)
            return Integer.MAX_VALUE;

        int minValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            }
        }
        return minValue;
    }

    // 4. Average of elements in an int[]
    public static double average(int[] arr) {
        if (arr.length == 0)
            return 0.0;

        int total = sum(arr);
        return (double) total / arr.length;
    }

    // 5. Index of an element in an int[] (linear search)
    public static int indexOf(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    // 6. Copy an array
    public static int[] copy(int[] arr) {
        int[] copyArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
    }

    // 7. Reverse an int[] in-place
    public static void reverse(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            // Swap elements
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }

    // 8. Print a 2D int array as a matrix
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}