package logic;

public class Solution {
    /*
    Увеличивает каждый элемент массива на 1 в исходном массиве. Не создаёт новый массив.
     */
    public static void incrementEach(int[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                matrix[r][c]++;
            }
        }
    }
}
