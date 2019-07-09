package com.example.marksheet.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentDataList {

    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("mname")
    @Expose
    private String mname;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("standard")
    @Expose
    private String standard;
    @SerializedName("parent_name")
    @Expose
    private String parent_name;
    @SerializedName("parent_phone")
    @Expose
    private String parent_phone;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("blood_group")
    @Expose
    private String blood_group;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("__v")
    @Expose
    private String __v;

    // getters and setters for above attributes
    public String get_id() { return _id; }

    public void set_id(String _id) { this._id = _id; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getFname() { return fname; }

    public void setFname(String fname) { this.fname = fname; }

    public String getMname() { return mname; }

    public void setMname(String mname) { this.mname = mname; }

    public String getLname() { return lname; }

    public void setLname(String lname) { this.lname = lname; }

    public String getStandard() { return standard; }

    public void setStandard(String standard) { this.standard = standard; }

    public String getParent_name() { return parent_name; }

    public void setParent_name(String parent_name) { this.parent_name = parent_name; }

    public String getParent_phone() { return parent_phone; }

    public void setParent_phone(String parent_phone) { this.parent_phone = parent_phone; }

    public String getDob() { return dob; }

    public void setDob(String dob) { this.dob = dob; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getBlood_group() { return blood_group; }

    public void setBlood_group(String blood_group) { this.blood_group = blood_group; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public String get__v() { return __v; }

    public void set__v(String __v) { this.__v = __v; }

}
