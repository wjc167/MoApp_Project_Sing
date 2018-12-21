package com.example.dnwls.moapp_project_sing;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Serch extends Fragment {

    private ProgressDialog progressDialog = null;
    private SimpleAdapter adapter = null;
    private EditText searchKeyword = null;
    private List<HashMap<String,String>> songList = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button buttonRequestJSON = (Button)getView().findViewById(R.id.searchbutton);
        ListView listviewPhtoList = (ListView)getView().findViewById(R.id.songListview);
        searchKeyword = (EditText)getView().findViewById(R.id.searchbox);

        songList = new ArrayList<HashMap<String,String>>();

        String[] from = new String[]{"가수","제목","번호"};
        int[] to = new int[] {R.id.singer, R.id.songname,R.id.songnumber};
        adapter = new SimpleAdapter(getActivity(), songList, R.layout.listview_items, from, to);
        listviewPhtoList.setAdapter(adapter);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_serch, container, false);
    }
}
