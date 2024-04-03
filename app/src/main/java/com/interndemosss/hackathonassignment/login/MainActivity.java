package com.interndemosss.hackathonassignment.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.interndemosss.hackathonassignment.createAcount.CreateAccountActivity;
import com.interndemosss.hackathonassignment.R;
import com.interndemosss.hackathonassignment.Retrofitintance.RetrofitInstance;
import com.interndemosss.hackathonassignment.Retrofitintance.RetrofitInterface;
import com.interndemosss.hackathonassignment.homeDashBoard.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    EditText loginPassword, LoginNOorEmail;
    boolean isPasswordVisible = false;
     RetrofitInterface retrofitInterface ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LoginNOorEmail = findViewById(R.id.LoginNOorEmail);
        loginPassword = findViewById(R.id.LoginPassword);
        Drawable drawable = getResources().getDrawable(R.drawable.show);
        drawable.setBounds(0, 0, 50, 50);
        SpannableStringBuilder ssb = new SpannableStringBuilder("     ");
        ssb.setSpan(new ImageSpan(drawable), 2, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        loginPassword.setCompoundDrawables(null, null, drawable, null);
        loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

        loginPassword.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (loginPassword.getRight() - loginPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        togglePasswordVisibility();
                        return true;
                    }
                }
                return false;
            }
        });

        TextView createAccountTextView = findViewById(R.id.TxtCreateAc);
        createAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Create New Account", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
        AppCompatButton loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser(getApplicationContext());
                 Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                 startActivity(intent);
            }
        });
        retrofitInterface = RetrofitInstance.getSeviceData();
    }
    private void togglePasswordVisibility() {
        Drawable drawable;
        if (isPasswordVisible) {
            loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            drawable = getResources().getDrawable(R.drawable.hide);
            isPasswordVisible = false;
        } else {
            loginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            drawable = getResources().getDrawable(R.drawable.show);
            isPasswordVisible = true;
        }
        drawable.setBounds(0, 0, 50, 50);
        SpannableStringBuilder ssb = new SpannableStringBuilder("     ");
        ssb.setSpan(new ImageSpan(drawable), 2, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        loginPassword.setCompoundDrawables(null, null, drawable, null);
    }

    private void LoginUser(Context context) {
        String email = LoginNOorEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        // Make sure email and password are not empty
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<LoginRequestBody> call = retrofitInterface.loginUser(email, password);
        call.enqueue(new Callback<LoginRequestBody>() {
            @Override
            public void onResponse(Call<LoginRequestBody> call, Response<LoginRequestBody> response) {
                if (response.isSuccessful()) {
                    // Handle successful login response
                    LoginRequestBody loginResponse = response.body();
                    Log.d("LoginResponse", "Login Successful. User ID: " + loginResponse.getEmail().toString());
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();

                } else {
                    // Handle unsuccessful login response
                    Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginRequestBody> call, Throwable t) {
                // Handle API call failure
                Log.e("LoginFailure", "API Call Error: " + t.getMessage());
                Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
