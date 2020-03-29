package org.hackathome.covid19;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.hackathome.covid19.model.Misurazione;
import org.hackathome.covid19.rest.RestManager;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FirstFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private TextView mTextHead;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.recycler);
        mTextHead = view.findViewById(R.id.textview_first);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        getMisurazioni();
    }


    private ArrayList<Misurazione> mMisurazioni = new ArrayList<>();

    private void getMisurazioni() {

        RestManager.getInstance(getContext()).getMisurazioni("666",
                response -> {
                    Log.i("MAIN", response.toString());

                    for (Misurazione m: response) {
                        Log.i("FIRST", m.toString());
                        mMisurazioni.add(m);
                    }

                    mTextHead.setText(mMisurazioni.get(0).getPaziente().getNome() +  " "+
                            mMisurazioni.get(0).getPaziente().getCognome());

                    refreshRecyclerView();
                }, error -> {
                    Log.i("MAIN", "Error");
                });

    }

    private void refreshRecyclerView() {
        MisurazioniAdapter listAdapter = new MisurazioniAdapter(mMisurazioni);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(listAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public class MisurazioniAdapter extends RecyclerView.Adapter<MisurazioniAdapter.MisurazioneViewHolder> {
        private List<Misurazione> listOfItems;


    MisurazioniAdapter(List<Misurazione> listOfItems) {
            this.listOfItems = listOfItems;
        }

        @NonNull
        @Override
        public MisurazioniAdapter.MisurazioneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View viewLayout =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_misurazione, parent, false);

            return new MisurazioniAdapter.MisurazioneViewHolder(viewLayout);
        }

        @Override
        public void onBindViewHolder(@NonNull MisurazioniAdapter.MisurazioneViewHolder holder, int position) {
            Misurazione item = listOfItems.get(position);

            if (item.getData() != null) {
                Log.i("TAG", item.getData());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
                String dateString = formatter.format(new Date(Long.parseLong(item.getData())));
                holder.data.setText(dateString);
            } else {
                holder.data.setText("Nessuna data");
            }


            holder.misurazione.setText(Float.toString(item.getTemperaturaCorporea()));
        }

        @Override
        public int getItemCount() {
            return listOfItems.size();
        }

        class MisurazioneViewHolder extends RecyclerView.ViewHolder {
            TextView data;
            TextView misurazione;

            MisurazioneViewHolder(View itemView) {
                super(itemView);
                data = itemView.findViewById(R.id.text_data);
                misurazione = itemView.findViewById(R.id.text_misurazione);
            }
        }
    }

}
