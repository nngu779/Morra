package nz.ac.auckland.se281;

import java.util.List;

public class TopStrategy implements Strategy {

  protected int fingersAi;
  protected int sumAi;

  @Override
  public int getFingersAi() {
    fingersAi = Utils.getRandomNumber(1, 5);
    return fingersAi;
  }

  @Override
  public int getSumAi(List<Integer> humanFingersCount) {
    int top = 0;
    int max = 0;
    // find maximum number in arraylist indicating the maximum number of times that a particular
    // finger was played
    for (int i = 0; i < 5; i++) {
      if (max < humanFingersCount.get(i)) {
        max = humanFingersCount.get(i);
        // index of max + 1 is the most played finger
        top = i + 1;
      }
    }
    sumAi = fingersAi + top;
    return sumAi;
  }
}
