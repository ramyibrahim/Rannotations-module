package android.com.rannotationsmodule.Helper;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.com.rannotations.Binder;
import android.com.rannotations.BinderClass;
import android.com.rannotations.FieldsValidator;
import android.com.rannotations.Validator;
import android.com.rannotationsmodule.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Alaa on 7/18/2018.
 */
@Binder(binder = {"edt_","btn_"})
@SuppressLint("ValidFragment")
class Form_Android_Fragment extends Fragment {
    @Validator(required = true, min_length = 3,id = R.id.firstname)
    EditText edt_firstname;
    @Validator(min_length = 8,id = R.id.lastname, required = true)
    EditText edt_lastname;
    @Validator(min_length = 4,id = R.id.username)
    EditText edt_username;
    @Validator(required = true, is_trimable = false,id = R.id.password)
    EditText edt_password;
    @Validator(required = true, is_email = true,id = R.id.email)
    EditText edt_email;
    @Validator(required = true, only_numeric = true,id = R.id.mobile)
    EditText edt_mobile;
    Button btn_validate;

    public Form_Android_Fragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        new BinderClass(this).bind();
        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FieldsValidator validator = new FieldsValidator(Form_Android_Fragment.this);
                String response = validator.validate();
                if(!response.equalsIgnoreCase("")){
                    Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"submitted",Toast.LENGTH_LONG).show();
                }
            }
        });

        if(edt_firstname != null){
            edt_firstname.setText("Alaa");
            Toast.makeText(getActivity(),"success",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getActivity(),"fail",Toast.LENGTH_LONG).show();
        }
    }
}
