package com.conorsheppard.util;

import java.util.Arrays;
import java.util.stream.Stream;

public class Helpers {

//    public static <T> void printRoverPosition(T plateau) {
//        var plateauArray = new char[plateau.ySize() + 1][plateau.xSize() + 1];
//        plateauArray[this.position[1]][this.position[0]] = this.direction.toString().charAt(0);
//        printPlateauRecursive(plateauArray, 0);
//        System.out.println("-------------");
//    }

    public static void printPlateauRecursive(char[][] plateau, int count) {
        if (plateau.length == 0) {
            System.out.println("[]");
            return;
        }
        count++;
        if (count != plateau.length) printPlateauRecursive(plateau, count);
        System.out.println(Arrays.toString(plateau[count - 1]));
    }

    public static <T> boolean isInBounds(T[][] array, int row, int col) {
        return array != null &&
                row >= 0 && row < array.length &&
                array[row] != null &&
                col >= 0 && col < array[row].length;
    }

    public static boolean isInBounds(int[][] array, int row, int col) {
        return array != null &&
                row >= 0 && row < array.length &&
                col >= 0 && col < array[row].length;
    }

    public static boolean isInBounds(char[][] array, int row, int col) {
        return array != null &&
                row >= 0 && row < array.length &&
                col >= 0 && col < array[row].length;
    }

    public static boolean isInBounds(double[][] array, int row, int col) {
        return array != null &&
                row >= 0 && row < array.length &&
                col >= 0 && col < array[row].length;
    }

    public static <T> void printMatrix(T[][] matrix) {
        Stream.of(matrix)
                .map(Arrays::toString)
                .forEach(System.out::println);
    }

    public static void printMatrix(Object matrix) {
        Class<?> c = matrix.getClass();

        switch (c.getName()) {
            case "[[Ljava.lang.Object;" -> printMatrix((Object[][]) matrix);
            case "[[I" -> { // int[][]
                for (int[] row : (int[][]) matrix) System.out.println(Arrays.toString(row));
            }
            case "[[C" -> { // char[][]
                for (char[] row : (char[][]) matrix) System.out.println(Arrays.toString(row));
            }
            case "[[D" -> { // double[][]
                for (double[] row : (double[][]) matrix) System.out.println(Arrays.toString(row));
            }
            case "[[Z" -> { // boolean[][]
                for (boolean[] row : (boolean[][]) matrix) System.out.println(Arrays.toString(row));
            }
            case "[[J" -> { // long[][]
                for (long[] row : (long[][]) matrix) System.out.println(Arrays.toString(row));
            }
            case "[[F" -> { // float[][]
                for (float[] row : (float[][]) matrix) System.out.println(Arrays.toString(row));
            }
            case "[[S" -> { // short[][]
                for (short[] row : (short[][]) matrix) System.out.println(Arrays.toString(row));
            }
            case "[[B" -> { // byte[][]
                for (byte[] row : (byte[][]) matrix) System.out.println(Arrays.toString(row));
            }
            default -> throw new IllegalArgumentException("Unsupported matrix type: " + c);
        }
    }
}
