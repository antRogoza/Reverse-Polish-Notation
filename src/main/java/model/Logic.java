package model;

import org.apache.log4j.Logger;
import view.View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Сlass for displaying business logic program and reading from a file
 * The class has pattern "Builder"
 */
public class Logic {
    final static Logger logger = Logger.getLogger(model.Logic.class);
    private List<String> expressions = new ArrayList<>();
    public Parser parser;
    View view;

    public List<String> getExpressions() {
        return expressions;
    }

    public static Logic.BuilderLogic newBuilder() {
        return new Logic().new BuilderLogic();
    }

    public class BuilderLogic {

        private BuilderLogic() {
        }

        public BuilderLogic setParse(Parser p) {
            Logic.this.parser = p;
            return this;
        }

        public BuilderLogic setView(View v) {
            Logic.this.view = v;
            return this;
        }

        public Logic build() {
            return Logic.this;
        }
    }

    /**
     * Method for reading from the file
     */

    public void readFromFile() {
        String fileName = View.FILE_NAME;
        try {
            URI url = ClassLoader.getSystemResource(fileName).toURI();
            logger.info(View.bundle.getString(View.GOT_FILE));
        } catch (URISyntaxException e) {
            logger.error(e);
        }
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            int numberOfExpressions = Integer.parseInt(fileReader.readLine());
            if (numberOfExpressions > 0 && numberOfExpressions <= 100) {
                String line;
                for (int i = 0; i < numberOfExpressions; i++) {
                    line = fileReader.readLine();
                    if (line.length() <= 400)
                        addExpressionToList(line);
                }
            }
            view.printMessage(View.bundle.getString(View.REVERSED_EXPRESSIONS));
        } catch (IOException e) {
            view.printError(e.getMessage());
        }
    }

    public void addExpressionToList(String exp) {
        expressions.add(exp);
    }
}
