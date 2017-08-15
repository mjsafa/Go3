package mojtaba.safaeian.go3.domain;

import mojtaba.safaeian.go3.api.domain.GameStateException;
import mojtaba.safaeian.go3.api.domain.Player;

import java.util.Random;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/14/2017.
 */
public class LocalPlayerImpl implements Player {

    private enum State {
        NONE, RECEIVED, SENT, FINISHED;
    }

    private boolean win;

    private State lastState = State.NONE;
    private Integer lastReceivedAnswer;

    @Override
    public Integer generateFirstAnswer() {
        this.lastState = State.SENT;
        return new Random().nextInt(10000) + 1000;
    }

    @Override
    public void receiveAnswer(Integer answer) {
        if(answer > 1) {
            lastReceivedAnswer = answer;
            this.lastState = State.RECEIVED;
        }else {
            this.lastState = State.FINISHED;
            this.win = false;
        }
    }


    @Override
    public int answer() {
        if(lastReceivedAnswer != 1 && lastState != State.FINISHED){
            int answer = generateAnswer(lastReceivedAnswer);
            if(answer == 1){
                this.lastState = State.FINISHED;
                win = true;
            }else {
                this.lastState = State.SENT;
            }
            return answer;
        }else {
            throw new GameStateException("Game is finished, can not answer.");
        }
    }

    @Override
    public boolean isWin(){
        return lastState == State.FINISHED && win;
    }

    @Override
    public boolean isLose(){
        return lastState == State.FINISHED && !win;
    }

    private int generateAnswer(int n) {
        int appropriateValue =  n % 3 == 0 ? 0 :
                (n % 3 == 1 ? -1 : 1);
        return (n + appropriateValue) / 3;
    }

    @Override
    public boolean isMyTurn(){
        return this.lastState == State.RECEIVED && !isLose() && !isWin();
    }
}
