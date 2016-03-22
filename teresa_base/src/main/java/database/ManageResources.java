package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jariv on 21/03/2016.
 */
public class ManageResources {
    private static SessionFactory factory;
    private static ArrayList<File> fileArrayList = new ArrayList<>();

    public ManageResources() throws Exception {
        try {
            factory = new Configuration().configure().addAnnotatedClass(Resources.class).buildSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void createNew() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        File[] home = new File(System.getProperty("user.home")).listFiles();
        showMusicFiles(home);
        for (File aFileArrayList : fileArrayList) {
            Resources resources = new Resources();
            resources.setLocation(aFileArrayList.getAbsolutePath());
            resources.setName(aFileArrayList.getName());
            resources.setType("music");
            session.save(resources);
        }
        transaction.commit();
    }

    public boolean isEmpty() {
        Session session = factory.openSession();
        List list = session.createQuery("from database.Resources").list();
        return list.isEmpty();
    }

    public void showMusicFiles(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getName());
                showMusicFiles(file.listFiles()); // Calls same method again.
            } else {
                if (file.getName().endsWith(".mp3") || file.getName().endsWith(".wav")) {
                    fileArrayList.add(file);
                }
            }
        }
    }
}
