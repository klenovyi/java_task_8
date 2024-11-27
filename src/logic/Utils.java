package logic;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    public static int[][] readIntMatrixFromFile(String fileName) throws IOException {
        int[][] a;
        List<int[]> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                rows.add(Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray());
                line = reader.readLine();
            }
            a = new int[rows.size()][];
            rows.toArray(a);
        }
        return a;
    }

    public static void writeIntMatrixToFile(String fileName, boolean[][] matrix) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Проходим по каждой строке матрицы
            for (int i = 0; i < matrix.length; i++) {
                String[] strRow = new String[matrix[i].length];
                for (int j = 0; j < matrix[i].length; j++) {
                    strRow[j] = String.valueOf(matrix[i][j]);
                }
                writer.write(String.join(" ", strRow));
                writer.write('\n');  // добавляем перевод строки
            }

        }
    }
}
