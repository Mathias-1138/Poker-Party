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
            raise(getGameState().getTableMinBet());
        }
        else if(shouldAllIn()) {
            allIn();
        }
    }

    @Override
    protected boolean shouldFold() {
        return false;
    }

    @Override
    protected boolean shouldCheck() {
//        if(!getGameState().isActiveBet()) {
//            return true;
//        }
        return false;
 }

    @Override
    protected boolean shouldCall() {
        return false;
    }

    @Override
    protected boolean shouldRaise() {
        if(getGameState().isActiveBet()) {
            if(getBank() > getGameState().getTableBet() * 2)
            return true;
        }
        return false;
    }

    @Override
    protected boolean shouldAllIn() {
        return false;
    }
}
