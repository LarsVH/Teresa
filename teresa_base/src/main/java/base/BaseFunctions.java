package base;

/**
 * @author Jari Van Melckebeke
 */
public class BaseFunctions {
    private static boolean doExit = false;
    private static boolean teresaSaid = false;
    private static Output output;

    public BaseFunctions() {
        output = new Output();
    }

    public static boolean isDoExit() {
        return doExit;
    }

    public static void setDoExit(boolean doExit) {
        BaseFunctions.doExit = doExit;
    }

    public static boolean isTeresaSaid() {
        return teresaSaid;
    }

    public static void setTeresaSaid(boolean teresaSaid) {
        BaseFunctions.teresaSaid = teresaSaid;
    }

    public static void goThroughBaseFunctions(String response) {
        response = response.toLowerCase();
        System.out.println(response);
        switch (response) {
            case "quit":
                output.say("farewell");
                doExit = true;
                break;
            case "teresa":
                output.say("yes, I am listening");
                teresaSaid = true;
                break;
            case "thank you":
                output.say("you're welcome");
                teresaSaid = false;
                break;
        }
    }
}
