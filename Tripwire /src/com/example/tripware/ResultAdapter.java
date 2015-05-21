package com.example.tripware;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ResultAdapter extends ArrayAdapter<String> {
    private Activity context;
    private ListItemRow itemRow;
    private List<String> list;
    private LayoutInflater layoutInflater;


        public ResultAdapter(Activity context, List list) {
            super(context, R.layout.result, list);
            this.context = context;
            this.list = list;
        }
@Override
public View getView(int position,View convertView,ViewGroup parent){
        View rowView=convertView;
        if(rowView==null){
        itemRow=new ListItemRow();
        layoutInflater=context.getLayoutInflater();
        rowView=layoutInflater.inflate(R.layout.list_item,null,true);
        itemRow.photo=(ImageView)rowView.findViewById(R.id.imageView1);
        itemRow.title=(TextView)rowView.findViewById(R.id.text1);
        rowView.setTag(itemRow);
        }else{
        itemRow=(ListItemRow)rowView.getTag();
        }
  String s=  list.get(position).toString();
         itemRow.title.setText(list.get(position).toString());

    if (s.contains("(Kritik)")) {
        itemRow.photo.setImageResource(R.drawable.folder);

    } else if (s.contains("(Normal)")) {
       itemRow.photo.setImageResource(R.drawable.folder);

    }
        return rowView;

        }


private class ListItemRow {
    private ImageView photo;
    private TextView title;
}
}