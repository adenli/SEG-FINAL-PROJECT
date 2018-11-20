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



public class ServiceNavigation extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_bar);

        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
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
            case R.id.services:
                try{
                    serviceMenu fragment = serviceMenu.class.newInstance();
                    fragment.setUsername(username);
                    // Insert the fragment by replacing any existing fragment
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
            case R.id.account:
                try{
                    myaccountMenu fragment = myaccountMenu.class.newInstance();
                    // Insert the fragment by replacing any existing fragment
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
            case R.id.availability:
                try{
                    Intent intent = getIntent();
                    String id = intent.getStringExtra("uid");
                    Bundle bundle= new Bundle();
                    bundle.putString("uid",id);
                    availabilityMenu fragment = availabilityMenu.class.newInstance();
                    fragment.setArguments(bundle);
                    // Insert the fragment by replacing any existing fragment
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
            case R.id.settings:
                try{
                    availabilityMenu fragment = availabilityMenu.class.newInstance();
                    // Insert the fragment by replacing any existing fragment
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
}