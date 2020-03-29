package org.hackathome.covid19;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

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

import static org.hackathome.covid19.MainActivity.NAME;
import static org.hackathome.covid19.MainActivity.PATIENT_ID;
import static org.hackathome.covid19.MainActivity.SURNAME;

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

        final CheckBox sCheck1 = view.findViewById(R.id.checkBox);
        final CheckBox sCheck2 = view.findViewById(R.id.checkBox2);
        final CheckBox sCheck3 = view.findViewById(R.id.checkBox3);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Misurazione mis = new Misurazione();
                Paziente paziente = new Paziente(PATIENT_ID, NAME, SURNAME);
                Sintomi sintomi = new Sintomi();
                List<Sintomi> listaSintomi = new ArrayList<>();
                Sintomi tosse = new Sintomi(0, "Tosse");
                Sintomi affaticamento = new Sintomi(1, "Affaticamento respiratorio");
                Sintomi raffreddore = new Sintomi(2, "Raffreddore");

                if(sCheck1.isChecked())
                    listaSintomi.add(tosse);
                if(sCheck2.isChecked())
                    listaSintomi.add(affaticamento);
                if(sCheck3.isChecked())
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
