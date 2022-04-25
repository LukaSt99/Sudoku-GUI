import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

class Killer_SudokuTest {

    @Test
    void killer_Check() {

        Coordinates coord1=  new Coordinates(1,1,9);
        Coordinates coord2=  new Coordinates(1,2,9);
        Coordinates coord3=  new Coordinates(1,3,9);
        Coordinates coord4=  new Coordinates(1,4,9);
        Coordinates coord5=  new Coordinates(1,5,9);
        Coordinates coord6=  new Coordinates(1,6,9);
        Coordinates coord7=  new Coordinates(2,5,9);
        Coordinates coord8=  new Coordinates(2,6,9);
        Coordinates coord9=  new Coordinates(3,5,9);

        Sum sum1=new Sum (3);
        Sum sum2=new Sum (15);
        Sum sum3=new Sum (22);

        sum1.add_Coordinates(coord1);
        sum1.add_Coordinates(coord2);
        sum2.add_Coordinates(coord3);
        sum2.add_Coordinates(coord4);
        sum2.add_Coordinates(coord5);

        sum3.add_Coordinates(coord6);
        sum3.add_Coordinates(coord7);
        sum3.add_Coordinates(coord8);
        sum3.add_Coordinates(coord9);
        HashSet<Sum> sum = new HashSet<>();
        sum.add(sum1);
        sum.add(sum2);
        sum.add(sum3);
        Killer_Sudoku killer=new Killer_Sudoku(9,sum);

        boolean check1=killer.Killer_Check(1,6,'9');
        killer.player(1,6,'9');
        assertEquals(true ,check1);
        boolean check2=killer.Killer_Check(1,10,'3');
        killer.player(1,10,'3');
        assertEquals(false,check2);
        boolean check3=killer.Killer_Check(2,5,'4');
        killer.player(2,5,'4');
        assertEquals(true,check3);
        boolean check4=killer.Killer_Check(3,5,'3');
        killer.player(3,5,'3');
        assertEquals(true,check4);
        boolean check5=killer.Killer_Check(0,8,'3');
        killer.player(0,8,'3');
        assertEquals(false,check5);
        boolean check6=killer.Killer_Check(1,3,'s');
        killer.player(1,3,'s');
        assertEquals(false,check6);
        boolean check7=killer.Killer_Check(2,6,'8');
        killer.player(2,6,'8');
        assertEquals(false,check7);
        HashSet<Character> help =new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if(i==0 || i==1) {
                help.add((char) ('0' + (i + 1)));
            }
        }
        assertEquals(help,killer.killer_helper(1,2));
        boolean check8=killer.Killer_Check(1,5,'4');
        assertEquals(true,check8);
        killer.player(1,5,'4');
        boolean check9=killer.Check(1,5,'4');
        assertEquals(false,check9);

    }

}