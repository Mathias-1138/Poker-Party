package players;

import game.HandRanks;
import game.Player;

public class mathiasPlayer extends Player {
     /**
     * Constructs a new Player with the specified name.
     * Initializes the player's hand, bank balance, and various status flags.
     *
     * @param name The name of the player.
     */

     private HandRanks handRank;
    private int minBet;
    private int currentBet;
    private int bank;
    public mathiasPlayer(String name) {
        super(name);
    }

    @Override
    protected void takePlayerTurn() {
        System.out.println("I have a " + evaluatePlayerHand());

        if(evaluatePlayerHand().getValue() > HandRanks.HIGH_CARD.getValue()) {
            System.out.println(("I have a hand greater than a high card"));
        }else {
            System.out.println("I have a high card");
        }

        System.out.println("This is all the info form the Gamestate object");
        printExampleStateInformation();
        minBet = getGameState().getTableMinBet();


        if(shouldFold()) {
            fold();
        }
        else if(shouldCheck()) {
            check();
        }
        else if(shouldCall()) {
            call();
        }
        else if(shouldRaise()) {
            raise(minBet * 2);
        }
        else if(shouldAllIn()) {
            allIn();
        }
    }

    @Override
    protected boolean shouldFold() {
        return evaluatePlayerHand().getValue() < HandRanks.PAIR.getValue()
                && getGameState().isActiveBet();
    }

    @Override
    protected boolean shouldCheck() {
       return !getGameState().isActiveBet();
 }

    @Override
    protected boolean shouldCall() {
        handRank = evaluatePlayerHand();
        return handRank.getValue() >= HandRanks.PAIR.getValue() &&
                handRank.getValue() < HandRanks.THREE_OF_A_KIND.getValue();
    }

    @Override
    protected boolean shouldRaise() {
        HandRanks handRank = evaluatePlayerHand();
        currentBet = getGameState().getTableBet();
        bank = getBank();
        return handRank.getValue() >= HandRanks.THREE_OF_A_KIND.getValue()
                && bank > currentBet * 3;
    }

    @Override
    protected boolean shouldAllIn() {
        handRank = evaluatePlayerHand();
        return handRank.getValue() >= HandRanks.FOUR_OF_A_KIND.getValue();
    }
}
