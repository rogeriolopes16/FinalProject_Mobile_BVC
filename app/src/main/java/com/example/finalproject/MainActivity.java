package com.example.finalproject;

import static com.example.finalproject.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    String timeSystem;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

    }

    public void main_page_login(View view){
        timeSystem = new SimpleDateFormat("EEE, MMM d, h:mm a").format(Calendar.getInstance().getTime());
        try{
            TextView email = findViewById(id.email_register);
            TextView pass = findViewById(id.pass_register);
            TextView login_error = findViewById(id.login_error);
            ImageView logged = findViewById(id.btn_home);
            Log.d("email", email.getText().toString());
            Log.d("pass", pass.getText().toString());


            String email_db, pass_db;

            SharedPreferences prefs = getSharedPreferences("profile", MODE_PRIVATE);
            SharedPreferences.Editor edit = prefs.edit();
            email_db = prefs.getString("email", "");
            pass_db = prefs.getString("pass", "");


            if (email.getText().toString().equals(email_db) && pass.getText().toString().equals(pass_db)){
                setContentView(layout.main_page);
                TextView tView = findViewById(id.time_result);
                tView.setText(timeSystem);
            }else{
                login_error.setText("Login error!");
            }

        }catch (Exception e){
            setContentView(layout.main_page);
            TextView tView = findViewById(id.time_result);
            tView.setText(timeSystem);
        }




    }

    public void profile_page(View view){
        timeSystem = new SimpleDateFormat("EEE, MMM d, h:mm a").format(Calendar.getInstance().getTime());
        setContentView(layout.profile_page);
        TextView tView = findViewById(id.time_result);
        tView.setText(timeSystem);

        TextView name_register = findViewById(id.name_profile);
        TextView email_register = findViewById(id.email_profile);
        TextView phone_register = findViewById(id.phone_profile);
        TextView card_register = findViewById(id.card_profile);

        String name, email, phone, card;

        SharedPreferences prefs = getSharedPreferences("profile", MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        name = prefs.getString("name", "");
        email = prefs.getString("email", "");
        phone = prefs.getString("phone", "");
        card = prefs.getString("card", "");

        name_register.setText(name);
        email_register.setText(email);
        phone_register.setText(phone);
        card_register.setText(card);
    }

    public void payment_page(View view){
        timeSystem = new SimpleDateFormat("EEE, MMM d, h:mm a").format(Calendar.getInstance().getTime());
        setContentView(layout.payment_page);
        TextView tView = findViewById(id.time_result);
        tView.setText(timeSystem);

        TextView p_hist = findViewById(R.id.p_hist);
        try{

            String p_history;

            SharedPreferences prefs = getSharedPreferences("tickets", MODE_PRIVATE);
            SharedPreferences.Editor edit = prefs.edit();
            p_history = prefs.getString("purchased_tickets", "");

            Log.d("purchase_history", p_history);
            p_hist.setText(p_history);

        }catch (Exception e){
            setContentView(layout.payment_page);
        }
    }
    public void success_page(View view){
        timeSystem = new SimpleDateFormat("EEE, MMM d, h:mm a").format(Calendar.getInstance().getTime());
        String purchased_hist;

        TextView purchased_tickets = findViewById(id.purchased_tickets);
        Log.d("purchased_tickets", purchased_tickets.getText().toString());

        SharedPreferences prefs = getSharedPreferences("tickets", MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        try{
            purchased_hist = prefs.getString("purchased_tickets", "");
            purchased_hist += timeSystem +" | "+ purchased_tickets.getText().toString() + " tickets \n";
        }catch (Exception e){
            purchased_hist = timeSystem +" | "+ purchased_tickets.getText().toString() + " tickets \n";
        }

        edit.putString("purchased_tickets", purchased_hist);
        edit.commit();

        setContentView(layout.success_page);
        TextView tView = findViewById(id.time_result);
        tView.setText(timeSystem);

    }

    public void register(View view){
        timeSystem = new SimpleDateFormat("EEE, MMM d, h:mm a").format(Calendar.getInstance().getTime());
        setContentView(layout.register_page);
    }

    public void location_page(View view){
        timeSystem = new SimpleDateFormat("EEE, MMM d, h:mm a").format(Calendar.getInstance().getTime());
        setContentView(layout.location_page);
        TextView tView = findViewById(id.time_result);
        tView.setText(timeSystem);

        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:51.0467693,-114.0611634"));
        i.setClassName("com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity");
        startActivity(i);
    }

    public void save_register(View view){
        timeSystem = new SimpleDateFormat("EEE, MMM d, h:mm a").format(Calendar.getInstance().getTime());
        TextView name_register = findViewById(id.name_register);
        TextView email_register = findViewById(id.email_register);
        TextView pass_register = findViewById(id.pass_register);
        TextView phone_register = findViewById(id.phone_register);
        TextView card_register = findViewById(id.card_register);

        Log.d("name", name_register.getText().toString());
        Log.d("email", email_register.getText().toString());
        Log.d("pass", pass_register.getText().toString());
        Log.d("phone", phone_register.getText().toString());
        Log.d("card", card_register.getText().toString());

        SharedPreferences prefs = getSharedPreferences("profile", MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("name", name_register.getText().toString());
        edit.putString("email", email_register.getText().toString());
        edit.putString("pass", pass_register.getText().toString());
        edit.putString("phone", phone_register.getText().toString());
        edit.putString("card", card_register.getText().toString());
        edit.commit();


        setContentView(layout.activity_main);
    }

    public void exit_app(View view){
        timeSystem = new SimpleDateFormat("EEE, MMM d, h:mm a").format(Calendar.getInstance().getTime());
        finishAffinity();
    }



}

