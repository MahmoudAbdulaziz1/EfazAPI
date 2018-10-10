package com.taj.entity.test;

import javax.persistence.*;

/**
 * Created by User on 10/10/2018.
 */
@Entity
@Table(name = "efaz_school_request_offer_date")
public class CompanyOfferDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private long date;

    public CompanyOfferDateEntity(long date) {
        this.date = date;
    }

    public CompanyOfferDateEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
