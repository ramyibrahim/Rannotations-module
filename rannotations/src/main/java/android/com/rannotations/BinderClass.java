package android.com.rannotations;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by Ramy on 7/15/2018.
 */

public class BinderClass {
    Context context;
    Field[] fields;
    Binder annotation;
    Fragment fragment;
    View FView;
    public BinderClass(Context context){
        this.context = context;
        this.fields = context.getClass().getFields();
        annotation = context.getClass().getAnnotation(Binder.class);
    }

    public BinderClass(Fragment fragment) {
        this.fields = fragment.getClass().getFields();
        this.annotation = fragment.getClass().getAnnotation(Binder.class);
        this.context = fragment.getActivity();
        this.fragment = fragment;
    }


    public void bind(){
        for(Field field : fields) {
            if(annotation.binder().equalsIgnoreCase("") || field.getName().toString().startsWith(annotation.binder())){
                int resID = context.getResources().getIdentifier(field.getName().substring(annotation.binder().length()), "id", context.getPackageName());
                View view = ((Activity)context).findViewById(resID);
                try {
                    if(fragment != null)
                        field.set(fragment,view);
                    else
                        field.set(context,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Log.i("BinderClass",field.getName()+" : "+ field.getType().toString());
            }
        }
    }
}
