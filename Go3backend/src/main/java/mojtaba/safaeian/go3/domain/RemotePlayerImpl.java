package mojtaba.safaeian.go3.domain;

import mojtaba.safaeian.go3.api.domain.Player;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/15/2017.
 */
public class RemotePlayerImpl implements Player {

    @Override
    public Integer generateFirstAnswer() {
        return null;
    }

    @Override
    public void receiveAnswer(Integer answer) {

    }

    @Override
    public int answer() {
        return 0;
    }

    @Override
    public boolean isWin() {
        return false;
    }

    @Override
    public boolean isLose() {
        return false;
    }

    @Override
    public boolean isMyTurn() {
        return false;
    }
}
