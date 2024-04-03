package com.interndemosss.hackathonassignment.bookDesk;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.interndemosss.hackathonassignment.calenderSlot.CalenderSlotActivity;
import com.interndemosss.hackathonassignment.R;
import com.interndemosss.hackathonassignment.homeDashBoard.HomeActivity;

public class BookedDeskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booked_desk);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView toolbarBackIcon = findViewById(R.id.toolbarBackIcon);
        toolbarBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookedDeskActivity.this, CalenderSlotActivity.class);
                startActivity(intent);
            }
        });
        AppCompatButton BookDeskButton = findViewById(R.id.BookDeskButton);
        BookDeskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookedDeskActivity.this, "Clicked To Conform", Toast.LENGTH_SHORT).show();
                showConfirmationPopup();
            }
        });
    }

    private void showConfirmationPopup() {
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_confirm_booking, null);

        // Find views in the popup layout
        TextView textDeskId = popupView.findViewById(R.id.textDeskId);
        TextView textDesk = popupView.findViewById(R.id.textDesk);
        TextView textSlot = popupView.findViewById(R.id.textSlot);
        Button btnConfirm = popupView.findViewById(R.id.btnConfirm);

        // Set the details
        textDeskId.setText("Desk ID : 123456");
        textDesk.setText("Desk 14");
        textSlot.setText("Slot : Wed 31 May, 05:00PM-06:00PM");

        // Create the popup window
        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //nothing
            }
        });

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                Toast.makeText(BookedDeskActivity.this, "Booking Confrom", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(BookedDeskActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}