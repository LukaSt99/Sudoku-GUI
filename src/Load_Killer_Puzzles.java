import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
/**

 dfshjg
 @author Despoina Giatagana
 @author Loukas Stogiolaris
 @version 10/1/2020
 */
public class Load_Killer_Puzzles {

    private  HashSet<Sum> sums;
    String filename;

    /**
     *
     @param index
     */
    public Load_Killer_Puzzles(int index)  {

        sums = new HashSet<>();
        filename="resources/"+index+"k.txt";

        try {
            FileReader in = new FileReader(filename);
            Scanner scanner = new Scanner(in);
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] lines = line.split("\\W+");
                Sum sum = new Sum(Integer.parseInt(lines[0]));
                for (int i=1;i<lines.length;i++){
                    Coordinates cord=new Coordinates(Integer.parseInt(lines[i])/10,Integer.parseInt(lines[i])%10, 9);
                    sum.add_Coordinates(cord);
                    sums.add(sum);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("EOF");
        }
    }
    /**
    @return
     */
    public HashSet<Sum> getSums() {
        return sums;
    }
}