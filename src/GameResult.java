import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Data class to hold the result of a game
 * NOTE: You can refactor and edit this file if needed
 */
public class GameResult {
    public final boolean humanWasPlaying;
    public final int correctValue;
    public final int numGuesses;

    public GameResult(boolean humanWasPlaying, int correctValue, int numGuesses){
        this.humanWasPlaying = humanWasPlaying;
        this.correctValue = correctValue;
        this.numGuesses = numGuesses;
    }

    /**
     * Writes game results to CSV file
     */
    public void writeToFile(LocalDateTime now){
        try(CSVWriter writer = new CSVWriter(new FileWriter(StatsFile.FILENAME, true))) {

            String [] record = new String[2];
            record[0] = now.toString();
            record[1] = Integer.toString(numGuesses);

            writer.writeNext(record);
        } catch (IOException e) {
            // NOTE: In a full implementation, we would log this error and possibly alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        }
    }
}
