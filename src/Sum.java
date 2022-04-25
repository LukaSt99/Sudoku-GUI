import java.util.ArrayList;
import java.util.HashSet;
/**

 @author Despoina Giatagana
 @author Loukas Stogiolaris
 @version 10/1/2020
 */
public class Sum {
    private int sum;
    private ArrayList<Coordinates> coordinates;
    private int numbers_of_coord;

    /**

     @param sum
     */
    public Sum(int sum){
        this.sum=sum;
        coordinates=new ArrayList<>();
        numbers_of_coord=0;
    }
    /**

     @param e
     */
    public void add_Coordinates(Coordinates e) {
        if (e.getX() >= 1 && e.getX() <= e.getDimension() && e.getY() >= 1 && e.getY() <= e.getDimension()) {
            coordinates.add(e);
            numbers_of_coord++;

        }
    }
    /**
     @return
     */
    public ArrayList<Coordinates> getCoordinates() {
        return coordinates;
    }
    /**
     @return
     */
    public int getSum() {
        return sum;
    }
    /**
     @return
     */
    public int getNumbers_of_coord() {
        return numbers_of_coord;
    }
}
