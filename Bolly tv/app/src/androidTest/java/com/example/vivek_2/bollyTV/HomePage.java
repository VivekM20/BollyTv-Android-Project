package com.example.vivek_2.bollyTV;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;


    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Movie List");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_search_black_24dp);

        final String name=getIntent().getStringExtra("name");
        Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();


        mAuth = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        final String letterlist[]={"Rowdy Rathore","Gangs of wassepur2","pk","Dangal","piku","Dhoom3","Chennai Express","Tiger zinda hai",
                "Bajranji Bhaijaan","Sultan","Krrish 3","Raees","Happy new year","Golmaal Again","prem ratan","Kick",
        "Ye jawani hai deewani","Bajirao Mastani","Bang Bang","Dabang 2","Bagghi 2","Dilwale","Tanu weds Manu returns","Singham Returns",
        "Bodyguard","Dabang","Judwa 2","Toilet ek prem katha","MS Dhoni-the untold story","Rustom","Airlift","Jab tak hai jaan","Bahubali 1",
        "Ready","Tubelight","Badrinath ki dulhaniya","Ram Leela","Jolly llb2","Jai Ho","Agneepath","Ghajni","Ra-one",
        "Barfi!","Ae Dil Hai Mushkil","Holiday","Bhagg Milkha Bagh","Hosefull 3","Sonu ke Titu ki sweety","ABCD-2","Don2","Golmal3"
                ,"Son Of Sardar","Ek Villan","Housefull2","Raid","2 States","Bol Bacchan","Kaabil","Grand Masti","Race 2","Singham",
        "Shivaay","Welcome Back","Baby","Talaash","Raajneeti","Singh is Bling","Zindagi Na milegi dobara","Gabbar is Back","Fan",
        "Brothers","OMG!"};


        String links[]={"https://accessbollywood.files.wordpress.com/2012/06/rowdy_rathore.jpg",
        "https://shyfyy.files.wordpress.com/2012/06/gow3.jpg",
        "http://www.boxofficemovies.in/now/wp-content/uploads/New-poster-of-PK-movie-featuring-Aamir-Khan-and-Asnuskha-Sharma.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/Dangal-1-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/423545346.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/74218191-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/films14-306x393.jpg",
        "http://stat2.bollywoodhungama.in/wp-content/uploads/2016/09/Tiger-Zinda-Hai-6-1-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/429191586-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/445918987-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/74218192-306x393.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/Raees1-1-306x393.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/89650084-306x393.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2017/09/Golmaal-Again-2-306x393.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/03/433500130-306x354.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/83723409-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/74637319.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/435712225-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/84611268-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/74637331.jpg",
        "http://stat2.bollywoodhungama.in/wp-content/uploads/2018/03/Baaghi-2-embarks-on-good-advance-booking-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/435950187-306x393.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/423243299.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/03/85690800-306x393.jpg",
        "http://stat2.bollywoodhungama.in/wp-content/uploads/2016/04/60848236-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/192390xcitefun-dabangg-posters-1-306x393.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/03/Judwaa-2-6-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/Toilet-Ek-Prem-Katha-1-306x393.jpg",
        "http://stat2.bollywoodhungama.in/wp-content/uploads/2016/03/749462683-306x393.jpg",
        "http://stat2.bollywoodhungama.in/wp-content/uploads/2016/03/440668378-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/435593690.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/292800_380692665346754_899779156_n-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/430033947.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/60696307-306x393.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/04/Tubelight-17-306x393.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/04/Badrinath-Ki-Dulhania-2-2-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/74218197-306x393.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/JoLLy-LLB-2-9-306x393.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/75439684-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/Agneepath-Poster-Feature-306x393.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/Ghajjni-306x393.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/03/Ra-One-Poster-Feature-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/74637364.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/03/feature1-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/79951279.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/74637391.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/housefull3-306x393.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/11/Sonu-Ke-Titu-Ki-Sweety-3-306x393.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/03/425912702-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/Done-2-Poster-Feature-306x393.jpg",
        "http://stat2.bollywoodhungama.in/wp-content/uploads/2016/03/Golmaal-Poster-Feature-306x393.jpg",
        "http://stat2.bollywoodhungama.in/wp-content/uploads/2016/03/74637370.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/82792532-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/Housefull-2-Poster-Feature-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2017/08/Raid-3-1-306x393.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/79504474.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/03/74637344.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/Kaabil-1-3-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/74637349.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/74637373.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/singham-Poster-Feature-306x393.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/03/Shivaay-Review3-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/432729792-306x393.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/92864437-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/74637379.jpg",
        "http://stat2.bollywoodhungama.in/wp-content/uploads/2016/03/rajneeti-Poster-Feature-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/432721149-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/ZNMD-Poster-Feature-306x393.jpg",
        "http://stat2.bollywoodhungama.in/wp-content/uploads/2016/03/423049326-306x393.jpg",
        "http://stat2.bollywoodhungama.in/wp-content/uploads/2016/03/435145397-306x369.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/03/426389974.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/74637358.jpg"};
        gridView=findViewById(R.id.grid_view);
        GridAdapter adapter=new GridAdapter(HomePage.this,links,letterlist);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent i=new Intent(HomePage.this,Movies.class);
                i.putExtra("name",letterlist[position]);
                startActivity(i);

            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {
                final AlertDialog.Builder builder=new AlertDialog.Builder(HomePage.this);
                builder.setMessage("Movie Title  :  " +letterlist[position] );
                builder.setCancelable(true);
                builder.setNegativeButton("Download Movie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i=new Intent(HomePage.this,Movies.class);
                        i.putExtra("name",letterlist[position]);
                        startActivity(i);

                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
               // Toast.makeText(HomePage.this, "hello", Toast.LENGTH_SHORT).show();
                return false;
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {


            return true;
        }
        if (id==R.id.exit)
        {
            final AlertDialog.Builder builder=new AlertDialog.Builder(HomePage.this);
            builder.setMessage("Exit app ?");
            builder.setCancelable(true);
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alertdialog=builder.create();
            alertdialog.show();
        }
        if(id==R.id.sign_out)
        {
            progressDialog.setMessage("Signing out...");
            progressDialog.show();
            mAuth.signOut();
            Intent i=new Intent(HomePage.this,LoginActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_settings)
        {
            Intent i=new Intent(HomePage.this,Settings.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share)
        {
            Intent i=new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            startActivity(i);



        } else if (id == R.id.nav_exit)
        {
            progressDialog.setMessage("Signing out...");
            progressDialog.show();
            mAuth.signOut();
            Intent i=new Intent(HomePage.this,LoginActivity.class);
            startActivity(i);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
