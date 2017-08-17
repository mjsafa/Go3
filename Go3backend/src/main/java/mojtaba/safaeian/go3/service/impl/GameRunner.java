package mojtaba.safaeian.go3.service.impl;

import mojtaba.safaeian.go3.api.domain.Game;
import mojtaba.safaeian.go3.api.domain.GameRunnerStatus;
import mojtaba.safaeian.go3.api.service.RemoteServiceException;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/15/2017.
 */
public class GameRunner implements Runnable {




    private Game game;
    private boolean terminate = false;
    private GameRunnerStatus status;
    private SimpMessagingTemplate template;
    private int lastReceivedAnswer;

    private Exception terminateException;

    public GameRunner(Game game, SimpMessagingTemplate template) {
        this.game = game;
        this.status = GameRunnerStatus.NOT_STARTED;
        this.template = template;
    }

    public GameRunnerStatus getStatus() {
        return status;
    }

    public void pause(){
        setStatus(GameRunnerStatus.PAUSED);
    }

    @Override
    public void run() {

        while (!terminate && !game.isFinished() && status != GameRunnerStatus.PAUSED) {
            try {

                if(game.getLastReceivedAnswer() != lastReceivedAnswer && game.getLastReceivedAnswer() > 0){
                    lastReceivedAnswer = game.getLastReceivedAnswer();
                    notifyAnswerReceived(lastReceivedAnswer);
                }

                if (!this.game.isStarted()) {
                    this.game.startPlay();
                    setStatus(GameRunnerStatus.STARTED);
                } else {
                    if (game.isMyTurn()) {
                        game.answer();
                        notifyAnswerSent(game.getHistories().get(game.getHistories().size() - 1).getNumber());
                    }
                }
            }catch (RemoteServiceException re){
                setStatus(GameRunnerStatus.RETRYABLE_REMOTE_ERROR);
            }

            //Delay 400 ms so user can see the game process
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                this.terminate = true;
                setStatus(GameRunnerStatus.TERMINATED);
            }

        }
    }

    private void setStatus(GameRunnerStatus status){
        this.status = status;
        notifyStatus(status);
    }

    private void notifyStatus(GameRunnerStatus status){
        this.template.convertAndSend("/topic/game/status", status);
    }

    private void notifyAnswerReceived(int answer){
        this.template.convertAndSend("/topic/game/answers/received", answer);
    }

    private void notifyAnswerSent(int answer){
        this.template.convertAndSend("/topic/game/answers/sent", answer);
    }
}
