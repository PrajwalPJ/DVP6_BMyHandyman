// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

package com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel;

public class ServiceOrder {

    // member variables
    //private int mId;
    public String mTitle;
    public String mDescription;
    public String mLocation;
    public String mTime;
    public String mDate;


    // constructor
    public ServiceOrder(String mTitle, String mDescription, String mLocation, String mTime, String mDate) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mLocation = mLocation;
        this.mTime = mTime;
        this.mDate = mDate;
    }

}
