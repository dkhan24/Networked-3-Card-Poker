import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Dealer {
  private ArrayList<Card> cards;
  private boolean hasCards;

  public Dealer(ArrayList<Card> cards) {
    this.cards = cards;
    this.hasCards = true;
  }

  public boolean getHasCards() {
    return this.hasCards;
  }

  public ArrayList<Card> DealerDraw() {
    return cards;
  }
}