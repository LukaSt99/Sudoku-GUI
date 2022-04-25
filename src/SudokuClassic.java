/**

 @author Despoina Giatagana
 @author Loukas Stogiolaris
 @version 10/1/2020
 */
public  class SudokuClassic extends Sudoku {

    /**

     @param letter_numbers
     */
    public SudokuClassic(int letter_numbers){
        super(9,letter_numbers);
    }
    /**

     @param cells
     @param dimensions
     @param letter_numbers
     */
    public SudokuClassic(Cell[][] cells,int dimensions, int letter_numbers){
            super(cells,dimensions,letter_numbers);
    }


}
