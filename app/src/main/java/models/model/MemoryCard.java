package models.model;


public class MemoryCard {
    private int it;
    private Boolean isFaceUp = false;
    private Boolean isMatched = false;

    public MemoryCard(int it, Boolean isFaceUp, Boolean isMatched) {
        this.it = it;
        this.isFaceUp = isFaceUp;
        this.isMatched = isMatched;
    }

    public int getIt() {
        return it;
    }

    public void setIt(int it) {
        this.it = it;
    }

    public Boolean getFaceUp() {
        return isFaceUp;
    }

    public void setFaceUp(Boolean faceUp) {
        isFaceUp = faceUp;
    }

    public Boolean getMatched() {
        return isMatched;
    }

    public void setMatched(Boolean matched) {
        isMatched = matched;
    }
}
