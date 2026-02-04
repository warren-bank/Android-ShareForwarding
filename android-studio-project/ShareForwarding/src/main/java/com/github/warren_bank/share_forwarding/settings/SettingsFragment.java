package com.github.warren_bank.share_forwarding.settings;

import com.github.warren_bank.share_forwarding.R;

import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends PreferenceFragment {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.preferences);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);

    // fix for Android 15+ edge-to-edge layout enforcement
    if ((view != null) && (Build.VERSION.SDK_INT >= 14))
      view.setFitsSystemWindows(true);

    return view;
  }
}
