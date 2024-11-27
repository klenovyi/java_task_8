package cmd;

/**
 * Класс исключения для обработки ошибок при разборе командных аргументов.
 */
public class CmdArgsParseError extends Exception {

    // Конструктор без сообщения
    public CmdArgsParseError() {
        super(); // Вызываем конструктор родительского класса
    }

    // Конструктор с сообщением об ошибке
    public CmdArgsParseError(String message) {
        super(message); // Передаем сообщение в конструктор родительского класса
        // System.err.println(message);
    }

}

