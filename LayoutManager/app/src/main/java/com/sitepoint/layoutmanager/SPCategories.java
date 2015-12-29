package com.sitepoint.layoutmanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by chrisward on 28/12/15.
 */

public class SPCategories extends BaseAdapter {

    private Context context;
    private String[] texts = {"HTML", "JS", "PHP", "Ruby", "Mobile", "Design"};

    public SPCategories(Context context) {
        this.context = context;
    }

    public int getCount() {
        return 5;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv;
        if (convertView == null) {
            tv = new TextView(context);
            tv.setLayoutParams(new GridView.LayoutParams(200, 85));
        }
        else {
            tv = (TextView) convertView;
        }

        tv.setText(texts[position]);
        return tv;
    }
}