package com.openclassrooms.fr.restfulwebservicewithspring;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity {
    Button btnCallRestApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCallRestApi=(Button) findViewById(R.id.btnCallRestApi);

        btnCallRestApi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // call rest api
                new HttpReqTask().execute();
            }
        });
    }

    private class HttpReqTask extends AsyncTask<Void,Void,User[]>{

        @Override
        protected User[] doInBackground(Void... params) {
            try{
                String apiUrl="https://api.appery.io/rest/1/code/a84178eb-f16b-433b-98ab-275d5c2abca6/exec";
                RestTemplate restTemplate=new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                User[] users= restTemplate.getForObject(apiUrl,User[].class);
                return users;
            }catch (Exception ex){
                Log.e("",ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(User[] users) {
            super.onPostExecute(users);

            for(User u : users){
                Log.i("User: ","#############################");
                Log.i("user_id: ", String.valueOf(u.getId()));
                Log.i("user-content: ",u.getContent());

            }
        }
    }
}
