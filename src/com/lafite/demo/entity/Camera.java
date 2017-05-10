package com.lafite.demo.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author LafiteHao
 * @create 2017-05-09 13:49
 **/
@Entity
public class Camera implements Serializable {
    private Integer id;
    private String name;
    private String place;
    private String url;

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "place", nullable = true, length = 45)
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 128)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Camera camera = (Camera) o;

        if (id != null ? !id.equals(camera.id) : camera.id != null) return false;
        if (name != null ? !name.equals(camera.name) : camera.name != null) return false;
        if (place != null ? !place.equals(camera.place) : camera.place != null) return false;
        if (url != null ? !url.equals(camera.url) : camera.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
