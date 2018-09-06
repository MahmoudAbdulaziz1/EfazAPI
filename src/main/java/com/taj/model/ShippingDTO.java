package com.taj.model;

/**
 * Created by User on 9/6/2018.
 */
public class ShippingDTO {

    private int id;
    private double ship;
    private int ship_company_offer_id;


    public ShippingDTO(int id, double ship, int ship_company_offer_id) {
        this.id = id;
        this.ship = ship;
        this.ship_company_offer_id = ship_company_offer_id;
    }

    public ShippingDTO(double ship, int ship_company_offer_id) {
        this.ship = ship;
        this.ship_company_offer_id = ship_company_offer_id;
    }

    public ShippingDTO() {
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

    public int getShip_company_offer_id() {
        return ship_company_offer_id;
    }

    public void setShip_company_offer_id(int ship_company_offer_id) {
        this.ship_company_offer_id = ship_company_offer_id;
    }
}
