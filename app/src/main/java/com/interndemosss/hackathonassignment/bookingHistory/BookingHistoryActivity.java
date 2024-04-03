package com.interndemosss.hackathonassignment.bookingHistory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.interndemosss.hackathonassignment.homeDashBoard.HomeActivity;
import com.interndemosss.hackathonassignment.R;
import com.interndemosss.hackathonassignment.Retrofitintance.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingHistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BookingHistoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView HistorybackArrow = findViewById(R.id.HistorybackArrow);
        HistorybackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingHistoryActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchBookingData();
    }

    private void fetchBookingData() {
        RetrofitInstance.getSeviceData().getBookings().enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if (response.isSuccessful()) {
                    List<Booking> bookingList = response.body();
                    adapter = new BookingHistoryAdapter(bookingList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(BookingHistoryActivity.this, "Failed to fetch bookings", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                Toast.makeText(BookingHistoryActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("fail_responce", "Error fetching bookings: " + t.getMessage(), t);
            }
        });
    }
}