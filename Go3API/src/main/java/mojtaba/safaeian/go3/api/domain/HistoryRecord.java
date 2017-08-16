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

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setHistoryRecordType(HistoryRecordType historyRecordType) {
        this.historyRecordType = historyRecordType;
    }

    public Integer getNumber() {
        return number;
    }

    public HistoryRecordType getHistoryRecordType() {
        return historyRecordType;
    }
}
