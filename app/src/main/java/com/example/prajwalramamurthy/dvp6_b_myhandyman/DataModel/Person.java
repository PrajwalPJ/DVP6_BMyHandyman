// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

package com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel;



public class Person
{

    // member variables
    public int mId;
    public String mFirstName;
    public String mLastName;
    public String mEmail;
    public String mPhoneNumber;
    public String id_img;
    public String profile_img;


    public Person(){}

    public Person(String mFirstName, String mLastName, String mEmail, String mPhoneNumber) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mEmail = mEmail;
        this.mPhoneNumber = mPhoneNumber;
    }

    public Person(String mFirstName, String mEmail,String image) {
        this.mFirstName = mFirstName;
        this.mEmail = mEmail;
        this.profile_img = image
                ;
    }

}
