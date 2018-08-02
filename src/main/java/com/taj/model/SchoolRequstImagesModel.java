package com.taj.model;

/**
 * Created by User on 8/2/2018.
 */
public class SchoolRequstImagesModel {

    private int image_id;
    private byte[] image_one;
    private byte[] image_two;
    private byte[] image_three;
    private byte[] image_four;

    public SchoolRequstImagesModel() {
    }

    public SchoolRequstImagesModel(int image_id, byte[] image_one, byte[] image_two, byte[] image_three, byte[] image_four) {
        this.image_id = image_id;
        this.image_one = image_one;
        this.image_two = image_two;
        this.image_three = image_three;
        this.image_four = image_four;
    }

    public SchoolRequstImagesModel(byte[] image_one, byte[] image_two, byte[] image_three, byte[] image_four) {
        this.image_one = image_one;
        this.image_two = image_two;
        this.image_three = image_three;
        this.image_four = image_four;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public byte[] getImage_one() {
        return image_one;
    }

    public void setImage_one(byte[] image_one) {
        this.image_one = image_one;
    }

    public byte[] getImage_two() {
        return image_two;
    }

    public void setImage_two(byte[] image_two) {
        this.image_two = image_two;
    }

    public byte[] getImage_three() {
        return image_three;
    }

    public void setImage_three(byte[] image_three) {
        this.image_three = image_three;
    }

    public byte[] getImage_four() {
        return image_four;
    }

    public void setImage_four(byte[] image_four) {
        this.image_four = image_four;
    }
}
