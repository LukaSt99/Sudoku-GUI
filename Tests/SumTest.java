import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SumTest {

    @Test
    void Sum_tester() {
        Sum sum1= new Sum(13);

        Coordinates coord1=  new Coordinates(1,3,9);
        Coordinates coord2=  new Coordinates(2,3,9);
        Coordinates coord3=  new Coordinates(2,2,9);
        Coordinates coord4=  new Coordinates(-1,5,9);
        Coordinates coord5=  new Coordinates(3,2,9);
        Coordinates coord6=  new Coordinates(4,2,9);
        Coordinates coord7=  new Coordinates(1,10,9);


        sum1.add_Coordinates(coord1);
        sum1.add_Coordinates(coord2);
        sum1.add_Coordinates(coord3);
        sum1.add_Coordinates(coord4);
        sum1.add_Coordinates(coord5);
        sum1.add_Coordinates(coord6);
        sum1.add_Coordinates(coord7);

        ArrayList<Coordinates> coordinates=new ArrayList<>();
        coordinates.add(coord1);
        coordinates.add(coord2);
        coordinates.add(coord3);
        coordinates.add(coord5);
        coordinates.add(coord6);

        assertEquals(13,sum1.getSum());
        assertEquals(coordinates,sum1.getCoordinates());


    }

}