package ca.uottawa.uvaug070.homerepair;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class timepickerfragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    ArrayList<String> availability;
    String day;
    String status;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog a = new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));


        return a;
    }


    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        day=getArguments().getString("day");
        availability=getArguments().getStringArrayList("availability");
        status=getArguments().getString("status");
        ListView tv1= (ListView) getActivity().findViewById(R.id.ass);


        availability.add(day+" "+status+" Hour: "+view.getCurrentHour()+" Minute: "+view.getCurrentMinute());


        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getActivity(), R.layout.simple_list_item_1,availability);

        tv1.setAdapter(arrayAdapter2);
        //tv1.setText("Hour: "+view.getCurrentHour()+" Minute: "+view.getCurrentMinute());




    }
}