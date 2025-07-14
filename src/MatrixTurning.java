import java.util.Random;
import java.util.Scanner;

public class MatrixTurning {

    public static final int SIZE = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix = new int[SIZE][SIZE];

        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = random.nextInt(256);
            }
        }

        printMatrix(matrix);
        while (true) {
            printMenu();
            String userChoice = scanner.nextLine();
            if (userChoice.equals("end")) {
                break;
            } else {
                int operation = Integer.parseInt(userChoice);
                switch (operation) {
                    case (1):
                        int[][] transposedMatrix = transpositionMatrix(matrix);
                        int[][] rotated90degreesMatrix = rotationMatrix90Degrees(transposedMatrix);
                        printMatrix(rotated90degreesMatrix);
                        break;
                    case (2):
                        int[][] rotated180DegreesMatrix = rotationMatrix180degrees(matrix);
                        printMatrix(rotated180DegreesMatrix);
                        break;
                    case (3):
                        int[][] transposedMatrixFor270 = transpositionMatrix(matrix);
                        int[][] rotated270DegreesMatrix = rotationMatrix270Degrees(transposedMatrixFor270);
                        printMatrix(rotated270DegreesMatrix);
                        break;
                }
            }

        }
    }

    public static int[][] transpositionMatrix(int[][] matrix) {

        for (int i = 0; i < SIZE; i++) {
            for (int j = i; j < SIZE; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        return matrix;
    }

    public static int[][] rotationMatrix90Degrees(int[][] matrix) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][SIZE - 1 - j];
                matrix[i][SIZE - 1 - j] = temp;
            }
        }
        return matrix;
    }

    public static int[][] rotationMatrix270Degrees(int[][] matrix) {
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE / 2; i++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[SIZE - 1 - i][j];
                matrix[SIZE - 1 - i][j] = temp;
            }
        }
        return matrix;
    }

    public static int[][] rotationMatrix180degrees(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n; j++) {
                int oppositeI = n - 1 - i;
                int oppositeJ = n - 1 - j;
                if (i < oppositeI || (i == oppositeI && j < oppositeJ)) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[oppositeI][oppositeJ];
                    matrix[oppositeI][oppositeJ] = temp;
                }
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.format("%4d", cell);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public static int[][] copyMatrix(int[][] originalMatrix) {
        int n = originalMatrix.length;
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = originalMatrix[i][j];
            }
        }
        return copy;
    }

    public static void printMenu() {
        String basicPhrase = "%d. Повернуть матрицу на %d углов \n";

        System.out.printf(basicPhrase, 1, 90);
        System.out.printf(basicPhrase, 2, 180);
        System.out.printf(basicPhrase, 3, 270);
        System.out.println("-= Для выхода из программы введите 'end' =-");

    }
}
