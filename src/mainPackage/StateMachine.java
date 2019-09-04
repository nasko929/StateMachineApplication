package mainPackage;

import enums.State;

/**
 * A class to represent the state machine and all of its functionality.
 */
public class StateMachine {

    private State currentState; // A State object to tell us the current state of the machine.
    private boolean coinPut; // A boolean value to tell whether we have a coin in the machine or not.

    /**
     * A constructor with no parameters setting initial values - for the state LOCKED, for the coinPut - false.
     */
    public StateMachine() {
        this.coinPut = false;
        this.currentState = State.LOCKED;
    }

    /**
     * Getters and setters.
     */
    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public boolean isCoinPut() {
        return coinPut;
    }

    public void setCoinPut(boolean coinPut) {
        this.coinPut = coinPut;
    }

    /**
     * A method which handles putting a coin. If a coin is already put or the state of the machine is OPEN,
     * the coin is rejected therefore we return false.
     * Otherwise we set the state of the machine to PAID and return true.
     *
     * @return - true / false, based on whether we accept the coin or not.
     */
    public boolean putCoin() {
        if (currentState == State.OPEN || currentState == State.PAID)
            return false;
        currentState = State.PAID;
        coinPut = true;
        return true;
    }

    /**
     * A method which tells us whether we can pass or not. If the state of the machine is LOCKED, we can't pass ->
     * therefore we return false;
     * If it is PAID, then the customer may pass but we change the state to locked and set the coinPut to false,
     * because the customer just consumed it.
     * In the preceding scenario and also in case the state of the machine's state is OPEN, we return true.
     *
     * @return - true / false, based on whether we can pass or not.
     */
    public boolean canPass() {
        if (currentState == State.LOCKED)
            return false;
        if (currentState == State.PAID) {
            coinPut = false;
            currentState = State.LOCKED;
        }
        return true;
    }

    /**
     * A void method to simulate an administrative key's influence on the machine.
     * If the machine's state is LOCKED or PAID, it changes to OPEN ( and if PAID, it "loses" the coin ) .
     * If the machine's state is OPEN, it changes to LOCKED.
     */
    public void key() {
        if (currentState == State.LOCKED) {
            currentState = State.OPEN;
        } else if (currentState == State.OPEN) {
            currentState = State.LOCKED;
        } else {
            coinPut = false;
            currentState = State.OPEN;
        }
    }
}
