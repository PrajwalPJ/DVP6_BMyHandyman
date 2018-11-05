// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

package com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel;

import java.io.Serializable;
import java.net.URI;

public class Person implements Serializable
{

    // member variables
    private int mId;
    private final String mFirstName;
    private final String mLastName;
    private final String mEmail;
    private final String mPhoneNumber;
    //private final URI mImage;


    public Person(String mFirstName, String mLastName, String mEmail, String mPhoneNumber) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mEmail = mEmail;
        this.mPhoneNumber = mPhoneNumber;
    }

    public Person(int mId, String mFirstName, String mLastName, String mEmail, String mPhoneNumber) {
        this.mId = mId;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mEmail = mEmail;
        this.mPhoneNumber = mPhoneNumber;
    }
}
