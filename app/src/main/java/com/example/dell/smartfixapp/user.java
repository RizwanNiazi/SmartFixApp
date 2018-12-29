package com.example.dell.smartfixapp;


class user {
    public static String name;
    public static String email;
    public static String password;
    public static double longitude;
    public static double lattitude;
    public static String usertype;
    public static String id;
    public static String getName() {
        return name;
    }


    public static String getEmail() {
        return email;
    }


    public static String getPassword() {
        return password;
    }


    public user( String email, double lattitude,double longitude,String name, String usertype) {
        //this.setId(id);
        this.email = email;
        this.setLattitude(lattitude);
        this.setLongitude(longitude);
        this.name = name;
        this.setUsertype(usertype);
    }


    public static double getLongitude() {
        return longitude;
    }

    public static void setLongitude(double longitude) {
        user.longitude = longitude;
    }

    public static double getLattitude() {
        return lattitude;
    }

    public static void setLattitude(double lattitude) {
        user.lattitude = lattitude;
    }

    public static String getUsertype() {
        return usertype;
    }

    public static void setUsertype(String usertype) {
        user.usertype = usertype;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        user.id = id;
    }
}
