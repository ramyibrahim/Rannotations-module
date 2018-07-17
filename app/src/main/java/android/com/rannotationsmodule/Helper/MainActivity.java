package android.com.rannotationsmodule.Helper;

import android.com.rannotations.Binder;
import android.com.rannotations.BinderClass;
import android.com.rannotations.FieldsValidator;
import android.com.rannotations.Validator;
import android.com.rannotationsmodule.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


@Binder(binder = "anno_")
public class MainActivity extends AppCompatActivity {
    @Validator(min_length = 4,id = R.id.firstname)
    EditText anno_firstname;
    @Validator(min_length = 5,id = R.id.lastname)
    EditText anno_lastname;
    @Validator(required = true,min_length = 5,id = R.id.username)
    EditText anno_username;
    @Validator(required = true, is_trimable = false,id = R.id.password)
    EditText anno_password;
    @Validator(required = true, is_email = true,id = R.id.email)
    EditText anno_email;
    @Validator(required = true, only_numeric = true,id = R.id.mobile)
    EditText anno_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new BinderClass(MainActivity.this).bind();
        final Button validate = (Button)findViewById(R.id.validate);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FieldsValidator validator = new FieldsValidator(MainActivity.this);
                String response = validator.validate();
                if(!response.equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                }else{
                    Intent i = new Intent(MainActivity.this,FragmentsActivity.class);
                    startActivity(i);
                }
            }
        });

        if(anno_firstname != null){
            anno_firstname.setText("Ramy");
            Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this,"fail",Toast.LENGTH_LONG).show();
        }

    }
}