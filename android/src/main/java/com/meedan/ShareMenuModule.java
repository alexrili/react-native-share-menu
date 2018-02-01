package com.meedan;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import com.meedan.ShareMenuPackage;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class ShareMenuModule extends ReactContextBaseJavaModule {
  public ShareMenuModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "ShareMenu";
  }

  protected void onNewIntent(Intent intent) {
    Activity mActivity = getCurrentActivity();
    mActivity.setIntent(intent);
  }  

  @ReactMethod
  public void getSharedText(Callback successCallback) {
    Activity mActivity = getCurrentActivity();
    Intent intent = mActivity.getIntent();
    String inputText = intent.getStringExtra(Intent.EXTRA_TEXT);
    successCallback.invoke(inputText);
  }

  @ReactMethod
  public void getSharedFile(Callback successCallback) {
    Activity mActivity = getCurrentActivity();
    Intent intent = mActivity.getIntent();
    Uri inputFile = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
    if(inputFile !=null) {
      successCallback.invoke(inputFile.toString());
    }else{
      successCallback.invoke("");
    }
  }


  @ReactMethod
  public void clearSharedText() {
    Activity mActivity = getCurrentActivity();
    Intent intent = mActivity.getIntent();
    intent.removeExtra(Intent.EXTRA_TEXT);
  }

  @ReactMethod
  public void clearSharedFile() {
    Activity mActivity = getCurrentActivity();
    Intent intent = mActivity.getIntent();
    intent.removeExtra(Intent.EXTRA_STREAM);
  }
}
