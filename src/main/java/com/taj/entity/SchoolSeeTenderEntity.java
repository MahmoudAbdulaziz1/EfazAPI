package com.taj.entity;

import javax.persistence.*;

/**
 * Created by User on 7/9/2018.
 */
@Entity(name = "takatf_school_see_tender")
@Table
public class SchoolSeeTenderEntity {

    @Id
    @GeneratedValue
    private int seen_id;
    @Column
    private int seen_tender_id;
    @Column
    private int seen_school_id;

    public SchoolSeeTenderEntity() {
    }

    public SchoolSeeTenderEntity(int seen_id, int seen_tender_id, int seen_school_id) {
        this.seen_id = seen_id;
        this.seen_tender_id = seen_tender_id;
        this.seen_school_id = seen_school_id;
    }

    public int getSeen_id() {
        return seen_id;
    }

    public void setSeen_id(int seen_id) {
        this.seen_id = seen_id;
    }

    public int getSeen_tender_id() {
        return seen_tender_id;
    }

    public void setSeen_tender_id(int seen_tender_id) {
        this.seen_tender_id = seen_tender_id;
    }

    public int getSeen_school_id() {
        return seen_school_id;
    }

    public void setSeen_school_id(int seen_school_id) {
        this.seen_school_id = seen_school_id;
    }
}
