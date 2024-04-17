import javafx.fxml.Initializable;
import java.util.ArrayList;


import java.io.Serializable;

public class Pokerinfo implements Serializable {

  public int turn;

  public ArrayList<Card> playerCard;

  public ArrayList<Card> dealerCard;

  public String logs;

  public int anteWager;
  public int pairPlusWager;

  public int playWager;

  public String playerMove;
  // public String dealerMove;
  public boolean won;

  public int winnings;

  public Pokerinfo() {

    turn = 0;
    this.playerCard = new ArrayList<Card>(3);
    this.dealerCard = new ArrayList<Card>(3);
    logs = " ";
    playerMove = "";
    // dealerMove = "";
    playWager = 0;
    anteWager = 0;
    pairPlusWager = 0;
    winnings = 0;
    won = false;
  }
}
