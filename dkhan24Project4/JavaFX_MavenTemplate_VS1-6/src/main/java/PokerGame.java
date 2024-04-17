import java.net.*;
import java.util.ArrayList;
import java.io.*;


public class PokerGame {

  private ArrayList<Card> deck;
  // int newRandom;
  private ArrayList<Player> players;
  private Dealer dealer;
  boolean dealerWon;

  public PokerGame() {

    this.dealerWon = false;
    this.players = new ArrayList<Player>();
    this.deck = new ArrayList<Card>(52);
    String[] types = { "❤", "♦", "♠", "♣" };
    for (int i = 0; i < 13; i++) {
      for (int j = 0; j < 4; j++) {
        Card card = new Card(types[j], i + 2); // 2 -> 14, 14 -> ACE
        this.deck.add(card);
      }
    }
  }

  public void addPlayer(Player player) {
    this.players.add(player);
  }

  public ArrayList<Card> getDealerCard() {
    return this.dealer.DealerDraw();
  }

  public ArrayList<Card> getPlayerCard(int index) {
    return this.players.get(index).getCards();
  }

  public int getPlayerWinnings(int index) {
    return this.players.get(index).getWinnings();
  }

  public void setPlayerMove(String move, int index, int playWager) {
    if (move == "play") {
      this.players.get(index).play(playWager);
    } else {
      this.players.get(index).fold();
    }
  }

  private ArrayList<Card> drawThreeCards() {
    ArrayList<Card> returningCards = new ArrayList<Card>(3);
    for (int i = 0; i < 3; i++) {
      int randomIndex = (int) (Math.random() * deck.size());
      returningCards.add(deck.get(randomIndex));
      deck.remove(randomIndex);
    }
    return returningCards;
  }

  public void assignDealer() {
    if (dealer != null && dealer.getHasCards() == true) {
      return;
    }
    ArrayList<Card> dealerCard = drawThreeCards();
    this.dealer = new Dealer(dealerCard);
  }

  public void assignPlayer(int index) {
    ArrayList<Card> playerCard = drawThreeCards();
    this.players.get(index).setCards(playerCard);
  }

  public void assignAllPlayers() {
    for (int i = 0; i < this.players.size(); i++) {
      assignPlayer(i);
    }
  }

  public ArrayList<Card> getDeck() {
    return deck;
  }

  private int pairWagerWinnings(ArrayList<Card> playerCard, int PairWager) {
    boolean straightFlush = true;
    boolean threeOfAKind = true;
    boolean straight = true;
    boolean flush = true;
    boolean pair = false;


    // ArrayList<Card> playerCard = player.getCards();
    for (int j = 0; j < playerCard.size() - 1; j++) {
      for (int x = 0; x < playerCard.size() - j - 1; x++) {
        if (playerCard.get(x).getNumber() > playerCard.get(x + 1).getNumber()) {
          Card tempCard = playerCard.get(x + 1);
          playerCard.set(x + 1, playerCard.get(x));
          playerCard.set(x, tempCard);
        }
      }
    }
    // Checking for Straight Flush
    String type = "";
    type = playerCard.get(0).getType();

    for (int j = 1; j < playerCard.size(); j++) {
      if (playerCard.get(j).getType() != type) {
        straightFlush = false;
      }
      if (playerCard.get(j - 1).getNumber() != playerCard.get(j).getNumber() - 1) {
        straightFlush = false;
      }
    }

    // threeOfAKind
    if (!(straightFlush)) {
      for (int j = 1; j < playerCard.size(); j++) {
        if (playerCard.get(j - 1).getNumber() != playerCard.get(j).getNumber()) {
          threeOfAKind = false;
        }
      }
    } else {
      return PairWager + (40 * PairWager);
    }

    if (!(threeOfAKind)) {
      for (int j = 1; j < playerCard.size(); j++) {
        if (playerCard.get(j - 1).getNumber() != playerCard.get(j).getNumber() - 1) {
          straight = false;
        }
      }
    } else {
      return PairWager + (30 * PairWager);
    }

    if (!(straight)) {
      for (int j = 1; j < playerCard.size(); j++) {
        if (playerCard.get(j).getType() != type) {
          flush = false;
        }
      }
    } else {
      return PairWager + (6 * PairWager);
    }
    if (!(flush)) {
      boolean foundPair = false;
      for (int j = 0; j < playerCard.size() - 1; j++) {
        for (int k = j + 1; k < playerCard.size(); k++) {
          if (playerCard.get(j).getNumber() == playerCard.get(k).getNumber()) {
            foundPair = true;
            break;
          }
        }
        if (foundPair) {
          break;
        }
      }
      if (foundPair) {
        return PairWager + (1 * PairWager);
      } else {
        return 0;
      }
    } else {
      return PairWager + (3 * PairWager);
    }
  }
  public void calculateWinnings() {

    for (int i = 0; i < this.players.size(); i++) {
      if (this.players.get(i).isFolded()) {
        this.players.get(i).setWinnings(0);
      }

      else {
        int winning = 0;
        // Winnings by play Wager
        boolean greaterEqualQueen = false;
        ArrayList<Card> dealerCard = this.dealer.DealerDraw();
        for (int j = 0; j < dealerCard.size(); j++) {
          if (dealerCard.get(j).getNumber() >= 12) { // 12->Queen, 13->King, 14->ACE
            greaterEqualQueen = true;
          }
        }
        if (!(greaterEqualQueen)) {
          winning += this.players.get(i).getplayWager();
        } else {
          // Winning by Ante Wager
          int dealerWinnings = pairWagerWinnings(this.dealer.DealerDraw(), 10);
          int playerWinnings = pairWagerWinnings(this.players.get(i).getCards(), 10);
          if (dealerWinnings < playerWinnings) {
            int wager = this.players.get(i).getanteWager() + this.players.get(i).getplayWager();
            winning += wager + (wager * 1);
          } else {
            dealerWon = true;
          }
        }
        winning += pairWagerWinnings(this.players.get(i).getCards(), this.players.get(i).getpairPlus());
        this.players.get(i).setWinnings(winning);
      }
    }
  }
}