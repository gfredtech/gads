package me.gfred.gads.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import me.gfred.gads.MyViewModel;
import me.gfred.gads.R;
import me.gfred.gads.model.HoursEntry;
import me.gfred.gads.model.IQEntry;
import me.gfred.gads.network.ApiClient;
import me.gfred.gads.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String BASE_URL = "https://gadsapi.herokuapp.com/api/";

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        ApiService service = ApiClient.getRetrofit(BASE_URL).create(ApiService.class);

        Call<List<IQEntry>> iqCall = service.getSkillIQ();
        Call<List<HoursEntry>> hoursCall = service.getHours();

        iqCall.enqueue(new Callback<List<IQEntry>>() {
            @Override
            public void onResponse(@NonNull Call<List<IQEntry>> call, @NonNull Response<List<IQEntry>> response) {
                myViewModel.getIQEntry().postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<IQEntry>> call, @NonNull Throwable t) {}
        });

        hoursCall.enqueue(new Callback<List<HoursEntry>>() {
            @Override
            public void onResponse(@NonNull Call<List<HoursEntry>> call, @NonNull Response<List<HoursEntry>> response) {
                myViewModel.getHoursEntry().postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<HoursEntry>> call, @NonNull Throwable t) {}
        });

        myViewModel.getIQEntry().observe(this, iqEntries -> myViewModel.getHoursEntry().observe(this, hoursEntry -> {
            if (iqEntries != null && hoursEntry != null) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putParcelableArrayListExtra("SKILL_IQ", new ArrayList<>(iqEntries));
                intent.putParcelableArrayListExtra("HOURS", new ArrayList<>(hoursEntry));
                startActivity(intent);
            }
        }));
    }
}