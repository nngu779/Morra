package nz.ac.auckland.se281;

import java.util.List;

public class AverageStrategy implements Strategy {

  protected int fingersAi;
  protected int sumAi;

  @Override
  public int getFingersAi() {
    fingersAi = Utils.getRandomNumber(1, 5);
    return fingersAi;
  }

  @Override
  public int getSumAi(List<Integer> humanFingersCount) {
    int totalHumanFingers = 0;
    int count = 0;

    for (int i = 0; i < 5; i++) {
      // count is the number of rounds played previously
      count += humanFingersCount.get(i);
      // value of totalHumanFingers is calculated based on how many times (value of
      // humanFingersCount at index i) a particular number is played (i+1)
      totalHumanFingers += humanFingersCount.get(i) * (i + 1);
    }

    sumAi = fingersAi + Math.round((float) totalHumanFingers / count);
    return sumAi;
  }
}
