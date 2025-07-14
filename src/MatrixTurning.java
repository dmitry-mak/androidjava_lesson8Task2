import java.util.Random;
import java.util.Scanner;

public class MatrixTurning {

    public static final int SIZE = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] originalMatrix = new int[SIZE][SIZE];

        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                originalMatrix[i][j] = random.nextInt(256);
            }
        }

        int[][] currentMatrix = copyMatrix(originalMatrix);

        printMatrix(currentMatrix);

        while (true) {
            printMenu();
            String userChoice = scanner.nextLine();
            if (userChoice.equals("end")) {
                break;
            } else {
                int operation = Integer.parseInt(userChoice);
                switch (operation) {
                    case (1):
                        currentMatrix = rotationMatrix90Degrees(currentMatrix);
                        printMatrix(currentMatrix);
                        break;
                    case (2):
                        currentMatrix = rotationMatrix180degrees(currentMatrix);
                        printMatrix(currentMatrix);
                        break;
                    case (3):
                        currentMatrix = rotationMatrix270Degrees(currentMatrix);
                        printMatrix(currentMatrix);
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
        int[][] rotatedMatrix = copyMatrix(matrix);
        rotatedMatrix = transpositionMatrix(rotatedMatrix);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE / 2; j++) {
                int temp = rotatedMatrix[i][j];
                rotatedMatrix[i][j] = rotatedMatrix[i][SIZE - 1 - j];
                rotatedMatrix[i][SIZE - 1 - j] = temp;
            }
        }
        return rotatedMatrix;
    }

    public static int[][] rotationMatrix270Degrees(int[][] matrix) {
        int[][] rotatedMatrix = copyMatrix(matrix);
        rotatedMatrix = transpositionMatrix(rotatedMatrix);
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE / 2; i++) {
                int temp = rotatedMatrix[i][j];
                rotatedMatrix[i][j] = rotatedMatrix[SIZE - 1 - i][j];
                rotatedMatrix[SIZE - 1 - i][j] = temp;
            }
        }
        return rotatedMatrix;
    }

    public static int[][] rotationMatrix180degrees(int[][] matrix) {
        int n = matrix.length;
        int[][] rotatedMatrix = copyMatrix(matrix);
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n; j++) {
                int oppositeI = n - 1 - i;
                int oppositeJ = n - 1 - j;
                if (i < oppositeI || (i == oppositeI && j < oppositeJ)) {
                    int temp = rotatedMatrix[i][j];
                    rotatedMatrix[i][j] = rotatedMatrix[oppositeI][oppositeJ];
                    rotatedMatrix[oppositeI][oppositeJ] = temp;
                }
            }
        }
        return rotatedMatrix;
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
