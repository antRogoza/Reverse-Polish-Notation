package controller;

import model.Model;
import model.Parser;
import model.iterator.Iterator;
import model.iterator.implement.ConcreteAggregate;
import view.View;

import java.util.List;

public class Controller {
    Model model;
    View view;

    public Controller(Model m, View v) {
        this.model = m;
        this.view = v;
    }

    /**
     * Work Method
     */

    public void processUser() {
        model.logic.readFromFile();
        List<String> result = Parser.sortingStation(model.logic.getExpressions(), Parser.OPERATIONS);
        ConcreteAggregate concreteAggregate = new ConcreteAggregate(result);
        Iterator iterator = concreteAggregate.getIterator();

        while (iterator.hasNext()) {
            view.printMessage((String) iterator.next());
        }
    }
}
