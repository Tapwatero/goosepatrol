package team2.eng1;

enum AnimationId {
    Walk,
    Idle,
}

public class AnimationSheet {

    private AnimationId animationId;
    private float frameDuration;
    private int sheetCols;
    private int sheetRows;

    public AnimationSheet(AnimationId animationId, float frameDuration, int sheetCols, int sheetRows) {
        this.animationId = animationId;
        this.frameDuration = frameDuration;
        this.sheetCols = sheetCols;
        this.sheetRows = sheetRows;
    }

    public AnimationId getAnimationId() {
        return animationId;
    }

    public float getFrameDuration() {
        return frameDuration;
    }

    public int getSheetCols() {
        return sheetCols;
    }

    public int getSheetRows() {
        return sheetRows;
    }
}
