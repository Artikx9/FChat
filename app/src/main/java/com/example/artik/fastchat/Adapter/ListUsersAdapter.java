package com.example.artik.fastchat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.artik.fastchat.R;
import com.quickblox.users.model.QBUser;

import java.util.ArrayList;

/**
 * Created by artik on 09.01.2018.
 */

public class ListUsersAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<QBUser> qbUserArrayList;

    public ListUsersAdapter(Context context, ArrayList<QBUser> qbUserArrayList) {
        this.context = context;
        this.qbUserArrayList = qbUserArrayList;
    }

    @Override
    public int getCount() {
        return qbUserArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return qbUserArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view=inflater.inflate(android.R.layout.simple_list_item_multiple_choice, null);
            TextView textView = (TextView)view.findViewById(android.R.id.text1);
            textView.setText(qbUserArrayList.get(position).getLogin());
        }
        return  view;
    }
}
