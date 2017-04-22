package com.teamenfuego.barcodeshopper.barcodeshopper;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Collection;

/**
 * Created by natha on 4/21/2017.
 */

public class ListAdapter extends ArrayAdapter<Item>
{
    ListAdapter(Context context, Collection<Item> items)
    {
        super(context,0);
        addAll(items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Item item_position = getItem(position);
        TextView text = new TextView(getContext());
        text.setTextSize(20);
        text.setTextColor(Color.BLACK);
        text.setText(item_position.toString());

        return text;
    }
}
