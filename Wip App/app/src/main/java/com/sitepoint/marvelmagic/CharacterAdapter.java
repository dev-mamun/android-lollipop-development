package com.sitepoint.marvelmagic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chrisward on 23/01/16.
 */
public class CharacterAdapter extends ArrayAdapter<Character> {
    public CharacterAdapter(Context context, ArrayList<Character> characters) {
        super(context, 0, characters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Character character = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_character, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.chName);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.chDescription);

        tvName.setText(character.name);
        tvDescription.setText(character.description);
        return convertView;
    }
}