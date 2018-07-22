# Rannotations-module
This library contains two main annotations:-

1- Binder annotation

		if you are getting sick form null pointer exceptions because you always 
		forget to bind your view reference using findViewById or you may try 
		to use the view reference before binding it to the right view, then 
		this annotation is the answer. only in 3 steps you can get rid of this repeated problem.
			
			I- copy the following lines to your gradles files, then sync:-
			
				allprojects {
					repositories {
						....
						maven { url 'https://jitpack.io' }
					}
				}
				dependencies {
					implementation 'com.github.ramyibrahim:Rannotations-module:-SNAPSHOT'
				}
			
			II- apply the Binder annotation as a class annottaion for any activity or Fragment class like this:
				@Binder(binder = {"edt_","btn_"})
				public class MainActivity extends AppCompatActivity {
					EditText edt_username,edt_password;
					Button btn_login;
					@Override
					protected void onCreate(Bundle savedInstanceState) {
							...............
					}
				}
				
				and here is the layout:- 
				
				<EditText
				android:hint="username"
				android:id="@+id/username"
				android:layout_width="match_parent"
				android:layout_height="wrap_content" />
				<EditText
				android:hint="password"
				android:id="@+id/password"
				android:layout_width="match_parent"
				android:layout_height="wrap_content" />
				<Button
				android:text="login"
				android:layout_gravity="center"
				android:id="@+id/login"
				android:layout_width="wrap_content"
				android:layout_height="wrap_content" />
				
				IMPORTAT!! please note you have to define one or more prefix in the binder array that you use in 
				your class view references as these prefixes will make the library distinguish view references and other references.
				i.e:- here i defined prefix "edt_" and "btn_" in the binder array that to make the class recognise the any reference
				name starts with one of these prefixes as a reference to view item in your layout so any reference name starts with 
				edt_ or btn_ will be considered as reference paramter to a view object.
				
				IMPORTANT!! the second part of the view reference name after the defined prefixes in binder array must be the id
				defined to the view in the layout so, for example
				i defined prefix "edt_" and "btn_" and i set the ids in the layout for the username Edittext to be "username"
				so the username reference parameter name must be edt_username or btn_username.
				
				please note you may define many prefixes to match "naming convension" but one is enough is you don't follow
				references naming convension or you can skip deifining the prefixes step but in this case all defined paramters
				in the class will be considered as view references.
			
			
			III- write the following line at the beginning of the onCreate method for activity or the onStart method of Fragment
				new BinderClass(this).bind();
	  	That's it you can use your declared references as now they are bound to right layout views. 
			
2- Validator annotation

 		this annotation will help you to set some restrictions on Edittext user inputs for example:-
		to set some field as required or set the minimum or maximum field length to use this annotation
		you have to setup your gradle project as shown in I above.
		II- apply the Validator annotation as follow:-
			@Validator(required = true,min_length = 5,max_length = 10)
    	EditText edt_username;
		this will apply the defined rules on the user input.
		please note don't forget to apply the rule (is_trimable = false) as the default is true on password 
		references as this rule will truncate any white spaces if it is true.
		
		III- to validate the user input with the rules you set you can do the following.
		 new FieldsValidator(this).validate();
		 this will return error message as a return String if there is any rule violates by the user input
		 and will return empty string if the user input is match the rules set by you.
