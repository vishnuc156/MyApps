package com.jersey.smartmi.adapter.recycler_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jersey.smartmi.R;
import com.jersey.smartmi.fragments.view_quotes_fragment.ViewQuoteDetailsFragment;
import com.jersey.smartmi.listeners.FragmentChangeListener;
import com.jersey.smartmi.models.view_quotes_response_parser.Quotes;

import java.util.ArrayList;

/**
 * Created by Vishnu on 25/02/16.
 */
public class ViewQuotesAdapter extends RecyclerView.Adapter<ViewQuotesAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Quotes> responseQuotesList;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ViewQuotesAdapter(Context context, ArrayList<Quotes> responseQuotesList) {
        this.responseQuotesList = responseQuotesList;
        this.mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewQuotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_quotes_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vholder = new ViewHolder(v);
        return vholder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tvQuoteId.setText(responseQuotesList.get(position).quoteId);
        holder.tvQuoteRef.setText(responseQuotesList.get(position).quoteReference);
        holder.tvQuoteCustName.setText(responseQuotesList.get(position).customerName);
        holder.tvQuoteProjName.setText(responseQuotesList.get(position).projectName);

        holder.llQuoteParent.setOnClickListener(clickListener);
        holder.llQuoteParent.setTag(holder);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return (null != responseQuotesList ? responseQuotesList.size() : 0);
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ViewHolder holder = (ViewHolder) view.getTag();
            int position = holder.getLayoutPosition();
            FragmentChangeListener fragmentPageListener = (FragmentChangeListener) mContext;
            fragmentPageListener.callQuoteDetails(responseQuotesList.get(position).quoteId, "ViewQuoteDetailsFragment", new ViewQuoteDetailsFragment());

        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvQuoteId;
        protected TextView tvQuoteRef;
        protected TextView tvQuoteCustName;
        protected TextView tvQuoteProjName;
        protected LinearLayout llQuoteParent;


        public ViewHolder(View view) {
            super(view);
            this.tvQuoteId = (TextView) view.findViewById(R.id.tvQuoteId);
            this.tvQuoteRef = (TextView) view.findViewById(R.id.tvQuoteRef);
            this.tvQuoteCustName = (TextView) view.findViewById(R.id.tvQuoteCustName);
            this.tvQuoteProjName = (TextView) view.findViewById(R.id.tvQuoteProjName);
            this.llQuoteParent = (LinearLayout) view.findViewById(R.id.llQuoteParent);
        }
    }

}
