import java.sql.SQLOutput;
import java.util.ArrayList;

/**

 @author Despoina Giatagana
 @author Loukas Stogiolaris
 @version 10/1/2020
 */
public class Cell
{

    private char value;
    private boolean filled;
    private boolean locked;
    private int dimension;
    private char dimension_num;
    private char dimension_letter;
    protected ArrayList<Character> letters;
    private int letter_numbers;
    private boolean blocked_cell;

    /**

     @param dimension
     @param letter_numbers
     */
    public Cell(int dimension,int letter_numbers){


        this.letter_numbers=letter_numbers;
        this.dimension=dimension;
        this.value =' ';
        this.filled = false;
        locked = false;
        blocked_cell=false;

        dimension_num=(char) ('0' + dimension);
        letters = new ArrayList<>();
        for (char j='A';j<='Y';j++){
            letters.add(j);
        }
        dimension_letter=letters.get(dimension-1);

    }

    /**

     @param lock
     @param letter_numbers
     @param c
     @param dimension
     */
    public Cell(char c, boolean lock,int dimension,int letter_numbers){

        this.letter_numbers=letter_numbers;
        dimension_num=(char) ('0' + dimension);
        letters = new ArrayList<>();
        for (char j='A';j<='Y';j++){
            letters.add(j);
        }
        dimension_letter=letters.get(dimension-1);
        this.dimension=dimension;
        if(letter_numbers==1 && c >='1' && c <= dimension_num )
        {
            this.value = c;
            this.filled = false;
            locked = lock;
        }else if(letter_numbers==0 && c>='A' && c<= dimension_letter)
        {
            this.value = c;
            this.filled = false;
            locked = lock;
        }else{
            this.value =' ';
            this.filled = false;
            locked = false;
        }
    }

    /**

     @param value
     */
    public void setValue(char value) {
        if( letter_numbers==1 && value >='1' && value <= dimension_num && locked==false )
        {
            this.value = value;
            filled=true;
        }else if( letter_numbers==0 && value>='A' && value <= dimension_letter && locked==false)
        {
            this.value = value;
            filled=true;
        }
    }
    /**

     */
    public void deleteValue(){
        if(filled){
            value=' ';
            filled=false;
        }
        if(isBlocked_cell()){
            blocked_cell=false;
        }
        if(isLocked()){
            locked=false;
        }

    }
    /**

     @return
     */
    public char getValue(){
        return value;
    }
    /**

     @return
     */
    public boolean isFilled(){
        return filled;
    }
    /**
     @return
     */
    public boolean isLocked() {
        return locked;
    }
    /**
     @param locked
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    /**

     @return
     */
    public ArrayList<Character> getLetters() {
        return letters;
    }
    /**
     @return
     */
    public boolean isBlocked_cell() {
        return blocked_cell;
    }
    /**

     @param blocked_cell
     */
    public void setBlocked_cell(boolean blocked_cell) {
        this.blocked_cell = blocked_cell;
    }
}