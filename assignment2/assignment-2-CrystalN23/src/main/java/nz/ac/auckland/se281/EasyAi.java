package nz.ac.auckland.se281;

public class EasyAi extends Ai {

  // EasyAi implements Random strategy till end of game
  @Override
  public void chooseStrategy(int counter) {
    setStrategy(new RandomStrategy());
  }
}
