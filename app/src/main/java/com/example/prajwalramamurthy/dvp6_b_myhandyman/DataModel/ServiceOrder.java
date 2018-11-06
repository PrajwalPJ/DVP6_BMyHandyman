package com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel;

import java.io.Serializable;

public class ServiceOrder {

    // member variables
    public int mId;
    public String mTitle;
    public String mDescription;
    public String mLocation;
    public String mTime;
    public String mDate;

    public ServiceOrder() {}

    public ServiceOrder(String mTitle, String mDescription, String mLocation, String mTime, String mDate) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mLocation = mLocation;
        this.mTime = mTime;
        this.mDate = mDate;
    }

    public ServiceOrder(int mId, String mTitle, String mDescription, String mLocation, String mTime, String mDate) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mLocation = mLocation;
        this.mTime = mTime;
        this.mDate = mDate;
    }
}
