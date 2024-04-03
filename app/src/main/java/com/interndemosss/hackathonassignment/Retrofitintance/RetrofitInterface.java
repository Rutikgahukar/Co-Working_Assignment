package com.interndemosss.hackathonassignment.Retrofitintance;

import com.interndemosss.hackathonassignment.bookingHistory.Booking;
import com.interndemosss.hackathonassignment.calenderSlot.SlotResponce;
import com.interndemosss.hackathonassignment.login.LoginRequestBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @FormUrlEncoded
    @POST("digitalflake/api/login")
    Call<LoginRequestBody> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("digitalflake/api/get_slots")
    Call<SlotResponce> getSlots(@Query("date") String date);
    @GET("digitalflake/api/get_bookings")
    Call<List<Booking>> getBookings();
}
