package edu.washington.mchs.greatmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users/test1");
        ref.setValue("test person");

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
 //       final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
 //       if (user == null) {
  //          // user auth state is changed - user is null
  //          // launch login activity
  //          startActivity(new Intent(MainActivity.this, LoginActivity.class));
   //         finish();
   //     }

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        Button toManagerViewButton = (Button) findViewById(R.id.money_manager_button);

        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("gManager");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("mManager");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("logout");
        TabHost.TabSpec tab4 = tabHost.newTabSpec("settings");

        tab1.setIndicator("Grocery");
        tab1.setContent(R.id.tab1);

        tab2.setIndicator("Money");
        tab2.setContent(R.id.tab2);

        //this tab logs person out, not sure how to do that
        tab3.setIndicator("Settings");
        tab3.setContent(new Intent(this,MoneyInputActivity.class));

        //settings
        tab4.setIndicator("Logout");
        tab4.setContent(new Intent(this, GroceryInputActivity.class));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        tabHost.addTab(tab4);


    }

    public void moneyManager(View view) {
        Intent intent = new Intent(MainActivity.this, MoneyManager.class);
        startActivity(intent);
    }

    public void groceryManager(View view) {
        Intent intent = new Intent(MainActivity.this, GroceryManager.class);
        startActivity(intent);
    }
}
