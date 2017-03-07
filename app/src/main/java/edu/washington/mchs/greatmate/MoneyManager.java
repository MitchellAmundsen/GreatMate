package edu.washington.mchs.greatmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MoneyManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_manager);
    }

    public void addMoneyInput(View view) {
        Intent intent = new Intent(MoneyManager.this, MoneyInputActivity.class);
        startActivity(intent);
    }

}
