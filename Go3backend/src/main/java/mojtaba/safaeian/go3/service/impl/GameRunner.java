package mojtaba.safaeian.go3.service.impl;

import mojtaba.safaeian.go3.api.domain.Game;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/15/2017.
 */
public class GameRunner implements Runnable{

    private Game game;
    private boolean terminate = false;

    private Exception terminateException;

    public GameRunner(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (!terminate && !game.isFinished()){
            if(game.isMyTurn()){
                game.answer();
            }

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                this.terminate = true;
            }
        }
    }
}
