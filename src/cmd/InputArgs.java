package cmd;

/*
Класс для хранения пути входного и выходного файла
 */
public class InputArgs {
    public String inputFile;
    public String outputFile;

    public InputArgs(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public String getInputFile() {
        return inputFile;
    }

    public String getOutputFile(){
        return outputFile;
    }
}
