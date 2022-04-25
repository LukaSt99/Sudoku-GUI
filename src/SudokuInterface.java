import java.util.HashSet;
/**

 @author Despoina Giatagana
 @author Loukas Stogiolaris
 @version 10/1/2020
 */
interface SudokuInterface {
    /**

     @param x
     @param y
     @param c
     */
    void setValue(int x, int y, char c);
    /**

     @param x
     @param y
     @return
     */
    HashSet<Character> Helper(int x, int y);
    /**

     @param x
     @param y
     @return
     */
    int Grid3x3Index (int x, int y);
    /**

     @param x
     @param y
     @param c
     @return
     */
    boolean check_rules(int x,int y,char c);

    /**

     @param x
     @param y
     @param c
     @return
     */
    boolean Check(int x, int y, char c);


}
