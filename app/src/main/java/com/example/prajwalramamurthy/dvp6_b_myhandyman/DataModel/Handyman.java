package com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel;

public class Handyman
{
    public String mTitle;
    public String mBio;
    public String mLocation;
    public String mAvailability;
    public int mYearsExp;
    public int mHourRate;

    public Handyman() {
    }

    public Handyman(String mTitle, String mBio, String mLocation, String mAvailability, int mYearsExp, int mHourRate) {
        this.mTitle = mTitle;
        this.mBio = mBio;
        this.mLocation = mLocation;
        this.mAvailability = mAvailability;
        this.mYearsExp = mYearsExp;
        this.mHourRate = mHourRate;
    }
}
