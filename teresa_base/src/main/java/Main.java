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
<<<<<<< HEAD
        String command = input.getCommand(false);
        if (command.equals("TERESA")) {
            Output.speak("yes, i am listening");
            command = input.getCommand(true);
        }
=======
        String command = input.getCommand();
>>>>>>> origin/master
        if (command.contains("TERESA")) Output.speak(Action.doAction(command.substring(7)));
        else Output.speak(Action.doAction(command));
    }
}
