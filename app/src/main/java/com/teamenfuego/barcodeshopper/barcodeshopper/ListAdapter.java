package com.teamenfuego.barcodeshopper.barcodeshopper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Collection;

/**
 * Created by natha on 4/21/2017.
 */

public class ListAdapter extends ArrayAdapter<String>
{
    ListAdapter(Context context, Collection<String> items)
    {
        super(context,0);
        addAll(items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        String item_position = getItem(position);
        TextView item = new TextView(getContext());
        item.setText(item_position);
        return item;
    }
}
