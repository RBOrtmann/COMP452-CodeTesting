import java.util.Random;

/**
 * A game where a human guesses a number between 1 and UPPER_BOUND
 * Tracks the target, the number of guesses made, and if the number has been guessed
 * <p>
 * NOTE: You can refactor and edit this file if needed
 */
public class HumanGuessesGame {
    public static final int UPPER_BOUND = 1000;

    private final int target;
    private int numGuesses;
    private boolean gameIsDone; // true iff makeGuess has been called with the target value

    public HumanGuessesGame() {
        this(new Random());
    }

    public HumanGuessesGame(final Random randGen) {
        this.target = randGen.nextInt(UPPER_BOUND) + 1;
    }

    public GuessResult makeGuess(int value) {
        return makeGuess(value, target);
    }

    public GuessResult makeGuess(int value, int target) {
        numGuesses += 1;

        if (value < target) {
            return GuessResult.LOW;
        } else if (value > target) {
            return GuessResult.HIGH;
        }

        return GuessResult.CORRECT;
    }

    public int getNumGuesses() {
        return numGuesses;
    }

    public boolean isDone() {
        return gameIsDone;
    }
}
