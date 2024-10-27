package web.data;

import java.util.List;

public class MoveRequest {
    private List<Integer> coordinate;

    private int player;

    public int getPlayer() {
        return player;
    }

    public List<Integer> getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(List<Integer> coordinate) {
        this.coordinate = coordinate;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}
