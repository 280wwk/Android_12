package com.example.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if (v.getId()==R.id.send_request) {
//            sendRequestWithHttpURLConnection();
            sendRequestWithOkHttp();
        }
    }
//            private void sendRequestWithHttpURLConnection() {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        HttpURLConnection connection = null;
//                        BufferedReader reader = null;
//                        try {
//                            URL url=new URL("http://www.baidu.com");
//                            connection=(HttpURLConnection)url.openConnection();
//                            connection.setRequestMethod("GET");
//                            connection.setConnectTimeout(8000);
//                            connection.setReadTimeout(8000);
//                            InputStream in =connection.getInputStream();
//                            reader = new BufferedReader(new InputStreamReader(in));
//                            StringBuilder response = new StringBuilder();
//                            String Line;
//                            while ((Line=reader.readLine())!=null){
//                                response.append(Line);
//                            }
//                            showResponse(response.toString());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        } finally {
//                            if (reader != null) {
//                                try {
//                                    reader.close();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                            if (connection != null){
//                                connection.disconnect();
//                    }
//                }
//            }
//        }).start();
//    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://php.weather.sina.com.cn/xml.php?city=%B1%B1%BE%A9&password=DJOYnieT8234jlsK&day=0")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                ???????????????UI????????????????????????????????????
                responseText.setText(response);
            }
        });
    }
}

