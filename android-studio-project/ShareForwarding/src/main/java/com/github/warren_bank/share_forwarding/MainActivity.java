package com.github.warren_bank.share_forwarding;

import com.github.warren_bank.share_forwarding.settings.SettingsUtils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Patterns;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent intent = getIntent();
    String action = intent.getAction();
    String text   = intent.getStringExtra(Intent.EXTRA_TEXT);

    if (Intent.ACTION_SEND.equals(action) && (text != null) && !text.isEmpty()) {
      handle_text(text);
    }
    finish();
  }

  // IMPORTANT:
  //   - check email before web
  //   - check GPS before phone
  private boolean handle_text(String text) {
    return
        handle_email_address(text)
     || handle_web_url(text)
     || handle_map_gps_coordinates(text)
     || handle_phone(text)
    ;
  }

  // https://developer.android.com/guide/components/intents-common.html#Email
  private boolean handle_email_address(String text) {
    boolean handled = false;
    Pattern pattern = Patterns.EMAIL_ADDRESS;
    Matcher matcher = pattern.matcher(text);
    if (matcher.find()) {
      String[] addresses = new String[]{ matcher.group() };
      Intent intent = new Intent(Intent.ACTION_SENDTO);
      intent.setData(Uri.parse("mailto:"));
      intent.putExtra(Intent.EXTRA_EMAIL, addresses);
      start_activity(intent);
      handled = true;
    }
    return handled;
  }

  // https://developer.android.com/guide/components/intents-common.html#Browser
  private boolean handle_web_url(String text) {
    boolean handled = false;
    Pattern pattern = Patterns.WEB_URL;
    Matcher matcher = pattern.matcher(text);
    if (matcher.find()) {
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setData(Uri.parse(matcher.group()));
      start_activity(intent);
      handled = true;
    }
    return handled;
  }

  // https://developer.android.com/guide/components/intents-common.html#Maps
  // https://stackoverflow.com/a/18690202
  private boolean handle_map_gps_coordinates(String text) {
    boolean handled = false;
    Pattern pattern = Pattern.compile("([-+]?(?:[1-8]?\\d(?:\\.\\d+)?|90(?:\\.0+)?)),\\s*([-+]?(?:180(?:\\.0+)?|(?:1[0-7]\\d|[1-9]?\\d)(?:\\.\\d+)?))");
    Matcher matcher = pattern.matcher(text);
    if (matcher.find()) {
      String latitude  = matcher.group(1);
      String longitude = matcher.group(2);
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setData(Uri.parse("geo:" + latitude + "," + longitude));
      start_activity(intent);
      handled = true;
    }
    return handled;
  }

  // https://developer.android.com/guide/components/intents-common.html#Phone
  private boolean handle_phone(String text) {
    boolean handled = false;
    Pattern pattern = Patterns.PHONE;
    Matcher matcher = pattern.matcher(text);
    if (matcher.find()) {
      Intent intent = new Intent(Intent.ACTION_DIAL);
      intent.setData(Uri.parse("tel:" + matcher.group()));
      start_activity(intent);
      handled = true;
    }
    return handled;
  }

  private void start_activity(Intent intent) {
    try {
      if (SettingsUtils.getForceChooserDialogPreference(MainActivity.this)) {
        intent = createChooser(intent);
      }

      startActivity(intent);
    }
    catch(ActivityNotFoundException e) {
      Toast.makeText(
        MainActivity.this,
        getString(R.string.toast_no_app_is_installed_for) + ":\n\n" + intent.getDataString(),
        Toast.LENGTH_SHORT
      ).show();
    }
    catch(Exception e) {
      String message = e.getMessage();
      if (message != null) {
        Toast.makeText(
          MainActivity.this,
          message,
          Toast.LENGTH_SHORT
        ).show();
      }
    }
  }

  private Intent createChooser(Intent intent) {
    List<Intent> initialIntents = new ArrayList<Intent>();

    try {
      PackageManager pm = getPackageManager();
      int flags = (Build.VERSION.SDK_INT >= 23) ? PackageManager.MATCH_ALL : 0;
      List<ResolveInfo> infos = pm.queryIntentActivities(intent, flags);

      for (ResolveInfo info : infos) {
        Intent in = (Intent) intent.clone();
        in.setClassName(
          info.activityInfo.packageName,
          info.activityInfo.name
        );
        initialIntents.add(in);
      }
    }
    catch(Exception e) {}

    Intent chooserIntent = Intent.createChooser(intent, /* title= */ null);
    if (!initialIntents.isEmpty()) {
      chooserIntent.putExtra(
        Intent.EXTRA_INITIAL_INTENTS,
        initialIntents.toArray(
          new Parcelable[initialIntents.size()]
        )
      );
    }
    return chooserIntent;
  }

}
