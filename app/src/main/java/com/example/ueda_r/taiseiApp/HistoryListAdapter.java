package com.example.ueda_r.taiseiApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ueda_r.osmdroidtest1022.R;

import java.util.ArrayList;

public class HistoryListAdapter extends ArrayAdapter<HistoryListItem>{

    private int mResource;
    private ArrayList<HistoryListItem> mItems;
    private LayoutInflater mInflater;

    private HistoryListAdapterCallback callback;

    public HistoryListAdapter(Context context, int resource, ArrayList<HistoryListItem> items) {
        super(context,resource,items);
        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        } else {
            view = mInflater.inflate(mResource,null);
        }

        HistoryListItem item = mItems.get(position);

        final TextView title = (TextView)view.findViewById(R.id.filename);
        title.setText(item.getTitle());

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = title.getText().toString();

                Context context = HistoryListAdapter.super.getContext();
                callback.titlePressed(filename);

            }
        });

        ImageButton deleteButton = view.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = title.getText().toString();
                callback.deletePressed(filename);
            }
        });
        return view;
    }

    public void setCallback(HistoryListAdapterCallback callback) {
        this.callback = callback;
    }

    public interface HistoryListAdapterCallback {
        void titlePressed(String filename);
        void deletePressed(String filename);
    }

}