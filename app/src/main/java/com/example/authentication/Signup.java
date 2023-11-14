package com.example.authentication;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    EditText name, email, password;
    Button registerButton;
    TextView gotoLoginPage;

    private FirebaseAuth authentication;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        authentication=FirebaseAuth.getInstance();
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.Password);
        registerButton=findViewById(R.id.SignUp);
        gotoLoginPage=findViewById(R.id.textView);
        registerButton.setOnClickListener(view ->{
            String fullname=name.getText().toString();
            String emailID=email.getText().toString();
            String passwords=password.getText().toString();
            if(TextUtils.isEmpty(fullname)) {
                Toast.makeText(Signup.this, "Please enter your full name",Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(emailID)){
                    Toast.makeText(Signup.this,"Please enter your email id",Toast.LENGTH_SHORT).show();

            }
            else if(TextUtils.isEmpty((CharSequence) password)){
                Toast.makeText(Signup.this,"Please enter your password",Toast.LENGTH_SHORT).show();

            }else{
                authentication.createUserWithEmailAndPassword(emailID,passwords).addOnCompleteListener(task-> {
                    if(task.isSuccessful()){
                        Toast.makeText(Signup.this,"Success",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(Signup.this,"Signup Failed",Toast.LENGTH_SHORT).show();

                    }
                        }

                );
            }


        });
        gotoLoginPage.setOnClickListener(view->{
            Intent intent= new Intent(Signup.this,SignIn.class);
            startActivity(intent);
            finish();
                }

        );

    }
}