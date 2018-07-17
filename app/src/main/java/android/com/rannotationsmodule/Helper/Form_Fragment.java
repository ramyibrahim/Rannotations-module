package android.com.rannotationsmodule.Helper;

import android.annotation.SuppressLint;
import android.com.rannotations.Binder;
import android.com.rannotations.BinderClass;
import android.com.rannotations.FieldsValidator;
import android.com.rannotations.Validator;
import android.com.rannotationsmodule.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ramy on 17/07/18.
 */
@Binder(binder = "anno_")
@SuppressLint("ValidFragment")
class Form_Fragment extends Fragment {
    private View myFragmentView;
    @Validator(required = true, min_length = 5,id = R.id.firstname)
    EditText anno_firstname;
    @Validator(min_length = 8,id = R.id.lastname)
    EditText anno_lastname;
    @Validator(required = true,min_length = 4,id = R.id.username)
    EditText anno_username;
    @Validator(required = true, is_trimable = false,id = R.id.password)
    EditText anno_password;
    @Validator(required = true, is_email = true,id = R.id.email)
    EditText anno_email;
    @Validator(required = true, only_numeric = true,id = R.id.mobile)
    EditText anno_mobile;

    public Form_Fragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (myFragmentView == null) {
            myFragmentView = inflater.inflate(R.layout.activity_main, container, false);

        }
        return myFragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        new BinderClass(this).bind();
        final Button validate = (Button)myFragmentView.findViewById(R.id.validate);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FieldsValidator validator = new FieldsValidator(Form_Fragment.this);
                String response = validator.validate();
                if(!response.equalsIgnoreCase("")){
                    Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"submitted",Toast.LENGTH_LONG).show();
                }
            }
        });

        if(anno_firstname != null){
            anno_firstname.setText("Ramy");
            Toast.makeText(getActivity(),"success",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getActivity(),"fail",Toast.LENGTH_LONG).show();
        }

    }
}
