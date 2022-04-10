package com.example.ecoledesloustics.db;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converters {
    @TypeConverter
    public static ArrayList<String> fromStringList(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static ArrayList<Long> fromLongList(String value) {
        Type listType = new TypeToken<ArrayList<Long>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String longFromArrayList(ArrayList<Long> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static HashMap<String, List<String>> fromStringMap(String value) {
        Type mapType = new TypeToken<HashMap<String, List<String>>>() {
        }.getType();
        return new Gson().fromJson(value, mapType);
    }

    @TypeConverter
    public static String fromMap(HashMap<String, List<String>> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
