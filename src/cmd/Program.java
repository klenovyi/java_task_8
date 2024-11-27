package cmd;

import logic.Solution;
import logic.Utils;

import java.io.IOException;

public class Program {
    public static void printHelp() {
        System.out.println(
                "Использование: java l6.task8_sample.cmd.Program INPUT OUTPUT\n" +
                        "Увеличивает каждый элемент целочисленного массива из файла INPUT на 1. Результат записывается в файл OUTPUT.\n"
        );
    }

    // Метод для проверки расширения файла
    private static boolean isValidTxtFile(String fileName) {
        return fileName != null && fileName.endsWith(".txt");
    }

    // Метод для разбора параметров командной строки
    public static InputArgs parseCmdArgs(String[] args) throws CmdArgsParseError {
        String inputFile = null;
        String outputFile = null;

        if (args.length == 2) {
            inputFile = args[0];
            outputFile = args[1];
            for (int i = 0; i < args.length; i++) {
                if (args[i].contains("--input-file")) {
                    inputFile = args[0].split("=")[1];
                }
                if (args[i].contains("--output-file")) {
                    outputFile = args[1].split("=")[1];
                }
            }
        } else if (args.length == 4) {
            // Обработка именованных параметров
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-i")) {
                    if (i + 1 < args.length) {
                        inputFile = args[i + 1];
                        i++;  // Пропускаем значение
                    } else {
                        throw new CmdArgsParseError("Отсутствует входной файл.");
                    }
                } else if (args[i].equals("-o")) {
                    if (i + 1 < args.length) {
                        outputFile = args[i + 1];
                        i++;  // Пропускаем значение
                    } else {
                        throw new CmdArgsParseError("Отсутствует выходной файл.");
                    }
                }
            }
        } else {
            throw new CmdArgsParseError("Неверное количество аргументов.");
        }

        // Проверяем, что оба файла были переданы
        if (inputFile == null || outputFile == null) {
            throw new CmdArgsParseError("Ожидаются аргументы: -i INPUT_FILE -o OUTPUT_FILE.");
        }

        // Проверяем, что файлы имеют расширение .txt
        if (!isValidTxtFile(inputFile) || !isValidTxtFile(outputFile)) {
            throw new CmdArgsParseError("Файлы должны иметь расширение .txt.");
        }

        return new InputArgs(inputFile, outputFile);
    }

    public static void main(String[] args) {
        InputArgs inputArgs = null;
        System.out.println("HELLO WORLD");
        try {
            inputArgs = parseCmdArgs(args);
        } catch (CmdArgsParseError e) {
            System.out.println("CmdArgsParseError");
            printHelp();
            System.err.println("Ошибка разбора аргументов коммандной строки");
            System.exit(1);
        }

        int[][] sourceMatrix = new int[0][];
        try {
            sourceMatrix = Utils.readIntMatrixFromFile(inputArgs.inputFile);
        } catch (IOException e) {
            System.out.println("IOException read");
            System.err.printf("Ошибка при чтении исходного файла %s", e.toString());
            System.exit(2);
        }
        boolean[][] resultMatrix = Solution.findSaddlePoints(sourceMatrix);
        try {
            Utils.writeIntMatrixToFile(inputArgs.outputFile, resultMatrix);
        } catch (IOException e) {
            System.out.println("IOException write");
            System.err.printf("Ошибка при записи массива в файл %s", e.toString());
            System.exit(3);
        }

    }

}


