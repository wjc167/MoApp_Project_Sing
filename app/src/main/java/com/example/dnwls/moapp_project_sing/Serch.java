package com.example.dnwls.moapp_project_sing;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Serch extends Fragment {
    private static final String TAG = "imagesearchexample";
    public static final int LOAD_SUCCESS = 101;
    private String URL1 = "https://api.manana.kr/karaoke/song/";
    private String URL2 = "/kumyoung.json";

    private ProgressDialog progressDialog = null;
    private SimpleAdapter adapter = null;
    private List<HashMap<String,String>> songinfoList = null;
    private EditText searchKeyword = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = getLayoutInflater().inflate(R.layout.fragment_serch, container, false);


//        Button buttonRequestJSON = (Button)v.findViewById(R.id.button_main_requestjson);
//        textviewJSONText = (TextView)v.findViewById(R.id.textview_main_jsontext);
//        textviewJSONText.setMovementMethod(new ScrollingMovementMethod());

        Button buttonRequestJSON = (Button)v.findViewById(R.id.button_main_requestjson);
        ListView listviewPhtoList = (ListView)v.findViewById(R.id.listview_main_list);
        searchKeyword = (EditText)v.findViewById(R.id.edittext_main_searchkeyword);

        songinfoList = new ArrayList<HashMap<String,String>>();

        String[] from = new String[]{"no", "title","singer","composer","release"};
        int[] to = new int[] {R.id.textview_main_listviewdata1, R.id.textview_main_listviewdata2,
                R.id.textview_main_listviewdata3, R.id.textview_main_listviewdata4};
        adapter = new SimpleAdapter(getActivity(), songinfoList, R.layout.listview_items, from, to);
        listviewPhtoList.setAdapter(adapter);


        buttonRequestJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog( getActivity() );
                progressDialog.setMessage("데이터를 받는 중");
                progressDialog.show();

                String keyword = searchKeyword.getText().toString();
                getJSON(keyword);
            }
        });

        return v;
    }




    private final MyHandler mHandler = new MyHandler(this);


    private static class MyHandler extends Handler {
        private final WeakReference<Serch> weakReference;

        public MyHandler(Serch serch) {
            weakReference = new WeakReference<Serch>(serch);
        }

        @Override
        public void handleMessage(Message msg) {

            Serch serch = weakReference.get();

            if (serch != null) {
                switch (msg.what) {

                    case LOAD_SUCCESS:
                        serch.progressDialog.dismiss();
                        serch.adapter.notifyDataSetChanged();
                        break;
                }
            }
        }
    }




    public void  getJSON(final String keyword) {

        if ( keyword == null) return;

        Thread thread = new Thread(new Runnable() {

            public void run() {

                String result;

                try {

                    Log.d(TAG, URL1+keyword+URL2);
                    URL url = new URL(URL1+keyword+URL2);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


//                    httpURLConnection.setReadTimeout(3000);
//                    httpURLConnection.setConnectTimeout(3000);
//                    httpURLConnection.setDoOutput(true);
//                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setRequestMethod("GET");
//                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.connect();


                    int responseStatusCode = httpURLConnection.getResponseCode();

                    InputStream inputStream;
                    if (responseStatusCode == HttpURLConnection.HTTP_OK) {

                        inputStream = httpURLConnection.getInputStream();
                    } else {
                        inputStream = httpURLConnection.getErrorStream();

                    }


                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder sb = new StringBuilder();
                    String line;


                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }

                    bufferedReader.close();
                    httpURLConnection.disconnect();

                    result = sb.toString().trim();


                } catch (Exception e) {
                    result = e.toString();
                }



                if (jsonParser(result)){

                    Message message = mHandler.obtainMessage(LOAD_SUCCESS);
                    mHandler.sendMessage(message);
                }
            }

        });
        thread.start();
    }



    public boolean jsonParser(String jsonString){


        if (jsonString == null ) return false;

        jsonString = jsonString.replace("[", "{\"song\":[");
        jsonString = jsonString.replace("]", "]}");

        try {
//            JSONObject jsonObject = new JSONObject(jsonString);
//            JSONArray song = jsonObject.getJSONArray("song");

            JSONArray song = new JSONObject(jsonString).getJSONArray("song");

            songinfoList.clear();

            for (int i = 0; i < song.length(); i++) {
                JSONObject songInfo = song.getJSONObject(i);

                String no = songInfo.getString("no");
                String title = songInfo.getString("title");
                String singer = songInfo.getString("singer");
                String composer = songInfo.getString("composer");

                HashMap<String, String> songinfoMap = new HashMap<String, String>();
                songinfoMap.put("no", no);
                songinfoMap.put("title", title);
                songinfoMap.put("singer", singer);
                songinfoMap.put("composer", composer);

                songinfoList.add(songinfoMap);

            }

            return true;
        } catch (JSONException e) {

            Log.d(TAG, e.toString() );
        }

        return false;
    }

}