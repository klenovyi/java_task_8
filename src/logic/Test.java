package logic;

import cmd.CmdArgsParseError;
import cmd.InputArgs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static cmd.Program.parseCmdArgs;


public class Test {

    public static void main(String[] args) throws IOException {
        testValidInputOutputFiles();
        testValidInputOutputFilesWithFullNamedArgs();;
        testValidInputOutputFilesWithNamedArgs();
        testInvalidArgumentCount();
        testInvalidFileExtension();
        testInvalidNamedArguments();
        testSolution();
    }

    static void testValidInputOutputFiles() {
        String[] args = {"input.txt", "output.txt"};
        try {
            InputArgs result = parseCmdArgs(args);
            if ("input.txt".equals(result.getInputFile()) && "output.txt".equals(result.getOutputFile())) {
                System.out.println("testValidInputOutputFiles OK");
            } else {
                System.out.println("testValidInputOutputFiles FAIL");
            }
        } catch (CmdArgsParseError e) {
            System.out.println("testValidInputOutputFiles FAIL: " + e.getMessage());
        }
    }

    static void testValidInputOutputFilesWithFullNamedArgs() {
        String[] args = {"--input-file=input.txt", "--output-file=output.txt"};
        try {
            InputArgs result = parseCmdArgs(args);
            if ("input.txt".equals(result.getInputFile()) && "output.txt".equals(result.getOutputFile())) {
                System.out.println("testValidInputOutputFiles OK");
            } else {
                System.out.println("testValidInputOutputFiles FAIL");
            }
        } catch (CmdArgsParseError e) {
            System.out.println("testValidInputOutputFiles FAIL: " + e.getMessage());
        }
    }

    static void testValidInputOutputFilesWithNamedArgs() {
        String[] args = {"-i", "input.txt", "-o", "output.txt"};
        try {
            InputArgs result = parseCmdArgs(args);
            if ("input.txt".equals(result.getInputFile()) && "output.txt".equals(result.getOutputFile())) {
                System.out.println("testValidInputOutputFilesWithNamedArgs OK");
            } else {
                System.out.println("testValidInputOutputFilesWithNamedArgs FAIL");
            }
        } catch (CmdArgsParseError e) {
            System.out.println("testValidInputOutputFilesWithNamedArgs FAIL: " + e.getMessage());
        }
    }

    static void testInvalidArgumentCount() {
        String[] args = {"input.txt"}; // Неверное количество аргументов
        try {
            parseCmdArgs(args);
            System.out.println("testInvalidArgumentCount FAIL: ожидалось исключение");
        } catch (CmdArgsParseError e) {
            if ("Неверное количество аргументов.".equals(e.getMessage())) {
                System.out.println("testInvalidArgumentCount OK");
            } else {
                System.out.println("testInvalidArgumentCount FAIL: " + e.getMessage());
            }
        }
    }

    static void testInvalidFileExtension() {
        String[] args = {"input.pdf", "output.docx"};
        try {
            parseCmdArgs(args);
            System.out.println("testInvalidFileExtension FAIL: ожидалось исключение");
        } catch (CmdArgsParseError e) {
            if ("Файлы должны иметь расширение .txt.".equals(e.getMessage())) {
                System.out.println("testInvalidFileExtension OK");
            } else {
                System.out.println("testInvalidFileExtension FAIL: " + e.getMessage());
            }
        }
    }

    static void testInvalidNamedArguments() {
        String[] args = {"-i", "input.pdf", "-o", "output.docx"};
        try {
            parseCmdArgs(args);
            System.out.println("testInvalidNamedArguments FAIL: ожидалось исключение");
        } catch (CmdArgsParseError e) {
            if ("Файлы должны иметь расширение .txt.".equals(e.getMessage())) {
                System.out.println("testInvalidNamedArguments OK");
            } else {
                System.out.println("testInvalidNamedArguments FAIL: " + e.getMessage());
            }
        }
    }

    static void testSolution() throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output02.txt"))){

            // Тест 1: Простая матрица 2x2
            int[][] in1 = {
                    {1, 2},
                    {3, 4},
            };
            boolean[][] out1 = {
                    {false, true},
                    {true, false},
            };
            boolean[][] result1 = Solution.findSaddlePoints(in1);
            if (Arrays.deepEquals(result1, out1)) {
                writer.write("testSolution case 2*2 with sedlo  OK");
            } else {
                System.out.println("testSolution case 2*2 with sedlo FAIL");
            }

            // Тест 2: Матрица с седловыми точками
            int[][] in2 = {
                    {5, 3, 4},
                    {1, 6, 2},
                    {7, 8, 0},
            };
            boolean[][] out2 = {
                    {false, false, false},
                    {false, false, false},
                    {false, false, false},
            };
            boolean[][] result2 = Solution.findSaddlePoints(in2);
            if (Arrays.deepEquals(result2, out2)) {
                writer.write("testSolution case 3*3 without sedlo OK");
            } else {
                System.out.println("testSolution case 3*3 without sedlo FAIL");
            }

            // Тест 3: Матрица 1x1 (граничный случай)
            int[][] in3 = {
                    {42},
            };
            boolean[][] out3 = {
                    {true},
            };
            boolean[][] result3 = Solution.findSaddlePoints(in3);
            if (Arrays.deepEquals(result3, out3)) {
                writer.write("testSolution case 1*1 OK");
            } else {
                System.out.println("Test 3 FAIL");
            }

            // Тест 4: Матрица 3x3 без седловых точек
            int[][] in4 = {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9},
            };
            boolean[][] out4 = {
                    {false, false, true},
                    {false, false, false},
                    {true, false, false},
            };
            boolean[][] result4 = Solution.findSaddlePoints(in4);
            if (Arrays.deepEquals(result4, out4)) {
                writer.write("testSolution case 3*3 with Sedlo OK");
            } else {
                System.out.println("testSolution case 3*3 with Sedlo FAIL");
            }

        } catch (Exception e) {
            System.out.println();
        }
    }

    // Здесь должны быть ваши методы parseCmdArgs и классы InputArgs и CmdArgsParseError
}

