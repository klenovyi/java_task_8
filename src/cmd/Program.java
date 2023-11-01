package cmd;

import logic.Solution;
import logic.Utils;

import java.io.IOException;

public class Program {
    public static void printHelp() {
        System.out.println(
                "Использование: java l6.task8_sample.cmd.Program INPUT OUTPUT\n"+
                "Увеличивает каждый элемент целочисленного массива из файла INPUT на 1. Результат записывается в файл OUTPUT.\n"
                );
    }

    public static InputArgs parseCmdArgs(String[] args) throws CmdArgsParseError {
        if (args.length != 2) {
            printHelp();
            throw new CmdArgsParseError();
        }
        return new InputArgs(args[0], args[1]);
    }

    public static void main(String[] args) {
        InputArgs inputArgs = null;
        try {
            inputArgs = parseCmdArgs(args);
        }
        catch (CmdArgsParseError e){
            printHelp();
            System.err.println("Ошибка разбора аргументов коммандной строки");
            System.exit(1);
        }

        int[][] sourceMatrix = new int[0][];
        try {
            sourceMatrix = Utils.readIntMatrixFromFile(inputArgs.inputFile);
        }
        catch (IOException e) {
            System.err.printf("Ошибка при чтении исходного файла %s", e.toString());
            System.exit(2);
        }
        Solution.incrementEach(sourceMatrix);
        try {
            Utils.writeIntMatrixToFile(inputArgs.outputFile, sourceMatrix);
        }
        catch (IOException e) {
            System.err.printf("Ошибка при записи массива в файл %s", e.toString());
            System.exit(3);
        }

    }

}
