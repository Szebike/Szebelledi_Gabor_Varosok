package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    private EditText editTextNev, editTextOrszag, editTextLakossag;
    private Button buttonMentes, buttonVissza;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();
        buttonMentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adatRogzites();
            }
        });
        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMentesbolVissza = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intentMentesbolVissza);
                finish();
            }
        });

    }



    public void init(){
        editTextNev = findViewById(R.id.editTextNev);
        editTextOrszag= findViewById(R.id.editTextOrszag);
        editTextLakossag = findViewById(R.id.editTextLakossag);
        buttonMentes = findViewById(R.id.buttonMentes);
        buttonVissza = findViewById(R.id.buttonVissza);

        adatbazis = new DBHelper(InsertActivity.this);
    }
    public void adatRogzites(){
        String nev = editTextNev.getText().toString().trim();
        String orszag = editTextOrszag.getText().toString().trim();
        String lakossag = editTextLakossag.getText().toString().trim();
        if (nev.isEmpty()){
            Toast.makeText(this, "Név megadása kötelező", Toast.LENGTH_SHORT).show();
            editTextNev.setError("Név megadása kötelező");
            return;
        }
        if (orszag.isEmpty()){
            Toast.makeText(this, "Ország megadása kötelező", Toast.LENGTH_SHORT).show();
            editTextOrszag.setError("Ország megadása kötelező");
            return;
        }
        if (lakossag.isEmpty()){
            Toast.makeText(this, "JLakosság megadása kötelező", Toast.LENGTH_SHORT).show();
            editTextLakossag.setError("Lakosság megadása kötelező!");
            return;
        }
        int lakosszam = Integer.parseInt(lakossag);
        if (adatbazis.insert(nev, orszag, lakossag)){
            Toast.makeText(this, "Sikeres adatrögzítés", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            Toast.makeText(this, "Sikertelen adatrögzítés", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}