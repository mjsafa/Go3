package mojtaba.safaeian.go3.api.domain;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/14/2017.
 */
public class HistoryRecord {

    public enum HistoryRecordType{
        SENT, RECEIVED;
    }

    private Integer number;
    private HistoryRecordType historyRecordType;

    public HistoryRecord(Integer number, HistoryRecordType historyRecordType) {
        this.number = number;
        this.historyRecordType = historyRecordType;
    }
}
