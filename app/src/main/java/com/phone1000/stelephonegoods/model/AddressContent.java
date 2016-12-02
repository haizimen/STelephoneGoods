package com.phone1000.stelephonegoods.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by my on 2016/12/1.
 */
@Table(name="AddressContent")
public class AddressContent  implements Parcelable{
    @Column(name="name")
    private String name;
    @Column(name="phoneNum")
    private String phoneNum;
    @Column(name = "location")
    private String location;
    @Column(name="locationDetail")
    private String locationDetail;
    @Column(name="isDefault")
    private boolean isDefault;
    @Column(name="_id", isId = true)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AddressContent() {
    }

    protected AddressContent(Parcel in) {
        name = in.readString();
        phoneNum = in.readString();
        location = in.readString();
        locationDetail = in.readString();
        isDefault = in.readByte() != 0;
    }

    public static final Creator<AddressContent> CREATOR = new Creator<AddressContent>() {
        @Override
        public AddressContent createFromParcel(Parcel in) {
            return new AddressContent(in);
        }

        @Override
        public AddressContent[] newArray(int size) {
            return new AddressContent[size];
        }
    };

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phoneNum);
        dest.writeString(location);
        dest.writeString(locationDetail);
        dest.writeByte((byte) (isDefault ? 1 : 0));
    }
}
