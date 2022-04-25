/**

 @author Despoina Giatagana
 @author Loukas Stogiolaris
 @version 10/1/2020
 */
public class Coordinates {
    int x;
    int y;
    int dimension;
    /**
     *
     @param dimension
     */
    public Coordinates(int dimension){
        this.dimension=dimension;
    }
    /**

     @param x
     @param y
     @param dimension
     */
    public Coordinates(int x,int y,int dimension){
        this.dimension=dimension;
        this.x = x;
        this.y = y;

    }
    /**

     @param x
     */
    public void setX(int x) {
        if(x>=1 && x<=dimension) {
            this.x = x;
        }
    }
    /**

     @param y
     */
    public void setY(int y) {
        if(y>=1 && y<=dimension) {
            this.y = y;
        }
    }
    /**
     @return
     */
    public int getX() {
        return x;
    }
    /**
    @return
     */
    public int getY() {
        return y;
    }
    /**
     @return
     */
    public int getDimension() {
        return dimension;
    }
}
