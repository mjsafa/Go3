package mojtaba.safaeian.go3.domain;

import mojtaba.safaeian.go3.api.domain.Game;
import mojtaba.safaeian.go3.api.domain.Player;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/14/2017.
 */

@Component
public class GameFactory {

    public Game createGame(Player myPlayer, Player ... competitors){
        Assert.notEmpty(competitors,"Can not create game without any competitors");
        Assert.notNull(myPlayer, "Can not create game without my player");
        return new TwoPlayerGameImpl(myPlayer, competitors[0]);
    }
}
