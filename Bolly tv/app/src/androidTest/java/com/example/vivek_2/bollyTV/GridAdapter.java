package com.example.vivek_2.bollyTV;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class GridAdapter extends BaseAdapter {

    private String icons[];
    private String letters[];
    private Context context;

    private LayoutInflater inflater;

    public GridAdapter(Context context,String icons[],String letters[])
    {
        this.context=context;
        this.icons=icons;
        this.letters=letters;

    }



    @Override
    public int getCount() {
        return letters.length;
    }

    @Override
    public Object getItem(int position) {
        return letters[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View gridview=convertView;
        if(convertView==null)
        {
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridview=inflater.inflate(R.layout.custom_layout,null);
        }

        ImageView icon=(ImageView) gridview.findViewById(R.id.icons);
        TextView letter=(TextView) gridview.findViewById(R.id.letters);
        Picasso.get().load(icons[position]).into(icon);
        letter.setText(letters[position]);
        return gridview;
    }
}
