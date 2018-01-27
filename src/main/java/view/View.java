package view;

import java.util.Locale;
import java.util.ResourceBundle;

public class View {
    public static final String MESSAGES_BUNDLE_NAME = "messages";
    public static ResourceBundle bundle =
            ResourceBundle.getBundle(
                    MESSAGES_BUNDLE_NAME,
                    //new Locale("ua"));                // Ukrainian
                    new Locale("en"));        // English

    public static String FILE_NAME = "input.txt";
    public static String UNMATCHED_BRACKETS = "unmatched.brackets";
    public static String NOT_SPECIFIED_EXPRESSION = "not.specified.exp";
    public static String NOT_SPECIFIED_OPERATION = "not.specified.oper";
    public static String GOT_FILE = "got.file";
    public static String REVERSED_EXPRESSIONS = "reversed.expressions";

    public void printError(String err) {
        System.err.println(err);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
