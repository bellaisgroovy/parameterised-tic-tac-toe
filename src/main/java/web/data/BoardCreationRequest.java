package web.data;

import java.util.List;

public class BoardCreationRequest {
    private List<Integer> sizes;

    private int streakToWin;

    public List<Integer> getSizes() {
        return sizes;
    }

    public void setSizes(List<Integer> sizes) {
        this.sizes = sizes;
    }

    public int getStreakToWin() {
        return streakToWin;
    }

    public void setStreakToWin(int streakToWin) {
        this.streakToWin = streakToWin;
    }
}
