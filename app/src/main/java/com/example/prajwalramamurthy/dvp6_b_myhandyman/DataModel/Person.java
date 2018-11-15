// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

package com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel;

public class Person
{

    // member variables
    private int mId;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mPhoneNumber;


    public Person(){}

    public Person(String mFirstName, String mLastName, String mEmail, String mPhoneNumber) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mEmail = mEmail;
        this.mPhoneNumber = mPhoneNumber;
    }

    public Person(String mFirstName, String mEmail) {
        this.mFirstName = mFirstName;
        this.mEmail = mEmail;
    }

}
