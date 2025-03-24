package com.github.warren_bank.share_forwarding.settings;

import com.github.warren_bank.share_forwarding.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingsUtils {

  public static SharedPreferences getPrefs(Context context) {
    return PreferenceManager.getDefaultSharedPreferences(context);
  }

  // --------------------

  public static boolean getForceChooserDialogPreference(Context context) {
    return getForceChooserDialogPreference(context, getPrefs(context));
  }

  private static boolean getForceChooserDialogPreference(Context context, SharedPreferences prefs) {
    String pref_key     = context.getString(R.string.pref_force_chooser_dialog_key);
    String pref_default = context.getString(R.string.pref_force_chooser_dialog_default);
    boolean val_default = "true".equals(pref_default);

    return prefs.getBoolean(pref_key, val_default);
  }

  // --------------------

  public static boolean getForceTextHtmlPreference(Context context) {
    return getForceTextHtmlPreference(context, getPrefs(context));
  }

  private static boolean getForceTextHtmlPreference(Context context, SharedPreferences prefs) {
    String pref_key     = context.getString(R.string.pref_force_text_html_key);
    String pref_default = context.getString(R.string.pref_force_text_html_default);
    boolean val_default = "true".equals(pref_default);

    return prefs.getBoolean(pref_key, val_default);
  }

  // --------------------

  public static boolean getConvertGeoToGoogleMapsPreference(Context context) {
    return getConvertGeoToGoogleMapsPreference(context, getPrefs(context));
  }

  private static boolean getConvertGeoToGoogleMapsPreference(Context context, SharedPreferences prefs) {
    String pref_key     = context.getString(R.string.pref_convert_geo_to_googlemaps_key);
    String pref_default = context.getString(R.string.pref_convert_geo_to_googlemaps_default);
    boolean val_default = "true".equals(pref_default);

    return prefs.getBoolean(pref_key, val_default);
  }

}
