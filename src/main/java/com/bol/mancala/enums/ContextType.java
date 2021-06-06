package com.bol.mancala.enums;

public enum ContextType {
    initial(1),
    gettingStones(2),
    decidingWinner(3);

    private int contextType;

    ContextType(int contextType) {
        this.contextType = contextType;
    }

    public int getContextType() {
        return contextType;
    }
}
