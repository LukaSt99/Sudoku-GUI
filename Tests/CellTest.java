import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CellTest {

    @Test
    void setValue() {
        Cell cell_1= new Cell('W',false,9,0);
        assertEquals(' ',cell_1.getValue());
        Cell cell_2= new Cell('0',false,9,1);
        assertEquals(' ',cell_2.getValue());
        Cell cell_3= new Cell('3',true,9,1);
        cell_3.setValue('4');
        assertEquals('3',cell_3.getValue());
        assertEquals(false,cell_3.isFilled());
        Cell cell_4= new Cell(9,0);
        cell_4.setValue('K');
        cell_4.setValue('A');
        cell_4.setLocked(true);
        cell_4.setValue('B');
        assertEquals('A',cell_4.getValue());
    }

    @Test
    void deleteValue() {
        Cell cell_1= new Cell(9,1);
        cell_1.deleteValue();
        assertEquals(' ',cell_1.getValue());
        Cell cell_2= new Cell('0',false,9,1);
        cell_2.deleteValue();
        assertEquals(' ',cell_2.getValue());
        Cell cell_3= new Cell('W',true,9,1);
        cell_3.deleteValue();
        assertEquals(' ',cell_3.getValue());
        assertEquals(false,cell_3.isLocked());

    }
}