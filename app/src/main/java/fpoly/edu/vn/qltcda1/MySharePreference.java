package fpoly.edu.vn.qltcda1;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharePreference {
    private static  final String MY_SHARED_PREFERENCE= "MY_SHARED_PREFERENCE";
    private Context context;

    public MySharePreference(Context context){
        this.context=context;
    }
    public void putBooleanValue(String key,boolean value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED_PREFERENCE,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    public boolean getBooleanValue(String key){
        SharedPreferences  sharedPreferences = context.getSharedPreferences(MY_SHARED_PREFERENCE,0);
        return  sharedPreferences.getBoolean(key,false);
    }
}
