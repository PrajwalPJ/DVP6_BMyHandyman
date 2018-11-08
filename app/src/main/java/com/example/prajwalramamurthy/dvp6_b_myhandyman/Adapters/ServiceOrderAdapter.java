package com.example.prajwalramamurthy.dvp6_b_myhandyman.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel.ServiceOrder;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;

import java.util.ArrayList;
import java.util.Objects;

public class ServiceOrderAdapter extends BaseAdapter
{

    // BASE ID
    private static final long BASE_ID = 0x01001;

    // Reference to our owning screen (context)
    private final Context mContext;

    // Reference to our collection
    private final ArrayList<ServiceOrder> myServiceOrders;

    public ServiceOrderAdapter(Context mContext, ArrayList<ServiceOrder> myServiceOrders)
    {
        this.mContext = mContext;
        this.myServiceOrders = myServiceOrders;
    }

    // get size
    @Override
    public int getCount()
    {
        if (myServiceOrders != null)
        {
            return myServiceOrders.size();
        }
        return 0;
    }

    // get position
    @Override
    public Object getItem(int position)
    {

        if (myServiceOrders != null && position >= 0 || position < Objects.requireNonNull(myServiceOrders).size()) {
            return myServiceOrders.get(position);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_serviceorder, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        ServiceOrder p = (ServiceOrder) getItem(position);

        // see if person is null
        if(p != null)
        {
            viewHolder.tv_fullName.setText(p.mTitle);

            return convertView;
        }
        return null;
    }

    // Optimize with view holder!
    static class ViewHolder
    {
        final TextView tv_fullName;


        ViewHolder(View layout)
        {
            tv_fullName = layout.findViewById(R.id.titleViewAdapter);

        }
    }
}
