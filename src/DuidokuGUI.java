import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class DuidokuGUI {
    Duidoku logic;
    JFrame main;
    JPanel board;
    JPanel side;
    JButton[][] tiles;
    int display;
    JLabel label;
    JLabel label1;
    String player_name;
    private ResourceBundle bundle;


    public DuidokuGUI(String player, int display, Locale loc) {

        bundle = ResourceBundle.getBundle("i18n.MessageListBundle", loc);
        this.display=display;
        player_name=player;
        logic = new Duidoku(display);
        main = new JFrame("Duidoku 4x4");
        board = new JPanel();

        label=new JLabel(bundle.getString("gl"));
        label1=new JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground (Color.lightGray.darker());

        main.setForeground(Color.BLACK);
        board.setSize(600, 600);
        board.setLayout(new GridLayout(5, 5));

        side = new JPanel();
        side.setSize(30, 200);
        side.setLayout(new GridLayout(3, 1));
        side.setVisible(true);
        JPanel pn=new JPanel();
        pn.add(label);
        side.add(label1);
        side.add(pn);

        tiles = new Tile[4][4]; //See code for the tiles
        for (int i = 0; i < 4; i++) {
            tiles[i] = new Tile[4];
            for (int j = 0; j < 4; j++) {

                tiles[i][j] = new Tile(' ', false, i + 1, j + 1, display);
                if((i==j)||(i==0 && j==1)||(i==1 && j==0)||(i==2 && j==3)||(i==3 && j==2)){
                    tiles[i][j].setBorder(BorderFactory.createLineBorder(Color.gray.darker(),2));
                }
                tiles[i][j].setSize(15, 15);
                tiles[i][j].setVisible(true);
                board.add(tiles[i][j]);
            }
        }

        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp.setResizeWeight(0.55);
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
                if(!logic.isFull()) {
                    int b = JOptionPane.showConfirmDialog(main, bundle.getString("backmsg"), bundle.getString("main"), JOptionPane.YES_NO_OPTION);
                    if (b == 0) {
                        main.dispose();
                        Menu main = new Menu(loc);
                    }
                }else{
                    main.dispose();
                    Menu main = new Menu(loc);
                }
            }
        });


        JButton select = new JButton(bundle.getString("new"));
        select.setBackground(Color.WHITE);
        select.setPreferredSize(new Dimension(100, 50));
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!logic.isFull())
                {
                    int n = JOptionPane.showConfirmDialog(main, bundle.getString("newgame"), bundle.getString("new"), JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
                        main.dispose();
                        DuidokuGUI dui = new DuidokuGUI(player, display, loc);
                    }
                } else {
                    main.dispose();
                    DuidokuGUI dui = new DuidokuGUI(player, display, loc);
                }

            }
        });

        JPanel header = new JPanel();
        header.setSize(400, 50);
        header.add(back);
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
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setVisible(true);
        main.setResizable(true);
    }

    public class Tile extends JButton {

        Coordinates c;
        JButton[] choices;
        char value;
        boolean locked;
        JFrame help;

        public Tile(char value, boolean locked, int x, int y, int display) {

            this.value = value;
            this.setText(String.valueOf(value));
            c = new Coordinates(x, y, logic.dimensions);
            choices = new JButton[4];
            this.locked = locked;
            setFont(new Font("Arial", Font.BOLD, 30));
            setBackground(Color.WHITE.brighter());
            if (!locked) {

                this.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        main.setEnabled(false);
                        help = new JFrame(bundle.getString("choose"));
                        help.setResizable(false);
                        help.setSize(250, 250);
                        help.setLocationRelativeTo(DuidokuGUI.Tile.this);
                        choose(display);
                        help.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                main.setEnabled(true);
                            }
                        });
                    }
                });
            }
        }

        public void choose(int display) {
            JPanel pane = new JPanel();
            pane.setVisible(true);
            setFont(new Font("Arial", Font.BOLD, 30));
            pane.setLayout(new GridLayout(2, 2, 2, 2));
            for (int i = 0; i < 4; i++) {
                if (display == 1) //If i chose to display numbers
                {
                    choices[i] = new DuidokuGUI.Tile.Choice(Integer.toString(i + 1));
                    choices[i].setFont(new Font("Arial", Font.BOLD, 30));
                    if (!logic.Helper(c.getX(), c.getY()).contains((char) ('0' + (i + 1)))) {
                        choices[i].setEnabled(false);
                        choices[i].setBackground(Color.lightGray);
                    }else{
                        choices[i].setBackground(Color.WHITE);
                    }

                } else {
                    choices[i] = new DuidokuGUI.Tile.Choice(Character.toString(logic.getLetters().get(i)));
                    choices[i].setFont(new Font("Arial", Font.BOLD, 30));
                    if (!logic.Helper(c.getX(), c.getY()).contains(logic.getLetters().get(i))) {
                        choices[i].setEnabled(false);
                        choices[i].setBackground(Color.lightGray);
                    }else{
                        choices[i].setBackground(Color.WHITE.brighter());
                    }
                }
                pane.add(choices[i]);
            }
            help.add(pane);
            help.setVisible(true);

        }

        public class Choice extends JButton {

            char val;
            public Choice(String text) {
                this.setText(text);
                val = text.charAt(0);
                this.setSize(30, 30);
                this.setVisible(true);
                this.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if(!logic.isFull()) {
                            main.setEnabled(true);
                            logic.player(c.getX(), c.getY(), val);
                            setBackground(Color.LIGHT_GRAY);
                            DuidokuGUI.Tile.this.setText(Character.toString(logic.getTable()[c.getX() - 1][c.getY() - 1].getValue())); //Goes to the logic's table and matches the numbers to the UI display because putting in the value may not have vbeen successful
                            DuidokuGUI.Tile.this.help.dispose();
                            DuidokuGUI.Tile.this.setEnabled(false);
                            check();
                            if (logic.isFull()) {
                                label.setText(bundle.getString("win"));
                                //εδω κανει το save για τα score(οταν κερδιζει)
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
                                            found = true;
                                            break;
                                        }
                                    }
                                    if(!found)
                                    {
                                        players.add(new Player(player_name, 1, 0, null, null));
                                    }
                                    save.Write(players, "scores.txt");
                                }

                            }
                            if(!logic.isFull()) {
                                logic.pc();
                                tiles[logic.getCurrent_x_pc()][logic.getCurrent_y_pc()].setText(Character.toString(logic.getCurrent_c_pc()));
                                tiles[logic.getCurrent_x_pc()][logic.getCurrent_y_pc()].setBackground(Color.pink);
                                tiles[logic.getCurrent_x_pc()][logic.getCurrent_y_pc()].setEnabled(false);
                                check();
                                if (logic.isFull()) {
                                    label.setText(bundle.getString("lose"));
                                    // εδω κανει το save για τα score(οταν χανει)

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
                                                p.setDefeats(p.getDefeats() + 1);
                                                found = true;
                                                break;
                                            }
                                        }
                                        if(!found)
                                        {
                                            players.add(new Player(player_name, 0, 1, null, null));
                                        }
                                        save.Write(players, "scores.txt");
                                    }

                                }
                            }
                        }
                    }
                });
            }
        }

        public void check() {
            logic.check_duidoku();
            for (int i = 0; i < logic.dimensions; i++) {
                for (int j = 0; j < logic.dimensions; j++) {
                    if (logic.getTable()[i][j].isBlocked_cell()) {
                        tiles[i][j].setEnabled(false);
                        tiles[i][j].setBackground(Color.black.brighter());
                    }
                }
            }
        }
    }

}


