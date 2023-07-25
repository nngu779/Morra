package nz.ac.auckland.se281;

public class MasterAi extends Ai {

  // MasterAi implements Random strategy for 3 rounds then alternates between Average and Top
  // strategies
  @Override
  public void chooseStrategy(int counter) {
    if (counter <= 3) {
      setStrategy(new RandomStrategy());
    } else {
      if (counter % 2 == 0) {
        // set Average strategy for even rounds after 3rd round
        setStrategy(new AverageStrategy());
      } else {
        // set Top strategy for odd rounds after 3rd round
        setStrategy(new TopStrategy());
      }
    }
  }
}
