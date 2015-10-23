package com.memoirs.travel.travelmemoirs;

/**
 * Created by mat on 22.10.2015..
 */
public class User {
    private int id;
    private int idMemoir;
    private String email;
    private String image;


    //konstruktori
    public User(int id, int idMemoir, String email, String image) {
        this.id = id;
        this.idMemoir = idMemoir;
        this.email = email;
        this.image = image;
    }

    public User(User user) {
        this.id = user.id;
        this.idMemoir = user.idMemoir;
        this.email = user.email;
        this.image = user.image;
    }

    public User() {

    }

    //getteri i setteri
    public int getIdMemoir() {
        return idMemoir;
    }

    public void setIdMemoir(int idMemoir) {
        this.idMemoir = idMemoir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

}
