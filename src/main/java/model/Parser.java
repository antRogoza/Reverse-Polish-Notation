package model;

import view.View;

import java.util.*;

/**
 * The class contains utilities for parsing mathematical expressions\
 */

public class Parser {

    /**
     * Main priorities.
     *
     * @see #sortingStation(List<String>, java.util.Map)
     */

    public static final Map<String, Integer> OPERATIONS;

    static {
        OPERATIONS = new HashMap<String, Integer>();
        OPERATIONS.put("^", 1);
        OPERATIONS.put("*", 1);
        OPERATIONS.put("/", 1);
        OPERATIONS.put("+", 2);
        OPERATIONS.put("-", 2);
    }

    private List<String> expressions;

    /**
     * Reversing expression in RPN
     *
     * @param exps       list of expressions in infix form.
     * @param operations операторы, использующиеся в выражении (ассоциированные, либо лево-ассоциированные).
     *                   All operators in the constant {@link #OPERATIONS}.
     * @return reversed expression in RPN.
     */
    public static List<String> sortingStation(List<String> exps, Map<String, Integer> operations, String leftBracket,
                                              String rightBracket) {
        List<String> reversedPNs = new ArrayList<>();
        for (String expression : exps) {
            if (expression == null || expression.length() == 0)
                throw new IllegalStateException(View.bundle.getString(View.NOT_SPECIFIED_EXPRESSION));
            if (operations == null || operations.isEmpty())
                throw new IllegalStateException(View.bundle.getString(View.NOT_SPECIFIED_OPERATION));

            List<String> out = new ArrayList<String>();
            Stack<String> stack = new Stack<String>();

            expression = expression.replace(" ", "");

            Set<String> operationSymbols = new HashSet<String>(operations.keySet());
            operationSymbols.add(leftBracket);
            operationSymbols.add(rightBracket);

            int index = 0;

            boolean findNext = true;
            while (findNext) {
                int nextOperationIndex = expression.length();
                String nextOperation = "";
                for (String operation : operationSymbols) {
                    int i = expression.indexOf(operation, index);
                    if (i >= 0 && i < nextOperationIndex) {
                        nextOperation = operation;
                        nextOperationIndex = i;
                    }
                }
                if (nextOperationIndex == expression.length()) {
                    findNext = false;
                } else {
                    if (index != nextOperationIndex) {
                        out.add(expression.substring(index, nextOperationIndex));
                    }
                    if (nextOperation.equals(leftBracket)) {
                        stack.push(nextOperation);
                    } else if (nextOperation.equals(rightBracket)) {
                        while (!stack.peek().equals(leftBracket)) {
                            out.add(stack.pop());
                            if (stack.empty()) {
                                throw new IllegalArgumentException(View.bundle.getString(View.UNMATCHED_BRACKETS));
                            }
                        }
                        stack.pop();
                    } else {
                        while (!stack.empty() && !stack.peek().equals(leftBracket) &&
                                (operations.get(nextOperation) >= operations.get(stack.peek()))) {
                            out.add(stack.pop());
                        }
                        stack.push(nextOperation);
                    }
                    index = nextOperationIndex + nextOperation.length();
                }
            }
            if (index != expression.length()) {
                out.add(expression.substring(index));
            }
            while (!stack.empty()) {
                out.add(stack.pop());
            }
            StringBuffer result = new StringBuffer();
            if (!out.isEmpty())
                result.append(out.remove(0));
            while (!out.isEmpty())
                result.append(" ").append(out.remove(0));

            reversedPNs.add(result.toString());
        }
        return reversedPNs;
    }

    public static List<String> sortingStation(List<String> exp, Map<String, Integer> operations) {
        return sortingStation(exp, operations, "(", ")");
    }
}
