import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Locale;

public class Killer_Puzzle extends JButton
{
    public Killer_Puzzle(String text, String name, int index, Locale loc)
    {
        setText(text);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Load_Killer_Puzzles load = new Load_Killer_Puzzles(index);
                HashSet<Sum> sums = load.getSums();
                Killer_Sudoku_GUI gui = new Killer_Sudoku_GUI(name, sums, 9, index, loc);
            }
        });


    }
}