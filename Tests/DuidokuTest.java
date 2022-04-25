
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DuidokuTest {

    @Test
    void check_dudikou_letters() {
     Duidoku duidoku1=new Duidoku(1);

     duidoku1.player(1,5,'4');
     duidoku1.player(0,3,'1');
     duidoku1.check_duidoku();
     duidoku1.player(1,1,'1');
     duidoku1.player(3,3,'9');
     duidoku1.check_duidoku();
     duidoku1.player(4,2,'4');
     duidoku1.check_duidoku();
     duidoku1.player(2,2,'3');
     duidoku1.check_duidoku();
     duidoku1.player(4,1,'2');
     duidoku1.check_duidoku();
     duidoku1.player(2,1,'4');
     duidoku1.check_duidoku();
     duidoku1.player(3,2,'1');
     duidoku1.check_duidoku();
     duidoku1.player(1,4,'2');
     duidoku1.check_duidoku();
     duidoku1.player(3,4,'3');
     duidoku1.check_duidoku();
     assertEquals(true,duidoku1.table[0][1].isBlocked_cell());
     assertEquals(true,duidoku1.table[2][0].isBlocked_cell());

     duidoku1.player(4,3,'1');
     duidoku1.check_duidoku();
     duidoku1.player(2,4,'1');
     duidoku1.check_duidoku();
     duidoku1.player(1,3,'4');

     duidoku1.check_duidoku();
     duidoku1.pc();
     assertEquals('2',duidoku1.getCurrent_c_pc());
     assertEquals(true,duidoku1.table[1][3].isLocked() || duidoku1.table[2][2].isLocked());
     assertEquals(true,duidoku1.table[1][2].isBlocked_cell());
     assertEquals(true,duidoku1.table[3][3].isBlocked_cell());
    }
    @Test
    void check_dudikou_numbers() {
        Duidoku duidoku2=new Duidoku(0);

        duidoku2.player(1,5,'C');
        duidoku2.player(0,3,'D');
        duidoku2.check_duidoku();
        duidoku2.player(1,1,'A');
        duidoku2.player(3,3,'9');
        duidoku2.check_duidoku();
        duidoku2.player(3,3,'B');
        duidoku2.check_duidoku();
        duidoku2.player(4,2,'C');
        duidoku2.check_duidoku();
        duidoku2.player(2,4,'C');
        duidoku2.check_duidoku();
        duidoku2.player(4,1,'D');
        duidoku2.check_duidoku();
        duidoku2.player(3,2,'A');
        duidoku2.check_duidoku();
        duidoku2.player(1,4,'D');
        duidoku2.check_duidoku();
        duidoku2.player(2,2,'B');
        duidoku2.check_duidoku();
        assertEquals(true,duidoku2.table[1][0].isBlocked_cell());
        assertEquals(true,duidoku2.table[0][1].isBlocked_cell());
        assertEquals(true,duidoku2.table[2][0].isBlocked_cell());

        duidoku2.player(2,3,'A');
        duidoku2.check_duidoku();
        duidoku2.pc();

        assertEquals('A',duidoku2.getCurrent_c_pc());
        assertEquals(true,duidoku2.table[2][3].isBlocked_cell() || duidoku2.table[3][3].isBlocked_cell());
        assertEquals(true,duidoku2.table[0][2].isBlocked_cell());
        assertEquals(true,duidoku2.table[3][2].isBlocked_cell());
        assertEquals(true,duidoku2.table[2][3].isBlocked_cell());

    }
}