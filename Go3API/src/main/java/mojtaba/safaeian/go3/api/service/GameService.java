package mojtaba.safaeian.go3.api.service;

import mojtaba.safaeian.go3.api.domain.Game;
import mojtaba.safaeian.go3.api.dto.Answer;
import mojtaba.safaeian.go3.api.dto.RemotePlayerDescriptor;

/**
 * @author Mojtaba Safaeian
 * Created at: 08/14/2017.
 */
public interface GameService {

    Game startNewGame(Answer answer, RemotePlayerDescriptor remotePlayerDescriptor);

    void addAnswer(Answer answer);
}
