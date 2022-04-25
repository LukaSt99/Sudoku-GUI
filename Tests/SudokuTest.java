

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {

    @Test
    void check_Sudoku_numbers() {

        Sudoku sudoku1= new Sudoku(4,1);
        sudoku1.setValue(1,5,'6');
        sudoku1.setValue(0,3,'3');
        sudoku1.setValue(1,3,']');
        sudoku1.setValue(3,3,'4');
        sudoku1.setValue(1,3,'3');
        sudoku1.setValue(3,6,'2');
        sudoku1.setValue(2,1,'4');
        int full=sudoku1.getFull();
        assertEquals(3,full);

        boolean check=sudoku1.Check(1,3,'4');
        assertEquals(false,check);
        boolean check2=sudoku1.Check(4,1,'0');
        assertEquals(false,check2);
        boolean check3=sudoku1.Check(4,4,'4');
        assertEquals(false,check3);
        HashSet<Character>[] set=sudoku1.getSets();
        HashSet<Character> set1=new HashSet<>();
        set1.add('4');
        assertEquals(set1,set[0]);
        HashSet<Character> set2=new HashSet<>();
        set2.add('3');
        assertEquals(set2,set[1]);
        HashSet<Character> set3=new HashSet<>();
        HashSet<Character> set4=new HashSet<>();
        set4.add('4');
        assertEquals(set3,set[2]);
        assertEquals(set4,set[3]);
    }

    @Test
    void check_Sudoku_letters() {

        Sudoku sudoku1= new Sudoku(4,0);
        sudoku1.setValue(1,5,'F');
        sudoku1.setValue(0,3,'C');
        sudoku1.setValue(1,3,'?');
        sudoku1.setValue(3,3,'A');
        sudoku1.setValue(1,3,'C');
        sudoku1.setValue(3,6,'B');
        sudoku1.setValue(2,1,'D');
        sudoku1.setValue(4,3,'C');
        sudoku1.setValue(2,4,'D');
        int full=sudoku1.getFull();
        assertEquals(3,full);

        boolean check=sudoku1.Check(1,4,'D');
        assertEquals(true,check);
        boolean check2=sudoku1.Check(4,1,'5');
        assertEquals(false,check2);
        boolean check3=sudoku1.Check(4,4,'D');
        assertEquals(true,check3);
        HashSet<Character>[] set=sudoku1.getSets();
        HashSet<Character> set1=new HashSet<>();
        set1.add('D');
        assertEquals(set1,set[0]);
        HashSet<Character> set2=new HashSet<>();
        set2.add('C');
        assertEquals(set2,set[1]);
        HashSet<Character> set3=new HashSet<>();
        HashSet<Character> set4=new HashSet<>();
        set4.add('A');
        assertEquals(set3,set[2]);
        assertEquals(set4,set[3]);

    }

    @Test
    void helper() {

        Sudoku sudoku3=new Sudoku(4,1);
        sudoku3.setValue(1,2,'3');
        sudoku3.setValue(1,3,'0');
        sudoku3.setValue(3,4,'2');
        HashSet<Character> helper= new HashSet<>();
        for(int i=1;i<=4;i++){
            if(i!=2) {
                helper.add((char) ('0' + i));
            }
        }
        HashSet<Character> helper2= new HashSet<>();
        for(int i=1;i<=4;i++){
            if(i!=3) {
                helper2.add((char) ('0' + i));
            }
        }
        assertEquals(helper,sudoku3.Helper(4,4));
        assertEquals(helper2,sudoku3.Helper(1, 3));

        Sudoku sudoku4=new Sudoku(4,0);
        sudoku4.setValue(3,3,'E');
        sudoku4.setValue(1,2,'C');
        sudoku4.setValue(3,4,'B');
        sudoku4.setValue(1,4,'A');

        HashSet<Character> helper3= new HashSet<>();
        for(char j='A'; j<='D';j++){
            if(j!= 'A' && j!= 'B')
            helper3.add(j);
        }
        assertEquals(helper3,sudoku4.Helper(2,4));

    }

}