package nz.ac.auckland.se281;

import java.util.List;

public interface Strategy {

  public abstract int getFingersAi();

  public abstract int getSumAi(List<Integer> humanFingersCount);
}
