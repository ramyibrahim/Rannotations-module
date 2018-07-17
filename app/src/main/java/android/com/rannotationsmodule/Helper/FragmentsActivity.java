package android.com.rannotationsmodule.Helper;

import android.com.rannotationsmodule.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
        FragmentManager manager = this.getSupportFragmentManager();
        Fragment newFragment = new Form_Fragment();
        manager.beginTransaction().replace(R.id.fl_Container, newFragment).commit();
    }
}
