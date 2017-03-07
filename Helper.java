import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by PaulCK on 9/2/2016.
 */
public class Helper {
    private Context mContext;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private long mLastClickTime = 0;
    private long misClickTime = 0;
    private AlertDialog.Builder alert;

    Helper(Context context) {
        mContext = context;
        editor = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE).edit();
        preferences = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE);
        alert = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.AppThemeNoActionBar));
    }

    public void storePreference(String[] tags, String[] val) {
        for (int i = 0; i < tags.length; i++) {
            editor.putString(tags[i], val[i]).apply();
        }
    }
    public String[] getPreference(String[] tags) {
        for (int i = 0; i < tags.length; i++) {
            tags[i] = preferences.getString(tags[i],null);
        }
        return tags;
    }

   public void removePreference(String[] tags) {
        for (int i = 0; i < tags.length; i++) {
            editor.remove(tags[i]).apply();
        }
    }

    public void clearPreference() {
        editor.clear().apply();
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
        if (SystemClock.elapsedRealtime() - mLastClickTime < misClickTime) {
            return false;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        return true;
    }

    public void alertBuilderConfirm(String title, String msg, String yes, DialogInterface.OnClickListener yesListen) {
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.setCancelable(false);
        alert.setPositiveButton(yes, yesListen);
        alert.show();
    }

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

}
