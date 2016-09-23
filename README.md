# Simple-Useful-Class-For-Android

Simple and useful class that for own purpose, including view's state, sharedpreferences and misclick check

#### [SharedPreferences](#sharedpreferences-method)
#### [View state controls](#view-state-controls-method)
#### [Misclick check](#misclick-check-method)
#### [AlertDialog Builder](#alertdialog-builder-method)

#Using the Helper class's methods

Very simple to use; Below code you can just simply copy and paste (remember to import library) and actually no need to pull this git:)

Some variable need to be initialized first
```java
    private Context mContext;
    private long mLastClickTime = 0;
```

Helper Constructor
```java
    Helper(Context context) {
        mContext = context;
    }
```

### SharedPreferences method

storePrefrence method
```java
   public void storePrefrence(String[] tag, String[] val) { // create prefrence and store prefrence use this same method
        SharedPreferences.Editor editor = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE).edit();
        for (int i = 0; i < tag.length; i++) {
            editor.putString(tag[i], val[i]).apply();
        }
    }
```

getPrefrence method
```java
    public String[] getPrefrence(String[] tag) { // Accept String type array
        SharedPreferences  editor = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE);
        for (int i = 0; i < tag.length; i++) {
            tag[i] = editor.getString(tag[i],null);
        }
        return tag;
    }
```

clearPrefrence method
```java
    public void clearPrefrence() { // All SharedPreferences will be erased
        SharedPreferences.Editor editor = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE).edit();
        editor.clear().apply();
    }
```

removePrefrence method
```java
    public void removePrefrence(String[] tag) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE).edit();
        for (int i = 0; i < tag.length; i++) {
            editor.remove(tag[i]).apply();
        }
    }
```

### View state controls method

stateControls method
```java
    public static void stateControls(boolean able, ViewGroup vg) { // able for control setEnabled value, vg accept View and its child that you want to control
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
        // mis-clicking prevention, using threshold of 1000 ms
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
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
        AlertDialog.Builder alert = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.AppThemeNoActionBar));
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
        AlertDialog.Builder alert = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.AppThemeNoActionBar));
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
