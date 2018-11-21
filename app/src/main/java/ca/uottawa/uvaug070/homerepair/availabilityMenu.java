package ca.uottawa.uvaug070.homerepair;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class availabilityMenu extends Fragment {
    ArrayList<String> availability = new ArrayList<String>();

    DatabaseReference availibilityUID = FirebaseDatabase.getInstance().getReference("times");
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        View view = inflater.inflate(R.layout.fragment_availability, container, false);
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        String a= b.getString("uid");
        DatabaseReference availibilityDatabase = FirebaseDatabase.getInstance().getReference("times").child(a);
        availibilityDatabase.addValueEventListener(new ValueEventListener() {

                                                  @Override

                                                  public void onDataChange(DataSnapshot dataSnapshot) {
                                                      availability.clear();

                                                      for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                                          String item= (String) postSnapshot.getValue();

                                                              availability.add(item);


                                                      }

                                                  }

                                                  @Override
                                                  public void onCancelled(DatabaseError databaseError) {

                                                  }
                                              });

            Spinner spin = (Spinner) view.findViewById(R.id.spinner);
        Spinner spin1= view.findViewById(R.id.spinner1);
        Spinner spin2= view.findViewById(R.id.spinner2);
        Spinner spin3= view.findViewById(R.id.spinner3);
        Spinner spin4= view.findViewById(R.id.spinner4);
        Spinner spin5= view.findViewById(R.id.spinner5);
        Spinner spin6= view.findViewById(R.id.spinner6);
//        String[] Spinnerlist={"Opening time","Closing time"};

//        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,Spinnerlist);
//        spin.setAdapter(arrayAdapter);
        spin.setEnabled(false);
        spin1.setEnabled(false);
        spin2.setEnabled(false);
        spin3.setEnabled(false);
        spin4.setEnabled(false);
        spin5.setEnabled(false);
        spin6.setEnabled(false);

        ListView availList = getActivity().findViewById(R.id.availlist);



        return view;
        //this will initialize the layout of the activity servproaddserv
        // you must add the functionality of the servproaddserv here ex: pulling list from firebase etc
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView tv1= (ListView) getActivity().findViewById(R.id.availlist);

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getActivity(), R.layout.simple_list_item_1,availability);

        tv1.setAdapter(arrayAdapter2);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Calendar");

    }

    private void search(String day){
        ArrayList<String> removeList = new ArrayList<>();
        ListView tv1= (ListView) getActivity().findViewById(R.id.availlist);
        Iterator<String> iterator = availability.iterator();

        Integer counter = -1;
        while(iterator.hasNext()){
           String temp = iterator.next();
            counter = counter + 1;
           if (temp.toString().contains(day)){
               removeList.add(temp);
           }
        }

        availability.removeAll(removeList);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getActivity(), R.layout.simple_list_item_1,availability);

        tv1.setAdapter(arrayAdapter2);
    }

    private boolean validatelist(String day, boolean c){
        String a = "";
        String b = "";
        List<String> item = null;
        List<String> item1=null;

        boolean exists=false;
        Iterator<String> iterator = availability.iterator();
        while (iterator.hasNext()){
            String temp=iterator.next();
            if (temp.contains(day)&&temp.contains("Closing")){
                a=temp;
                String a1 = temp.replaceAll(day, "").replaceAll(" Closing Hour: ","").replaceAll("Minute: ","");
                //a1= something like 18 09
                item = Arrays.asList(a1.split(" "));


            }
            if (temp.contains(day)&&temp.contains("Opening")){
                b=temp;
                String b1 = temp.replaceAll(day, "").replaceAll(" Opening Hour: ","").replaceAll("Minute: ","");
                item1 = Arrays.asList(b1.split(" "));
            }
        }


        if ((a.length()!=0)&&(b.length()!=0)){

            exists=true;
        }
        else if ((a.length()==0)&&((b.length()!=0))){

            if (c==true){
                exists=true;
            }
            else{
                exists=false;
            }
        }
        else if ((b.length()==0)&&((a.length()!=0))){

            if (c==false){
                exists=true;
            }
            else{
                exists=false;
            }
        }

        return exists;

    }


    @Override
    public void onStart() {

        super.onStart();

        Switch simpleSwitch = (Switch) getActivity().findViewById(R.id.monday);

        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Spinner spin = (Spinner) getActivity().findViewById(R.id.spinner);


            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on) {
                if (on) {

                    spin.setEnabled(true);
                    // Get Current Time


                    spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                            switch (position) {
                                case 1:
                                    DialogFragment newFragment = new timepickerfragment();
                                    boolean a = validatelist("Monday",true);
                                    if ((a == true)) {
                                        Toast.makeText(getActivity().getApplicationContext(), "A time was already set, to edit the times, double click the switch", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                    Bundle bundle = new Bundle();
                                    bundle.putStringArrayList("availability", availability);
                                    bundle.putString("day", "Monday");
                                    bundle.putString("status", "Opening");
                                    newFragment.setArguments(bundle);
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    spin.setSelection(0);
                                    break;
                                case 2:
                                    newFragment = new timepickerfragment();
                                    a = validatelist("Monday",false);
                                    if (a == true) {
                                        Toast.makeText(getActivity().getApplicationContext(), "A time was already set, to edit the times, double click the switch", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                    bundle = new Bundle();
                                    bundle.putStringArrayList("availability", availability);
                                    bundle.putString("day", "Monday");
                                    bundle.putString("status", "Closing");
                                    newFragment.setArguments(bundle);
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    spin.setSelection(0);
                                    break;
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }

                    });
                } else {
                    search("Monday");
                    spin.setEnabled(false);

                }
            }
        });

        Switch simpleSwitch1 = (Switch) getActivity().findViewById(R.id.tuesday);
        simpleSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Spinner spin1 = (Spinner) getActivity().findViewById(R.id.spinner1);


            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on) {
                if (on) {

                    spin1.setEnabled(true);
                    // Get Current Time


                    spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            switch (position) {
                                case 1:
                                    DialogFragment newFragment = new timepickerfragment();
                                    boolean a = validatelist("Tuesday",true);
                                    if (a == true) {
                                        Toast.makeText(getActivity().getApplicationContext(), "A time was already set, to edit the times, double click the switch", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                    Bundle bundle = new Bundle();
                                    bundle.putStringArrayList("availability", availability);
                                    bundle.putString("day", "Tuesday");
                                    bundle.putString("status", "Opening");
                                    newFragment.setArguments(bundle);
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    spin1.setSelection(0);
                                    break;
                                case 2:
                                    newFragment = new timepickerfragment();
                                    a = validatelist("Tuesday",false);
                                    if (a == true) {
                                        Toast.makeText(getActivity().getApplicationContext(), "A time was already set, to edit the times, double click the switch", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                    bundle = new Bundle();
                                    bundle.putStringArrayList("availability", availability);
                                    bundle.putString("day", "Tuesday");
                                    bundle.putString("status", "Closing");
                                    newFragment.setArguments(bundle);
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    spin1.setSelection(0);
                                    break;
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }

                    });
                } else {
                    search("Tuesday");
                    spin1.setEnabled(false);
                }
            }
        });


        Switch simpleSwitch2 = (Switch) getActivity().findViewById(R.id.wednesday);
        simpleSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Spinner spin2 = (Spinner) getActivity().findViewById(R.id.spinner2);


            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on) {
                if (on) {

                    spin2.setEnabled(true);
                    // Get Current Time


                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            switch (position) {
                                case 1:
                                    DialogFragment newFragment = new timepickerfragment();
                                    boolean a = validatelist("Wednesday",true);
                                    if (a == true) {
                                        Toast.makeText(getActivity().getApplicationContext(), "A time was already set, to edit the times, double click the switch", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                    Bundle bundle = new Bundle();
                                    bundle.putStringArrayList("availability", availability);
                                    bundle.putString("day", "Wednesday");
                                    bundle.putString("status", "Opening");
                                    newFragment.setArguments(bundle);
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    spin2.setSelection(0);
                                    break;
                                case 2:
                                    newFragment = new timepickerfragment();
                                    a = validatelist("Wednesday",false);
                                    if (a == true) {
                                        Toast.makeText(getActivity().getApplicationContext(), "A time was already set, to edit the times, double click the switch", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                    bundle = new Bundle();
                                    bundle.putStringArrayList("availability", availability);
                                    bundle.putString("day", "Wednesday");
                                    bundle.putString("status", "Opening");
                                    newFragment.setArguments(bundle);
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    spin2.setSelection(0);
                                    break;
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }

                    });
                } else {
                    search("Wednesday");
                    spin2.setEnabled(false);
                }
            }
        });

        Switch simpleSwitch3 = (Switch) getActivity().findViewById(R.id.thursday);
        simpleSwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Spinner spin3 = (Spinner) getActivity().findViewById(R.id.spinner3);


            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on) {
                if (on) {

                    spin3.setEnabled(true);
                    // Get Current Time


                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            switch (position) {
                                case 1:
                                    DialogFragment newFragment = new timepickerfragment();
                                    boolean a = validatelist("Thursday",true);
                                    if (a == true) {
                                        Toast.makeText(getActivity().getApplicationContext(), "A time was already set, to edit the times, double click the switch", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                    Bundle bundle = new Bundle();
                                    bundle.putStringArrayList("availability", availability);
                                    bundle.putString("day", "Thursday");
                                    bundle.putString("status", "Opening");
                                    newFragment.setArguments(bundle);
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    spin3.setSelection(0);
                                    break;
                                case 2:
                                    newFragment = new timepickerfragment();

                                    a = validatelist("Thursday",false);
                                    if (a == true) {
                                        Toast.makeText(getActivity().getApplicationContext(), "A time was already set, to edit the times, double click the switch", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                    bundle = new Bundle();
                                    bundle.putStringArrayList("availability", availability);
                                    bundle.putString("day", "Thursday");
                                    bundle.putString("status", "Closing");
                                    newFragment.setArguments(bundle);
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    spin3.setSelection(0);
                                    break;
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }

                    });
                } else {
                    search("Thursday");
                    spin3.setEnabled(false);
                }
            }
        });


        Switch simpleSwitch4 = (Switch) getActivity().findViewById(R.id.friday);
        simpleSwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Spinner spin4 = (Spinner) getActivity().findViewById(R.id.spinner4);


            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on) {
                if (on) {

                    spin4.setEnabled(true);
                    // Get Current Time

                    spin4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            switch (position) {
                                case 1:
                                    DialogFragment newFragment = new timepickerfragment();
                                    boolean a = validatelist("Friday",true);
                                    if (a == true) {
                                        Toast.makeText(getActivity().getApplicationContext(), "A time was already set, to edit the times, double click the switch", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                    Bundle bundle = new Bundle();
                                    bundle.putStringArrayList("availability", availability);
                                    bundle.putString("day", "Friday");
                                    bundle.putString("status", "Opening");
                                    newFragment.setArguments(bundle);
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    spin4.setSelection(0);
                                    break;
                                case 2:
                                    newFragment = new timepickerfragment();

                                    a = validatelist("Friday",false);
                                    if (a == true) {
                                        Toast.makeText(getActivity().getApplicationContext(), "A time was already set, to edit the times, double click the switch", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                    bundle = new Bundle();
                                    bundle.putStringArrayList("availability", availability);
                                    bundle.putString("day", "Friday");
                                    bundle.putString("status", "Closing");
                                    newFragment.setArguments(bundle);
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    spin4.setSelection(0);
                                    break;
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }

                    });
                } else {
                    search("Friday");
                    spin4.setEnabled(false);
                }
            }
        });


        Switch simpleSwitch5 = (Switch) getActivity().findViewById(R.id.saturday);
        simpleSwitch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Spinner spin5 = (Spinner) getActivity().findViewById(R.id.spinner5);


            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on) {
                if (on) {

                    spin5.setEnabled(true);
                    // Get Current Time


                    spin5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Object value = parent.getItemAtPosition(position);
                            switch (position) {
                                case 1:
                                    DialogFragment newFragment = new timepickerfragment();
                                    boolean a = validatelist("Saturday",true);
                                    if (a == true) {
                                        Toast.makeText(getActivity().getApplicationContext(), "A time was already set, to edit the times, double click the switch", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                    Bundle bundle = new Bundle();
                                    bundle.putStringArrayList("availability", availability);
                                    bundle.putString("day", "Saturday");
                                    bundle.putString("status", "Opening");
                                    newFragment.setArguments(bundle);
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    spin5.setSelection(0);
                                    break;
                                case 2:
                                    newFragment = new timepickerfragment();
                                    a = validatelist("Saturday",false);
                                    if (a == true) {
                                        Toast.makeText(getActivity().getApplicationContext(), "A time was already set, to edit the times, double click the switch", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                    bundle = new Bundle();
                                    bundle.putStringArrayList("availability", availability);
                                    bundle.putString("day", "Saturday");
                                    bundle.putString("status", "Closing");
                                    newFragment.setArguments(bundle);
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    spin5.setSelection(0);
                                    break;
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }

                    });
                } else {
                    search("Saturday");
                    spin5.setEnabled(false);
                }
            }
        });


        Switch simpleSwitch6 = (Switch) getActivity().findViewById(R.id.sunday);
        simpleSwitch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Spinner spin6 = (Spinner) getActivity().findViewById(R.id.spinner6);

            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on) {
                if (on) {

                    spin6.setEnabled(true);
                    // Get Current Time

                    spin6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            switch (position) {
                                case 1:
                                    DialogFragment newFragment = new timepickerfragment();
                                    boolean a = validatelist("Sunday",true);
                                    if (a == true) {
                                        Toast.makeText(getActivity().getApplicationContext(), "A time was already set, to edit the times, double click the switch", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                    Bundle bundle = new Bundle();
                                    bundle.putStringArrayList("availability", availability);
                                    bundle.putString("day", "Sunday");
                                    bundle.putString("status", "Opening");
                                    newFragment.setArguments(bundle);
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    spin6.setSelection(0);
                                    break;
                                case 2:
                                    newFragment = new timepickerfragment();
                                    a = validatelist("Sunday",false);
                                    if (a == true) {
                                        Toast.makeText(getActivity().getApplicationContext(), "A time was already set, to edit the times, double click the switch", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                    bundle = new Bundle();
                                    bundle.putStringArrayList("availability", availability);
                                    bundle.putString("day", "Sunday");
                                    bundle.putString("status", "Closing");
                                    newFragment.setArguments(bundle);
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    spin6.setSelection(0);
                                    break;
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }

                    });
                } else {
                    search("Sunday");
                    spin6.setEnabled(false);
                }

            }
        });

        final Button button = getActivity().findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //push to database

                Iterator<String> iterator = availability.iterator();

                Bundle b = getArguments();
                String a= b.getString("uid");
                availibilityUID.child(a).removeValue();
                while (iterator.hasNext()){
                    String temp = iterator.next();
                    String uid = availibilityUID.push().getKey();
                    availibilityUID.child(a).child(uid).setValue(temp);
                    //availibilityDatabase.child(id).setValue(temp);
                }
                Toast.makeText(getActivity().getApplicationContext(), "Confirmed!", Toast.LENGTH_LONG).show();
                ListView tv1= (ListView) getActivity().findViewById(R.id.availlist);

                ArrayAdapter arrayAdapter2 = new ArrayAdapter(getActivity(), R.layout.simple_list_item_1,availability);

                tv1.setAdapter(arrayAdapter2);


            }
        });
    }}
