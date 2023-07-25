package nz.ac.auckland.se281;

public class MediumAi extends Ai {

  // MediumAi implements Random strategy for 3 rounds then implements Top strategy till end of game
  @Override
  public void chooseStrategy(int counter) {
    if (counter <= 3) {
      setStrategy(new RandomStrategy());
    } else {
      setStrategy(new AverageStrategy());
    }
  }
}
