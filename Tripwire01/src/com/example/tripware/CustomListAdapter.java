package com.example.tripware;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
public class CustomListAdapter extends ArrayAdapter<String> {
    private Activity context;
    private ListItemRow itemRow;
    private List<String> list;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Activity context, List list) {
        super(context, R.layout.main, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            itemRow = new ListItemRow();
            layoutInflater = context.getLayoutInflater();
            rowView = layoutInflater.inflate(R.layout.list, null, true);
            itemRow.photo = (ImageView) rowView.findViewById(R.id.imgIcon);
            itemRow.title = (TextView) rowView.findViewById(R.id.txtTitle);
            rowView.setTag(itemRow);
        } else {
            itemRow = (ListItemRow) rowView.getTag();
        }


        itemRow.photo.setImageResource(R.drawable.folder);

        itemRow.title.setText(list.get(position).toString());
        return rowView;
    }

    // ------------ Listedeki Satırların içinde bulunacak olan componentler ------------ //
    private class ListItemRow {
        private ImageView photo;
        private TextView title;
    }

}
