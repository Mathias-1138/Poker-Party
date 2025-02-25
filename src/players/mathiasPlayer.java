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
// if the hand is bad and far into the game, the player folds.
    @Override
    protected boolean shouldCheck() {
       return !getGameState().isActiveBet();
 }
 // if the hand is not that bad, it will check to see how their hand goes.
    @Override
    protected boolean shouldCall() {
        handRank = evaluatePlayerHand();
        return handRank.getValue() >= HandRanks.PAIR.getValue() &&
                handRank.getValue() < HandRanks.THREE_OF_A_KIND.getValue();
    }
// calls if the hand is good but not enough to justify making big plays
    @Override
    protected boolean shouldRaise() {
        HandRanks handRank = evaluatePlayerHand();
        currentBet = getGameState().getTableBet();
        bank = getBank();
        return handRank.getValue() >= HandRanks.THREE_OF_A_KIND.getValue()
                && bank > currentBet * 3;
    }
// Raises when the hand is good and raises aggressively because it is confident that its hand is better
    @Override
    protected boolean shouldAllIn() {
        handRank = evaluatePlayerHand();
        return handRank.getValue() >= HandRanks.FOUR_OF_A_KIND.getValue();
    }
}
// goes all in when the hand is amazing it goes in hard and towards the later parts of the game
