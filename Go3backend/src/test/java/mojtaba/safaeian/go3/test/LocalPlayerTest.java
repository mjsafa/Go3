package mojtaba.safaeian.go3.test;

import mojtaba.safaeian.go3.api.domain.Player;
import mojtaba.safaeian.go3.domain.LocalPlayerImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/15/2017.
 */
public class LocalPlayerTest {

    Player player;

    @Before
    public void initPlayer() {
        player = new LocalPlayerImpl();
    }

    @Test
    public void testGenerateFirstAnswer() {
        Integer firstAnswer = player.generateFirstAnswer();
        assertThat(firstAnswer, is(greaterThan(1)));
        assertThat(player.isMyTurn(), is(equalTo(false)));
    }

    @Test
    public void testStateAndAnswerAfterReceiveAnswer() {
        player.receiveAnswer(10);
        assertThat(player.isMyTurn(), is(equalTo(true)));
        int answer = player.answer();
        assertThat(answer, is(equalTo(3)));
        assertThat(player.isMyTurn(), is(equalTo(false)));
        assertThat(player.isWin(), is(equalTo(false)));
        assertThat(player.isLose(), is(equalTo(false)));
    }

    @Test
    public void testWinIfReceive2() {
        player.receiveAnswer(2);
        int answer = player.answer();
        assertThat(answer, is(equalTo(1)));
        assertThat(player.isWin(), is(equalTo(true)));
        assertThat(player.isLose(), is(equalTo(false)));
    }
}
