// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

package com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel;

public class Handyman
{
    public String mTitle;
    public String mBio;
    public String mYearsExp;
    public String mHourRate;


    // constructor
    public Handyman(String mTitle, String mBio, String mAvailability, String mYearsExp, String mHourRate) {
        this.mTitle = mTitle;
        this.mBio = mBio;
        String mAvailability1 = mAvailability;
        this.mYearsExp = mYearsExp;
        this.mHourRate = mHourRate;
    }
}
