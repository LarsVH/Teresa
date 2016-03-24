package database;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * @author Jari Van Melckebeke
 */
@Entity
@Table(name = "music", catalog = "teresaDB")
public class Music {
    @Id
    @GenericGenerator(
            name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "resources")
    )
    @GeneratedValue(generator = "generator")
    @NotNull
    @PrimaryKeyJoinColumn(foreignKey = @ForeignKey(name = "fk_resources_music1"))
    @Column(name = "music_id")
    private int musicId;

    @Column(name = "music_name")
    private String musicName;

    @Column(name = "music_artist")
    private String musicArtist;

    @Nullable
    @Column(name = "music_album")
    private String musicAlbum;

    @OneToOne
    private Resources resources;


    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicArtist() {
        return musicArtist;
    }

    public void setMusicArtist(String musicArtist) {
        this.musicArtist = musicArtist;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public String getMusicAlbum() {
        return musicAlbum;
    }

    public void setMusicAlbum(String musicAlbum) {
        this.musicAlbum = musicAlbum;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
