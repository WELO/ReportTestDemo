package untitled.example.com.reporttestdemo.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Amber_Chen on 2018/10/1.
 */

public class SharedPrefMgr {
    /**
     * For saving int data type.
     */
    public static void saveSharedPref(Context context, String key, int value, String category)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(category, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * For saving long data type.
     */
    public static void saveSharedPref(Context context, String key, long value, String category)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(category, Context.MODE_PRIVATE).edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * For saving boolean data type.
     */
    public static void saveSharedPref(Context context, String key, boolean value, String category)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(category, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * For saving String data type.
     */
    public static void saveSharedPref(Context context, String key, String value, String category)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(category, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * For saving bundle object.
     */
    public static void saveSharedPref(Context context, String key, Bundle bundle, String category)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(category, Context.MODE_PRIVATE).edit();

        Object object;
        for(String secondKey : bundle.keySet())
        {
            object = bundle.get(secondKey);

            if(object instanceof Integer)
                saveSharedPref(context, key + "_" + secondKey, (Integer) object, category);
            else if(object instanceof Long)
                saveSharedPref(context, key + "_" + secondKey, (Long) object, category);
            else if(object instanceof Boolean)
                saveSharedPref(context, key + "_" + secondKey, (Boolean) object, category);
            else if(object instanceof String)
                saveSharedPref(context, key + "_" + secondKey, (String) object, category);
        }

        editor.apply();
    }

    /**
     * For loading int data type.
     */
    public static int loadSharedPref(Context context, String key, int value, String category)
    {
        SharedPreferences sharedPref = context.getSharedPreferences(category, Context.MODE_PRIVATE);
        return sharedPref.getInt(key, value);
    }

    /**
     * For loading long data type.
     */
    public static long loadSharedPref(Context context, String key, long value, String category)
    {
        SharedPreferences sharedPref = context.getSharedPreferences(category, Context.MODE_PRIVATE);
        return sharedPref.getLong(key, value);
    }

    /**
     * For loading boolean data type.
     */
    public static boolean loadSharedPref(Context context, String key, boolean value, String category)
    {
        SharedPreferences sharedPref = context.getSharedPreferences(category, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, value);
    }

    /**
     * For loading String data type.
     */
    public static String loadSharedPref(Context context, String key, String value, String category)
    {
        SharedPreferences sharedPref = context.getSharedPreferences(category, Context.MODE_PRIVATE);
        return sharedPref.getString(key, value);
    }

    /**
     * For loading bundle object.
     */
    public static Bundle loadSharedPref(Context context, String key, String category)
    {
        Bundle bundle = new Bundle();

        SharedPreferences sharedPref = context.getSharedPreferences(category, Context.MODE_PRIVATE);
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) sharedPref.getAll();
        Iterator<String> iterator = map.keySet().iterator();

        Object object;
        while(iterator.hasNext())
        {
            String temp = iterator.next();
            if(temp.startsWith(key))
            {
                String secondKey = temp.split("_")[1];
                object = map.get(temp);

                if(object instanceof Integer)
                    bundle.putInt(secondKey, (Integer) object);
                else if(object instanceof Long)
                    bundle.putLong(secondKey, (Long) object);
                else if(object instanceof Boolean)
                    bundle.putBoolean(secondKey, (Boolean) object);
                else if(object instanceof String)
                    bundle.putString(secondKey, (String) object);
            }
        }

        return bundle;
    }

    /**
     * Remove the specific pair by key
     */
    public static void removeSharedPref(Context context, String key, String category)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(category, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * Clear all sharedPreference of specific file
     */
    public static void clearSharedPref(Context context, String category)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(category, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }
}
