package ca.uottawa.uvaug070.homerepair;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

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
        ListView tv1= (ListView) getActivity().findViewById(R.id.availlist);
        boolean status1;

        List<String> a = validatelist(day, status);


        if (a==null){
            availability.add(day + " " + status + " Hour: " + view.getCurrentHour() + " Minute: " + view.getCurrentMinute());
            ArrayAdapter arrayAdapter2 = new ArrayAdapter(getActivity(), R.layout.simple_list_item_1, availability);
            tv1.setAdapter(arrayAdapter2);
            //tv1.setText("Hour: "+view.getCurrentHour()+" Minute: "+view.getCurrentMinute());
        }
        else if ((Integer.parseInt(a.get(0))> hourOfDay)&&(status=="Closing")) {
            Toast.makeText(getActivity().getApplicationContext(), "Please select a valid Time", Toast.LENGTH_LONG).show();

        }
        else if ((Integer.parseInt(a.get(0))< hourOfDay)&&(status=="Opening")) {
            Toast.makeText(getActivity().getApplicationContext(), "Please select a valid Time", Toast.LENGTH_LONG).show();

        }
        else if (((Integer.parseInt(a.get(0)))==hourOfDay)&&(Integer.parseInt(a.get(1))>minute)&&(status=="Closing")){
            Toast.makeText(getActivity().getApplicationContext(), "Please select a valid Time", Toast.LENGTH_LONG).show();
        }
        else if (((Integer.parseInt(a.get(0)))==hourOfDay)&&(Integer.parseInt(a.get(1))<minute)&&(status=="Opening")){
            Toast.makeText(getActivity().getApplicationContext(), "Please select a valid Time", Toast.LENGTH_LONG).show();
        }
        else{
            availability.add(day + " " + status + " Hour: " + view.getCurrentHour() + " Minute: " + view.getCurrentMinute());
            ArrayAdapter arrayAdapter2 = new ArrayAdapter(getActivity(), R.layout.simple_list_item_1, availability);
            tv1.setAdapter(arrayAdapter2);
        }


    }


    private List<String> validatelist(String day, String status){
        String a = "";
        String b = "";
        List<String> item = null;        List<String> item1=null;
        Iterator<String> iterator = availability.iterator();
        while (iterator.hasNext()){
            String temp=iterator.next();

            if (temp.contains(day)&&status=="Opening"){

                String a1 = temp.replaceAll(day, "").replaceAll(" Closing Hour: ","").replaceAll("Minute: ","");
                //a1= something like 18 09

                item1 = Arrays.asList(a1.split(" "));
            }
            if (temp.contains(day)&&status=="Closing"){
                String b1 = temp.replaceAll(day, "").replaceAll(" Opening Hour: ","").replaceAll("Minute: ","");

                item1=Arrays.asList(b1.split(" "));

            }

        }

        return item1;

    }
}