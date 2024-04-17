import java.util.ArrayList;

public class ThreeCardLogic {

    public static int test1() {
        // Test case 1: Two players, dealer wins with Straight Flush
        PokerGame game = new PokerGame();
        Player player1 = new Player(10,10);
        Player player2 = new Player(10,10);
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.assignDealer();
        game.assignAllPlayers();
        game.setPlayerMove("fold", 0, 0);
        game.setPlayerMove("play", 1, 10);
        ArrayList<Card> dealerCards = game.getDealerCard();
        ArrayList<Card> playerCards = game.getPlayerCard(0);
        ArrayList<Card> playerCards1 = game.getPlayerCard(1);

        //Making sure 2 players don't have same type and number of card.
        if (playerCards.get(0).getNumber() == playerCards1.get(0).getNumber() && playerCards.get(0).getType() == playerCards1.get(0).getType()) {
            System.out.println("Test case 1 failed");
            return 0;
        }
        if (playerCards.get(1).getNumber() == playerCards1.get(1).getNumber() && playerCards.get(1).getType() == playerCards1.get(1).getType()) {
            System.out.println("Test case 1 failed");
            return 0;
        }
        if (playerCards.get(2).getNumber() == playerCards1.get(2).getNumber() && playerCards.get(2).getType() == playerCards1.get(2).getType()) {
            System.out.println("Test case 1 failed");
            return 0;
        }
        return 1;

    }

    public static int test2() {
        // Test case 1: Two players, dealer wins with Straight Flush
        PokerGame game = new PokerGame();
        Player player1 = new Player(10,10);
        Player player2 = new Player(10,10);
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.assignDealer();
        game.assignAllPlayers();
        game.setPlayerMove("fold", 0, 0);
        game.setPlayerMove("play", 1, 10);
        ArrayList<Card> dealerCards = game.getDealerCard();
        ArrayList<Card> playerCards = game.getPlayerCard(0);
        ArrayList<Card> playerCards1 = game.getPlayerCard(1);

        //Making sure 2 players and dealer don't have same type and number of card.
        if (playerCards.get(0).getNumber() == dealerCards.get(0).getNumber() && playerCards.get(0).getType() == dealerCards.get(0).getType()) {
            System.out.println("Test case 2 failed");
            return 0;
        }
        if (playerCards.get(1).getNumber() == dealerCards.get(1).getNumber() && playerCards.get(1).getType() == dealerCards.get(1).getType()) {
            System.out.println("Test case 2 failed");
            return 0;
        }
        if (playerCards.get(2).getNumber() == dealerCards.get(2).getNumber() && playerCards.get(2).getType() == dealerCards.get(2).getType()) {
            System.out.println("Test case 2 failed");
            return 0;
        }
        return 1;
    }

    public static int test3() {
            // Test case 1: Two players, dealer wins with Straight Flush
            PokerGame game = new PokerGame();
            Player player1 = new Player(10,10);
            Player player2 = new Player(10,10);
            game.addPlayer(player1);
            game.addPlayer(player2);
            game.assignDealer();
            game.assignAllPlayers();
            game.setPlayerMove("fold", 0, 0);
            game.setPlayerMove("play", 1, 10);
            ArrayList<Card> dealerCards = game.getDealerCard();
            ArrayList<Card> playerCards = game.getPlayerCard(0);
            ArrayList<Card> playerCards1 = game.getPlayerCard(1);

            //assign players card until we are out of cards
            while (game.getDeck().size() > 0 && game.getDeck().size() > 5) {
                game.assignAllPlayers();
            }
            return 1;

        }
    //main for testing
    public static void main(String[] args) {
        int test1 = test1();
        int test2 = test2();
        int test3 = test3();
        if (test1 == 1 && test2 == 1 && test3 == 1) {
            System.out.println("All tests passed");
        }
    }

}
