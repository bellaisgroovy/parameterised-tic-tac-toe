package data;

public enum GameState {
    DRAW(0),
    ONGOING(-1);

    public final int value;

    GameState(int value){
        this.value = value;
    }
}
