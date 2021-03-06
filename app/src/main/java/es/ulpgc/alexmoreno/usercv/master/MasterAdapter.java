package es.ulpgc.alexmoreno.usercv.master;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import es.ulpgc.alexmoreno.usercv.R;
import es.ulpgc.alexmoreno.usercv.data.User;

public class MasterAdapter extends ArrayAdapter<User> {


    private final List<User> itemList;
    private final View.OnClickListener clickListener;


    public MasterAdapter(
            Context context, List<User> items, View.OnClickListener listener) {

        super(context, 0, items);

        itemList = items;
        clickListener = listener;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public User getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return itemList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.master_recycler_cell, parent, false);
        }

        itemView.setTag(itemList.get(position));
        itemView.setOnClickListener(clickListener);

        final TextView contentView = itemView.findViewById(R.id.recylerCellTextView);
        contentView.setText(String.valueOf(itemList.get(position).getName()));

        return itemView;
    }

}