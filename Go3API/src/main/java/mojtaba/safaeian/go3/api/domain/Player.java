package mojtaba.safaeian.go3.api.domain;

/**
 * @author Mojtaba Safaeian
 * Created at: 08/14/2017.
 */
public interface Player {

    Integer generateFirstAnswer();

    void receiveAnswer(Integer answer);

    int answer();

    boolean isWin();

    boolean isLose();

    boolean isMyTurn();
}
