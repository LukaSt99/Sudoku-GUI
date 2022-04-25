import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SudokuClassicTest {

    @Test
    void check_Sudoku_numbers() {

        SudokuClassic sudoku1= new SudokuClassic(1);
        sudoku1.setValue(1,10,'6');
        sudoku1.setValue(0,3,'3');
        sudoku1.setValue(1,3,']');
        sudoku1.setValue(3,3,'4');
        sudoku1.setValue(1,2,'3');
        sudoku1.setValue(3,6,'2');
        sudoku1.setValue(4,2,'9');
        sudoku1.setValue(2,1,'4');
        int full=sudoku1.getFull();
        assertEquals(4,full);
        boolean check=sudoku1.Check(1,3,'4');
        assertEquals(false,check);
        boolean check2=sudoku1.Check(3,1,'0');
        assertEquals(false,check2);
        boolean check3=sudoku1.Check(6,3,'4');
        assertEquals(false,check3);
        HashSet<Character>[] set=sudoku1.getSets();
        HashSet<Character> set1=new HashSet<>();
        set1.add('3');
        set1.add('4');
        assertEquals(set1,set[0]);
        HashSet<Character> set2=new HashSet<>();
        set2.add('2');
        assertEquals(set2,set[1]);
        HashSet<Character> set3=new HashSet<>();
        HashSet<Character> set4=new HashSet<>();
        set4.add('9');
        assertEquals(set3,set[2]);
        assertEquals(set4,set[3]);
    }

    @Test
    void check_Sudoku_letters() {

        SudokuClassic sudoku2= new SudokuClassic(0);
        sudoku2.setValue(1,10,'A');
        sudoku2.setValue(0,3,'B');
        sudoku2.setValue(1,3,'2');
        sudoku2.setValue(3,3,'D');
        sudoku2.setValue(1,2,'C');
        sudoku2.setValue(3,6,'B');
        sudoku2.setValue(4,2,'I');
        sudoku2.setValue(2,1,'D');
        sudoku2.setValue(1,5,'B');
        int full=sudoku2.getFull();
        assertEquals(4,full);
        boolean check=sudoku2.Check(1,3,'D');
        assertEquals(false,check);
        boolean check2=sudoku2.Check(3,1,'A');
        assertEquals(true,check2);
        boolean check3=sudoku2.Check(6,3,'D');
        assertEquals(false,check3);
        HashSet<Character>[] set=sudoku2.getSets();
        HashSet<Character> set1=new HashSet<>();
        set1.add('C');
        set1.add('D');
        assertEquals(set1,set[0]);
        HashSet<Character> set2=new HashSet<>();
        set2.add('B');
        assertEquals(set2,set[1]);
        HashSet<Character> set3=new HashSet<>();
        HashSet<Character> set4=new HashSet<>();
        set4.add('I');
        assertEquals(set3,set[2]);
        assertEquals(set4,set[3]);

    }

    @Test
    void helper() {

        SudokuClassic sudoku3=new SudokuClassic(1);
        sudoku3.setValue(1,2,'3');
        sudoku3.setValue(1,3,'0');
        sudoku3.setValue(3,6,'2');
        HashSet<Character> helper= new HashSet<>();
        for(int i=1;i<=9;i++){
            helper.add((char) ('0' + i));
        }
        HashSet<Character> helper2= new HashSet<>();
        for(int i=1;i<=9;i++){
            if(i!=2 && i!=3) {
                helper2.add((char) ('0' + i));
            }
        }
        assertEquals(helper,sudoku3.Helper(9,9));
        assertEquals(helper2,sudoku3.Helper(3,2));
        Sudoku sudoku4=new Sudoku(9,0);
        sudoku4.setValue(3,3,'E');
        sudoku4.setValue(1,2,'C');
        sudoku4.setValue(3,6,'B');
        sudoku4.setValue(8,5,'A');
        HashSet<Character> helper3= new HashSet<>();
        for(char j='A'; j<='I';j++){
            helper3.add(j);
        }
        HashSet<Character> helper4= new HashSet<>();
        for(char j='A'; j<='I';j++){
            if(j!= 'A' && j!= 'B'  && j!= 'C')
                helper4.add(j);
        }
        assertEquals(helper3,sudoku4.Helper(9,9));
        assertEquals(helper4,sudoku4.Helper(1,5));

    }


}