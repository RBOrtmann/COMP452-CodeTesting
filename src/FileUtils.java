import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import com.opencsv.CSVWriter;

public class FileUtils {
    
    /**
     * Writes game results to CSV file
     * @param result contains game results
     */
    public static void writeToFile(GameResult result, LocalDateTime now){
        try(CSVWriter writer = new CSVWriter(new FileWriter(StatsFile.FILENAME, true))) {

            String [] record = new String[2];
            record[0] = now.toString();
            record[1] = Integer.toString(result.numGuesses);

            writer.writeNext(record);
        } catch (IOException e) {
            // NOTE: In a full implementation, we would log this error and possibly alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        }
    }
}
