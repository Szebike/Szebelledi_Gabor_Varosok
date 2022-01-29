package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonFelvetel, buttonKereses;
    public EditText editTextOrszag;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        buttonFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFelvesz = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intentFelvesz);
                finish();
            }

        });

        buttonKereses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentKeres = new Intent(MainActivity.this, SearchResultActivity.class);
                if (editTextOrszag.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "az Ország mező üres", Toast.LENGTH_SHORT).show();

                } else {
                    String orszagKeres = editTextOrszag.getText().toString();
                    Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
                    intent.putExtra("OrszagKeres", editTextOrszag.getText().toString().trim());
                    startActivity(intentKeres);
                    finish();
                }


            }
        });
    }
    public void init(){
        buttonFelvetel = findViewById(R.id.buttonFelvetel);
        buttonKereses = findViewById(R.id.buttonKereses);
        editTextOrszag = findViewById(R.id.editTextOrszag);
        adatbazis = new DBHelper(this);
    }


}