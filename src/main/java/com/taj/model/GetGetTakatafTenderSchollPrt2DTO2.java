package com.taj.model;

/**
 * Created by User on 9/12/2018.
 */
public class GetGetTakatafTenderSchollPrt2DTO2 {

    private int id;
    private String category_name;
    private int count;


    public GetGetTakatafTenderSchollPrt2DTO2(int id, String category_name,  int count) {
        this.id = id;
        this.category_name = category_name;
        this.count = count;
    }

    public GetGetTakatafTenderSchollPrt2DTO2() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
