package com.example.prajwalramamurthy.dvp6_b_myhandyman.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel.ServiceOrder;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServiceOrderAdapter extends BaseAdapter implements Filterable
{

    // BASE ID
    private static final long BASE_ID = 0x01001;

    // Reference to our owning screen (context)
    private final Context mContext;

    // Reference to our collection
    public  ArrayList<ServiceOrder> myServiceOrders;

    public ServiceOrderAdapter(Context mContext, ArrayList<ServiceOrder> myServiceOrders)
    {
        this.mContext = mContext;
        this.myServiceOrders = myServiceOrders;
        this.filteredData = myServiceOrders;
    }

    // get size
    @Override
    public int getCount()
    {
        if (filteredData != null)
        {
            return filteredData.size();
        }
        return 0;
    }

    // get position
    @Override
    public Object getItem(int position)
    {

        if (filteredData != null && position >= 0 || position < Objects.requireNonNull(filteredData).size()) {
            return filteredData.get(position);
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
    public View getView(final int position, View convertView, ViewGroup parent)
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

        final ServiceOrder order = (ServiceOrder) getItem(position);

        // see if person is null
        if(order != null)
        {
            viewHolder.title.setText(order.mTitle);
            viewHolder.desc.setText(order.mDescription);
            viewHolder.loc.setText(order.mLocation);
            viewHolder.time.setText(order.mTime);
            viewHolder.date.setText(order.mDate);

            viewHolder.loc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String query = "geo:0,0?q=" + order.mLocation;
                    Uri gmmIntentUri = Uri.parse(query);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    mContext.startActivity(mapIntent);
                }
            });

            return convertView;
        }
        return null;
    }

    private ItemFilter mFilter = new ItemFilter();

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public List<ServiceOrder>filteredData = null;

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<ServiceOrder> list = myServiceOrders;

            int count = list.size();
            final ArrayList<ServiceOrder> nlist = new ArrayList<ServiceOrder>(count);

            ServiceOrder filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.mTitle.toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<ServiceOrder>) results.values;
            notifyDataSetChanged();
        }

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
