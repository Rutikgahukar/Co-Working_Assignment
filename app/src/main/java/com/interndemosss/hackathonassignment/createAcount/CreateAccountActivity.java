package com.interndemosss.hackathonassignment.createAcount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.interndemosss.hackathonassignment.homeDashBoard.HomeActivity;
import com.interndemosss.hackathonassignment.R;
import com.interndemosss.hackathonassignment.login.MainActivity;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView backToMainActivityTextView = findViewById(R.id.BackLoginActivity);
        backToMainActivityTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(CreateAccountActivity.this, "Clicked to Login Page", Toast.LENGTH_SHORT).show();
            }
        });
        AppCompatButton CreateAcButton = findViewById(R.id.CreateAcButton);
        CreateAcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateAccountActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateAccountActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}