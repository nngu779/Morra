package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nz.ac.auckland.se281.Main.Difficulty;

public class Morra {

  private String playerName;
  private int counter;
  private Ai jarvis;
  private int fingersHuman;
  private int sumHuman;
  private int correctSum;
  private int pointsToWin;
  private int fingersAi;
  private int sumAi;
  private List<Integer> humanFingersCount;
  private int pointsAi = 0;
  private int pointsHuman = 0;
  private boolean gameStart = false;

  public Morra() {}

  public void newGame(Difficulty difficulty, int pointsToWin, String[] options) {
    MessageCli.WELCOME_PLAYER.printMessage(options[0]);

    // Save playerName and pointsToWin
    playerName = options[0];
    this.pointsToWin = pointsToWin;

    // Create a new AI instance when a new game is invoked
    jarvis = AiFactory.createAi(difficulty);

    // reset counter and fingersHuman
    counter = 1;
    fingersHuman = 0;

    // Initialise a new arrayList to count the number of time a particular finger number is played
    humanFingersCount = new ArrayList<Integer>(Collections.nCopies(5, 0));

    // Initialise that a game started once the newGame command is invoked
    gameStart = true;

    // Reset pointsHuman & pointsAi
    pointsHuman = 0;
    pointsAi = 0;
  }

  public void play() {

    // Print out error message if command Play is invoked before a game starts
    if (gameStart == false) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    // Initialise inputValidity
    boolean inputValidity = false;

    // Print Start_Round and Ask_Input messages
    MessageCli.START_ROUND.printMessage(Integer.toString(counter));
    MessageCli.ASK_INPUT.printMessage();

    // Get Jarvis's play
    jarvis.chooseStrategy(counter);
    fingersAi = jarvis.getFingersAi();
    sumAi = jarvis.getSumAi(humanFingersCount);

    while (inputValidity == false) {

      String input = Utils.scanner.nextLine();
      // Split by whitespace to get input fingers and sum
      String[] inputParts = input.split(" ");

      // Utilise try & catch exception for number formatting given in Utils.java file
      // User has to give finger number between 1 and 5 (inclusive), and sum between 1
      // and 10 (inclusive)
      if (inputParts.length == 2
          && Utils.isInteger(inputParts[0]) == true
          && Utils.isInteger(inputParts[1]) == true
          && Integer.parseInt(inputParts[0]) >= 1
          && Integer.parseInt(inputParts[0]) <= 5
          && Integer.parseInt(inputParts[1]) >= 1
          && Integer.parseInt(inputParts[1]) <= 10) {
        MessageCli.PRINT_INFO_HAND.printMessage(playerName, inputParts[0], inputParts[1]);
        // Increment index for Start_Round after a NEW_GAME is invoked
        counter++;
        fingersHuman = Integer.parseInt(inputParts[0]);
        sumHuman = Integer.parseInt(inputParts[1]);
        inputValidity = true;

        // Count how many times a finger shows up and save that in an arraylist
        humanFingersCount.set(fingersHuman - 1, humanFingersCount.get(fingersHuman - 1) + 1);

      } else {
        MessageCli.INVALID_INPUT.printMessage();
        MessageCli.ASK_INPUT.printMessage();
      }
    }

    // Print Jarvis's hand
    MessageCli.PRINT_INFO_HAND.printMessage(
        "Jarvis", Integer.toString(fingersAi), Integer.toString(sumAi));

    // Print round outcome
    correctSum = fingersAi + fingersHuman;
    // If both humans and AI are correct or both wrong then it's a draw, no points will be assigned
    if ((sumAi != correctSum & sumHuman != correctSum)
        || (sumAi == correctSum & sumHuman == correctSum)) {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
    } else if (sumAi == correctSum) {
      // Assign point to AI if AI is correct
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
      pointsAi++;
    } else if (sumHuman == correctSum) {
      // Assign point to human if human is correct
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
      pointsHuman++;
    }

    // Implement printing for end game
    if (pointsAi == pointsToWin) {
      // Print Jarvis winning
      MessageCli.END_GAME.printMessage("Jarvis", Integer.toString(counter - 1));
      gameStart = false;
    } else if (pointsHuman == pointsToWin) {
      // Print Human winning
      MessageCli.END_GAME.printMessage(playerName, Integer.toString(counter - 1));
      gameStart = false;
    }
  }

  public void showStats() {
    if (gameStart == true) {
      // Print statistics of a current game
      MessageCli.PRINT_PLAYER_WINS.printMessage(
          playerName, Integer.toString(pointsHuman), Integer.toString(pointsToWin - pointsHuman));
      MessageCli.PRINT_PLAYER_WINS.printMessage(
          "Jarvis", Integer.toString(pointsAi), Integer.toString(pointsToWin - pointsAi));
    } else {
      MessageCli.GAME_NOT_STARTED.printMessage();
    }
  }
}
