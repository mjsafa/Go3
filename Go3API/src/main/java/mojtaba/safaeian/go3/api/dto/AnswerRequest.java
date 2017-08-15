package mojtaba.safaeian.go3.api.dto;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/15/2017.
 */
public class AnswerRequest {

    private int number;
    private RemotePlayerDescriptor remotePlayerDescriptor;

    public AnswerRequest(int number, RemotePlayerDescriptor remotePlayerDescriptor) {
        this.number = number;
        this.remotePlayerDescriptor = remotePlayerDescriptor;
    }

    public int getNumber() {
        return number;
    }

    public RemotePlayerDescriptor getRemotePlayerDescriptor() {
        return remotePlayerDescriptor;
    }
}
