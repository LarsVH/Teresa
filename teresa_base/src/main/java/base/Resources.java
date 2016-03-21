package base;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by jariv on 21/03/2016.
 */
public class Resources {

    public static Resources createNew() {
        try {
            File file = new File(System.getProperty("user.dir") + "/src/main/resources/props.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            //main element
            Element rootElement = doc.createElement("properties");
            doc.appendChild(rootElement);

            //resource files element
            Element resourceElement = doc.createElement("resources");
            doc.appendChild(resourceElement);

            //music files element
            Element musicResElement = doc.createElement("music");
            Set<String>[] tracks = getTracks();
            doc.appendChild(musicResElement);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    private static Set<String>[] getTracks() {
        System.out.println("type the directory paths of music files, empty value will end scanner");
        Scanner sc = new Scanner("System.in");
        ArrayList<File> dirs = new ArrayList<>();
        while (sc.hasNextLine()) {
            dirs.add(new File(sc.nextLine()));
        }
        System.out.println(dirs);
        ArrayList<Set<String>> tracks = new ArrayList<>();
        for (File dir : dirs) {
            for (File track : dir.listFiles()) {
                tracks.add();
            }
        }
    }

    public static Resources loadLib(File file) {

    }
}
