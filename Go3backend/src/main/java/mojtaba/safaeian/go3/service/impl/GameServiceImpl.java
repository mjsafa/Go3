package mojtaba.safaeian.go3.service.impl;

import mojtaba.safaeian.go3.api.domain.Game;
import mojtaba.safaeian.go3.api.domain.Player;
import mojtaba.safaeian.go3.api.dto.Answer;
import mojtaba.safaeian.go3.api.dto.GameDto;
import mojtaba.safaeian.go3.api.dto.RemotePlayerDescriptor;
import mojtaba.safaeian.go3.api.service.GameService;
import mojtaba.safaeian.go3.domain.GameFactory;
import mojtaba.safaeian.go3.domain.LocalPlayerImpl;
import mojtaba.safaeian.go3.domain.PlayerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/14/2017.
 */

@Service
public class GameServiceImpl implements GameService{

    private final GameFactory gameFactory;
    private final PlayerFactory playerFactory;
    private final ModelMapper modelMapper;

    private Game runningGame = null;
    private GameRunner gameRunner;

    @Autowired
    public GameServiceImpl(GameFactory gameFactory, PlayerFactory playerFactory, ModelMapper modelMapper) {
        this.gameFactory = gameFactory;
        this.playerFactory = playerFactory;
        this.modelMapper = modelMapper;
    }

    @Override
    public Game startNewGame(Answer answer, RemotePlayerDescriptor remotePlayerDescriptor){
        Player myPlayer = new LocalPlayerImpl();
        Player remotePlayer = playerFactory.createRemotePlayer(remotePlayerDescriptor);
        Game game = gameFactory.createGame(myPlayer, remotePlayer);
        if(answer != null && answer.getNumber() > 0){
            game.receiveAnswer(answer.getNumber());
        }

        //Start running game
        this.gameRunner = new GameRunner(game);
        new Thread(gameRunner).start();
        return game;
    }

    @Override
    public void addAnswer(Answer answer){
        this.runningGame.receiveAnswer(answer.getNumber());
    }
}
