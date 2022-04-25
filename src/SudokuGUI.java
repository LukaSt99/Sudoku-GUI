

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class SudokuGUI
{
    JFrame main;
    JPanel board;
    JPanel side;
    JLabel back;
    JButton[][] tiles;
    Sudoku logic;
    String player_name;
    private JLabel label;
    private JLabel label1;
    private ResourceBundle bundle;
    private int index;

    public SudokuGUI(String player, Cell[][] cells, int dimensions, int display, int index, Locale loc)
    {
        bundle = ResourceBundle.getBundle("i18n.MessageListBundle", loc);
        logic = new SudokuClassic(cells, dimensions, display);
        player_name = player;
        this.index = index;

        main = new JFrame(bundle.getString("classic"));
        board = new JPanel();
        board.setSize(800, 800);
        board.setLayout(new GridLayout(10,10));

        label = new JLabel(bundle.getString("gl"));
        label1 = new JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground (Color.lightGray.darker());

        side = new JPanel();
        side.setSize(30, 200);
        side.setLayout(new GridLayout(3, 1));
        side.setVisible(true);
        JPanel pn=new JPanel();
        pn.add(label);
        side.add(label1);
        side.add(pn);

        tiles = new Tile[dimensions][dimensions]; //See code for the tiles
        for (int i = 0; i < dimensions; i++)
        {
            tiles[i] = new Tile[dimensions];
            for (int j = 0; j < dimensions; j++)
            {
                tiles[i][j] = new Tile(cells[i][j].getValue(), cells[i][j].isLocked(), i + 1, j + 1, dimensions, display);
                tiles[i][j].setSize(15, 15);
                tiles[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                tiles[i][j].setForeground(Color.BLACK.darker().darker().darker().darker());
                tiles[i][j].setVisible(true);
                if (!logic.table[i][j].isBlocked_cell()) {
                    tiles[i][j].setBackground(Color.WHITE);
                }else{
                    tiles[i][j].setBackground(Color.lightGray);
                }
                board.add(tiles[i][j]);
            }
        }

        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp.setResizeWeight(0.45);
        sp.setEnabled(false);
        sp.setDividerSize(0);
        sp.add(board);
        sp.add(side);

        JButton back = new JButton(bundle.getString("back"));
        back.setPreferredSize(new Dimension(100, 50));
        back.setBackground(Color.WHITE);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                int b = JOptionPane.showConfirmDialog(main, bundle.getString("backmsg"), bundle.getString("main") ,JOptionPane.YES_NO_OPTION);
                if(b == 0)
                {
                    main.dispose();
                    Menu main = new Menu(loc);
                }
            }
        });
        JButton clear = new JButton(bundle.getString("clear"));
        clear.setPreferredSize(new Dimension(100, 50));
        clear.setBackground(Color.WHITE);

        JButton select = new JButton(bundle.getString("new"));
        select.setPreferredSize(new Dimension(100, 50));
        select.setBackground(Color.WHITE);

        JPanel header = new JPanel();
        header.setSize(400, 50);
        header.add(back);
        header.add(clear);
        header.add(select);
        header.setVisible(true);


        JSplitPane sp1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp1.setResizeWeight(0.1);
        sp1.setEnabled(false);
        sp1.setDividerSize(0);
        sp1.add(header);
        sp1.add(sp);

        main.add(sp1);
        main.setSize(800, 600);
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        main.setVisible(true);
        main.setResizable(false);

        //Buttons
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(int i = 0; i < dimensions; i++)
                {
                    for (int j = 0; j < dimensions; j++)
                    {
                        if(logic.getTable()[i][j].isBlocked_cell()==false)
                        {
                            logic.Erase(i + 1, j + 1);
                            tiles[i][j].setText(" ");
                        }

                    }
                }
                logic.Show();
            }
        });

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                main.setEnabled(false);
                JFrame choose = new JFrame(player_name + ", " + bundle.getString("puzzle"));
                JPanel puz = new JPanel();
                puz.setLayout(new GridLayout(2,5));

                String complete = null;
                if(!player_name.equals("Anonymous"))
                {
                    PlayerReaderWriter load = new PlayerReaderWriter();
                    ArrayList<Player> players = load.Read("scores.txt");
                    for (Player p: players)
                    {
                        if(player_name.equals(p.getName()))
                        {
                            complete = p.getClassic();
                            break;
                        }
                    }
                }

                Puzzle[] puzzles = new Puzzle[10];
                for(int i = 0; i < 10; i++)
                {
                    puzzles[i] = new Puzzle("Puzzle"+i, display, player_name, i, loc);
                    puzzles[i].setSize(50,50);

                    if(complete != null && complete.contains(Integer.toString(i)))
                    {
                        puzzles[i].setBackground(Color.green.brighter().brighter().brighter().brighter());
                    }
                    else
                    {
                        puzzles[i].setBackground(Color.WHITE);
                    }

                    puz.add(puzzles[i]);
                    puzzles[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            choose.dispose();
                            main.dispose();
                        }
                    });
                }
                Puzzle random = new Puzzle(bundle.getString("rand"), display, player_name, ThreadLocalRandom.current().nextInt(0, 9), loc);
                random.setBackground(Color.LIGHT_GRAY);
                random.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        choose.dispose();
                        main.dispose();
                    }
                });
                choose.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        main.setEnabled(true);
                    }
                });
                puz.add(random);

                choose.add(puz);
                choose.setSize(800, 400);
                choose.setLocationRelativeTo(null);
                choose.setVisible(true);
            }
        });

    }


    public SudokuGUI(String player, Cell[][] cells, int display, int index, Locale loc)
    {
        this(player, cells,9, display, index, loc);
    }

    public class Tile extends JButton {

        Coordinates c;
        JButton[] choices;
        char value;
        boolean locked;
        JFrame help;

        public Tile(char value, boolean locked, int x, int y, int dimensions, int display)
        {
            this.value = value;
            this.setText(String.valueOf(value));

            c = new Coordinates(x, y, dimensions);
            choices = new JButton[9];
            this.locked = locked;

            if (!locked) {
                this.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        main.setEnabled(false);
                        help = new JFrame(bundle.getString("choose"));
                        help.setResizable(false);
                        help.setSize(250, 250);
                        help.setLocationRelativeTo(Tile.this);
                        choose(display, dimensions);
                        help.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                main.setEnabled(true);
                            }
                        });
                    }

                });
            }
            else
            {
                this.setEnabled(false);
                this.setBackground(Color.LIGHT_GRAY);
                this.setForeground(Color.BLACK);
            }
        }

        public void choose(int display, int dimensions)
        {

            JPanel pane = new JPanel();
            pane.setVisible(true);
            pane.setLayout(new GridLayout(3, 3, 2, 2));

            JButton erase = new JButton(bundle.getString("clear"));
            erase.setSize(200, 100);
            erase.setFont(new Font("Arial", Font.BOLD, 20));
            erase.setForeground(Color.BLACK);
            erase.setBackground(Color.WHITE);

            for (int i = 0; i < dimensions; i++) {

                if (display == 1) {
                    choices[i] = new SudokuGUI.Tile.Choice(Integer.toString(i + 1));
                    choices[i].setFont(new Font("Arial", Font.BOLD, 20));
                    choices[i].setForeground(Color.BLACK);
                    if (logic.table[c.getX() - 1][c.getY() - 1].getValue() == ' ') {
                        if (!logic.Helper(c.getX(), c.getY()).contains(Integer.toString(i + 1).charAt(0))) {
                            choices[i].setEnabled(false);
                            choices[i].setBackground(Color.lightGray);
                        } else {
                            choices[i].setBackground(Color.pink);
                        }
                        erase.setEnabled(false);
                    } else {
                        choices[i].setEnabled(false);
                        choices[i].setBackground(Color.lightGray);
                    }
                    pane.add(choices[i]);
                }else{

                    choices[i] = new SudokuGUI.Tile.Choice(Character.toString(logic.getLetters().get(i)));
                    choices[i].setFont(new Font("Arial", Font.BOLD, 20));
                    choices[i].setForeground(Color.BLACK);
                    if (logic.table[c.getX() - 1][c.getY() - 1].getValue() == ' ') {

                        if (!logic.Helper(c.getX(), c.getY()).contains(logic.getLetters().get(i))) {
                            choices[i].setEnabled(false);
                            choices[i].setBackground(Color.lightGray);
                        } else {
                            choices[i].setBackground(Color.pink);
                        }
                        erase.setEnabled(false);
                    } else {
                        choices[i].setEnabled(false);
                        choices[i].setBackground(Color.lightGray);
                    }
                    pane.add(choices[i]);
                }
            }

            erase.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    main.setEnabled(true);
                    if(logic.table[c.getX()-1][c.getY()-1].getValue() != ' ') {
                        logic.Erase(c.getX(), c.getY());
                        value = ' ';
                        SudokuGUI.Tile.this.setText(Character.toString(value));
                    }
                    SudokuGUI.Tile.this.help.dispose();
                }
            });

            help.add(erase, BorderLayout.SOUTH);
            help.add(pane);
            help.setVisible(true);

        }

        public class Choice extends JButton {
            char val;

            public Choice(String text)
            {
                this.setText(text);
                val = text.charAt(0); //it works only until 9
                this.setSize(40, 40);
                this.setVisible(true);
                this.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {

                        if (logic.isFull()) {
                            label.setText(bundle.getString("win"));
                            // το save για τα score

                            if(!player_name.equals("Anonymous"))
                            {
                                PlayerReaderWriter save = new PlayerReaderWriter();
                                ArrayList<Player> players = save.Read("scores.txt");
                                if (players == null)
                                {
                                    players = new ArrayList<>();
                                }
                                boolean found = false;
                                for (Player p : players)
                                {
                                    if(p.getName().equals(player_name))
                                    {
                                        p.setVictories(p.getVictories() + 1);
                                        String complete = p.getClassic();
                                        if(!complete.contains(Integer.toString(index)))
                                        {
                                            p.setClassic(complete+index);
                                        }
                                        found = true;
                                        break;
                                    }
                                }

                                if(!found)
                                {
                                    players.add(new Player(player_name, 1, 0, Integer.toString(index), null));
                                }
                                save.Write(players, "scores.txt");
                            }

                        }
                        main.setEnabled(true);
                        logic.setValue(c.getX(), c.getY(), val);
                        Tile.this.setText(Character.toString(logic.getTable()[c.getX() - 1][c.getY() - 1].getValue())); //Goes to the logic's table and matches the numbers to the UI display because putting in the value may not have vbeen successful
                        Tile.this.value = logic.getTable()[c.getX() - 1][c.getY() - 1].getValue();
                        logic.Show();
                        Tile.this.help.dispose();
                    }

                });
            }

        }

    }


}