package com.example.dnwls.moapp_project_sing;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.dnwls.moapp_project_sing.MainActivity.islogin;

public class loginActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    EditText Email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
    }

    public void loginClicked(View v){
        //에딧 테스트 정의
        Email = (EditText) findViewById(R.id.emailInput);
        password = (EditText) findViewById(R.id.passwordInput);

        //스트링 받기
        String emaildummy = Email.getText().toString();
        final String passworddummy = password.getText().toString();

        //DB연결
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("User").child(emaildummy).child("password").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue().equals(passworddummy)) {
                            islogin = true;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }
        );

        //같으면 다음연결
        if (islogin) {
            Intent intent = new Intent(this, MainActivity.class);//생성자에는 Context랑 서브클래스를 넘김 의미는 this 즉 메인이 sub.class 를 부름 이런뜻
            startActivity(intent);
        }
    }

    public void signupClicked(View v){
        Intent intent = new Intent(this, signupActivity.class);//생성자에는 Context랑 서브클래스를 넘김 의미는 this 즉 메인이 sub.class 를 부름 이런뜻
        startActivity(intent);
    }

    public class User {

        public String username;
        public String password;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

    }
}
