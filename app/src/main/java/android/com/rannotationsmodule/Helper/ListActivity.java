package android.com.rannotationsmodule.Helper;

import android.com.rannotations.Binder;
import android.com.rannotations.BinderClass;
import android.com.rannotationsmodule.R;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

@Binder(binder = {"btn_"})
public class ListActivity extends AppCompatActivity {
    Button btn_normal,btn_f4,btn_nfrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        new BinderClass(this).bind();
        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        btn_f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListActivity.this,FragmentsActivity.class);
                startActivity(i);
            }
        });
        btn_nfrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListActivity.this,AndroidFragmentActivity.class);
                startActivity(i);
            }
        });
    }
}
