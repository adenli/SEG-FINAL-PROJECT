package ca.uottawa.uvaug070.homerepair;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import static android.widget.SearchView.OnQueryTextListener;

public class userMenu extends Fragment {
    ListActivity listActivity;
    ArrayAdapter arrayAdapter2;
    ArrayList<String> accounts = new ArrayList<String>();

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
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Account temp = postSnapshot.getValue(Account.class);

                    if (temp.getRole() == Role.SERVICEPROVIDER) {
                        ServiceProvider account = postSnapshot.getValue(ServiceProvider.class);
                        accounts.add(account.getUsername());
                    }
                }


            }
        });

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getActivity(), R.layout.simple_list_item_1, accounts);
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
                Iterator<String> a = accounts.iterator();
                while (a.hasNext()){
                    String b = a.next();

                    if (b.contains(newText)){
                        tempaccount.add(b.toString());
                    }
                }
                servaddview.setAdapter(null);
                ArrayAdapter arrayAdapter2 = new ArrayAdapter(getActivity(), R.layout.simple_list_item_1, tempaccount);

                servaddview.setAdapter(arrayAdapter2);



                return true;
            }
        });

    }




    public void onStart() {


        super.onStart();



    }

    public void onCreateOptionsMenu(Menu menu){


    }}


