package com.taj.model;

/**
 * Created by User on 7/30/2018.
 */
public class AddOfferImage {
    private int images_id;
    private byte[] image_one;
    private byte[] image_two;
    private byte[] image_third;
    private byte[] image_four;

    public AddOfferImage() {
    }

    public AddOfferImage(int images_id, byte[] image_one, byte[] image_two, byte[] image_third, byte[] image_four) {
        this.images_id = images_id;
        this.image_one = image_one;
        this.image_two = image_two;
        this.image_third = image_third;
        this.image_four = image_four;
    }

    public AddOfferImage(byte[] image_one, byte[] image_two, byte[] image_third, byte[] image_four) {
        this.image_one = image_one;
        this.image_two = image_two;
        this.image_third = image_third;
        this.image_four = image_four;
    }

    public int getImages_id() {
        return images_id;
    }

    public void setImages_id(int images_id) {
        this.images_id = images_id;
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

    public byte[] getImage_third() {
        return image_third;
    }

    public void setImage_third(byte[] image_third) {
        this.image_third = image_third;
    }

    public byte[] getImage_four() {
        return image_four;
    }

    public void setImage_four(byte[] image_four) {
        this.image_four = image_four;
    }

}
