package mojtaba.safaeian.go3.api.dto;

import mojtaba.safaeian.go3.api.domain.HistoryRecord;

import java.util.List;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/14/2017.
 */
public class GameDto {

    private List<HistoryRecord> histories;
    private boolean myTurn;
    private boolean finished;

    public List<HistoryRecord> getHistories() {
        return histories;
    }

    public void setHistories(List<HistoryRecord> histories) {
        this.histories = histories;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
