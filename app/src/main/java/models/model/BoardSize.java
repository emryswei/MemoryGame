package models.model;


public enum BoardSize {
    EASY(8),
    MEDIUM(18),
    HARD(24);

    private final int numCards;

    BoardSize(int numCards){
        this.numCards = numCards;
    }

    public int getNumCards(){
        return numCards;
    }

    public int getColumnNum(){
        if(numCards==8){
            return 2;
        } else if(numCards==18){
            return 3;
        } else {
            return 4;
        }
    }

    public int getRowNum(){
        return numCards / getColumnNum();
    }

    public int getNumPairs(){
        return numCards / 2;
    }
}

