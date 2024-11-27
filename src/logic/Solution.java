package logic;

public class Solution {

    /*
    Функция для проверки, является ли элемент в позиции (r, c) седловой точкой
    */
    private static boolean isSaddlePoint(int[][] matrix, int r, int c) {
        int element = matrix[r][c];
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Проверяем, что элемент наименьший в строке
        boolean isMinInRow = true;
        for (int i = 0; i < cols; i++) {
            if (matrix[r][i] < element) {
                isMinInRow = false;
                break;
            }
        }

        // Проверяем, что элемент наибольший в столбце
        boolean isMaxInColumn = true;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][c] > element) {
                isMaxInColumn = false;
                break;
            }
        }

        // Проверяем, что элемент наибольший в строке
        boolean isMaxInRow = true;
        for (int i = 0; i < cols; i++) {
            if (matrix[r][i] > element) {
                isMaxInRow = false;
                break;
            }
        }

        // Проверяем, что элемент наименьший в столбце
        boolean isMinInColumn = true;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][c] < element) {
                isMinInColumn = false;
                break;
            }
        }

        // Если элемент является седловой точкой
        return (isMinInRow && isMaxInColumn) || (isMaxInRow && isMinInColumn);
    }

    /*
    Функция для поиска седловых точек в матрице.
    Седловой точкой является элемент, который:
    - наименьший в своей строке и наибольший в своём столбце,
    - или наибольший в своей строке и наименьший в своём столбце.
     */
    public static boolean[][] findSaddlePoints(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] saddlePoints = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                    saddlePoints[r][c] = isSaddlePoint(matrix, r, c);
            }
        }
        return saddlePoints;
    }
}
