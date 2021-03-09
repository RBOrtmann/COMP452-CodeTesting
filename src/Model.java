public class Model {
    private GameStats stats = new StatsFile();

    public int[] binGames(final int[] bins){
        return binGames(stats, bins);
    }

    public int[] binGames(final GameStats stats, final int[] bins) {
        int[] results = new int[bins.length];

        for (int binIndex = 0; binIndex < bins.length; binIndex++) {
            // Sum all the results from lowerBound on up
            results[binIndex] = sumGames(
                    bins[binIndex],
                    binIndex == bins.length - 1 ? stats.maxNumGuesses() : bins[binIndex + 1],
                    stats
            );
        }

        return results;
    }

    public int sumGames(final int lowerBound, final int upperBound, final GameStats stats) {
        int result = 0;

        for (int numGuesses = lowerBound; numGuesses < upperBound; numGuesses++) {
            result += stats.numGames(numGuesses);
        }

        return result;
    }

    public int setUpperBound(int upperBound, int lastGuess){
        return Math.min(upperBound, lastGuess);
    }

    public int setLastGuess(int lowerBound, int upperBound){
        return (lowerBound + upperBound + 1) / 2;
    }

    public int setLowerBound(int lowerBound, int lastGuess){
        return Math.max(lowerBound, lastGuess + 1);
    }
}
