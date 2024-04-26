
/**
 * Write a description of class Horse here.
 * 1. The Horse objects represent the horses in the race.
2. Each horse has a name and a symbol (1 character). The symbol is used for display during the 
race, while the name is used to announce the winner.
3. During the race, a horse may fall, resulting in its elimination from the race.
4. Horses are assigned a confidence rating ranging from 0 to 1. A higher confidence rating 
indicates faster running speed, but also increases the likelihood of falling.
5. Winning a race slightly increases a horse's confidence, while falling during a race slightly 
decreases it
 * @author Ibrahim Sadek 
 * @version 1
 */

 public class Horse {
    
	private final String name;
    private char symbol;
    private int distanceTravelled;
    private boolean hasFallen;
    private double confidence;
    
    public static void main(String[] args) {
        // Create instances of Horse
        Horse horse1 = new Horse('A', "APOLLO", 0.8);
        Horse horse2 = new Horse('B', "SHADOW", 0.6);

        // Display information about the horses
        System.out.println("Horse 1: " + horse1.getName() + ", Symbol: " + horse1.getSymbol() +
                ", Confidence: " + horse1.getConfidence() + ", Distance Travelled: " + horse1.getDistanceTravelled());
        System.out.println("Horse 2: " + horse2.getName() + ", Symbol: " + horse2.getSymbol() +
                ", Confidence: " + horse2.getConfidence() + ", Distance Travelled: " + horse2.getDistanceTravelled());

        // Make the horses move forward
        horse1.moveForward();
        horse2.moveForward();

        // Display updated distance travelled
        System.out.println("Horse 1 distance travelled: " + horse1.getDistanceTravelled());
        System.out.println("Horse 2 distance travelled: " + horse2.getDistanceTravelled());

        // Horse 1 Falls
        horse1.fall();

        // Check if a horse has fallen
        System.out.println("Horse 1 has fallen: " + horse1.hasFallen());
        System.out.println("Horse 2 has fallen: " + horse2.hasFallen());
    }
    
    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        symbol = horseSymbol;
        name = horseName;
        confidence = Math.max(0, Math.min(1, horseConfidence));
        distanceTravelled = 0;
        hasFallen = false;
    }

    public void fall() {
        hasFallen = true;
    }

    public double getConfidence() {
        return confidence;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void goBackToStart() {
        distanceTravelled = 0;
        hasFallen = false;
    }

    public boolean hasFallen() {
        return hasFallen;
    }

    public void moveForward() {
        distanceTravelled++;
    }

    public void setConfidence(double newConfidence) {
        confidence = Math.max(0, Math.min(1, newConfidence));
    }

    public void setSymbol(char newSymbol) {
        symbol = newSymbol;
    }

    
}
