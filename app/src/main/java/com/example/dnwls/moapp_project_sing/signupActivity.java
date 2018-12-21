package com.example.dnwls.moapp_project_sing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class signupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }
    public void signup2Clicked(View v){
        Intent intent = new Intent(this, loginActivity.class);//생성자에는 Context랑 서브클래스를 넘김 의미는 this 즉 메인이 sub.class 를 부름 이런뜻
        startActivity(intent);
    }

    public void cancelClicked(View v){
        Intent intent = new Intent(this, loginActivity.class);//생성자에는 Context랑 서브클래스를 넘김 의미는 this 즉 메인이 sub.class 를 부름 이런뜻
        startActivity(intent);
    }

}
