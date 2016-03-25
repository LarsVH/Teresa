package database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Jari Van Melckebeke
 */
@Entity
@Table(name = "resources", catalog = "teresaDB")
public class Resources implements Serializable {

    private int resId;
    private String sort;
    private String location;
    private int resMusicId;
    private Music music;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    public int getResId() {
        return resId;
    }

    @Column(name = "res_sort")
    public String getSort() {
        return sort;
    }

    @Column(name = "res_location")
    public String getLocation() {
        return location;
    }


    @Column(name = "res_music_id")
    public int getResMusicId() {
        return resMusicId;
    }


    public void setResId(int resId) {
        this.resId = resId;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public void setMusic(Music music) {
        this.music = music;
    }

    public void setResMusicId(int resMusicId) {
        this.resMusicId = resMusicId;
    }

    @Override
    public String toString() {
        return Arrays.toString(new String[]{"id:" + getResId(), "sort:" + getSort(), "location:" + getLocation(), "music-id:" + getResMusicId()});
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "resources", fetch = FetchType.LAZY)
    @JoinColumn(name = "res_music_id")
    public Music getMusic() {
        return music;
    }
}
