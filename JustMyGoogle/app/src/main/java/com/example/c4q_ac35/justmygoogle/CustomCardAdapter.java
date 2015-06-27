package com.example.c4q_ac35.justmygoogle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by c4q-marbella on 6/27/15.
 */
public class CustomCardAdapter extends ArrayAdapter<Feed> {

    public CustomCardAdapter( Context context, List<Feed> sportsFeedList) {
        super(context,R.layout.sports_row_item, sportsFeedList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get row data
        Feed singleFeed = getItem(position);

        // create row view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sports_row_item, parent, false);
        }

        //locate row view children
        ImageView feedIcon = (ImageView) convertView.findViewById(R.id.sportsImageView);
        TextView feedTitle = (TextView) convertView.findViewById(R.id.feed_title);
        TextView feedCategory = (TextView) convertView.findViewById(R.id.feed_category);
        TextView feedDate= (TextView) convertView.findViewById(R.id.feed_date);

        //assign data values to view childen
        feedTitle.setText(singleFeed.getTitle());
        feedCategory.setText(singleFeed.getCategory());
        feedDate.setText(singleFeed.getPubDate());
        feedIcon.setImageResource(R.drawable.cloudy);

        return convertView;
    }
}
