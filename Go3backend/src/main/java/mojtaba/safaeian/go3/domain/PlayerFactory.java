package mojtaba.safaeian.go3.domain;

import mojtaba.safaeian.go3.api.domain.Player;
import mojtaba.safaeian.go3.api.dto.RemotePlayerDescriptor;
import org.springframework.stereotype.Component;

/**
 * @author Mojtaba Safaeian
 * Created at: 08/14/2017.
 */

@Component
public class PlayerFactory {

    public Player createLocalPlayer() {
        return new LocalPlayerImpl();
    }

    public Player createRemotePlayer(RemotePlayerDescriptor descriptor, RemotePlayerDescriptor localPlayer) {
        return new RemotePlayerImpl(descriptor, localPlayer);
    }
}
