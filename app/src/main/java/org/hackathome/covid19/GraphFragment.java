package org.hackathome.covid19;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


/*import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;*/

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.util.Date;
import java.util.ListIterator;

import org.hackathome.covid19.model.Misurazione;

import java.util.ArrayList;

public class GraphFragment extends Fragment {

/*    LineChart chart;
    SeekBar seekBarX, seekBarY;
    TextView tvX, tvY;*/
    
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graphs, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LineGraphSeries<DataPoint> temperature;

        ListIterator<Misurazione> cycle = null;
        cycle = MainActivity.listaDiProva.listIterator();

        float y;
        int x = 0;

        GraphView temperatureGraph = view.findViewById(R.id.temperature_graph);
        temperature = new LineGraphSeries<DataPoint>();

        while(cycle.hasNext()){
            y = cycle.next().getTemperaturaCorporea();
            temperature.appendData(new DataPoint(x, y), true, 100);
            x = x+1;
        }
        temperatureGraph.addSeries(temperature);
    }
}