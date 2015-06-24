package com.example.c4q_ac35.justmygoogle;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by c4q-marbella on 6/23/15.
 */
public class SportAdapter extends ArrayAdapter<SportFields> {

    private Context context;
    private List<SportFields> sportList;

    public SportAdapter(Context context, List<SportFields> objects) {
        super(context, 0, objects);
    }

    //    public SportAdapter(Context context, List<SportFields> sportList) {
//        this.context = context;
//        this.sportList = sportList;
//    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_sports, parent, false);

        //Display flower name in the TextView widget
        SportFields sport = sportList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textView1);
        tv.setText(sport.getTitle());

        return view;
    }
}