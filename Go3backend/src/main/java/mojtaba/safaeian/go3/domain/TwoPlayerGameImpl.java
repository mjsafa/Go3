package mojtaba.safaeian.go3.domain;

import mojtaba.safaeian.go3.api.domain.Game;
import mojtaba.safaeian.go3.api.domain.HistoryRecord;
import mojtaba.safaeian.go3.api.domain.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/14/2017.
 */
public class TwoPlayerGameImpl implements Game {

    private List<HistoryRecord> answers;
    private Player myPlayer;
    private Player competitorPlayer;
    private boolean isStarted = false;
    private int lastAnswer = 0;

    public TwoPlayerGameImpl(Player myPlayer, Player competitorPlayer) {
        this.myPlayer = myPlayer;
        this.competitorPlayer = competitorPlayer;
        this.answers = new ArrayList<HistoryRecord>();
    }

    @Override
    public void startPlay() {
        Integer firstAnswer = myPlayer.generateFirstAnswer();
        competitorPlayer.receiveAnswer(firstAnswer);
        this.answers.add(new HistoryRecord(firstAnswer, HistoryRecord.HistoryRecordType.SENT));
        this.isStarted = true;
    }

    @Override
    public void receiveAnswer(Integer answer) {
        this.answers.add(new HistoryRecord(answer, HistoryRecord.HistoryRecordType.RECEIVED));
        this.myPlayer.receiveAnswer(answer);
        this.lastAnswer = answer;
    }

    @Override
    public void answer(){
        int answer = this.myPlayer.answer();
        this.answers.add(new HistoryRecord(answer, HistoryRecord.HistoryRecordType.SENT));
        this.competitorPlayer.receiveAnswer(answer);
    }

    @Override
    public boolean isMyTurn() {
        return this.myPlayer.isMyTurn();
    }

    @Override
    public boolean isFinished() {
        return this.myPlayer.isWin() || this.myPlayer.isLose();
    }

    @Override
    public List<HistoryRecord> getHistories() {
        return answers;
    }

    @Override
    public boolean isStarted(){
        return this.isStarted;
    }

    @Override
    public int getLastReceivedAnswer(){
        return this.lastAnswer;
    }
}
