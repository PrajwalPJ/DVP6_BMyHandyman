package com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel;

public class ServiceOrder
{

    // member variables
    private int mId;
    private final String mTitle;
    private final String mDescription;
    private final String mLocation;
    private final String mTime;
    private final String mDate;

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
