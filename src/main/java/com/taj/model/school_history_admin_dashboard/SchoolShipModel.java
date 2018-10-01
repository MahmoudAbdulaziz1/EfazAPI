package com.taj.model.school_history_admin_dashboard;

/**
 * Created by User on 10/1/2018.
 */
public class SchoolShipModel {

    private int id;
    private double ship;
    private int ship_school_request_id;

    public SchoolShipModel(int id, double ship, int ship_school_request_id) {
        this.id = id;
        this.ship = ship;
        this.ship_school_request_id = ship_school_request_id;
    }

    public SchoolShipModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getShip() {
        return ship;
    }

    public void setShip(double ship) {
        this.ship = ship;
    }

    public int getShip_school_request_id() {
        return ship_school_request_id;
    }

    public void setShip_school_request_id(int ship_school_request_id) {
        this.ship_school_request_id = ship_school_request_id;
    }
}
