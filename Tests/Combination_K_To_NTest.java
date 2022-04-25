import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class Combination_K_To_NTest {

    @Test
    void combinations_test() {

        Combination_K_To_N combinations = new Combination_K_To_N(9);
        combinations.Combinations(10,3);
        HashSet<Integer> values1=new HashSet<>();
        for(int i=1;i<=7;i++){
            values1.add(i);
        }
        assertEquals(values1,combinations.getValues());

        Combination_K_To_N combinations2 = new Combination_K_To_N(9);
        combinations2.Combinations(10,1);
        HashSet<Integer> values2=new HashSet<>();
        assertEquals(values2,combinations2.getValues());

        Combination_K_To_N combinations3 = new Combination_K_To_N(9);
        combinations3.Combinations(3,3);
        HashSet<Integer> values3=new HashSet<>();
        assertEquals(values3,combinations3.getValues());

        Combination_K_To_N combinations4 = new Combination_K_To_N(9);
        combinations4.Combinations(3,2);
        HashSet<Integer> values4=new HashSet<>();
        values4.add(1);
        values4.add(2);
        assertEquals(values4,combinations4.getValues());

    }

}