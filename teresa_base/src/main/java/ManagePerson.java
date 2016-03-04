import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jari Van Melckebeke
 */
public class ManagePerson {
    private static SessionFactory factory;

    public ManagePerson() throws Exception {
        try {
            factory = new AnnotationConfiguration().configure().addAnnotatedClass(Person.class).buildSessionFactory();
        } catch (Throwable e) {
            Output.speak("error 0 0 5 :failed to create factory object");
        }
    }

    public String listPersons() {
        Session session = factory.openSession();
        Transaction tx = null;
        String out = "";
        try {
            tx = session.beginTransaction();
            List persons = session.createQuery("from persons").list();
            for (Iterator iterator = persons.iterator(); iterator.hasNext(); ) {
                Person person = (Person) iterator.next();
                out += "person: " + person.getPersonName() + " " + person.getPersonSurname() + ".";
                out += "born on " + getBirthday(person) + ".";
                out += "gender: " + getGender(person) + ".";
                out += "email: " + person.getPersonEmail();
            }
            tx.commit();
        }catch (HibernateException e){
            if (tx != null) tx.rollback();
            return "error 0 0 6: hibernate exception";
        }
        return out;
    }

    private String getGender(Person person) {
        switch (person.getPersonGender()) {
            case 'v':
                return "female";
            case 'm':
                return "male";
            default:
                return "undefined";
        }
    }

    private String getBirthday(Person person) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd mmmmmmmmm yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, person.getBirthdayDay());
        calendar.set(Calendar.MONTH, person.getBirthdayMonth() - 1);
        calendar.set(Calendar.YEAR, person.getBirthdayYear());
        return simpleDateFormat.format(calendar.getTime());
    }
}
