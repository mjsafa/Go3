package mojtaba.safaeian.go3.service.impl;

import mojtaba.safaeian.go3.api.domain.Game;
import mojtaba.safaeian.go3.api.domain.GameRunnerStatus;
import mojtaba.safaeian.go3.api.service.RemoteServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/15/2017.
 */
public class GameRunner implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(GameRunner.class);

    private Game game;
    private boolean terminate = false;
    private GameRunnerStatus status;
    private SimpMessagingTemplate template;
    private int lastReceivedAnswer;
    private boolean automatic;
    private boolean userRequestedAnswer = false;

    private Exception terminateException;

    public GameRunner(Game game, SimpMessagingTemplate template, boolean automatic) {
        this.game = game;
        this.status = GameRunnerStatus.NOT_STARTED;
        this.template = template;
        this.automatic = automatic;
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
                logger.debug("starting game ... ");
                if(game.getLastReceivedAnswer() != lastReceivedAnswer && game.getLastReceivedAnswer() > 0){
                    lastReceivedAnswer = game.getLastReceivedAnswer();
                    notifyAnswerReceived(lastReceivedAnswer);
                }

                if (!this.game.isStarted()) {
                    this.game.startPlay();
                    setStatus(GameRunnerStatus.STARTED);
                    notifyAnswerSent(game.getHistories().get(0).getNumber());
                } else {
                    if (game.isMyTurn() && (automatic || userRequestedAnswer)) {
                        game.answer();
                        this.userRequestedAnswer = false;
                        notifyAnswerSent(game.getHistories().get(game.getHistories().size() - 1).getNumber());
                    }
                }

                if(status == GameRunnerStatus.RETRYABLE_REMOTE_ERROR && !game.isFinished()){
                    setStatus(GameRunnerStatus.STARTED);
                }
            }catch (RemoteServiceException re){
                logger.error("Problem playing game with remote player: {}", re.getMessage(), re);
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

        if(game.getLastReceivedAnswer() != lastReceivedAnswer && game.getLastReceivedAnswer() > 0){
            lastReceivedAnswer = game.getLastReceivedAnswer();
            notifyAnswerReceived(lastReceivedAnswer);
        }
        setStatus(GameRunnerStatus.TERMINATED);
    }

    public void requestAnswerByUser(){
        this.userRequestedAnswer = true;
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
