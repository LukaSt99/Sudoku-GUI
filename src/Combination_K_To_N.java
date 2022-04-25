import java.util.ArrayList;
import java.util.HashSet;
/**

 @author Loukas Stogiolaris
 @version 10/1/2020
 */
public class Combination_K_To_N {

    private HashSet<Integer> values ;
    int dimension;
    /**
     @param dimension
     */
    public Combination_K_To_N(int dimension){
        values = new HashSet<>();
        this.dimension=dimension;
    }
    /**
     *
     @param n
     @param k
     */
    public void Combinations(int n, int k){
            ArrayList<Integer> combinations = new ArrayList<>();
            combinations_Util(k, n, 0, 1, combinations);
    }
    /**

     @param k
     @param n
     @param sum
     @param start
     @param combination
     */
    public void combinations_Util(int k, int n, int sum, int start, ArrayList<Integer> combination){

            if(k==0) {
                if(sum==n) {
                    for (Integer key:combination) {
                        values.add(key);
                    }
                }
                return;
            }
            for (int i = start; i <=dimension ; i++) {
                combination.add(i);
                combinations_Util(k - 1, n, sum + i, i + 1, combination);
                combination.remove(combination.size()-1);
            }
        }

    /**
     @return
     */
    public HashSet<Integer> getValues() {
        return values;
    }

}

