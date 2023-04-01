package com.example.a5_activities_and_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements Constants {

    private TextInputEditText txtName;
    private Account account;

    private static final int REQUEST_CODE_SETTING_ACTIVITY = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account = new Account();
        initView();
    }

    private void initView() {
        MaterialButton btnGreetings = findViewById(R.id.btnGreetings);
        MaterialButton btnSettings = findViewById(R.id.btnSettings);
        txtName = findViewById(R.id.textName);
        final TextView txtGreetings = findViewById(R.id.textHello);

        btnGreetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String sayHello = getString(R.string.say_hello) + name;
                txtGreetings.setText(sayHello);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateAccount();
                Intent runSettings = new Intent(MainActivity.this, SettingsActivity.class);
                runSettings.putExtra(YOUR_ACCOUNT, account);

                startActivityForResult(runSettings, REQUEST_CODE_SETTING_ACTIVITY);
            }
        });
    }

    private void populateAccount() {
        account.setName(txtName.getText().toString());
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == RESULT_OK) {
            account = data.getExtras().getParcelable(YOUR_ACCOUNT);
            populateView();
        }
    }

    private void populateView() {
        txtName.setText(account.getName());
    }


}