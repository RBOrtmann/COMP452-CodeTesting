import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanGuessesGameTest {
    public HumanGuessesGame humanGuessesGame;

    @BeforeEach
    void setup(){
        humanGuessesGame = new HumanGuessesGame();
    }

    @Test
    void testMakeGuessIncrementsNumGuesses(){
        assertEquals(0, humanGuessesGame.getNumGuesses());

        humanGuessesGame.makeGuess(10, 0);

        assertEquals(1, humanGuessesGame.getNumGuesses());
    }

    @Test
    void testMakeGuessReturnsLow(){
        GuessResult result = humanGuessesGame.makeGuess(10, 100);

        assertEquals(GuessResult.LOW, result);
    }

    @Test
    void testMakeGuessReturnsHigh(){
        GuessResult result = humanGuessesGame.makeGuess(100, 10);

        assertEquals(GuessResult.HIGH, result);
    }

    @Test
    void testMakeGuessReturnsCorrect(){
        GuessResult result = humanGuessesGame.makeGuess(10, 10);

        assertEquals(GuessResult.CORRECT, result);
    }
}