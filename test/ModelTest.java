import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    public Model model;

    @BeforeEach
    void setup() {
        model = new Model();
    }

    @Test
    void testBinGames() {
        TestStats stats = new TestStats();
        int[] bins = {1, 5, 11};

        int[] result = model.binGames(stats, bins);

        assertEquals(3, result.length);
        assertEquals(10, result[0]);
        assertEquals(45, result[1]);
        assertEquals(0, result[2]);
    }

    @Test
    void testBinGamesNoBins() {
        TestStats stats = new TestStats();
        int[] bins = {};

        int[] result = model.binGames(stats, bins);

        assertEquals(0, result.length);
    }

    @Test
    void testSumGames() {
        TestStats stats = new TestStats();

        int result = model.sumGames(1, 5, stats);

        assertEquals(10, result);

        result = model.sumGames(8, 9, stats);

        assertEquals(8, result);
    }

    @Test
    void testSumGamesBackwardsBounds() {
        TestStats stats = new TestStats();

        int result = model.sumGames(2, 1, stats);

        assertEquals(0, result);
    }

    @Test
    void testSumGamesSameBounds() {
        TestStats stats = new TestStats();

        int result = model.sumGames(3, 3, stats);

        assertEquals(0, result);
    }

    @Test
    void testSumGamesOutsideOfMapRange() {
        TestStats stats = new TestStats();

        int result = model.sumGames(100, 110
                , stats);

        assertEquals(0, result);
    }

    class TestStats extends GameStats {

        private SortedMap<Integer, Integer> statsMap = new TreeMap<>();

        public TestStats() {
            for (int i = 1; i <= 10; i++) {
                statsMap.put(i, i);
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
}