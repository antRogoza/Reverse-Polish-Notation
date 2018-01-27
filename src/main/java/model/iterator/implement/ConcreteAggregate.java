package model.iterator.implement;

import model.iterator.Aggregate;
import model.iterator.Iterator;

import java.util.List;

public class ConcreteAggregate implements Aggregate {
    String[] expressions;

    public ConcreteAggregate(List<String> e) {
        expressions = new String[e.size()];
        int k = 0;

        for (String item : e) {
            expressions[k] = item;
            k++;
        }
    }

    @Override
    public Iterator getIterator() {
        return new ExpressionsIterator();
    }

    private class ExpressionsIterator implements Iterator {

        int index = 0;

        @Override
        public boolean hasNext() {
            if (index < expressions.length)
                return true;
            return false;
        }

        @Override
        public Object next() {
            return expressions[index++];
        }
    }
}
