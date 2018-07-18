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
    android.app.Fragment app_fragment;
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

    public BinderClass(android.app.Fragment fragment) {
        this.fields = fragment.getClass().getFields();
        this.annotation = fragment.getClass().getAnnotation(Binder.class);
        this.context = fragment.getActivity();
        this.app_fragment = fragment;
    }


    public void bind(){
        for(Field field : fields) {
            int binder_index = isExistInArray(field.getName().toString(),annotation.binder());
            if(annotation.binder().length == 0 || binder_index >= 0){
                try {
                    int resID = context.getResources().getIdentifier(field.getName().substring(annotation.binder()[binder_index].length()), "id", context.getPackageName());
                    if(resID > 0){
                        View view = ((Activity)context).findViewById(resID);
                        if(fragment != null)
                            field.set(fragment,view);
                        else if(app_fragment != null)
                            field.set(app_fragment,view);
                        else
                            field.set(context,view);
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Log.i("BinderClass",field.getName()+" : "+ field.getType().toString());
            }
        }
    }

    private int isExistInArray(String s, String[] binders) {
        for(int i = 0 ; i < binders.length; i++){
            if(s.startsWith(binders[i]))
                return i;
        }
        return -1;
    }
}
