package com.ozone.hollidays.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hollidays_id")
    private Holliday hollidays;

    public Holliday getHollidays() {
        return hollidays;
    }

    public void setHollidays(Holliday hollidays) {
        this.hollidays = hollidays;
    }

}