package com.example.dcl.course_registration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    int[] flag;
    String[] languageName;
    LayoutInflater layoutInflater;

    public CustomAdapter(Context context, int[] flag, String[] languageName) {
        this.context = context;
        this.flag = flag;
        this.languageName = languageName;

    }

    @Override
    public int getCount() {
        return languageName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           view = layoutInflater.inflate(R.layout.sample_view,viewGroup,false);
        }

        ImageView imageView = view.findViewById(R.id.imageIV);
        TextView textView  = view.findViewById(R.id.textTV);

        imageView.setImageResource(flag[i]);
        textView.setText(languageName[i]);
        return view;
    }
}
