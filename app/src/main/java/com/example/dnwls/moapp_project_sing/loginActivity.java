package com.example.dnwls.moapp_project_sing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static com.example.dnwls.moapp_project_sing.MainActivity.islogin;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    public void loginClicked(View v){
        islogin=true;
        Intent intent = new Intent(this, MainActivity.class);//생성자에는 Context랑 서브클래스를 넘김 의미는 this 즉 메인이 sub.class 를 부름 이런뜻
        startActivity(intent);
    }

    public void signupClicked(View v){
        Intent intent = new Intent(this, signupActivity.class);//생성자에는 Context랑 서브클래스를 넘김 의미는 this 즉 메인이 sub.class 를 부름 이런뜻
        startActivity(intent);
    }
}
