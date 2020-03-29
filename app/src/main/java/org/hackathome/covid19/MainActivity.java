package org.hackathome.covid19;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.hackathome.covid19.model.Istanza;
import org.hackathome.covid19.model.Misurazione;
import org.hackathome.covid19.model.Paziente;
import org.hackathome.covid19.model.Sintomi;
import org.hackathome.covid19.rest.RestManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    // TODO: only for test purpose
    public static ArrayList<Istanza> listaDiProva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Istanza a = new Istanza();
        a.date = new Date(790124750475L);
        a.name = "Ponzio";
        a.surname = "Pilato";
        a.temperature = 37.6F;
        a.hasDifficoltàRespiratoria = true;

        Istanza b = new Istanza();
        b.date = new Date(890124750475L);
        b.name = "Ponzio";
        b.surname = "Pilato";
        b.temperature = 37.8F;
        b.hasDifficoltàRespiratoria = true;

        Istanza c = new Istanza();
        c.date = new Date(990124750475L);
        c.name = "Ponzio";
        c.surname = "Pilato";
        c.temperature = 38.3F;
        c.hasDifficoltàRespiratoria = true;
        c.hasInappetenza = true;

        Istanza d = new Istanza();
        d.date = new Date(1090124750475L);
        d.name = "Ponzio";
        d.surname = "Pilato";
        d.temperature = 39.9F;
        d.hasDifficoltàRespiratoria = true;
        d.hasTosseSecca = true;


        listaDiProva = new ArrayList<Istanza>();
        listaDiProva.add(a);
        listaDiProva.add(b);
        listaDiProva.add(c);
        listaDiProva.add(d);


        Paziente paziente = new Paziente("666", "Pinco", "Pallino");


        //int id, Paziente paziente, List<Sintomi> sintomi, float temperaturaCorporea,
        //			String altrePatologie, String note, String data)

        Sintomi sintomo_1 = new Sintomi(0, "Sifilide");
        Sintomi sintomo_2 = new Sintomi(0, "Gonorrea");

        List<Sintomi> listSintomi = new ArrayList<>();
        listSintomi.add(sintomo_1);
        listSintomi.add(sintomo_2);

        Misurazione mis = new Misurazione(0, paziente, listSintomi, 37.6F, "nessuna", "", "");



//
//        RestManager.getInstance(this).postMisurazione (mis,
//                response -> {
//                    //Log.i("MAIN", response.toString());
//                }, error -> {
//                    Log.i("MAIN", "Error");
//                });


        RestManager.getInstance(this).getMisurazioni("666",
                response -> {
                    Log.i("MAIN", response.toString());
                }, error -> {
                    Log.i("MAIN", "Error");
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
