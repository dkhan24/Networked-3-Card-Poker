import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Player {

  private int anteWager;
  private int pairPlus;
  private int playWager;
  private int winnings;
  private boolean folded;
  private ArrayList<Card> cards;
  private boolean hasCards;

  public Player(int anteWager, int pairPlus) {
    this.anteWager = anteWager;
    this.pairPlus = pairPlus;
    this.winnings = 0;
    this.playWager = 0;
    this.folded = false;
    this.cards = new ArrayList<Card>(3);
    this.hasCards = false;
  }

  // getters
  public int getanteWager() {
    return this.anteWager;
  }

  public int getpairPlus() {
    return this.pairPlus;
  }

  public int getplayWager() {
    return this.playWager;
  }

  public int getWinnings() {
    return this.winnings;
  }

  public boolean isFolded() {
    return this.folded;
  }

  public ArrayList<Card> getCards() {
    return this.cards;
  }

  public boolean getHasCards() {
    return this.hasCards;
  }

  // setter
  public void setWinnings(int winnings) {
    this.winnings = winnings;
  }

  public void setCards(ArrayList<Card> cards) {
    this.cards = cards;
    this.hasCards = true;
  }
  // public void setPlayWager (int playWager) { this.playWager = playWager; }

  public ArrayList<Card> fold() {
    folded = true;
    return cards;
  }

  public ArrayList<Card> play(int playWager) {
    this.playWager = playWager;
    return cards;
  }

}
