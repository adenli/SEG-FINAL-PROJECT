package ca.uottawa.uvaug070.homerepair;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class WelcomeActivity extends AppCompatActivity {

        private DrawerLayout dl;
        private ActionBarDrawerToggle t;
        private NavigationView nv;
        private String username;
        private String uid;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.navigation_user);
            dl = (DrawerLayout) findViewById(R.id.activity_main);
            t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

            dl.addDrawerListener(t);
            t.syncState();

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            Bundle extras = getIntent().getExtras();
            this.username = extras.getString("username");
            this.uid = extras.getString("uid");
            welcomeMenu a= new welcomeMenu();
            setFragment(a);
            nv = (NavigationView) findViewById(R.id.nv);

            setupDrawerContent(nv);
        }


    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.drawer, fragment)
                .commit();
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });}
    private void selectDrawerItem(MenuItem menuItem) {

        // Create a new fragment and specify the fragment to show based on nav item clicked
        switch(menuItem.getItemId()) {
            case R.id.search:
                try{
                    Intent intent = getIntent();
                    String id = intent.getStringExtra("uid");

                    userMenu fragment = userMenu.class.newInstance();

                    Bundle bundle = new Bundle();

//                    fragment.setArguments(bundle);
                    // Insert the fragment by replacing any existing fragment
                    fragment.setArguments(bundle);

                    FragmentManager fragmentManager = getSupportFragmentManager();

                    fragmentManager.beginTransaction().replace(R.id.drawer, fragment).commit();

                    // Highlight the selected item has been done by NavigationView

                    // Set action bar title
                    setTitle(menuItem.getTitle());
                    // Close the navigation drawer
                    dl.closeDrawers();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.logout:

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (t.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
}



