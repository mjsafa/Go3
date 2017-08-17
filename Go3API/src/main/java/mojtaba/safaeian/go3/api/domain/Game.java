package mojtaba.safaeian.go3.api.domain;

import java.util.List;

/**
 * @author Mojtaba Safaeian
 * Created at: 08/14/2017.
 */
public interface Game {

    void startPlay();

    void receiveAnswer(Integer answer);

    void answer();

    boolean isMyTurn();

    boolean isFinished();

    List<HistoryRecord> getHistories();

    boolean isStarted();

    int getLastReceivedAnswer();
}
