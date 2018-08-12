package com.example.simha.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn = (Button) findViewById(R.id.addBtn);
        Button controlBtn = (Button) findViewById(R.id.controlBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                final String present_name = ((EditText) findViewById(R.id.addName)).getText().toString();

                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        if (present_name.equals("")){
                            Toast.makeText(MainActivity.this, "Presentation name can not be empty", Toast.LENGTH_LONG).show();
                        }

                        else if (snapshot.hasChild("presentations/" + present_name)) {
                            Toast.makeText(MainActivity.this, "Presentation already exists", Toast.LENGTH_LONG).show();
                        }

                        else{
                            mDatabase.child("presentations/" + present_name).setValue("init");
                            Intent intent = new Intent(MainActivity.this, ControlActivity.class);
                            intent.putExtra(ControlActivity.PRESENTATION_NAME, (String ) present_name);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });
            }
        });

        controlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                final String present_name = ((EditText) findViewById(R.id.controlName)).getText().toString();

                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        if (present_name.equals("")){
                            Toast.makeText(MainActivity.this, "Presentation name can not be empty", Toast.LENGTH_LONG).show();
                        }

                        else if (snapshot.hasChild("presentations/" + present_name)) {
                            Intent intent = new Intent(MainActivity.this, ControlActivity.class);
                            intent.putExtra(ControlActivity.PRESENTATION_NAME, (String ) present_name);
                            startActivity(intent);
                        }

                        else{
                            Toast.makeText(MainActivity.this, "No such presentation exists", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });
            }
        });
    }
}
