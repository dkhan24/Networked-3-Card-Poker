import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

import static java.lang.Integer.parseInt;

public class client extends Thread {

    Socket socketClient;

    ObjectOutputStream out;
    ObjectInputStream in;
    String ip;
    int port;
    Pokerinfo game;

    boolean check;

    private gamecontroller controller;

    private Consumer<Serializable> callback;

    client(Consumer<Serializable> call, String ip, int port) {
        this.game = new Pokerinfo();
        callback = call;
        this.ip = ip;
        this.port = port;
        System.out.println("client constructor called." );
        check = true;
    }

    public void run() {

        System.out.println("Client Run function called. ");

        try {
            socketClient = new Socket(ip, port);
            System.out.println("Trying to connect new Socket.");
            out = new ObjectOutputStream(socketClient.getOutputStream());
            in = new ObjectInputStream(socketClient.getInputStream());
            socketClient.setTcpNoDelay(true);
            System.out.println("New socket connected");
        } catch (Exception e) {
            System.out.println(e);
        }

        while (true) {
            if (game.turn != 0 && check) {
                try {
                    System.out.println("Reading the game object for turn " + game.turn);
                    game = (Pokerinfo) in.readObject();
                    System.out.println("Reading the game object for turn 1");
                    check = false;
                }
                catch (Exception e) {
                    System.out.println(e);
                    System.out.println("Here in receive object");
                    e.printStackTrace();
                    check = false;
                    return;
                }
            }
            if (game.turn == 0) {
                System.out.println(game.turn + " is game.turn and wagersubmitted is: " + gamecontroller.wagersubmitted);
                if (gamecontroller.wagersubmitted) {
                    System.out.println("Wager Submitted is true");
                    //System.out.flush();
                    int anteWager;
                    int pairPlusWager;
                    try{
                        anteWager = parseInt(gamecontroller.aWager);
                        pairPlusWager = parseInt(gamecontroller.pPlusWager);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Error parsing integer input: " + e.getMessage());
                        e.printStackTrace();
                        return;
                    }
                    System.out.println("AnteWager: " + anteWager + " pair plus wager: " + pairPlusWager);
                    System.out.flush();
                    game.anteWager = anteWager;
                    game.pairPlusWager = pairPlusWager;
                    game.turn = 1;
                    System.out.println("Just about to call send function");
                    send(game);
                }
            }
            else if (game.turn == 2) {
                System.out.println("game.turn " + 2 + " is running from here.");

                try {
//                    controller.DealerCard1.setText(game.dealerCard.get(0).getType() + game.dealerCard.get(0).getNumber());
//                    controller.DealerCard2.setText(game.dealerCard.get(1).getType() + game.dealerCard.get(1).getNumber());
//                    controller.DealerCard3.setText(game.dealerCard.get(2).getType() + game.dealerCard.get(2).getNumber());
                    gamecontroller.pCard1 = game.playerCard.get(0).getType() + game.playerCard.get(0).getNumber();
                    gamecontroller.pCard2 = game.playerCard.get(1).getType() + game.playerCard.get(1).getNumber();
                    gamecontroller.pCard3 = game.playerCard.get(2).getType() + game.playerCard.get(2).getNumber();

                }
                catch (Exception e) {
                    System.out.println(e);
                    System.out.println("in exception for dealer card and player card.");
                }
                while (gamecontroller.choiceMade == false) {
                    System.out.println("Loop for Choice Made");
                }
                System.out.println("The card is: " + game.dealerCard.get(0).getType() );
                gamecontroller.dCard1 = game.dealerCard.get(0).getType() + game.dealerCard.get(0).getNumber();
                gamecontroller.dCard2 = game.dealerCard.get(1).getType() + game.dealerCard.get(1).getNumber();
                gamecontroller.dCard3 = game.dealerCard.get(2).getType() + game.dealerCard.get(2).getNumber();


                game.playerMove = gamecontroller.choice;
                if (game.playerMove == "play") {
                    game.playWager = parseInt(gamecontroller.pWager);
                }
                game.turn = 3;
                send(game);
                check = true;
            }
            else if (game.turn==4) {
                if (game.won == true) {
                    System.out.println("We won");
                    gameovercontroller.setresult("Congratulation, you have won!!");
                    gameovercontroller.setresultMoney("Amount: " + game.winnings);
                    break;
                }
                else {
                    gameovercontroller.setresult("Sorry, you lost!");
                    gameovercontroller.setresultMoney("Amount: " + game.winnings);
                    break;

                }

            }
            //callback.accept(message);
        }

    }

    public void send(Pokerinfo game) {
        try {
            System.out.println("Sending the following game.");
            out.writeObject(game);
            System.out.println("Sent the following game.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("send Exception");
        }
    }

}