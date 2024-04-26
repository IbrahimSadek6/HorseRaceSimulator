import java.util.concurrent.TimeUnit;
import java.lang.Math;

/**
 * A race simulation where horses compete to reach the finish line.
 * Each horse runs in its own lane for a given distance.
 * Horses are added to the race, and then the race is started.
 * The race progresses until a horse reaches the finish line,
 * and the winner(s) are announced.
 * 
 * @author Ibrahim Sadek
 * @version 2.0
 */


public class Race {
    private int raceLength;
    private Horse lane1Horse;
    private Horse lane2Horse;
    private Horse lane3Horse;
    private double defaultConfidenceIncrement = 0.1;
    private int defaultSleepDuration = 100;
    private int defaultFallProbability = 10;

    public Race(int distance) {
        raceLength = distance;
        lane1Horse = null;
        lane2Horse = null;
        lane3Horse = null;
    }

    public void addHorse(Horse theHorse, int laneNumber) {
        if (laneNumber == 1) {
            lane1Horse = theHorse;
        } else if (laneNumber == 2) {
            lane2Horse = theHorse;
        } else if (laneNumber == 3) {
            lane3Horse = theHorse;
        } else {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
        }
    }

    public void startRace() {
        Horse winner = null;
        boolean allHorsesFallen = false;
        
        lane1Horse.goBackToStart();
        lane2Horse.goBackToStart();
        lane3Horse.goBackToStart();
                      
        while (winner == null && !allHorsesFallen) {
            moveHorse(lane1Horse);
            moveHorse(lane2Horse);
            moveHorse(lane3Horse);
                        
            printRace();
            
            if (lane1Horse.hasFallen() && lane2Horse.hasFallen() && lane3Horse.hasFallen()) {
                allHorsesFallen = true;
            }
            
            if ((winner = findWinner()) != null) {
                updateConfidence(winner);
            } else if (allHorsesFallen) {
                updateConfidenceAfterFall();
            }

            try { 
                TimeUnit.MILLISECONDS.sleep(defaultSleepDuration);
            } catch(Exception e){}
        }

        if (winner != null) {
            System.out.println("The winner is: " + winner.getName());
        } else {
            System.out.println("All horses have fallen. It's a draw!");
        }
    }

    private void moveHorse(Horse theHorse) {
        if (!theHorse.hasFallen() && Math.random() < theHorse.getConfidence()) {
            theHorse.moveForward();
            if (Math.random() * defaultFallProbability < theHorse.getConfidence() * theHorse.getConfidence()) {
                theHorse.fall();
                updateConfidenceAfterFall(theHorse);
            }
        }
    }
        
    private boolean raceWonBy(Horse theHorse) {
        return theHorse.getDistanceTravelled() == raceLength;
    }

    private void printRace() {
        System.out.print('\u000C');
        printLane(lane1Horse);
        printLane(lane2Horse);
        printLane(lane3Horse);
    }
    
    private void printLane(Horse theHorse) {
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
        
        System.out.print('|');
        multiplePrint(' ', spacesBefore);
        
        if (theHorse.hasFallen()) {
            System.out.print('\u2322');
        } else {
            System.out.print(theHorse.getSymbol());
        }
        
        multiplePrint(' ', spacesAfter);
        System.out.println('|');
    }
        
    private void multiplePrint(char aChar, int times) {
        int i = 0;
        while (i < times) {
            System.out.print(aChar);
            i = i + 1;
        }
    }

    private void updateConfidence(Horse theHorse) {
        if (raceWonBy(theHorse)) {
            theHorse.setConfidence(theHorse.getConfidence() + defaultConfidenceIncrement);
        } else {
            theHorse.setConfidence(Math.max(0, theHorse.getConfidence() - defaultConfidenceIncrement));
        }
    }

    private void updateConfidenceAfterFall() {
        lane1Horse.setConfidence(lane1Horse.getConfidence() + defaultConfidenceIncrement);
        lane2Horse.setConfidence(lane2Horse.getConfidence() + defaultConfidenceIncrement);
        lane3Horse.setConfidence(lane3Horse.getConfidence() + defaultConfidenceIncrement);
    }

    private void updateConfidenceAfterFall(Horse theHorse) {
        if (theHorse == lane1Horse) {
            lane2Horse.setConfidence(lane2Horse.getConfidence() + defaultConfidenceIncrement);
            lane3Horse.setConfidence(lane3Horse.getConfidence() + defaultConfidenceIncrement);
        } else if (theHorse == lane2Horse) {
            lane1Horse.setConfidence(lane1Horse.getConfidence() + defaultConfidenceIncrement);
            lane3Horse.setConfidence(lane3Horse.getConfidence() + defaultConfidenceIncrement);
        } else {
            lane1Horse.setConfidence(lane1Horse.getConfidence() + defaultConfidenceIncrement);
            lane2Horse.setConfidence(lane2Horse.getConfidence() + defaultConfidenceIncrement);
        }
    }

    private Horse findWinner() {
        if (raceWonBy(lane1Horse)) {
            return lane1Horse;
        } else if (raceWonBy(lane2Horse)) {
            return lane2Horse;
        } else if (raceWonBy(lane3Horse)) {
            return lane3Horse;
        }
        return null;
    }
}
