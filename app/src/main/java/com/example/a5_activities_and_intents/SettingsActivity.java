package com.example.a5_activities_and_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements Constants{
    TextInputEditText editName;
    TextInputEditText editSurname;
    TextInputEditText editAge;
    TextInputEditText editEmail;
    Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initView();

        account = getIntent().getExtras().getParcelable(YOUR_ACCOUNT);

        populateView();
    }

    private void initView() {
        editName = findViewById(R.id.editName);
        editSurname = findViewById(R.id.editSurname);
        editAge = findViewById(R.id.editAge);
        editEmail = findViewById(R.id.editEmail);

        MaterialButton btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResult = new Intent();
                intentResult.putExtra(YOUR_ACCOUNT, createAccount());
                setResult(RESULT_OK, intentResult);
                finish();
            }
        });
    }
    private void populateView() {
        editName.setText(account.getName());
        editSurname.setText(account.getSurName());
        editAge.setText(String.format(Locale.getDefault(), "%d", account.getAge()));
        editEmail.setText(account.getEmail());
    }

    private Account createAccount() {
        Account account = new Account(
          editName.getText().toString(),
          editSurname.getText().toString(),
          Integer.parseInt(editAge.getText().toString()),
                editEmail.getText().toString());
        return account;
    }
}