package androidsugar.paulck.androidsugar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by paulck on 9/2/2016.
 */

public class test extends Activity {
    public static Activity activity;
    private helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        activity = this;

        initData();
        initUI();


    }

    private void initData() {
        helper = new helper(activity);
        helper.storePrefrence(
                new String[]{
                        "foo",
                        "var",
                        "baz"},
                new String[]{
                        "foo",
                        "var",
                        "baz"}
        );

        String[] test = helper.getPrefrence(new String[]{
                "foo",
                "var",
                "baz"});

        for (int i = 0; i < test.length; i++) {
            Log.d("aap", test[i]);

        }

        helper.removePrefrence(new String[]{
                "foo",
                "var",
                "baz"});
        helper.clearPrefrence();

    }

    private void initUI() {

        helper.alertBuilderConfirm("title", "msg", "yes", null);



    }
}
