package com.example.prajwalramamurthy.dvp6_b_myhandyman.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel.ServiceOrder;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;

import java.util.ArrayList;
import java.util.Objects;

public class ServiceOrderAdapter extends BaseAdapter implements Filterable
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

        ServiceOrder order = (ServiceOrder) getItem(position);

        // see if person is null
        if(order != null)
        {
            viewHolder.title.setText(order.mTitle);
            viewHolder.desc.setText(order.mDescription);
            viewHolder.loc.setText(order.mLocation);
            viewHolder.time.setText(order.mTime);
            viewHolder.date.setText(order.mDate);

            return convertView;
        }
        return null;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    // Optimize with view holder!
    static class ViewHolder
    {
        final TextView title;
        final TextView desc;
        final TextView loc;
        final TextView time;
        final TextView date;


        ViewHolder(View layout)
        {
            title = layout.findViewById(R.id.titleOrderView);
            desc = layout.findViewById(R.id.descOrderView);
            loc = layout.findViewById(R.id.locOrderView);
            time = layout.findViewById(R.id.timeOrderView);
            date = layout.findViewById(R.id.dateOrderView);

        }
    }
}
