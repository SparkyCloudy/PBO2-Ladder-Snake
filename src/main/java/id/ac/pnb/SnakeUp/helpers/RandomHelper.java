package id.ac.pnb.SnakeUp.helpers;

import id.ac.pnb.SnakeUp.models.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomHelper {

  public static List<Tile> RandomTile(List<Tile> data, int start, int end,
                                      int interval) {
    if (interval <= 0) {
      interval = 1;
    }

    var numbers = new ArrayList<Tile>();
    for (int i = start; i <= end; i += interval) {
      numbers.add(data.get(i));
    }

    Collections.shuffle(numbers);

    return numbers;
  }
}
