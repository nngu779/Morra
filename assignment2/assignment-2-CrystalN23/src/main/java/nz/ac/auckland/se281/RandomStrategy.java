package nz.ac.auckland.se281;

import java.util.List;

public class RandomStrategy implements Strategy {

  protected int fingersAi;
  protected int sumAi;

  @Override
  public int getFingersAi() {
    fingersAi = Utils.getRandomNumber(1, 5);
    return fingersAi;
  }

  @Override
  public int getSumAi(List<Integer> humanFingersCount) {
    sumAi = fingersAi + Utils.getRandomNumber(1, 5);
    return sumAi;
  }
}
