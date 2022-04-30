package com.ozone.hollidays.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hollidays_id")

    private Holliday hollidays;

    public Image() {
    }

    public Image(Integer id, String url, Holliday hollidays) {
        this.id = id;
        this.url = url;
        this.hollidays = hollidays;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @JsonIgnore
    public Holliday getHollidays() {
        return hollidays;
    }

    public void setHollidays(Holliday hollidays) {
        this.hollidays = hollidays;
    }
}