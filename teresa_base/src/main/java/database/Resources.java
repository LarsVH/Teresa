package database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Jari Van Melckebeke
 */
@Entity
@Table(name = "resources", catalog = "teresaDB")
public class Resources {
    @Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen", strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private int resId;

    @Column(name = "res_sort")
    private String type;

    @Column(name = "res_location")
    private String location;

    @JoinColumn(name = "res_music_id", foreignKey = @ForeignKey(name = "fk_resources_music1"), referencedColumnName = "music_id")
    @Column(name = "res_music_id")
    private int resMusicId;

    @OneToOne(cascade = CascadeType.ALL)
    private Music music;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
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

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public int getResMusicId() {
        return resMusicId;
    }

    public void setResMusicId(int resMusicId) {
        this.resMusicId = resMusicId;
    }
}
