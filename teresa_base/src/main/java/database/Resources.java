package database;

import javax.persistence.*;

/**
 * Created by jariv on 21/03/2016.
 */
@Entity
@Table(name = "resources")
public class Resources {
    @Id
    @GeneratedValue
    @Column(name = "res_id")
    private int id;

    @Column(name = "res_type")
    private String type;

    @Column(name = "res_location")
    private String location;

    @Column(name = "res_name")
    private String name;

    @Column(name = "res_artist")
    private String artist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
