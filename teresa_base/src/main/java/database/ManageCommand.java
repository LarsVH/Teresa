package database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.Method;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Jari Van Melckebeke
 */
public class ManageCommand {
    private static SessionFactory factory;

    public ManageCommand() throws Exception {
        try {
            factory = new Configuration().configure().addAnnotatedClass(Command.class).buildSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public TreeMap<String, Method> getLibrary() throws Exception {
        TreeMap<String, Method> out = new TreeMap<>();

        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Command> commands = session.createQuery("from database.Command").list();
            for (Command command : commands) {
                Class comClass = Class.forName("actions." + command.getComClass());
                Method comMethod = comClass.getMethod(command.getComMethod(), String.class);
                out.put(command.getComQuestion().toUpperCase(), comMethod);
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new Exception("exception 0 1 2: hibernate error");
        }
        return out;
    }


    public List<String> listCommands() {
        Session session = factory.openSession();
        String out = "";
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List commands = session.createQuery("from database.Command").list();
            tx.commit();
            //System.out.println(commands);
            return commands;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }
        return null;
    }

    public Method getMethod(String response) throws Exception {
        TreeMap<String, Method> lib = getLibrary();
        String modResponse = getClosestCommand(response);
        //System.out.println(lib.toString());
        for (int i = 0; i < lib.size(); i++) {
            if (modResponse != null) {
                //System.out.println("modResponse = " + modResponse);
                //System.out.println("lib.keySet() = " + lib.keySet());
                if (lib.keySet().contains(modResponse)) {
                    return lib.get(modResponse);
                }
            }
        }
        return null;
    }

    private String getClosestCommand(String response) {
        List commands = listCommands();
        response = response.toUpperCase();
        //System.out.println("response = " + response);
        for (Object command : commands) {
            //System.out.println("command = " + ((Command) command).getComQuestion());
            if (response.startsWith(((Command) command).getComQuestion())) {
                return ((Command) command).getComQuestion();
            }
        }
        return null;
    }
}
