package com.news.fanabc.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SettingManager {


    private static final String PREFS_NAME = "fana_bc_pref";
    private final SharedPreferences.Editor editor;
    private SharedPreferences prefs;

    private final String language_key = "language_key";

    private Lang_Type.LangCode settingLanguage;

    public static SettingManager SettingBuilder(Context context){
        return new SettingManager(context);
    }
    public SettingManager(Context context){

        prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
    }

    public Lang_Type.LangCode getSettingLanguage() {
        String language_string = this.prefs.getString(language_key, null);
        if(language_string != null){
            return Lang_Type.LangCode.valueOf(language_string);
        }
        return Lang_Type.LangCode.valueOf("Amharic");
    }

    public void setSettingLanguage(Lang_Type.LangCode settingLanguage) {
        editor.putString(language_key, settingLanguage.name());
        editor.apply();
    }
}
