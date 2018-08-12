package com.example.simha.presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ControlActivity extends AppCompatActivity {

    public static final String PRESENTATION_NAME = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        Button btnnext = (Button) findViewById(R.id.present_next);
        Button btnprev = (Button) findViewById(R.id.present_previous);
        Button btnup = (Button) findViewById(R.id.present_up);
        Button btndown = (Button) findViewById(R.id.present_down);
        final Button btnplay = (Button) findViewById(R.id.button_play_pause_toggle);
        TextView presentName = (TextView) findViewById(R.id.present_name);

        final String present_name = (String) getIntent().getExtras().get(PRESENTATION_NAME);
        presentName.setText(present_name);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("presentations/" + present_name).setValue("next");
                mDatabase.child("presentations/" + present_name).setValue("");
            }
        });

        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("presentations/" + present_name).setValue("prev");
                mDatabase.child("presentations/" + present_name).setValue("");
            }
        });

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

                Drawable.ConstantState background = ((Drawable) btnplay.getBackground()).getConstantState();
                Drawable.ConstantState background2 = ((Drawable) ContextCompat.getDrawable(ControlActivity.this, R.drawable.play_icon)).getConstantState();

                if (background.equals(background2))
                {
                    btnplay.setBackground(ContextCompat.getDrawable(ControlActivity.this, R.drawable.pause_icon));
                    mDatabase.child("presentations/" + present_name).setValue("auto");
                }
                else
                {
                    btnplay.setBackground(ContextCompat.getDrawable(ControlActivity.this, R.drawable.play_icon));
                    mDatabase.child("presentations/" + present_name).setValue("pause");

                }

                mDatabase.child("presentations/" + present_name).setValue("");

            }
        });


        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("presentations/" + present_name).setValue("up");
                mDatabase.child("presentations/" + present_name).setValue("");
            }
        });

        btndown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("presentations/" + present_name).setValue("down");
                mDatabase.child("presentations/" + present_name).setValue("");
            }
        });
    }
}
