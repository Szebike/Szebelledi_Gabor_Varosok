package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResultActivity extends AppCompatActivity {
    private Button buttonVissza;
    private TextView textViewAdatok;
    private DBHelper adatbazis;
    private MainActivity OrszagKeres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Intent intent = getIntent();
        String OrszagKeres = intent.getStringExtra("OrszagKeres");
        init();
        adatlekerdezes();

        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVissza = new Intent(SearchResultActivity.this, MainActivity.class);
                startActivity(intentVissza);
                finish();
            }
        });
    }

    public void adatlekerdezes(){

        Cursor adatok = adatbazis.SearchResult();
        if (adatok==null){
            Toast.makeText(this, "Hiba történt a lekérdezés során", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adatok.getCount()==0){
            Toast.makeText(this, "Még nincs felvéve adat!", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            StringBuilder builder = new StringBuilder();
            builder.append("Választott ország:").append(OrszagKeres).append("\n");
            while (adatok.moveToNext()){
                    builder.append("Város név:").append(adatok.getString(1)).append("\n");
            }
            textViewAdatok.setText(builder);
            Toast.makeText(this, "Sikeres adatlekérdezés", Toast.LENGTH_SHORT).show();
        }
    }
    public void init(){
        buttonVissza = findViewById(R.id.buttonVissza2);
        textViewAdatok = findViewById(R.id.textviewAdatok);
        adatbazis = new DBHelper(this);
        textViewAdatok.setMovementMethod(new ScrollingMovementMethod());
    }

}
