package com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel;

public class Handyman
{
    private String mTitle;
    private String mBio;
    private String mLocation;
    private String mAvailability;
    private int mYearsExp;
    private int mHourRate;

    public Handyman(String mTitle, String mBio, String mLocation, String mAvailability, int mYearsExp, int mHourRate) {
        this.mTitle = mTitle;
        this.mBio = mBio;
        this.mLocation = mLocation;
        this.mAvailability = mAvailability;
        this.mYearsExp = mYearsExp;
        this.mHourRate = mHourRate;
    }
}
