package com.example.dnwls.moapp_project_sing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signupActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    EditText Email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    //가입
    public void signup2Clicked(View v){
        Email = (EditText) findViewById(R.id.emailcreate);
        password = (EditText) findViewById(R.id.passwordcreate);

        String Emaildummy = Email.getText().toString();
        String passworddummy = password.getText().toString();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        writeNewUser(Emaildummy, "더미", passworddummy);

        finish();
    }

    //취소
    public void cancelClicked(View v){
        finish();
    }

    public class User {
        public String username;
        public String password;
        public String level;
        public String like;
        public String fast;
        public String banung;
        public String drum;
        public String count;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
            this.level = "5";
            this.like = "5";
            this.fast = "5";
            this.banung = "5";
            this.drum = "5";
            this.count = "0";
}

    }

private void writeNewUser(String userId, String name, String password) {
        User user = new User(name, password);

        mDatabase.child("User").child(userId).setValue(user);
        }

}
