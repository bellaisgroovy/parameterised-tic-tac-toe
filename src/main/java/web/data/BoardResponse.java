package web.data;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty// necessary otherwise it will change the name of variable when returned by api
    private final String JSONBoard;
    @JsonProperty
    private final int streakToWin;
}
