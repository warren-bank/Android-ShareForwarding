package com.github.warren_bank.share_forwarding.settings;

import com.github.warren_bank.share_forwarding.R;

import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class SettingsActivity extends PreferenceActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (Build.VERSION.SDK_INT >= 11) {
      getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }
    else {
      Toast.makeText(
        SettingsActivity.this,
        getString(R.string.toast_settings_requires_api_11),
        Toast.LENGTH_SHORT
      ).show();

      finish();
    }
  }
}
