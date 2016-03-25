package database;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jari Van Melckebeke
 */
public class ManageResources {
    private static SessionFactory factory;
    private static ArrayList<File> fileArrayList = new ArrayList<>();

    public ManageResources() throws Exception {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void createNew() throws InvalidDataException, IOException, UnsupportedTagException {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        File[] home = new File(System.getProperty("user.home")).listFiles();
        showMusicFiles(home);
        for (int i = 0; i < fileArrayList.size(); i++) {
            transaction = session.beginTransaction();
            Resources resources = new Resources();
            Music music = new Music();
            resources.setLocation(fileArrayList.get(i).getAbsolutePath());
            resources.setSort("music");
            Mp3File mp3File = new Mp3File(fileArrayList.get(i));
            if (mp3File.getId3v2Tag() != null && mp3File.getId3v2Tag().getTitle() != null) {
                music.setMusicAlbum(mp3File.getId3v2Tag().getAlbum());
                music.setMusicName(mp3File.getId3v2Tag().getTitle());
                music.setMusicArtist(mp3File.getId3v2Tag().getArtist());
                resources.setMusic(music);
                System.out.println("resources = " + resources.getResMusicId());
                music.setMusicId(resources.getResMusicId());
                music.setResources(resources);
                System.out.println(music.toString());
                System.out.println(resources.toString());
                session.save(resources);
            }
            transaction.commit();
        }

        session.close();
    }

    public boolean isEmpty() {
        Session session = factory.openSession();
        List list = session.createQuery("from database.Resources").list();
        return list.isEmpty();
    }

    public void showMusicFiles(File[] files) {
        for (File file : files) {
            //System.out.println("file = " + file);
            if (file.isDirectory()) {
                //System.out.println("Directory: " + file.getName());
                try {
                    showMusicFiles(file.listFiles()); // Calls same method again.
                } catch (NullPointerException ignored) {
                }
            } else {
                if (file.getName().endsWith(".mp3")) {
                    //System.out.println("file: " + file.getName());
                    fileArrayList.add(file);
                }
            }
        }
    }
}
