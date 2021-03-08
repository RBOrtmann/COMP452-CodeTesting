import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class StatsFileTest {
    private StatsFile statsFile;

    @BeforeEach
    void setup() {
        statsFile = new StatsFile();
    }

    @Test
    void readValueThrowNullPointerException() {
        SortedMap<Integer, Integer> statsMap = new TreeMap<>();
        LocalDateTime now = LocalDateTime.now();

        assertThrows(NullPointerException.class, () -> statsFile.readValues(now, new String[2], statsMap));
        assertTrue(statsMap.isEmpty());
    }

    @Test
    void readValueThrowsNumberFormatException() {
        SortedMap<Integer, Integer> statsMap = new TreeMap<>();
        LocalDateTime now = LocalDateTime.now();
        String[] values = {"2020-02-24T20:10:29.110278500", "3askldf"};

        assertThrows(NumberFormatException.class, () -> statsFile.readValues(now, values, statsMap));
        assertTrue(statsMap.isEmpty());
    }

    @Test
    void readValueThrowsDateTimeParseException() {
        SortedMap<Integer, Integer> statsMap = new TreeMap<>();
        LocalDateTime now = LocalDateTime.now();
        String[] values = {"AASDFDNDFASgh", "3"};

        assertThrows(DateTimeParseException.class, () -> statsFile.readValues(now, values, statsMap));
        assertTrue(statsMap.isEmpty());
    }

    @Test
    void readValueParsesValuesBeforeTime() {
        SortedMap<Integer, Integer> statsMap = new TreeMap<>();
        LocalDateTime limit = LocalDateTime.parse("2021-02-25T09:42:00.138251300");

        String[] values = {"2020-02-24T20:10:29.110278500", "3"};
        String[] values2 = {"2020-02-24T21:06:40.860330300","10"};

        statsFile.readValues(limit, values, statsMap);
        statsFile.readValues(limit, values2, statsMap);

        assertEquals(2, statsMap.size());
        assertEquals(3, statsMap.firstKey());
        assertEquals(10, statsMap.lastKey());
        assertEquals(1, statsMap.get(3));
        assertEquals(1, statsMap.get(10));
    }

    @Test
    void readValueRemovesValuesAfterTime(){
        SortedMap<Integer, Integer> statsMap = new TreeMap<>();
        LocalDateTime limit = LocalDateTime.parse("2020-02-24T21:06:40.860330300");

        String[] values = {"2020-02-24T22:10:29.110278500", "3"};
        String[] values2 = {"2021-02-25T09:42:00.138251300","10"};

        statsFile.readValues(limit, values, statsMap);
        statsFile.readValues(limit, values2, statsMap);

        assertTrue(statsMap.isEmpty());
    }

    @Test
    void readValueRemovesValuesAtSameTimeAsLimit() {
        SortedMap<Integer, Integer> statsMap = new TreeMap<>();
        LocalDateTime limit = LocalDateTime.parse("2020-02-24T22:10:29.110278500");

        String[] values = {"2020-02-24T22:10:29.110278500", "3"};

        statsFile.readValues(limit, values, statsMap);

        assertTrue(statsMap.isEmpty());
    }
}