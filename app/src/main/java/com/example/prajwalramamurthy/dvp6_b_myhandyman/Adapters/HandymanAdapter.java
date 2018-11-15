package com.example.prajwalramamurthy.dvp6_b_myhandyman.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel.Handyman;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;

import java.util.ArrayList;
import java.util.Objects;

public class HandymanAdapter extends BaseAdapter
{

    // BASE ID
    private static final long BASE_ID = 0x01001;

    // Reference to our owning screen (context)
    private final Context mContext;

    // Reference to our collection
    private final ArrayList<Handyman> myHandymen;

    public HandymanAdapter(Context mContext, ArrayList<Handyman> myServiceOrders)
    {
        this.mContext = mContext;
        this.myHandymen = myServiceOrders;
    }

    // get size
    @Override
    public int getCount()
    {
        if (myHandymen != null)
        {
            return myHandymen.size();
        }
        return 0;
    }

    // get position
    @Override
    public Object getItem(int position)
    {

        if (myHandymen != null && position >= 0 || position < Objects.requireNonNull(myHandymen).size()) {
            return myHandymen.get(position);
        }

        return null;


    }

    // get position id
    @Override
    public long getItemId(int position)
    {
        return BASE_ID + position;
    }

    // put everything together
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;

        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_handyman, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Handyman item = (Handyman) getItem(position);

        // see if person is null
        if(item != null)
        {
            viewHolder.title.setText(item.mTitle);
            viewHolder.bio.setText(item.mBio);
          viewHolder.rate.setText(item.mHourRate);
          viewHolder.yearsEx.setText(item.mYearsExp);



            return convertView;
        }
        return null;
    }


    // Optimize with view holder!
    static class ViewHolder
    {
        final TextView title;
        final TextView yearsEx;
        final TextView rate;
        final TextView bio;


        ViewHolder(View layout)
        {
            title = layout.findViewById(R.id.titleHand);
            yearsEx = layout.findViewById(R.id.yearsHand);
            rate = layout.findViewById(R.id.rateHand);
            bio = layout.findViewById(R.id.bioHand);


        }
    }
}
