import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RacingGUI extends JFrame {

    private Race currentRace; // Keeps track of the ongoing race
    private BettingAlgorithm bettingAlgorithm; // Manages the betting odds

    public RacingGUI() {
        setTitle("Welcome to Racing Time!"); // Set the title of our app
        setSize(300, 200); // Set the size of our window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the window when we're done

        bettingAlgorithm = new BettingAlgorithm(); // Get our betting system ready

        JPanel panel = new JPanel(); // Create a panel to hold our buttons
        panel.setLayout(new GridLayout(2, 1)); // Arrange the buttons in a grid

        JButton startRaceButton = new JButton("Start the Race!"); // Button to start the race
        JButton viewBettingOddsButton = new JButton("View Betting Odds"); // Button to see the betting odds

        // When the "Start the Race!" button is clicked...
        startRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentRace = createRace(); // Create a new race
                currentRace.startRace(); // Race starts
            }
        });

        // When the "View Betting Odds" button is clicked
        viewBettingOddsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentRace != null) {
                    showBettingOddsWindow(); // Display the betting odds if a race is active
                } else {
                    showErrorDialog("No active race. Start one first!"); // Show error if no race is active
                }
            }
        });

        panel.add(startRaceButton); // Add the "Start the Race!" button to the panel
        panel.add(viewBettingOddsButton); // Add the "View Betting Odds" button to the panel

        add(panel); // Add the panel to the window
        setVisible(true); // Make the window visible
    }

    // Create a new race with predefined horses
    private Race createRace() {
        Race race = new Race(1000); // Set up a 1000-meter race track
        // Add some exciting horses to the race (customization can be added)
        Horse horse1 = new Horse('A', "APOLLO", 0.8);
        Horse horse2 = new Horse('B', "SHADOW", 0.6);
        Horse horse3 = new Horse('C', "TWIZZLE",  0.7);
        race.addHorse(horse1);
        race.addHorse(horse2);
        race.addHorse(horse3);
        return race; // Return the exciting new race
    }

    // Display the betting odds window
    private void showBettingOddsWindow() {
        JFrame oddsFrame = new JFrame("Betting Odds"); // Create a window for the betting odds
        oddsFrame.setSize(300, 200); // Set the size of the window
        oddsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the window when we're done

        JPanel panel = new JPanel(); // Create a panel to hold the betting odds
        panel.setLayout(new GridLayout(4, 1)); // Arrange the odds in a grid

        // Calculate the odds for each horse using our special algorithm
        double[] oddsArray = bettingAlgorithm.calculateOdds(currentRace);

        // Display the odds for each horse
        for (int i = 0; i < oddsArray.length; i++) {
            JLabel oddsLabel = new JLabel("Horse " + (i + 1) + " Odds: " + oddsArray[i]);
            panel.add(oddsLabel); // Add the odds to the panel
        }

        JTextField betAmountField = new JTextField(); // Field to enter the bet amount
        betAmountField.setPreferredSize(new Dimension(100, 30)); // Set the size of the field

        JButton placeBetButton = new JButton("Place Your Bet!"); // Button to place a bet
        placeBetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double betAmount = Double.parseDouble(betAmountField.getText()); // Gets the bet amount
                System.out.println("You placed a bet of $" + betAmount + " on Horse 1"); // Displays the bet details
            }
        });

        panel.add(betAmountField); // Adds the bet amount field to the panel
        panel.add(placeBetButton); // Adds the "Place Your Bet!" button to the panel

        oddsFrame.add(panel); // Adsd the panel to the betting odds window
        oddsFrame.setVisible(true); // Makes the betting odds window visible
    }

    // Show an error message dialog
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Oops, Error!", JOptionPane.ERROR_MESSAGE);
    }

    // Main method to launch the RacingGUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RacingGUI(); // Start the racing adventure!
            }
        });
    }
}
