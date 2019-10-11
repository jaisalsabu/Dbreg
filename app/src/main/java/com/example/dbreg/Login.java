package com.example.dbreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText a10,a11;
    Button btm,bto;
   // SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // sharedPreferences=getSharedPreferences("asd",MODE_PRIVATE);
        a10=findViewById(R.id.editText2);
        a11=findViewById(R.id.editText4);
        btm=findViewById(R.id.button2);
        bto=findViewById(R.id.button3);
        //SharedPreferences.Editor editor=sharedPreferences.edit();
       // editor.putString("name",a10.getText().toString());
        //editor.apply();
        btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(a10.getText().toString().isEmpty() || a11.getText().toString().isEmpty()))
                {


                    StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://intown-film.000webhostapp.com/logintb/fetcher.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server

                                    Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();
                                    //Intent iso= new Intent(getApplicationContext(),HOME_page.class);
                                    //startActivity(iso);
                                    try {
                                        JSONArray jsonArray=new JSONArray(response);
                                        for(int i=0;i<jsonArray.length();i++){
                                            JSONObject json_obj = jsonArray.getJSONObject(i);

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                                }

                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
//Adding parameters to request

                            params.put("name",a10.getText().toString());
                            params.put("password",a11.getText().toString());

//returning parameter
                            return params;
                        }
                    };
                   Intent iso=new Intent(getApplicationContext(),HOME_page.class);
                    iso.putExtra("name",a10.getText().toString());
                   startActivity(iso);
//Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                    requestQueue.add(stringRequest);
                }
                else
                {
                    Toast.makeText(Login.this,"INVALID CREDENTIALS",Toast.LENGTH_LONG).show();
                }
            }
        });
    bto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent ibe= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(ibe);
        }
    });
    }
}
