/**
 * @author Jari Van Melckebeke
 */
public class Main {
    /**
     * in deze methode vind de main runtime plaats.
     *
     * @param args default system variables
     */
    public static void main(String[] args) throws Exception {
        System.out.println("system started");
        Input input = new Input();
        String command = input.getCommand(false);
        if (command.equals("TERESA")) {
            Output.speak("yes, i am listening");
            command = input.getCommand(true);
        }
        do {
            Output.speak(Action.doAction(command));
            command = input.getCommand(true);
        } while (true);
    }
}
