package database;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Jari Van Melckebeke
 */
@Entity
@Table(name = "music", catalog = "teresaDB")
public class Music implements Serializable {

    private int musicId;
    private String musicName;
    private String musicArtist;
    private String musicAlbum;
    private Resources resources;

    @Column(name = "music_name")
    public String getMusicName() {
        return musicName;
    }

    @Column(name = "music_artist")
    public String getMusicArtist() {
        return musicArtist;
    }

    @Id
    @GenericGenerator(name = "foreign", strategy = "foreign", parameters = {@Parameter(name = "property", value = "resources")})
    @GeneratedValue(generator = "foreign")
    @Column(name = "music_id")
    public int getMusicId() {
        return musicId;
    }


    @Column(name = "music_album")
    @Nullable
    public String getMusicAlbum() {
        return musicAlbum;
    }


    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public void setMusicAlbum(String musicAlbum) {
        this.musicAlbum = musicAlbum;
    }

    public void setMusicArtist(String musicArtist) {
        this.musicArtist = musicArtist;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    @Override
    public String toString() {
        return Arrays.toString(new String[]{"id:" + getMusicId(), "name:" + getMusicName(), "artist:" + getMusicArtist(), "album:" + getMusicAlbum()});
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
