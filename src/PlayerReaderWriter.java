import java.io.*;
import java.util.ArrayList;
/**

 @author Despoina Giatagana
 @author Loukas Stogiolaris
 @version 10/1/2020
 */
public class PlayerReaderWriter
{
    /**

     @param name
     @return
     */
    public ArrayList<Player> Read(String name)
    {
        ArrayList<Player> players = new ArrayList<Player>();
        try{
            FileInputStream file = new FileInputStream(name);

            ObjectInputStream input = new ObjectInputStream(file);
            try
            {
                int size = input.readInt();
                for(int i = 0; i < size; i++) {
                    Player pl = (Player) input.readObject();
                    players.add(pl);
                }

            }
            catch(EOFException e)
            {
                System.out.println("End of file");
            }

            input.close();
            file.close();


        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
           return null;
        }

        return players;
    }
    /**

     @param players
     @param name
     */
    public void Write(ArrayList<Player> players, String name)
    {
        try{
            FileOutputStream file = new FileOutputStream(name);
            ObjectOutputStream out = new ObjectOutputStream(file);

            int size = players.size();
            out.writeInt(size);
            try
            {
                for(Player p: players)
                {
                    out.writeObject(p);
                }
                out.close();
                file.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
