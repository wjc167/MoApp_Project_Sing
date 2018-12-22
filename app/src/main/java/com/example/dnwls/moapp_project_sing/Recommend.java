package com.example.dnwls.moapp_project_sing;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class Recommend extends Fragment {
    TextView Name;
    TextView Singer;
    ImageView albumcover;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = getLayoutInflater().inflate(R.layout.fragment_recommend, container, false);

        Name = (TextView)view.findViewById(R.id.textView);
        Singer = (TextView)view.findViewById(R.id.textView2);
        albumcover = (ImageView)view.findViewById(R.id.imageView);

        ImageButton Likebtn = (ImageButton) view.findViewById(R.id.imageButton);
        ImageButton nextbtn = (ImageButton) view.findViewById(R.id.imageButton4);

        Likebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (Name.getText().equals("마음")){
                    Name.setText("열애중");
                    Singer.setText("벤");
                    albumcover.setImageResource(R.drawable.ben);
                }
                else {
                    Name.setText("마음");
                    Singer.setText("아이유");
                    albumcover.setImageResource(R.drawable.aaa);
                }
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Name.setText("Dance The Night Away");
                Singer.setText("트와이스");
                albumcover.setImageResource(R.drawable.a);
            }
        });

        return view;
    }
}
