import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Card implements Serializable {

  private String type; // hearts, diamonds, spades and clubs
  private int number; // 1 -> 13 | 11 -> Jack, 12 -> Queen, 13 -> King

  public Card(String type, int number) {
    this.type = type;
    this.number = number;
  }

  public String getType() {
    return type;
  }

  public int getNumber() {
    return number;
  }

}