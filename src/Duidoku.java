import java.util.Random;
import java.util.Scanner;
/**


 @author Loukas Stogiolaris
 @version 10/1/2020
 */
public class Duidoku  extends Sudoku{

    private int x; //the value that the player will put in each time and its coordinates
    private int y;
    private char c;
    private int current_x_pc;
    private int current_y_pc;
    private char current_c_pc;
    /**

     @param letters_numbers
     */
    public Duidoku(int letters_numbers){
        super(4,letters_numbers);
    }

  /*
  χωρις gui
    public void run(){

        String player="player";
        String pc="pc";
        String turn="player";


        while(full<dimensions*dimensions){

             if(turn.equals("player")) {
                 System.out.println("ΠΑΙΖΕΙ Ο ΠΑΙΚΤΗΣ");
                 player();
                 check_duidoku();
                 turn="pc";
                 if(full==dimensions*dimensions){
                     System.out.println("ΚΕΡΔΙΣΕ Ο ΠΑΙΚΤΗΣ");
                     return;
                 }

             }else {
                 System.out.println("ΠΑΙΖΕΙ TO PC");
                 pc();
                 check_duidoku();
                 turn="player";
                 if(full==dimensions*dimensions){
                     System.out.println("ΚΕΡΔΙΣΕ ΤΟ PC");
                     return;
                 }
             }
        }
    }


    private void player(){
        System.out.print("x:");
        Scanner sca = new Scanner(System.in);
        int s = sca.nextInt();

        System.out.print("y:");
        Scanner sca2 = new Scanner(System.in);
        int s2 = sca2.nextInt();

        System.out.print("c:");
        Scanner sca3 = new Scanner(System.in);
        char s3 = sca3.next(".").charAt(0);

        setValue(s, s2, s3);
        table[s - 1][s2 - 1].setLocked(true);
    }
*/

    /**

     @param x
     @param y
     @param c
     */
    public void player(int x, int y, char c){
        setValue(x, y, c);
    }
    /**

     */
    public void pc(){
        int x,y,random,flag;
        char c;
        flag = 0;
        if(letters_or_numbers==1) {
            do {
                x = new Random().nextInt(dimensions);
                y = new Random().nextInt(dimensions);
                random = new Random().nextInt(dimensions);
                c = (char) ('0' + random + 1);
                if (Check(x + 1, y + 1, c) == true && table[x][y].isLocked() == false) {
                    setValue(x + 1, y + 1, c);
                    table[x][y].setLocked(true);
                    current_x_pc=x;
                    current_y_pc=y;
                    current_c_pc=c;
                    flag = 1;
                }

            } while (flag == 0);
        }else{
            do {
                x = new Random().nextInt(dimensions);
                y = new Random().nextInt(dimensions);
                random = new Random().nextInt(dimensions);
                Character[] letters={'A','B','C','D'};
                c =letters[random];
                if (Check(x + 1, y + 1, c) == true && table[x][y].isLocked() == false) {
                    setValue(x + 1, y + 1, c);
                    table[x][y].setLocked(true);
                    current_x_pc=x;
                    current_y_pc=y;
                    current_c_pc=c;
                    flag = 1;
                }

            } while (flag == 0);
        }
    }
    /**


     */
    public void check_duidoku(){

        for(int i=1;i<=dimensions;i++){
            for (int j=1;j<=dimensions;j++){
                if(Helper(i,j).isEmpty()==true && table[i-1][j-1].isLocked()==false){
                    table[i-1][j-1].setLocked(true);
                    table[i-1][j-1].setBlocked_cell(true);
                    full++;
                }
            }
        }
    }

    /**
     @param
     */
   public void setX(int x) {
        this.x = x;
    }
    /**
     @param
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     @param
     */
    public void setC(char c) {
        this.c = c;
    }
    /**
     @return
     */
    public int getCurrent_x_pc() {
        return current_x_pc;
    }
    /**
     @return
     */
    public int getCurrent_y_pc() {
        return current_y_pc;
    }
    /**
     @return
     */
    public char getCurrent_c_pc() { return current_c_pc; }
}
