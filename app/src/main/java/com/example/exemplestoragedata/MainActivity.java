package com.example.exemplestoragedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btnInternal;
    Context _context;
    FileOutputStream fosData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _context = this;
        btnInternal = findViewById(R.id.btnInternalStorage);

        btnInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monFichier = "FichierData.txt";
                String data = "Hello World ! ";


                try {
                    //Ouverture du fichier
                    fosData = openFileOutput(monFichier, MODE_PRIVATE);
                    //Ecriture dans le dossier
                    fosData.write(data.getBytes());
                    //On ferme le fichier
                    fosData.close();
                } catch (FileNotFoundException e) {
                    Log.e("Main - FosOpen :", e.getMessage());
                } catch (IOException e) {
                    Log.e("Main - FosClose :", e.getMessage());
                }
            }
        });
    }
}
