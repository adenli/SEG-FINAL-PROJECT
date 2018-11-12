package ca.uottawa.uvaug070.homerepair;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;



public class ServiceNavigation extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_bar);

        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Class<myaccountMenu> fragmentClass;
                switch (id) {
                    case R.id.account:
                        Toast.makeText(ServiceNavigation.this, "My Account", Toast.LENGTH_SHORT).show();  //IN THE CASE THAT ACCOUNT IN THE MENU WAS PRESSED THIS WILL RUN
                        fragmentClass=myaccountMenu.class;
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        try {
                            fragmentManager.beginTransaction().replace(R.id.activity_main, fragmentClass.newInstance()).commit();
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main);
                            drawer.closeDrawer(GravityCompat.START);

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }

                    case R.id.availability:
                        Toast.makeText(ServiceNavigation.this, "Settings", Toast.LENGTH_SHORT).show();
                    case R.id.settings:
                        Toast.makeText(ServiceNavigation.this, "Settings", Toast.LENGTH_SHORT).show();
                    case R.id.logout:
                        Toast.makeText(ServiceNavigation.this, "", Toast.LENGTH_SHORT).show();
                    default:
                        return true;
                }

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }



}