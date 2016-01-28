package com.sitepoint.marvelmagic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chrisward on 23/01/16.
 */
public class Character {
    public String name;
    public String description;

    public Character(JSONObject object){
        try {
            this.name = object.getString("name");
            this.description = object.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Character> fromJson(JSONArray jsonObjects) {
        ArrayList<Character> characters = new ArrayList<Character>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                characters.add(new Character(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return characters;
    }

}