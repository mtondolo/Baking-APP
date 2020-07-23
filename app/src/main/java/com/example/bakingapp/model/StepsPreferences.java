package com.example.bakingapp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.bakingapp.R;

public class StepsPreferences {

  /*  // Returns true if the user prefers to see notifications from COMESANews, false otherwise.
    public static boolean isTwoPane(Context context) {
        String twoPaneKey = context.getString(R.string.pref_two_pane_key);
        boolean isTwoPaneDefault = context
                .getResources()
                .getBoolean(R.bool.is_two_pane_by_default);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        boolean shouldDisplayNotifications = sp
                .getBoolean(twoPaneKey, isTwoPaneDefault);
        return shouldDisplayNotifications;
    }*/

    // Returns the status of the pane
    public static boolean getPaneStatus(Context context) {
        String paneStatusKey = context.getString(R.string.pref_pane_status_flag);
        boolean paneStatusDefault = context
                .getResources()
                .getBoolean(R.bool.pane_status_default);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        boolean paneStatus = sp.getBoolean(paneStatusKey, paneStatusDefault);
        //boolean shouldDisplayNotifications = sp.getBoolean(twoPaneKey, isTwoPaneDefault)
        return paneStatus;
    }

    // Saves the status of the pane
    public static void savePaneStatus(Context context, boolean paneStatus) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        String paneStatusKey = context.getString(R.string.pref_pane_status_flag);
        editor.putBoolean(paneStatusKey, paneStatus);
        editor.apply();
    }

}
