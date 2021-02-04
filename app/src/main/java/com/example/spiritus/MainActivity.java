package com.example.spiritus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CardView cardView = (CardView) findViewById(R.id.bankcardId5);

        cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),DevotionActivity.class);
                startActivity(i);

            }
        });

        final CardView cardView2 = (CardView) findViewById(R.id.bankcardId4);

        cardView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent a = new Intent(getApplicationContext(),RequestActivity.class);
                startActivity(a);

            }
        });

        final Button button = (Button) findViewById(R.id.btn_facebook);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent a = new Intent(getApplicationContext(),ShowWebView.class);
                startActivity(a);

            }
        });



    }


}