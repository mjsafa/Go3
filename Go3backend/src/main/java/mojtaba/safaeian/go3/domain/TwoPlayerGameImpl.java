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
public class TwoPlayerGameImpl implements Game{

    private List<HistoryRecord> answers;
    private Player myPlayer;
    private Player competitorPlayer;

    public TwoPlayerGameImpl(Player myPlayer, Player competitorPlayer) {
        this.myPlayer = myPlayer;
        this.competitorPlayer = competitorPlayer;
        this.answers = new ArrayList<>();
    }

    public void startPlay(){
        Integer firstAnswer = myPlayer.generateFirstAnswer();
        competitorPlayer.receiveAnswer(firstAnswer);
        this.answers.add(new HistoryRecord(firstAnswer, HistoryRecord.HistoryRecordType.SENT));
    }

    public void receiveAnswer(Integer answer){
        this.answers.add(new HistoryRecord(answer, HistoryRecord.HistoryRecordType.RECEIVED));
        this.myPlayer.receiveAnswer(answer);
    }

    public boolean isFinished(){
        return this.myPlayer.isWin() || this.myPlayer.isLose();
    }
}
