package mojtaba.safaeian.go3.service.impl;

import mojtaba.safaeian.go3.api.domain.Game;
import mojtaba.safaeian.go3.api.domain.GameRunnerStatus;
import mojtaba.safaeian.go3.api.service.RemoteServiceException;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/15/2017.
 */
public class GameRunner implements Runnable {




    private Game game;
    private boolean terminate = false;
    private GameRunnerStatus status;

    private Exception terminateException;

    public GameRunner(Game game) {
        this.game = game;
        this.status = GameRunnerStatus.NOT_STARTED;
    }

    public GameRunnerStatus getStatus() {
        return status;
    }

    public void pause(){
        this.status = GameRunnerStatus.PAUSED;
    }

    @Override
    public void run() {

        while (!terminate && !game.isFinished() && status != GameRunnerStatus.PAUSED) {
            try {
                if (!this.game.isStarted()) {
                    this.game.startPlay();
                    this.status = GameRunnerStatus.STARTED;
                } else {
                    if (game.isMyTurn()) {
                        game.answer();
                    }
                }
            }catch (RemoteServiceException re){
                this.status = GameRunnerStatus.RETRYABLE_REMOTE_ERROR;
            }

            //Delay 400 ms so user can see the game process
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                this.terminate = true;
                this.status = GameRunnerStatus.TERMINATED;
            }

        }
    }
}
