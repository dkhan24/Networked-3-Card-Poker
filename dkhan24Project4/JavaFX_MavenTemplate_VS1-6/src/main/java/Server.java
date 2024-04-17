import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;
/*
 * Clicker: A: I really get it    B: No idea what you are talking about
 * C: kind of following
 */

public class Server {

    int count = 1;
    ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    TheServer server;
    private Consumer<Serializable> callback;
    public ArrayList<String> logs;
    int currentPlayerIndex = 0;

    PokerGame pokergame;

    Server(Consumer<Serializable> call) {

        callback = call;
        server = new TheServer();
        pokergame = new PokerGame();
        logs = new ArrayList<String>();
        server.start();

        System.out.println("Constructor for server called.");

    }

    public class TheServer extends Thread {

        public void run() {

            try (ServerSocket mysocket = new ServerSocket(5555);) {
                System.out.println("Server is waiting for a client!");

                while (true) {

                    ClientThread c = new ClientThread(mysocket.accept(), count);
                    callback.accept("client has connected to server: " + "client #" + count);
                    clients.add(c);
                    c.start();
                    count++;

                }
            } // end of try
            catch (Exception e) {
                callback.accept("Server socket did not launch");
            }
        }// end of while
    }

    class ClientThread extends Thread {

        Socket connection;
        int count;

        ObjectInputStream in;
        Pokerinfo game;

        ObjectOutputStream out;

        ClientThread(Socket s, int count) {
            System.out.println("Constructor for client thread called.");
            this.connection = s;
            this.count = count;
            this.game = new Pokerinfo();
        }



        public void run() {

            try {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);
                System.out.println("Run for client is called and input streams are made");

            } catch (Exception e) {
                System.out.println("Streams not open");
            }


            while (true) {
                //waiting to read the game object from client.
                try {
                    game = (Pokerinfo) in.readObject();
                }
                catch (Exception e) {
                    System.out.println(e);
                }
                System.out.println("got the game input");
                if (game.turn == 1) {

                    Player one = new Player(game.anteWager, game.pairPlusWager);
                    logs.add("Player # " + count + "decided their anteWager and pairPlusWager");
                    turnoffcontroller.logs.add("Player # " + count + "decided their anteWager and pairPlusWager");
                    System.out.println("Player # " + count + "decided their anteWager and pairPlusWager");
                    pokergame.addPlayer(one);
                    pokergame.assignPlayer(count - 1);
                    pokergame.assignDealer();
                    game.playerCard = pokergame.getPlayerCard(count - 1);
                    game.dealerCard = pokergame.getDealerCard();
                    game.turn = 2;
                    try {
                        out.writeObject(game);
                    }
                    catch (Exception e) {
                        System.out.println("Exception caught on server side");
                    }
                    currentPlayerIndex = (currentPlayerIndex + 1) % clients.size();
                    // continue;// pokergame.

                } else if (game.turn == 3) {

                    if (game.playerMove == "play") {
                        // play
                        pokergame.setPlayerMove("play", count - 1, game.playWager);

                    } else {
                        // fold
                        pokergame.setPlayerMove("fold", count - 1, 0);
                    }
                    game.turn = 4;
                }
                boolean check = true;
                for (ClientThread t : clients) {
                    if (t.game.turn != 4) {
                        check = false;
                    }
                }

                if (check) {
                    // int index = 0;
                    pokergame.calculateWinnings();
                    game.winnings = pokergame.getPlayerWinnings(count - 1);
                    if (pokergame.dealerWon) {
                        game.won = false;
                    } else {
                        int won = 0;
                        int index = 0;
                        for (ClientThread t : clients) {
                            if (t.game.winnings > clients.get(won).game.winnings) {
                                won = index;
                                index++;
                            }
                        }
                        if (clients.get(won).game.winnings == 0) {
                            clients.get(won).game.won = false;
                        }
                        else {
                            clients.get(won).game.won = true;
                        }

                    }
                    try {
                        out.writeObject(game);
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }

                }
            }
        }// end of run

    }
}