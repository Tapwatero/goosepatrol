package team2.eng1;

enum AnimationId {
    Walk,
    Idle,
    SideIdlePlayer,
    SideWalkPlayer,
    UpIdlePlayer,
    UpWalkPlayer,
    DownIdlePlayer,
    DownWalkPlayer,
}

public class AnimationSheet {

    private AnimationId animationId;
    private float frameDuration;
    private int sheetCols;
    private int sheetRows;
    private int totalFrames;

    public AnimationSheet(AnimationId animationId, float frameDuration, int sheetCols, int sheetRows, int totalFrames) {
        this.animationId = animationId;
        this.frameDuration = frameDuration;
        this.sheetCols = sheetCols;
        this.sheetRows = sheetRows;
        this.totalFrames = totalFrames;
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

    public int getTotalFrames() {
        return totalFrames;
    }
}
