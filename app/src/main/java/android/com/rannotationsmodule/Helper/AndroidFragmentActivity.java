package android.com.rannotationsmodule.Helper;

import android.app.Fragment;
import android.app.FragmentManager;
import android.com.rannotations.Binder;
import android.com.rannotationsmodule.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

@Binder(binder = {"rl_"})
public class AndroidFragmentActivity extends AppCompatActivity {
    RelativeLayout rl_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_fragment);
        FragmentManager manager = getFragmentManager();
        Fragment newFragment = new Form_Android_Fragment();
        manager.beginTransaction().replace(R.id.fl_Container, newFragment).commit();
    }
}
