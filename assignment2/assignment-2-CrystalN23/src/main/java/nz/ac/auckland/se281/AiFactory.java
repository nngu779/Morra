package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Difficulty;

public class AiFactory {

  // create AI instance depending on chosen difficulty level
  public static Ai createAi(Difficulty difficulty) {
    switch (difficulty) {
      case EASY:
        // create an EasyAi level
        return new EasyAi();
      case MEDIUM:
        // create a MediumAi level
        return new MediumAi();
      case HARD:
        // create a HardAi level
        return new HardAi();
      case MASTER:
        // create a MasterAi level
        return new MasterAi();
      default:
        // invalidate any other input
        MessageCli.INVALID_DIFFICULTY.printMessage();
        System.exit(0);
    }
    return null;
  }
}
