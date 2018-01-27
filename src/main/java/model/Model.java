package model;

import view.View;

public class Model {
    public Logic logic;

    public Model() {
        this.logic = Logic.newBuilder()
                .setView(new View())
                .setParse(new Parser())
                .build();
    }
}
