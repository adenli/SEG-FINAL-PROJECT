package ca.uottawa.uvaug070.homerepair;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class timepickerfragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog a = new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        // Create a new instance of TimePickerDialog and return it

        a.setTitle("penis");
        return a;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        EditText tv1=(EditText) getActivity().findViewById(R.id.ass);
        tv1.setText("Hour: "+view.getCurrentHour()+" Minute: "+view.getCurrentMinute());

    }
}