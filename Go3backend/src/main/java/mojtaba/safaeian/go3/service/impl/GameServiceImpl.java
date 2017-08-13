package mojtaba.safaeian.go3.service.impl;

import mojtaba.safaeian.go3.api.domain.Game;
import mojtaba.safaeian.go3.api.dto.RemotePlayerDescriptor;
import mojtaba.safaeian.go3.api.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/14/2017.
 */

@Service
public class GameServiceImpl implements GameService{

    private Game runningGame = null;

    @Override
    public Game startNewGame(RemotePlayerDescriptor remotePlayerDescriptor){

        return null;
    }
}
