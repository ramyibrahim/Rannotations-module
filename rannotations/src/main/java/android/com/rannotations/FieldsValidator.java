package android.com.rannotations;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;



import java.lang.reflect.Field;

/**
 * Created by Ramy on 7/9/2018.
 */

public class FieldsValidator {
    Context context;
    Field[] fields;
    public FieldsValidator(Context context){
        this.context = context;
        this.fields = context.getClass().getFields();

    }
    public FieldsValidator(Fragment fragment){
        this.context = fragment.getContext();
        this.fields = fragment.getClass().getFields();
    }

    public FieldsValidator(android.app.Fragment fragment) {
        this.context = fragment.getActivity();
        this.fields = fragment.getClass().getFields();
    }

    public String validate(){
        String Error = "";
        for(Field field : fields) {
            Validator annotation = (Validator)field.getAnnotation(Validator.class);
            if(annotation != null) {
                int resID = annotation.id() == 0 ? context.getResources().getIdentifier(field.getName(), "id", context.getPackageName()):annotation.id();
                EditText edit = ((Activity)context).findViewById(resID);
                String text = edit.getText().toString();
                if(annotation.is_trimable()){
                    text = text.trim();
                }
                Log.i("validator",field.getName()+" : "+ edit.getText());
                if(annotation.required() && text.equalsIgnoreCase("")){
                    Error = field.getName()+" "+context.getString(R.string.cannotbeempty);
                }else if(annotation.required() && annotation.min_length() > text.length()){
                    Error = field.getName()+" "+context.getString(R.string.cannotbelessthan)+ " "+ annotation.min_length()+" "+context.getString(R.string.character);
                }else if(!annotation.required() && text.length() > 0 && annotation.min_length() > text.length()){
                    Error = field.getName()+" "+context.getString(R.string.cannotbelessthan)+ " "+ annotation.min_length()+" "+context.getString(R.string.character);
                }else if(annotation.required() && annotation.max_length() < text.length()){
                    Error = field.getName()+" "+context.getString(R.string.cannotbelongerthan)+ " "+ annotation.max_length()+" "+context.getString(R.string.character);
                }else if(!annotation.required()  && text.length() > 0 && annotation.max_length() < text.length()){
                    Error = field.getName()+" "+context.getString(R.string.cannotbelongerthan)+ " "+ annotation.max_length()+" "+context.getString(R.string.character);
                }else if(annotation.only_numeric() && !TextUtils.isDigitsOnly(text)){
                    Error = field.getName()+" "+context.getString(R.string.shouldbedigits);
                }else if(annotation.is_email() && !(!TextUtils.isEmpty(text) && android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches())){
                    Error = field.getName()+" "+context.getString(R.string.notvalidemail);
                }

            }
        }
        return Error;
    }
}
