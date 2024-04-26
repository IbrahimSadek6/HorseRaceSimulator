Betting Algorithm
import java.util.Random;

public class BettingAlgorithm {

    // Calculate the odds for each horse based on their performance
    public double[] calculateOdds(Race race) {
        // Get the number of horses in the race
        int numHorses = race.getNumberOfHorses();
        double[] oddsArray = new double[numHorses];

        // Calculate the total confidence of all horses
        double totalConfidence = 0.0;
        for (int i = 0; i < numHorses; i++) {
            Horse horse = race.getHorseAtLane(i);
            totalConfidence += horse.getConfidence();
        }

        // Calculate the odds ratio for each horse
        for (int i = 0; i < numHorses; i++) {
            Horse horse = race.getHorseAtLane(i);
            double oddsRatio = (horse.getConfidence() / totalConfidence) * 100;
            oddsArray[i] = oddsRatio;
        }

        return oddsArray;
    }

    // Simulate the betting process and return the winner based on odds
    public int simulateBetting(double[] odds) {
        Random random = new Random();
        double totalOdds = 0.0;

        // Calculate the total odds
        for (double odd : odds) {
            totalOdds += odd;
        }

        // Generate a random number between 0 and totalOdds
        double randomNumber = random.nextDouble() * totalOdds;

        // Find the winning horse based on the random number
        double cumulativeOdds = 0.0;
        for (int i = 0; i < odds.length; i++) {
            cumulativeOdds += odds[i];
            if (randomNumber <= cumulativeOdds) {
                return i + 1; // Return the winning horse's lane number (1-indexed)
            }
        }

        // If no winner is found, return -1
        return -1;
    }
}
