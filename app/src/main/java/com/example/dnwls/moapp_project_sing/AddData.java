package com.example.dnwls.moapp_project_sing;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class AddData extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_add_data, container, false);

        Button addbtn = (Button) view.findViewById(R.id.Addbtn);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "노래가 등록되었습니다.", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
