package com.interndemosss.hackathonassignment.calenderSlot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.interndemosss.hackathonassignment.Retrofitintance.RetrofitInstance;
import com.interndemosss.hackathonassignment.Retrofitintance.RetrofitInterface;
import com.interndemosss.hackathonassignment.bookDesk.BookedDeskActivity;
import com.sahana.horizontalcalendar.HorizontalCalendar;
import com.sahana.horizontalcalendar.OnDateSelectListener;
import com.sahana.horizontalcalendar.model.DateModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalenderSlotActivity extends AppCompatActivity {
    private HorizontalCalendar mHorizontalCalendar;
    private TextView mDateTextView;
    private RetrofitInterface apiService;
    private TextView previouslySelectedTimingSlot;
    private TextView timingSlot1,timingSlot2,timingSlot3,timingSlot4,timingSlot5,timingSlot6,timingSlot7,timingSlot8,timingSlot9,timingSlot10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calender_slot);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        apiService = RetrofitInstance.getRetrofit().create(RetrofitInterface.class);
        timingSlot1 = findViewById(R.id.TimingSlot1);
        timingSlot2 = findViewById(R.id.TimingSlot2);
        timingSlot3 = findViewById(R.id.TimingSlot3);
        timingSlot4 = findViewById(R.id.TimingSlot4);
        timingSlot5 = findViewById(R.id.TimingSlot5);
        timingSlot6 = findViewById(R.id.TimingSlot6);
        timingSlot7 = findViewById(R.id.TimingSlot7);
        timingSlot8 = findViewById(R.id.TimingSlot8);
        timingSlot9 = findViewById(R.id.TimingSlot9);
        timingSlot10 = findViewById(R.id.TimingSlot10);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(calendar.getTime());
        fetchSlots(date);

        ImageView CalenderbackArrow = findViewById(R.id.CalenderbackArrow);
        CalenderbackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderSlotActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        mHorizontalCalendar = findViewById(R.id.horizontalCalendar);
        mDateTextView = findViewById(R.id.dateTextView);
        mHorizontalCalendar.setOnDateSelectListener(new OnDateSelectListener() {
            @Override
            public void onSelect(DateModel dateModel) {
                mDateTextView.setText(dateModel != null ? dateModel.day + " " + dateModel.dayOfWeek + " " + dateModel.month + "," + dateModel.year : "");

            }
        });
        AppCompatButton SelectSlotsNextButton = findViewById(R.id.SelectSlotsNextButton);
        SelectSlotsNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderSlotActivity.this, BookedDeskActivity.class);
                startActivity(intent);
            }
        });
        ImageView celenderBack = findViewById(R.id.CalenderbackArrow);
        celenderBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderSlotActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void fetchSlots(String date) {
        Call<SlotResponce> call = apiService.getSlots(date);
        call.enqueue(new Callback<SlotResponce>() {
            @Override
            public void onResponse(Call<SlotResponce> call, Response<SlotResponce> response) {
                if (response.isSuccessful()) {
                    SlotResponce slotResponse = response.body();
                    if (slotResponse != null && slotResponse.getSlots() != null) {
                        List<slots> slots = slotResponse.getSlots();
                        setSlotNames(slots);
                    }
                    Toast.makeText(CalenderSlotActivity.this, "Successfully fetchSlots", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CalenderSlotActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SlotResponce> call, Throwable t) {
                Log.e("NetworkError", "Failed to fetch data", t);
                Toast.makeText(CalenderSlotActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setSlotNames(List<slots> slots) {
        for (slots slot : slots) {
            final TextView textView;
            switch (slot.getSlotId()) {
                case 1:
                    textView = timingSlot1;
                    break;
                case 2:
                    textView = timingSlot2;
                    break;
                case 3:
                    textView = timingSlot3;
                    break;
                case 4:
                    textView = timingSlot4;
                    break;
                case 5:
                    textView = timingSlot5;
                    break;
                case 6:
                    textView = timingSlot6;
                    break;
                default:
                    textView = null;
                    break;
            }

            if (textView != null) {
                textView.setText(slot.getSlotName());

                // Set background based on slot availability
                if (slot.isSlotActive()) {
                    textView.setBackgroundResource(R.drawable.available_slot_background);
                } else {
                    textView.setBackgroundResource(R.drawable.booked_unavailable_slot_background);
                }

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toggle selection status and update background accordingly
                        slot.setSlotActive(!slot.isSlotActive());
                        if (slot.isSlotActive()) {
                            textView.setBackgroundResource(R.drawable.selected_slot_background);
                        } else {
                            textView.setBackgroundResource(R.drawable.available_slot_background);
                        }
                    }
                });
            }
        }
    }

}
