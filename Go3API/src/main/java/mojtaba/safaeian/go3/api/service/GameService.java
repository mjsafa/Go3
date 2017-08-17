package mojtaba.safaeian.go3.api.service;

import mojtaba.safaeian.go3.api.domain.Game;
import mojtaba.safaeian.go3.api.domain.GameRunnerStatus;
import mojtaba.safaeian.go3.api.dto.Answer;
import mojtaba.safaeian.go3.api.dto.AnswerRequest;
import mojtaba.safaeian.go3.api.dto.RemotePlayerDescriptor;

/**
 * @author Mojtaba Safaeian
 * Created at: 08/14/2017.
 */
public interface GameService {

    Game startNewGame(Answer answer, RemotePlayerDescriptor remotePlayerDescriptor, boolean automatic);

    void addAnswer(AnswerRequest answerRequest);

    GameRunnerStatus getStatus();

    Game getRunningGame();

    void requestAnswerByUser();
}
