package mojtaba.safaeian.go3.api.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/15/2017.
 */
public class AnswerRequest {

    private Integer number;
    private RemotePlayerDescriptor remotePlayerDescriptor;

    public AnswerRequest() {
    }

    public AnswerRequest(Integer number, RemotePlayerDescriptor remotePlayerDescriptor) {
        this.number = number;
        this.remotePlayerDescriptor = remotePlayerDescriptor;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setRemotePlayerDescriptor(RemotePlayerDescriptor remotePlayerDescriptor) {
        this.remotePlayerDescriptor = remotePlayerDescriptor;
    }

    public Integer getNumber() {
        return number;
    }

    public RemotePlayerDescriptor getRemotePlayerDescriptor() {
        return remotePlayerDescriptor;
    }
}
