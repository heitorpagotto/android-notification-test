package com.example.activitysnacknotification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainLoginBtn = findViewById(R.id.app_login_button);
        mainLoginBtn.setOnClickListener(e -> {
            Login();
        });
    }

    public void Navigate() {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("name", GetName());
        homeIntent.putExtra("ra", GetRA());

        startActivity(homeIntent);
    }

    public void Login() {
        if (GetName().isEmpty()) {
            EditText nameText = findViewById(R.id.login_name_input);
            nameText.setError(getResources().getString(R.string.required_field_text_error));
        }
        if (GetRA().isEmpty()) {
            EditText raText = findViewById(R.id.login_ra_input);
            raText.setError(getResources().getString(R.string.required_field_text_error));
        }

        if (!GetRA().isEmpty() && !GetName().isEmpty()) {
            Navigate();
        }
    }

    public String GetName() {
        EditText nameText = findViewById(R.id.login_name_input);
        return nameText.getText().toString();
    }

    public String GetRA() {
        EditText raText = findViewById(R.id.login_ra_input);
        return raText.getText().toString();
    }
}