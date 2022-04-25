//package i18n;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Menu
{
    JFrame frame;
    JPanel panel1;
    JPanel panel2;
    JButton start;
    JButton stats;
    JButton exit;
    Locale locale;
    ResourceBundle bundle;

    public Menu(Locale loc)
    {

        locale = loc;
        bundle = ResourceBundle.getBundle("i18n.MessageListBundle", locale);

        frame = new JFrame("Sudoku");
        frame.setAlwaysOnTop(false);
        frame.setResizable(true);
        panel1 = new JPanel();

        JButton greek = new JButton();
        greek.add(new JLabel(new ImageIcon("resources/greek.png")));
        greek.setBackground(Color.WHITE);
        greek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Locale l = new Locale("gr", "GR");
                if(l != locale)
                {
                    Menu m = new Menu(l);
                    frame.dispose();
                }
            }
        });

        JButton eng = new JButton();
        eng.add(new JLabel(new ImageIcon("resources/uk.png")));
        eng.setBackground(Color.WHITE);
        eng.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Locale l = new Locale("en", "US");
                if(l != locale)
                {
                    Menu m = new Menu(l);
                    frame.dispose();
                }

            }
        });

        JPanel lang = new JPanel();
        lang.add(greek);
        lang.add(eng);
        frame.add(lang, BorderLayout.PAGE_START);

        frame.add(new JLabel(new ImageIcon("resources/greek.png")));
        frame.add(new JLabel(new ImageIcon("resources/uk.png")));

        JLabel title = new JLabel("SUDOKU");
        JLabel sub = new JLabel("AND MORE...");
        title.setFont(new Font("Roboto", Font.BOLD, 110));
        sub.setFont(new Font("Roboto", Font.BOLD, 40));

        panel2 = new JPanel();
        panel1.setSize(200, 200);
        panel2.setSize(200, 200);

        start = new JButton(bundle.getString("start"));
        start.setFont(new Font("Roboto", Font.BOLD, 25));
        //   start.setBackground(Color.green);
        start.setVisible(true);

        stats = new JButton(bundle.getString("stats"));
        stats.setFont(new Font("Roboto", Font.BOLD, 25));
        stats.setSize(400,100);
        stats.setVisible(true);

        exit = new JButton(bundle.getString("exit"));
        exit.setFont(new Font("Roboto", Font.BOLD, 25));
        exit.setSize(400,100);
        exit.setVisible(true);

        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JFrame type = new JFrame(bundle.getString("choose"));
                type.setAlwaysOnTop(true);
                JPanel pan = new JPanel();
                pan.setLayout(new GridLayout(3,1));
                pan.setSize(400, 400);

                type.setSize(400, 400);
                type.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                JButton classic = new JButton(bundle.getString("classic"));
                classic.setSize(50, 50);

                JButton kill = new JButton("Killer Sudoku");
                kill.setSize(50, 50);

                JButton dui = new JButton("Duidoku");
                dui.setSize(50, 50);

                classic.setVisible(true);
                kill.setVisible(true);
                dui.setVisible(true);

                pan.add(classic);
                pan.add(kill);
                pan.add(dui);

                type.add(pan);
                type.setLocationRelativeTo(null);
                type.setVisible(true);


                classic.setFont(new Font("Roboto", Font.BOLD, 30));
                classic.setBackground(Color.cyan.brighter().brighter().brighter().brighter().brighter());
                classic.addActionListener(new ActionListener() { //same is for the killer sudoku
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        type.getContentPane().removeAll();
                        type.repaint();
                        JPanel puz = new JPanel();

                        String name = JOptionPane.showInputDialog(type, bundle.getString("user"), "SudokuMaster123");
                        type.setAlwaysOnTop(false);
                        if (name == null)
                        {
                            name = "Anonymous";
                        }

                        String complete = null;
                        if(!name.equals("Anonymous"))
                        {
                            PlayerReaderWriter load = new PlayerReaderWriter();
                            ArrayList<Player> players = load.Read("scores.txt");
                            for (Player p: players)
                            {
                                if(name.equals(p.getName()))
                                {
                                    complete = p.getClassic();
                                    break;
                                }
                            }
                        }


                            int display = JOptionPane.showConfirmDialog(frame, bundle.getString("display"), "Display" ,JOptionPane.YES_NO_OPTION);
                        if(display != -1)
                        {
                            type.setTitle(name + ", " + bundle.getString("puzzle"));
                            puz.setLayout(new GridLayout(2,5));

                            Puzzle[] puzzles = new Puzzle[10];
                            for(int i = 0; i < 10; i++)
                            {
                                puzzles[i] = new Puzzle("Puzzle"+i, display, name, i, locale);
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
                                        type.dispose();
                                        frame.dispose();
                                    }
                                });
                            }
                            Puzzle random = new Puzzle(bundle.getString("rand"), display, name, ThreadLocalRandom.current().nextInt(0, 9), locale);
                            random.setBackground(Color.LIGHT_GRAY);
                            random.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent actionEvent) {
                                    type.dispose();
                                    frame.dispose();
                                }
                            });
                            puz.add(random);

                            type.add(puz);
                            type.setSize(800, 400);
                            type.setLocationRelativeTo(null);
                        }
                        else
                        {
                            type.dispose();
                        }

                    }
                });

                kill.setFont(new Font("Roboto", Font.BOLD, 30));
                kill.setBackground(Color.green.brighter().brighter().brighter().brighter().brighter().brighter());
                kill.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {

                        type.getContentPane().removeAll();
                        type.repaint();
                        JPanel puz = new JPanel();

                        String name = JOptionPane.showInputDialog(type, bundle.getString("user"), "SudokuMaster123");
                        if (name == null)
                        {
                            name = "Anonymous";
                        }
                            type.setTitle(name + ", " + bundle.getString("puzzle"));
                            puz.setLayout(new GridLayout(2,5));

                            String complete = null;
                            if(name != "Anonymous")
                            {
                                PlayerReaderWriter load = new PlayerReaderWriter();
                                ArrayList<Player> players = load.Read("scores.txt");
                                for (Player p: players)
                                {
                                    if(name.equals(p.getName()))
                                    {
                                        complete = p.getKiller();
                                        break;
                                    }
                                }
                            }


                            Killer_Puzzle[] puzzles = new Killer_Puzzle[10];
                            for(int i = 0; i < 10; i++)
                            {
                                puzzles[i] = new Killer_Puzzle("Puzzle"+i, name, i, locale);
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
                                    type.dispose();
                                    frame.dispose();
                                }
                            });
                            }
                            Killer_Puzzle random = new Killer_Puzzle(bundle.getString("rand"), name, ThreadLocalRandom.current().nextInt(0, 9), locale);
                            random.setBackground(Color.lightGray);
                            random.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent actionEvent) {
                                    type.dispose();
                                    frame.dispose();
                                }
                            });

                            puz.add(random);

                            type.add(puz);
                            type.setSize(800, 400);
                            type.setLocationRelativeTo(null);

                        }

                });

                dui.setFont(new Font("Roboto", Font.BOLD, 30));
                dui.setBackground(Color.pink);
                dui.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent)
                        {
                            type.getContentPane().removeAll();
                            type.repaint();
                            JPanel puz = new JPanel();

                            String name = JOptionPane.showInputDialog(type, bundle.getString("user"), "SudokuMaster123");
                            type.setAlwaysOnTop(false);
                            if (name == null)
                            {
                                name = "Anonymous";
                            }
                            int display = JOptionPane.showConfirmDialog(frame, bundle.getString("display"), "Display" ,JOptionPane.YES_NO_OPTION);

                            type.dispose();
                            if(display != -1)
                            {
                                DuidokuGUI gui = new DuidokuGUI(name, display, locale);
                                frame.dispose();
                            }

                        }

                });

            }
        });

        stats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                frame.setSize(410, 400);
                frame.setLocationRelativeTo(null);
                JButton back = new JButton(bundle.getString("back"));
                back.setFont(new Font("Roboto", Font.BOLD, 20));
                back.setBackground(Color.WHITE);
                JButton all = new JButton(bundle.getString("viewall"));
                all.setFont(new Font("Roboto", Font.BOLD, 20));
                all.setBackground(Color.MAGENTA.brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter());
                JButton p = new JButton(bundle.getString("search"));
                p.setFont(new Font("Roboto", Font.BOLD, 20));
                p.setBackground(Color.pink);

                JPanel pan = new JPanel();
                pan.setSize(400, 400);
                pan.setLayout(new GridLayout(3,1, 5, 5));

                all.setSize(300, 200);
                p.setSize(300, 200);
                pan.add(all);
                pan.add(p);
                pan.add(back);

                p.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        String name = JOptionPane.showInputDialog(frame, bundle.getString("searchmsg"), null);
                        if(name != null)
                        {
                            ArrayList<Player> players = new ArrayList<>();
                            PlayerReaderWriter read = new PlayerReaderWriter();
                            players = read.Read("scores.txt");
                            boolean found = false;

                            for(Player p : players)
                            {
                                if(name.equals(p.getName()))
                                {
                                    found = true;
                                    printStats(p);
                                    break;
                                }
                            }
                            if(!found)
                            {
                                JFrame player = new JFrame(name);
                                JPanel panel = new JPanel();
                                JLabel notFound = new JLabel(bundle.getString("nop"));
                                panel.add(notFound, BorderLayout.CENTER);
                                player.add(panel);
                                player.setSize(300, 100);
                                player.setLocationRelativeTo(null);
                                player.setVisible(true);
                            }

                        }
                    }

                });

                all.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        PlayerReaderWriter read = new PlayerReaderWriter();
                        ArrayList<Player> players = new ArrayList<>();
                        players = read.Read("scores.txt");

                        frame.getContentPane().removeAll();
                        frame.repaint();



                        JPanel statPane = new JPanel();
                        statPane.setLayout(new BoxLayout(statPane, BoxLayout.PAGE_AXIS));

                        frame.setLayout(new BorderLayout(2,2));
                        frame.add(statPane,BorderLayout.PAGE_START);
                        if(players != null)
                        {
                            Stats[] s = new Stats[players.size()];

                            for (int i = 0; i < players.size(); i++) {
                                s[i] = new Stats(players.get(i));
                                s[i].setAlignmentX(Component.CENTER_ALIGNMENT);
                                s[i].setBackground(Color.WHITE);
                                s[i].setFont(new Font("Roboto", Font.BOLD, 15));
                                statPane.add(s[i]);
                            }
                            JButton back_menu= new JButton("BACK");
                            back_menu.setFont(new Font("Roboto", Font.BOLD, 20));
                            back_menu.setBackground(Color.WHITE);
                            frame.add(back_menu,BorderLayout.PAGE_END);
                            back_menu.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent actionEvent) {
                                    frame.dispose();
                                    Menu m = new Menu(locale);
                                }
                            });
                        }
                        else {

                            JLabel error = new JLabel(bundle.getString("norep"));
                            statPane.add(error);
                        }


                        frame.setSize(300, 500);
                        frame.setLocationRelativeTo(null);
                    }
                });

                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame.dispose();
                        Menu m = new Menu(locale);
                    }
                });

                frame.getContentPane().removeAll();
                frame.repaint();
                frame.add(pan);

            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                int choice = JOptionPane.showConfirmDialog(frame, bundle.getString("exitmsg"), bundle.getString("exit") ,JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION)
                {
                    frame.dispose();
                }
            }
        });

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
        panel2.setVisible(true);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        sub.setAlignmentX(Component.LEFT_ALIGNMENT);

        start.setAlignmentX(Component.CENTER_ALIGNMENT);

        stats.setAlignmentX(Component.CENTER_ALIGNMENT);
        stats.setAlignmentY(Component.CENTER_ALIGNMENT);

        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentY(Component.CENTER_ALIGNMENT);


        panel1.add(title);
        panel1.add(sub);
        panel2.add(start);
        panel2.add(stats);
        panel2.add(exit);

        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setResizeWeight(0.35);
        sp.setEnabled(false);
        sp.setDividerSize(0);
        sp.add(panel1);
        sp.add(panel2);

        JPanel panel3 = new JPanel();
        JSplitPane sp2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp2.setResizeWeight(0.5);
        sp2.setDividerSize(0);
        sp2.setEnabled(false);

        JLabel creators = new JLabel("By Despoina Giatagana and Loukas Stogiolaris");
        panel3.add(creators);
        creators.setAlignmentX(Component.LEFT_ALIGNMENT);
        sp2.add(sp);
        sp2.add(panel3);
        panel3.setVisible(true);

        //   panel1.add(panel2, BorderLayout.CENTER);

        frame.add(sp2);
        //    frame.add(panel2, BorderLayout.CENTER);

        frame.setVisible(true);

    }

    private void printStats(Player p)
    {
        JFrame frame = new JFrame(p.getName());
        frame.setSize(200,200);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        JLabel[] info = new JLabel[4];
        for (int i = 0; i < 4; i++)
        {
            info[i] = new JLabel();
        }

        int sum = p.getVictories() + p.getDefeats();
        info[0].setText(bundle.getString("name") + " "+ p.getName());
        info[1].setText(bundle.getString("vic") + " " + p.getVictories() + " ( " + p.getVictories()/(double) sum * 100 + "% )");
        info[2].setText(bundle.getString("def") + " " + p.getDefeats() + " ( " + p.getDefeats()/(double) sum * 100 + "% )");

        for(int i = 0; i < 4; i++)
        {
            panel.add(info[i]);
        }
        frame.add(panel);
        frame.setVisible(true);
    }

    public class Stats extends JButton
    {
        public Stats(Player p)
        {
            this.setText(p.getName());
            this.setSize(200, 20);
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    printStats(p);
                }
            });
        }
    }

}