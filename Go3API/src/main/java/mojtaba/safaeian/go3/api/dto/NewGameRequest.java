package mojtaba.safaeian.go3.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/15/2017.
 */
public class NewGameRequest {

    private int firstNumber;
    private RemotePlayerDescriptor remotePlayerDescriptor;

    public NewGameRequest(@JsonProperty("firstNumber") int firstNumber, @JsonProperty("remotePlayerDescriptor") RemotePlayerDescriptor remotePlayerDescriptor) {
        this.firstNumber = firstNumber;
        this.remotePlayerDescriptor = remotePlayerDescriptor;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public RemotePlayerDescriptor getRemotePlayerDescriptor() {
        return remotePlayerDescriptor;
    }
}
