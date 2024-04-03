package com.interndemosss.hackathonassignment.homeDashBoard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.interndemosss.hackathonassignment.R;
import com.interndemosss.hackathonassignment.bookingHistory.BookingHistoryActivity;
import com.interndemosss.hackathonassignment.calenderSlot.CalenderSlotActivity;


public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView BookingHistoryButton = findViewById(R.id.BookingHistoryButton);
        BookingHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BookingHistoryActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Checked Booking History", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout meetingRoomLayout = findViewById(R.id.MeetingRoomLayout);
        LinearLayout workStationLayout = findViewById(R.id.workStationLayout);
        meetingRoomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetingRoomLayout.setBackgroundResource(R.drawable.button_background);
                workStationLayout.setBackgroundResource(R.drawable.fent_back);
                Intent intent = new Intent(HomeActivity.this, CalenderSlotActivity.class);
                startActivity(intent);
            }
        });
        workStationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workStationLayout.setBackgroundResource(R.drawable.button_background);
                meetingRoomLayout.setBackgroundResource(R.drawable.fent_back);
                Intent intent = new Intent(HomeActivity.this, CalenderSlotActivity.class);
                startActivity(intent);
            }
        });
    }


}