import java.util.ArrayList;
import java.util.HashSet;

public class Killer_Sudoku extends Sudoku {

    public HashSet<Sum> sums;
    public Killer_Sudoku(int dimension) {
        super(dimension, 1);
        sums = new HashSet<>();
    }

    public Killer_Sudoku(int dimension, HashSet<Sum> sums) {
        super(dimension, 1);
        this.sums = sums;
    }
    public boolean Killer_Check(int x, int y, char c) {

        Sum checked_sum;
        Coordinates coo = new Coordinates(x - 1, y - 1, dimensions);
        for (Sum sum : sums) {
            for (Coordinates coordinates : sum.getCoordinates()) {
                if (coo.getX() == coordinates.getX() - 1 && coo.getY() == coordinates.getY() - 1) {
                    checked_sum = sum;
                    int _sum = 0; //_sum is the sum which is calculated by the cells of these particular coordinates
                    for (Coordinates coords : checked_sum.getCoordinates()) {
                        if (table[coords.getX() - 1][coords.getY() - 1].getValue() != ' ') {
                            _sum += Character.getNumericValue(table[coords.getX() - 1][coords.getY() - 1].getValue());
                        }
                    }
                    if (_sum + Character.getNumericValue(c) <= checked_sum.getSum()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void player(int x, int y, char c) {
        if (Killer_Check(x, y, c)) {
            super.setValue(x, y, c);
        }
    }
    public HashSet<Character> killer_helper(int x, int y) {

        HashSet<Character> helper = new HashSet<>();
        HashSet<Character> sudokuhelper_set = this.Helper(x,y);
        HashSet<Integer> combinations_set = new HashSet<>();
        HashSet<Character> combinations_set_char = new HashSet<>();


        int num_coord=0;
        int cell_sum=0;
        int sum_ofSum=0;

       Sum requested_sum;
        Coordinates coo = new Coordinates(x - 1, y - 1, dimensions);
        for (Sum sum : sums) {
            for (Coordinates coordinates : sum.getCoordinates()) {
                if (coo.getX() == coordinates.getX() - 1 && coo.getY() == coordinates.getY() - 1) {
                  requested_sum = sum;
                  sum_ofSum=requested_sum.getSum();
                  num_coord=requested_sum.getNumbers_of_coord();

                    for (Coordinates coords : requested_sum.getCoordinates()) {

                        if (table[coords.getX() - 1][coords.getY() - 1].getValue() != ' ') {
                            cell_sum = cell_sum + Integer.parseInt(String.valueOf(table[coords.getX() - 1][coords.getY() - 1].getValue()));
                            num_coord--;
                        }
                    }
                }
            }
        }
        Combination_K_To_N combinations =new Combination_K_To_N(dimensions);
        combinations.Combinations(sum_ofSum-cell_sum,num_coord);
        combinations_set=combinations.getValues();

        for (Integer key:combinations_set) {
            combinations_set_char.add(Integer.toString(key).charAt(0));
        }


        HashSet<Character> allvalues=new HashSet<>();
        for (int i = 0; i < dimensions; i++) {
            allvalues.add((char) ('0' + (i + 1)));
        }
        sudokuhelper_set.retainAll(combinations_set_char);
        return sudokuhelper_set;
    }

}






