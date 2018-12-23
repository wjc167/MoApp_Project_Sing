package com.example.dnwls.moapp_project_sing;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.example.dnwls.moapp_project_sing.MainActivity.islogin;
import static com.example.dnwls.moapp_project_sing.MainActivity.user;
import static java.lang.Math.abs;


public class Recommend extends Fragment {
    TextView Name;
    TextView Singer;
    ImageView albumcover;
    DatabaseReference mDatabase;
    User mystate;
    ArrayList<HashMap> map2 = new ArrayList<HashMap>();
    List<String> list = new ArrayList();
    int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                // Inflate the layout for this fragment
        View view = getLayoutInflater().inflate(R.layout.fragment_recommend, container, false);

        Name = (TextView) view.findViewById(R.id.textView);
        Singer = (TextView) view.findViewById(R.id.textView2);
        albumcover = (ImageView) view.findViewById(R.id.imageView);

        ImageButton Likebtn = (ImageButton) view.findViewById(R.id.imageButton);
        ImageButton nextbtn = (ImageButton) view.findViewById(R.id.imageButton4);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        HashMap<String, String> map = (HashMap) dataSnapshot.child("User").child(user).getValue();
                        mystate = new User();
                        mystate.level = Integer.parseInt(map.get("level"));
                        mystate.like = Integer.parseInt(map.get("like"));
                        mystate.drum = Integer.parseInt(map.get("drum"));
                        mystate.fast = Integer.parseInt(map.get("fast"));
                        mystate.banung = Integer.parseInt(map.get("banung"));
                        mystate.count = Integer.parseInt(map.get("count"));

                        map2 = (ArrayList<HashMap>) dataSnapshot.child("Music").getValue();
                        final HashMap<String, Integer> songList = new HashMap<String, Integer>();

                        int score = 0;
                        for (int i = 0 ; i < map2.size(); i++){
                            score = 0;
                            score += abs(Integer.parseInt(map2.get(i).get("난이도").toString()) - mystate.level);
                            score += abs(Integer.parseInt(map2.get(i).get("속도").toString()) - mystate.level);
                            score += abs(Integer.parseInt(map2.get(i).get("박자감").toString()) - mystate.level);
                            score += abs(Integer.parseInt(map2.get(i).get("호응도").toString()) - mystate.level);
                            score += abs(Integer.parseInt(map2.get(i).get("인기").toString()) - mystate.level);
                            songList.put(Integer.toString(i), score);
                        }

                        list.addAll(songList.keySet());

                        Collections.sort(list, new Comparator(){
                            public int compare(Object o1, Object o2){
                                Object v1 = songList.get(o1);
                                Object v2 = songList.get(o2);

                                return ((Comparable) v1).compareTo(v2);
                            }
                        });

                        Name.setText(map2.get(Integer.parseInt(list.get(count))).get("노래제목").toString());
                        Singer.setText(map2.get(Integer.parseInt(list.get(count))).get("가수").toString());
                        albumcover.setImageResource(R.drawable.no_image);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }
        );

        Likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count += 1;
                Name.setText(map2.get(Integer.parseInt(list.get(count))).get("노래제목").toString());
                Singer.setText(map2.get(Integer.parseInt(list.get(count))).get("가수").toString());
                albumcover.setImageResource(R.drawable.no_image);
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count += 1;
                Name.setText(map2.get(Integer.parseInt(list.get(count))).get("노래제목").toString());
                Singer.setText(map2.get(Integer.parseInt(list.get(count))).get("가수").toString());
                albumcover.setImageResource(R.drawable.no_image);
            }
        });

        return view;
    }

    public class User {
        public int level;
        public int like;
        public int fast;
        public int banung;
        public int drum;
        public int count;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
            this.level = 0;
            this.like = 0;
            this.fast = 0;
            this.banung = 0;
            this.drum = 0;
            this.count = 0;
        }

        public void setlevel(int level){
            this.level = level;
        }

    }

}
