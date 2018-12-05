package ca.uottawa.uvaug070.homerepair;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import static android.widget.SearchView.OnQueryTextListener;

public class userMenu extends Fragment {

    ArrayAdapter arrayAdapter2;
    ArrayList<Account> accounts = new ArrayList<>();
    ArrayList<String> accountsDescriptions = new ArrayList<>();
    DatabaseReference databaseServPro = FirebaseDatabase.getInstance().getReference("accounts");
    ListView servaddview;



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.search_fragment, container, false);
        super.onCreate(savedInstanceState);

        servaddview = (ListView) view.findViewById(R.id.list_view);
        databaseServPro.addValueEventListener(new ValueEventListener() {

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                accounts.clear();
                accountsDescriptions.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Account temp = postSnapshot.getValue(Account.class);


                    if (temp.getRole() == Role.SERVICEPROVIDER) {
                        ServiceProvider account = postSnapshot.getValue(ServiceProvider.class);
                        DataSnapshot account1 = postSnapshot.child("Profile").child("companyName");
                        DataSnapshot account2 = postSnapshot.child("Profile").child("address");
                        DataSnapshot account3 = postSnapshot.child("Profile").child("phoneNumber");
                        DataSnapshot account4 = postSnapshot.child("Profile").child("description");
                        DataSnapshot account5 = postSnapshot.child("Profile").child("licensed");
                        DataSnapshot numRatings = postSnapshot.child("numRatings");
                        DataSnapshot cumulativeRating = postSnapshot.child("cumulativeRating");
                        DataSnapshot services = postSnapshot.child("services");
                        DatabaseReference times = FirebaseDatabase.getInstance().getReference("times");
                        DecimalFormat df = new DecimalFormat("#.#");

                        if (services.getValue()!=null && account1.getValue() != null){
                            StringBuilder toAdd = new StringBuilder();
                            for (DataSnapshot child : services.getChildren()) {
                                toAdd.append(child.getValue(Service.class).toString() + "\n");
                            }
                            try {
                                String isLicensed;
                                if(account5.getValue().toString() == "true") {
                                    isLicensed = "Licensed";
                                } else {
                                    isLicensed = "Unlicensed";
                                }
                                accounts.add(account);
                                accountsDescriptions.add(account1.getValue().toString()
                                        +"\nAddress: " +(account2.getValue().toString())
                                        +"\nPhone Number: " +(account3.getValue().toString())
                                        +"\nDescription: " +(account4.getValue().toString())
                                        +"\n" + isLicensed
                                        +"\nRating: " +df.format((double)cumulativeRating.getValue(Integer.class) / (double)numRatings.getValue(Integer.class))
                                        +"\nServices: \n"+ toAdd.toString()
                                );
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getActivity(), R.layout.simple_list_item_1, accountsDescriptions);
        servaddview.setAdapter(arrayAdapter2);


//        CharSequence query = simpleSearchView.getQuery()

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Search");
        SearchView simpleSearchView = (SearchView) getActivity().findViewById(R.id.search);
        simpleSearchView.setOnQueryTextListener(new OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<String> tempaccount = new ArrayList<String>();
                Iterator<String> a = accountsDescriptions.iterator();
                while (a.hasNext()){
                    String b = a.next();

                    if (b.toLowerCase().contains(newText.toLowerCase())){
                        tempaccount.add(b.toString());
                    }
                }
                servaddview.setAdapter(null);
                ArrayAdapter arrayAdapter2 = new ArrayAdapter(getActivity(), R.layout.simple_list_item_1, tempaccount);
                servaddview.setAdapter(arrayAdapter2);
                return true;
            }
        });

        servaddview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Object fragment = null;
                try {
                    fragment = welcomeMenu.class.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }




                Bundle bundle = new Bundle();
                bundle.putString("user",accounts.get(position).getUid());
                DialogFragment newFragment = new bookingDialog();
                newFragment.setArguments(bundle);
                newFragment.show(getActivity().getSupportFragmentManager(), "bookingDialog");
            }
        });


    }




    public void onStart() {


        super.onStart();



    }

    public void onCreateOptionsMenu(Menu menu){


    }}


