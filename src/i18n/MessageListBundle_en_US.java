package i18n;

import java.util.ListResourceBundle;
public class MessageListBundle_en_US extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            {"start", "Start"},
            {"stats", "Statistics"},
            {"exit", "Exit"},
            {"choose", "Choose"},
            {"classic", "Classic"},
            {"user", "Please enter your username. If you want to remain anonymous, press Cancel"},
            {"display", "Do you want to try to play with letter display (Wordoku)? If not, then you will see numbers, as normal."},
            {"puzzle", "select one of the following puzzles."},
            {"back", "Back"},
            {"clear", "Clear"},
            {"new", "New"},
            {"gl", "<html>GOOD<br>LUCK!</hl>"},
            {"win", "<html>CONGRATULATIONS!<br>YOU WIN!</hl>"},
            {"lose", "<html>DEFEAT!<br>TRY AGAIN!<html>"},
            {"rand", "Random"},
            {"backmsg", "Are you sure you want to go back to the main menu? All progress will be lost."},
            {"viewall", "View All"},
            {"search", "Search Player"},
            {"searchmsg", "Please enter a player's username: "},
            {"name", "Name"},
            {"vic", "Victories"},
            {"def", "Defeats"},
            {"nop", "Sorry, no such player was found..."},
            {"norep", "There are no registered players yet..."},
            {"exitmsg", "Are you sure you want to exit the game?"},
            {"main", "Main Menu"},
            {"newgame", "Are you sure you want to start a new game? All progress will be lost."}
    };
}