package web.data;

public class BoardResponse {
    public BoardResponse(String JSONArray, int streakToWin) {
        this.JSONBoard = JSONArray;
        this.streakToWin = streakToWin;
    }

    public String getJSONBoard() {
        return JSONBoard;
    }

    public int getStreakToWin() {
        return streakToWin;
    }

    private final String JSONBoard;
    private final int streakToWin;
}
