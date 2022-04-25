import javax.annotation.processing.Filer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Puzzle extends JButton {

    private int size;
    private Cell[][] cells;
    private String name;
    private Scanner scanner;
    private char[] letters;

    public Puzzle(String text, int display, String player, int index, Locale loc)
    {
        size = 9;
        this.setText(text);
        name = player;

        if(display == 0)
        {
            letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        }

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    scanner = new Scanner(new File("resources/Puzzle" + index + ".txt"));

                    int size = 9;
                    cells = new Cell[size][size];
                    int i = 0;

                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        System.out.println(line);
                        String[] lines = line.split(" ");
                        System.out.println(lines[0]);

                        cells[i] = new Cell[size];
                        for (int j = 0; j < size; j++) {
                            if (line.charAt(j) != ' ') {
                                if (display == 1)
                                {
                                    cells[i][j] = new Cell(line.charAt(j), true, size, display);
                                }
                                else
                                {
                                    char num = line.charAt(j);
                                    cells[i][j] = new Cell(letters[Character.getNumericValue(num) -1], true, size, display);
                                }

                            } else {
                                cells[i][j] = new Cell(size, display);
                            }

                        }
                        i++;
                    }

                    SudokuGUI gui = new SudokuGUI(name, cells, display, index, loc);

                } catch (FileNotFoundException e) {
                    return;
                }

            }
        });

    }

}