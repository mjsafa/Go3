package mojtaba.safaeian.go3.api.dto;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/14/2017.
 */
public class RemotePlayerDescriptor {

    private String host;
    private String port;

    public RemotePlayerDescriptor(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getHttpUrl(){
        return "http://" + this.host +
                (StringUtils.isNotBlank(port) ? ":" + port : "") ;
    }
}
