package data;

import java.util.ArrayList;
import java.util.List;

public interface Board {
    int getNoDimensions();

    List<Integer> getSizes();

    int getCellAt(List<Integer> indices);

    void setCellAt(List<Integer> indices, Integer value);

    String toString();
}
