package com.example.tripware;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.*;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by ulusoyy on 16.05.2015.
 */
public class ResultArrayAapter   extends ArrayAdapter<String> {

        private final Context context;
        private final List values;

        public ResultArrayAapter(Context context, List values) {
            super(context, R.layout.result, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.list, parent, false);
            TextView textView = (TextView) rowView.findViewById(R.id.txtTitle);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.imgIcon);
            textView.setText(values.get(position).toString());
            // Change icon based on name
            String s = values.get(position).toString();
            System.out.println(s);

            if (s.contains("(Kritik)")) {
                imageView.setImageResource(R.drawable.war);
                imageView.setMaxWidth(50);
                imageView.setMaxHeight(50);
            } else if (s.contains("(Normal)")) {
                imageView.setImageResource(R.drawable.nor);
                imageView.setMaxWidth(50);
            }
            return rowView;
        }
    }