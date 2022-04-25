import java.io.Serializable;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int victories;
    private int defeats;
    private String classic;
    private String killer;

    public Player(String n, int v, int d, String c, String k)
    {
        name = n;
        victories = v;
        defeats = d;
        classic = c;
        killer = k;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public String getClassic() {
        return classic;
    }

    public void setClassic(String c) {
        classic = c;
    }

    public String getKiller() {
        return killer;
    }

    public void setKiller(String k) {
        killer = k;
    }

    @Override
    public String toString()
    {
        return name + " " + victories + " " + defeats + " " + classic + " " + killer;
    }
}