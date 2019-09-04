package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import enums.State;
import mainPackage.StateMachine;

/**
 * A testing class to check whether the functionality of the machine is right or not.
 */
public class TestStateMachine {
    private StateMachine stateMachine;

    @BeforeMethod
    public void beforeMethod() {
        this.stateMachine = new StateMachine();
    }

    @Test
    public void canWePutCoinWhenLocked() {
        final boolean expected = true;

        final boolean actual = stateMachine.putCoin();

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void isStateBecomingPaidWhenPuttingCoin() {
        final boolean expected = true;
        stateMachine.putCoin();

        final boolean actual = (stateMachine.getCurrentState() == State.PAID);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void canWePassWhenNoCoinPutAndLocked() {
        final boolean expected = false;

        final boolean actual = stateMachine.canPass();

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void canWeUseKeyToChangeStateToOpenWhenLocked() {
        final boolean expected = true;
        stateMachine.key();

        final boolean actual = (stateMachine.getCurrentState() == State.OPEN);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void canWePutCoinWhenStateIsOpen() {
        final boolean expected = false;
        stateMachine.setCurrentState(State.OPEN);

        final boolean actual = stateMachine.putCoin();

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void canWePassWhenStateIsOpen() {
        final boolean expected = true;
        stateMachine.setCurrentState(State.OPEN);

        final boolean actual = stateMachine.canPass();

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void canWeUseKeyToChangeStateToLockedWhenOpen() {
        final boolean expected = true;
        stateMachine.setCurrentState(State.OPEN);
        stateMachine.key();

        final boolean actual = (stateMachine.getCurrentState() == State.LOCKED);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void canWePutCoinWhenStateIsPaid() {
        final boolean expected = false;
        stateMachine.setCurrentState(State.PAID);
        stateMachine.setCoinPut(true);

        final boolean actual = stateMachine.putCoin();

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void canWePassWhenStateIsPaid() {
        final boolean expected = true;
        stateMachine.putCoin();

        final boolean actual = stateMachine.canPass();

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void canWePassTwiceWithOneCoinWhenStateIsPaid() {
        final boolean expected = false;
        stateMachine.putCoin(); // This action will change our state to paid. The value that is returned always true.
        boolean canWePassFirstTime = stateMachine.canPass();

        final boolean actual = stateMachine.canPass();

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void canWeChangeToOpenFromPaidWithKey() {
        final boolean expected = true;
        stateMachine.putCoin(); // Same as line 110.
        stateMachine.key();

        final boolean actual = (stateMachine.getCurrentState() == State.OPEN);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void isTheCoinStillInTheMachineAfterKeyingThePaidStateMachine() {
        final boolean expected = false;
        stateMachine.putCoin();
        stateMachine.key();

        final boolean actual = stateMachine.isCoinPut();

        Assert.assertEquals(actual, expected);
    }
}
