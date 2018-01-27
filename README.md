# Simple Useful Class For Android

Simple and useful class that for own purpose, including view's state, sharedpreferences and misclick check

#### [SharedPreferences](#sharedpreferences-method)
#### [View state controls](#view-state-controls-method)
#### [Misclick check](#misclick-check-method)
#### [AlertDialog Builder](#alertdialog-builder-method)


This example using Helper.java as demo and you can complie and good to go. Still some setup need to be made by yourself (R.style.AppThemeNoActionBar for example)


Some variable need to be initialized first
```java
    private Context mContext;
    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    private long mLastClickTime = 0;
    private long misClickTime = 0;
    private AlertDialog.Builder alert;
```

Helper Constructor
```java
    Helper(Context context) {
        mContext = context;
        alert = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.AppThemeNoActionBar));
        prefs = mContext.getSharedPreferences(mContext.getPackageName(), mContext.MODE_PRIVATE);
        editor = prefs.edit();
    }
```

### SharedPreferences method

storePreference method
```java
    public void storePreference(String[] tags, String[] val) { // create preference and store preference also use this same method
        for (int i = 0; i < tags.length; i++) {
            editor.putString(tags[i], val[i]).apply();
        }
    }
```

getPreference method
```java
    public String[] getPrefrence(String[] tags) { // assign value to correspond position in tags array
        for (int i = 0; i < tags.length; i++) {
            tags[i] = prefs.getString(tags[i],null);
        }
        return tags;
    }

```

clearPreference method
```java
    public void clearPreference() { // All SharedPreferences will be erased
        editor.clear().apply();
    }
```

removePreference method
```java
  public void removePreference(String[] tags) {
        for (int i = 0; i < tags.length; i++) {
            editor.remove(tags[i]).apply();
        }
    }
```

### View state controls method

stateControls method
```java
    public static void stateControls(boolean able, ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            child.setEnabled(able);
            if (child instanceof ViewGroup) {
                stateControls(able, (ViewGroup) child);
            }
        }
    }
```

### Misclick check method

misClickCheck method
```java
    public boolean misClickCheck() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < misClickTime) {
            return false;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        return true;
    }
```

### AlertDialog Builder method

alertBuilderConfirm method
```java
    public void alertBuilderConfirm(String title, String msg, String yes, DialogInterface.OnClickListener yesListen) {
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.setCancelable(false);
        alert.setPositiveButton(yes, yesListen);
        alert.show();
    }
```

alertBuilder method
```java
    public void alertBuilder(String title, String msg, String yes, String no, DialogInterface.OnClickListener yesListen, DialogInterface.OnClickListener noListen) {
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.setCancelable(false);
        if (yes != null) {
            alert.setPositiveButton(yes, yesListen);
        }
        if (no != null) {
            alert.setNegativeButton(no, noListen);
        }
        alert.show();
    }
```
R.style.AppThemeNoActionBar
```xml
    <style name="AppThemeNoActionBar" parent="AppTheme.NoActionBar">
        <item name="android:textSize">@dimen/text_middle</item> <!-- set the text size you like in dimen.xml -->
        <item name="windowActionBar">false</item> 
        <item name="android:windowEnableSplitTouch">false</item>
        <item name="android:splitMotionEvents">false</item>
        <item name="windowNoTitle">true</item>
    </style>
```
