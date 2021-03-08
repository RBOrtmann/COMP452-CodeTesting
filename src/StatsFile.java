import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * File-backed implementation of GameStats
 * <p>
 * Returns the number of games *within the last 30 days* where the person took a given number of guesses
 */
public class StatsFile extends GameStats {
    public static final String FILENAME = "guess-the-number-stats.csv";


    // maps the number of guesses required to the number of games within
    // the past 30 days where the person took that many guesses
    private final SortedMap<Integer, Integer> statsMap = new TreeMap<>();

    public StatsFile(){
        this(LocalDateTime.now().minusDays(30));
    }

    public StatsFile(final LocalDateTime limit) {
        try (CSVReader csvReader = new CSVReader(new FileReader(FILENAME))) {
            for(String[] values : csvReader){
                readValues(limit, values, statsMap);
            }
        } catch (IOException e) {
            // NOTE: In a full implementation, we would log this error and alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        }
    }

    private void readValues(LocalDateTime limit, String[] values, final Map<Integer, Integer> statsMap) {
        // values should have the date and the number of guesses as the two fields
        try {
            LocalDateTime timestamp = LocalDateTime.parse(values[0]);
            int numGuesses = Integer.parseInt(values[1]);

            if (timestamp.isBefore(limit)) {
                statsMap.put(numGuesses, 1 + statsMap.getOrDefault(numGuesses, 0));
            }
        } catch (NumberFormatException | DateTimeParseException nfe) {
            // NOTE: In a full implementation, we would log this error and possibly alert the user
            throw nfe;
        }
    }

    @Override
    public int numGames(int numGuesses) {
        return statsMap.getOrDefault(numGuesses, 0);
    }

    @Override
    public int maxNumGuesses() {
        return statsMap.lastKey();
    }
}
