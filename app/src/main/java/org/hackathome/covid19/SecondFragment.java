package org.hackathome.covid19;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;

import org.hackathome.covid19.model.Misurazione;
import org.hackathome.covid19.model.Paziente;
import org.hackathome.covid19.model.Sintomi;
import org.hackathome.covid19.rest.RestManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextInputEditText sTempe = view.findViewById(R.id.textInputEditText2);
        final TextInputEditText sNote = view.findViewById(R.id.textInputEditText);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Misurazione mis = new Misurazione();
                Paziente paziente = new Paziente("666", "Pinco", "Pallino");
                Sintomi sintomi = new Sintomi();
                List<Sintomi> listaSintomi = new ArrayList<>();
                Sintomi tosse = new Sintomi(0, "Tosse");
                Sintomi affaticamento = new Sintomi(1, "Affaticamento respiratorio");
                Sintomi raffreddore = new Sintomi(2, "Raffreddore");

                if(R.id.checkBox == 1)
                    listaSintomi.add(tosse);
                if(R.id.checkBox2 == 1)
                    listaSintomi.add(affaticamento);
                if(R.id.checkBox3 == 1)
                    listaSintomi.add(raffreddore);

                mis.setId(0);

                mis.setNote(sNote.getText().toString());

                mis.setTemperaturaCorporea(Float.valueOf(sTempe.getText().toString()));
                mis.setPaziente(paziente);
                mis.setSintomi(listaSintomi);

                long millis = new Date().getTime();
                mis.setData(Long.toString(millis));

                Log.i("Sending", mis.toString());

                RestManager.getInstance(getContext()).postMisurazione (mis,
                response -> {

                    }, error -> {
                    Log.i("SecondFragment", "Error");
                });

                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }




}
