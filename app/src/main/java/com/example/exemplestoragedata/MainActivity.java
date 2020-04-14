package com.example.exemplestoragedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.exemplestoragedata.Entities.Adherent;
import com.example.exemplestoragedata.Entities.Adherents;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    Button btnInternal;
    Button btnOuverture;
    Context _context;
    FileOutputStream fosData = null;
    FileInputStream fisData = null;
    String monFichier = "FichierData.txt";
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _context = this;
        btnInternal = findViewById(R.id.btnInternalStorage);
        btnOuverture = findViewById(R.id.btnOuverture);
        gson = new Gson();
        btnInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Adherent adherent1 = new Adherent();
                adherent1.setNom("toto");
                Adherent adherent2 = new Adherent();
                adherent2.setNom("tata");
                Adherents adherents = new Adherents();
                adherents.add(adherent1);
                adherents.add(adherent2);

                String a1 = gson.toJson(adherents);

                String data = a1;
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
        btnOuverture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput(monFichier);
                    InputStreamReader inputStreamReader = new InputStreamReader(fis);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.e("data", sb.toString());
                    inputStreamReader.close();
                    Toast.makeText(_context, sb.toString(), Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    Log.e("Main - FisOpenn :", e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("Main - FisRead :", e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}
