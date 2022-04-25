import java.util.ArrayList;
import java.util.HashSet;
import java.io.*;
/**

 @author Despoina Giatagana
 @author Loukas Stogiolaris
 @version 10/1/2020
 */
public  class Sudoku implements SudokuInterface{

    protected Cell[][] table;
    protected int full;
    protected HashSet<Character> [] sets;
    protected int dimensions;
    public int letters_or_numbers;//0 for letters,1 for numbers
    private char dimension_num;
    private char dimension_letter;
    private ArrayList<Character> letters;

    /**

     @param dimensions
     @param letters_or_numbers
     */
    public Sudoku(int dimensions,int letters_or_numbers)
    {
        this.dimensions=dimensions;
        this.letters_or_numbers=letters_or_numbers;
        table = new Cell[dimensions][dimensions];
        sets = new HashSet[dimensions];

        dimension_num=(char) ('0' + dimensions);
        letters = new ArrayList<>();
        for (char j='A';j<='Y';j++){
            letters.add(j);
        }

        dimension_letter=letters.get(dimensions-1);
        for (int i = 0; i < dimensions; i++)
        {
            sets[i] = new HashSet<Character>();
            for (int j = 0; j < dimensions; j++)
            {
                table[i][j] = new Cell(9,letters_or_numbers);
            }
        }
        full = 0;
    }
    /**

     @param cells
     @param dimensions
     @param letter_or_numbers
     */
    public Sudoku(Cell[][] cells,int dimensions, int letter_or_numbers)
    {
        this(dimensions, letter_or_numbers);
        for (int i = 0; i < dimensions; i++)
        {
            for(int j = 0; j < dimensions; j++)
            {
                if(cells[i][j].getValue() != ' ')
                {
                    table[i][j] = cells[i][j];
                    table[i][j].setBlocked_cell(true);
                    sets[Grid3x3Index(i, j)].add(cells[i][j].getValue());
                }
            }
        }

    }
    /**

     @param x
     @param y
     @param c
     @return
     */
    public boolean Check(int x, int y, char c)
    {
        boolean check;
        if(x>=1 && x<=dimensions && y>=1 && y<=dimensions) {
            if (letters_or_numbers == 1 && c >= '1' && c <= dimension_num) {
                return check_rules(x, y, c);
            } else if (letters_or_numbers == 0 && c >= 'A' && c <= dimension_letter) {
                return check_rules(x, y, c);
            } else
                return false;
        }
        return false;

    }
    /**

     @param x
     @param y
     @param c
     @return
     */
    public boolean check_rules(int x,int y,char c){

        boolean row = true;
        boolean col = true;
        for (int j = 0; j < dimensions; j++) {
            if (table[x - 1][j].getValue() == c) {
                row = false;
                break;
            }
        }
        if (row) {
            for (int i = 0; i < dimensions; i++) {
                if (table[i][y - 1].getValue() == c) {
                    col = false;
                    break;
                }
            }
            if (col) {
                if(Grid3x3Index(x-1, y -1)!=-1) {
                    if (!sets[Grid3x3Index(x - 1, y - 1)].contains(c)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        return false;

    }
    /**

     @param x
     @param y
     @param c
     */
    public void setValue(int x, int y, char c){
        if(Check(x,y,c) && full<=dimensions*dimensions && table[x-1][y-1].isLocked()==false ) {
            table[x - 1][y - 1].setValue(c);
            if(Grid3x3Index(x-1,y-1)!=-1) {
                sets[Grid3x3Index(x - 1, y - 1)].add(c);
            }
            table[x - 1][y - 1].setLocked(true);
            full++;
        }
    }

    /**

     @param x
     @param y
     */
    public void Erase(int x, int y)
    {
        sets[Grid3x3Index(x - 1, y - 1)].remove(table[x - 1][y - 1].getValue());
        table[x - 1][y - 1].deleteValue();
        full--;
    }
    /**

     @param x
     @param y
     @return
     */
    public int Grid3x3Index (int x, int y){
        if(x>=0 && x<=dimensions-1 && y>=0 && y<=dimensions-1) {
            int index;
            index = (int) Math.sqrt(dimensions) * (x / (int) Math.sqrt(dimensions)) + y / (int) Math.sqrt(dimensions);
            return index;
        }else{
            return -1;
        }
    }
    /**

     @param x
     @param y
     @return
     */
    public HashSet<Character> Helper(int x, int y){

        HashSet<Character> existing = new HashSet<>();
        HashSet<Character> allvalues = new HashSet<>();
        if(letters_or_numbers==1) {
            for (int i = 0; i < dimensions; i++) {
                allvalues.add((char) ('0' + (i + 1)));
                existing.add(table[x - 1][i].getValue());
                existing.add(table[i][y - 1].getValue());
            }
            for (char keys : sets[Grid3x3Index(x - 1, y - 1)]) {
                existing.add(keys);
            }
            allvalues.removeAll(existing);
        }else {
            for (int i = 0; i < dimensions; i++) {

                allvalues.add(letters.get(i));
                existing.add(table[x - 1][i].getValue());
                existing.add(table[i][y - 1].getValue());
            }
            for (char keys : sets[Grid3x3Index(x - 1, y - 1)]) {
                existing.add(keys);
            }
            allvalues.removeAll(existing);
        }

        //values=allvalues.toArray();
        return allvalues;
    }

    public void Show()
    {
        for(int i = 0; i < dimensions; i++)
        {
            for (int j = 0; j < dimensions; j++)
            {
                System.out.print(table[i][j].getValue() + " ");
                if (j == dimensions-1) {
                    System.out.println();
                }
            }
        }
    }


    /**
     @return
     */
    public int getFull() {
        return full;
    }
    /**

     @return
     */
    public boolean isFull(){
        return full==Math.pow(dimensions,2);
    }
    /**
     @return
     */
    public HashSet<Character>[] getSets() {
        return sets;
    }
    /**
     @return
     */
    public Cell[][] getTable() {
        return table;
    }
    /**
     @return
     */
    public ArrayList<Character> getLetters()
    {
        return letters;
    }
}