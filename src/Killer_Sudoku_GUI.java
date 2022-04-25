import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Killer_Sudoku_GUI {

    //private
    private Killer_Sudoku logic;
    private JFrame main;
    private JPanel board;
    private JPanel side;
    private JLabel back;
    private JButton[][] tiles;
    private int dimensions;
    private JLabel label;
    private JLabel label1;
    private String player_name;
    private  ResourceBundle bundle;
    private int index;

    public Killer_Sudoku_GUI(String player,HashSet<Sum> sums, int dimensions, int index, Locale loc)
    {
        bundle = ResourceBundle.getBundle("i18n.MessageListBundle", loc);
        this.dimensions=dimensions;
        this.index = index;
        player_name=player;
        logic = new Killer_Sudoku(dimensions, sums);
        main = new JFrame("Killer Sudoku");

        label=new JLabel(bundle.getString("gl"));
        label1=new JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground (Color.lightGray.darker());

        board = new JPanel();
        main.setForeground(Color.BLACK);
        board.setSize(800, 800);
        board.setLayout(new GridLayout(10,10));

        side = new JPanel();
        side.setSize(30, 200);
        side.setLayout(new GridLayout(3, 1));
        side.setVisible(true);
        JPanel pn=new JPanel();
        pn.add(label);
        side.add(label1);
        side.add(pn);

        tiles = new Killer_Sudoku_GUI.Tile[dimensions][dimensions]; //See code for the tiles
        for (int i = 0; i < dimensions; i++)
        {
            tiles[i] = new Killer_Sudoku_GUI.Tile[dimensions];
            for (int j = 0; j < dimensions; j++)
            {
                tiles[i][j] = new Killer_Sudoku_GUI.Tile(' ', false, i + 1 , j + 1, dimensions); //1 for numbers
                tiles[i][j].setSize(15,15);
                tiles[i][j].setFont(new Font("Arial", Font.BOLD, 30));
                tiles[i][j].setVisible(true);
                tiles[i][j].setBorder(BorderFactory.createLineBorder(Color.gray.darker(),1));
                tiles[i][j].setBackground(Color.BLACK.darker().darker().darker().darker());
                board.add(tiles[i][j]);
            }
        }
        for (Sum s: sums)
        {

            int red = ThreadLocalRandom.current().nextInt(102,255);
            int blue = ThreadLocalRandom.current().nextInt(153,255);
            int green = ThreadLocalRandom.current().nextInt(102,255);
            Color color = new Color(red,blue,green);

            int p=0;
            for (Coordinates c : s.getCoordinates())
            {
                if(p==0){
                    JLabel sum=new JLabel(Integer.toString(s.getSum()));
                    sum.setFont(new Font("Arial", Font.BOLD, 11));
                    sum.setBackground(Color.BLACK.darker().darker().darker());
                    tiles[c.getX()-1][c.getY()-1].add(sum);
                }
                p++;
                tiles[c.getX()-1][c.getY()-1].setBackground(color);
                tiles[c.getX()-1][c.getY()-1].setOpaque(true);
            }
        }

        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp.setResizeWeight(0.6);
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
        JButton select = new JButton(bundle.getString("new"));
        select.setPreferredSize(new Dimension(100, 50));
        select.setBackground(Color.WHITE);
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //να ανοιγει το frame για τις επιλογες puzzles του Κιλλερ
            }
        });

        JButton clear = new JButton(bundle.getString("clear"));
        clear.setPreferredSize(new Dimension(100, 50));
        clear.setBackground(Color.WHITE);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(int i = 0; i < dimensions; i++)
                {
                    for (int j = 0; j < dimensions; j++)
                    {
                        if(!logic.getTable()[i][j].isBlocked_cell())
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

                Killer_Puzzle[] puzzles = new Killer_Puzzle[10];
                for(int i = 0; i < 10; i++)
                {
                    puzzles[i] = new Killer_Puzzle("Puzzle"+i, player_name, i, loc);
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
                Killer_Puzzle random = new Killer_Puzzle(bundle.getString("rand"), player_name, ThreadLocalRandom.current().nextInt(0, 9), loc);
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
        main.setSize(1200, 700);
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setVisible(true);
        main.setResizable(false);

    }

    public class Tile extends JButton {

        Coordinates c;
        JButton[] choices;
        char value;
        boolean locked;
        JFrame help;

        public Tile(char value, boolean locked, int x, int y, int dimensions) {
            this.value = value;
            this.setText(String.valueOf(value));

            c = new Coordinates(x, y, dimensions);
            choices = new JButton[dimensions];
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
                        help.setLocationRelativeTo(Killer_Sudoku_GUI.Tile.this);
                        choose(dimensions);
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

        public void choose(int dimensions)
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

                choices[i] = new Killer_Sudoku_GUI.Tile.Choice(Integer.toString(i + 1));
                choices[i].setFont(new Font("Arial", Font.BOLD, 20));
                choices[i].setForeground(Color.BLACK);
                if (logic.table[c.getX() - 1][c.getY() - 1].getValue() == ' ') {
                    if (!logic.killer_helper(c.getX(), c.getY()).contains(Integer.toString(i + 1).charAt(0))) {
                        choices[i].setEnabled(false);
                        choices[i].setBackground(Color.lightGray);
                    } else {
                        choices[i].setBackground(Color.pink);
                    }
                    erase.setEnabled(false);
                }else{
                    choices[i].setEnabled(false);
                    choices[i].setBackground(Color.lightGray);
                }
                pane.add(choices[i]);
            }

            erase.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    main.setEnabled(true);
                    if(logic.table[c.getX()-1][c.getY()-1].getValue() != ' ') {
                        logic.Erase(c.getX(), c.getY());
                        value = ' ';
                        Killer_Sudoku_GUI.Tile.this.setText(Character.toString(value));
                    }
                    Killer_Sudoku_GUI.Tile.this.help.dispose();
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
                val = text.charAt(0);
                this.setSize(40, 40);
                this.setVisible(true);
                this.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.out.println(logic.getFull());

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
                                        String complete = p.getKiller();
                                        if(!complete.contains(Integer.toString(index)))
                                        {
                                            p.setKiller(complete+index);
                                        }
                                        found = true;
                                        break;
                                    }
                                }
                                if(!found)
                                {
                                    players.add(new Player(player_name, 1, 0, null, Integer.toString(index)));
                                }
                                save.Write(players, "scores.txt");
                            }

                        }
                        main.setEnabled(true);
                        logic.player(c.getX(), c.getY(), val);
                        Killer_Sudoku_GUI.Tile.this.setText(Character.toString(logic.getTable()[c.getX() - 1][c.getY() - 1].getValue())); //Goes to the logic's table and matches the numbers to the UI display because putting in the value may not have vbeen successful
                        Killer_Sudoku_GUI.Tile.this.help.dispose();
                    }

                });
            }
        }
    }
}