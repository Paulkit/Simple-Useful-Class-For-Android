package androidsugar.paulck.androidsugar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by paulck on 9/2/2016.
 */
public class Helper {
    private Context mContext;

    private long mLastClickTime = 0;

    Helper(Context context) {
        mContext = context;
    }

    public void storePrefrence(String[] tag, String[] val) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE).edit();
        for (int i = 0; i < tag.length; i++) {
            editor.putString(tag[i], val[i]).apply();
        }
    }
    public String[] getPrefrence(String[] tag) {
        SharedPreferences  editor = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE);
        for (int i = 0; i < tag.length; i++) {
            tag[i] = editor.getString(tag[i],null);
        }
        return tag;
    }

    public void clearPrefrence() {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE).edit();
        editor.clear().apply();
    }

    public void removePrefrence(String[] tag) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE).edit();
        for (int i = 0; i < tag.length; i++) {
            editor.remove(tag[i]).apply();
        }
    }

    public static void stateControls(boolean able, ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            child.setEnabled(able);
            if (child instanceof ViewGroup) {
                stateControls(able, (ViewGroup) child);
            }
        }
    }

    public boolean misClickCheck() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return false;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        return true;
    }

    public void alertBuilderConfirm(String title, String msg, String yes, DialogInterface.OnClickListener yesListen) {
        AlertDialog.Builder alert = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.AppThemeNoActionBar));
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.setCancelable(false);
        alert.setPositiveButton(yes, yesListen);
        alert.show();
    }

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

}
