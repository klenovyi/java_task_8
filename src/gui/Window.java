package gui;

import logic.Solution;
import logic.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {
    private JPanel rootPanel;
    private JPanel workspace;
    private JTable table;
    private JButton button;
    private JButton rowDelButton;
    private JButton rowAddButton;
    private JButton colAddButton;
    private JButton colDelButton;

    public Window() {
        setContentPane(rootPanel);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // callback (метод который будет вызван при нажатии на кнопку Преобразовать)
        button.addActionListener(e -> {
            int[][] matrix = getTable();
            boolean [][] resultMatrix = Solution.findSaddlePoints(matrix);
            setTable(resultMatrix);
        });

        // callback (метод который будет вызван на кнопку + сверху таблицы (добавление столбца))
        colAddButton.addActionListener(e -> {
            table.addColumn(new TableColumn());
        });

        // callback (метод который будет вызван на кнопку - сверху таблицы (удаление последнего столбца))
        colDelButton.addActionListener(e -> {
            table.removeColumn(table.getColumnModel().getColumn(table.getColumnCount() - 1));
        });

        // callback (метод который будет вызван на кнопку + слева таблицы (добавление строки))
        rowAddButton.addActionListener(e -> {
            Object[] newRow = new Integer[table.getColumnCount()];
            for (int i = 0; i < table.getColumnCount(); i++)
                newRow[i] = 0;
            ((DefaultTableModel) table.getModel()).addRow(newRow);
        });

        // callback (метод который будет вызван на кнопку - слева таблицы (удаление последней строки))
        rowDelButton.addActionListener(e -> {
            ((DefaultTableModel) table.getModel()).removeRow(table.getRowCount() - 1);
        });
    }

    public static void main(String[] args) {
        Window w = new Window();
        w.setSize(600, 500);
        w.setLocation(300, 200);
    }

    private void createUIComponents() {
        JMenuBar menuBar = new JMenuBar();
        // Добавление в главное меню выпадающих пунктов меню
        menuBar.add(createFileMenu());
        // Подключаем меню к интерфейсу приложения
        setJMenuBar(menuBar);

        // Cоздание таблицы
        table = new JTable();
        setTable(new int[][] {
                {0, 1, 2, 4},
                {5, 1, 7, 7},
                {5, 17, 20, 4},
                {0, 0, 0, 0},
        });

    }

    private void saveMatrixInFile(){
        String filePath = chooseFile();
        if (filePath != null) {
            boolean[][] matrix = getBooleanTable();
            try {
                Utils.writeIntMatrixToFile(filePath, matrix);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void openMatrixFromFile(){
        String filePath = chooseFile();
        if (filePath != null) {
            try {
                int[][] matrix = Utils.readIntMatrixFromFile(filePath);
                setTable(matrix);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private JMenu createFileMenu() {
        // Создание выпадающего меню
        JMenu tableMenu = new JMenu("Таблица");

        // Пункт меню "Сохранить"
        JMenuItem saveItem = new JMenuItem("Сохранить в файл");
        // Добавление в меню пункта save
        tableMenu.add(saveItem);
        // callback (метод который будет вызван при нажатии кнопки save)
        saveItem.addActionListener(arg0 -> {
            saveMatrixInFile();
        });

        // Пункт меню "Открыть"
        JMenuItem openItem = new JMenuItem("Открыть из файла");
        tableMenu.add(openItem);
        openItem.addActionListener(arg0 -> {
            openMatrixFromFile();
        });

        // Добавление разделителя
        tableMenu.addSeparator();

        // Пункт меню "Заполнить нулями"
        JMenuItem fill0Item = new JMenuItem("Заполнить нулями");
        tableMenu.add(fill0Item);
        fill0Item.addActionListener(arg0 -> {
            // TODO
        });

        return tableMenu;
    }

    /*
    Метод показывает окно выбор файла и возвращает путь до выбранного файла. Если окно было закрыто возвращает null.
     */
    public String chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    /*
    Возвращает массив данных в таблице
     */
    public int[][] getTable() {
        int[][] matrix = new int[table.getRowCount()][table.getColumnCount()];
        for (int r = 0; r < table.getRowCount(); r++) {
            for (int c = 0; c < table.getColumnCount(); c++) {
                matrix[r][c] = Integer.parseInt(table.getValueAt(r, c).toString());
            }
        }
        return matrix;
    }

    public boolean[][] getBooleanTable() {
        boolean [][] matrix = new boolean[table.getRowCount()][table.getColumnCount()];
        for (int r = 0; r < table.getRowCount(); r++) {
            for (int c = 0; c < table.getColumnCount(); c++) {
                matrix[r][c] = Boolean.parseBoolean(table.getValueAt(r, c).toString());
            }
        }
        return matrix;
    }


    /*
    Устанавливает данные из массива в таблицу
     */
    public void setTable(int[][] matrix) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(matrix.length);
        dtm.setColumnCount(matrix[0].length);
        table.setModel(dtm);
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                table.setValueAt(matrix[r][c], r, c);
            }
        }
    }

    public void setTable(boolean[][] matrix) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(matrix.length);
        dtm.setColumnCount(matrix[0].length);
        table.setModel(dtm);
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                table.setValueAt(matrix[r][c], r, c);
            }
        }
    }

}
