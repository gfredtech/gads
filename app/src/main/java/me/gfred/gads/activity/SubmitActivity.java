package me.gfred.gads.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import me.gfred.gads.R;
import me.gfred.gads.dialog.ConfirmDialog;
import me.gfred.gads.dialog.DialogFragmentListener;
import me.gfred.gads.dialog.FailureDialog;
import me.gfred.gads.dialog.SuccessDialog;
import me.gfred.gads.network.ApiClient;
import me.gfred.gads.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity implements DialogFragmentListener {

    EditText firstName, lastName, emailAddress, githubLink;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        emailAddress = findViewById(R.id.email_address);
        githubLink = findViewById(R.id.github_link);
        fragmentManager = getSupportFragmentManager();
    }

    public void onSubmitProject(View view) {
        openConfirmationDialog();
    }

    void openConfirmationDialog() {
        ConfirmDialog confirmDialog = new ConfirmDialog();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("confirmation");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        confirmDialog.show(ft, "confirmation");
    }

    void openSuccessDialog() {
        SuccessDialog successDialog = new SuccessDialog();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("success");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        successDialog.show(ft, "success");
    }

    void openFailureDialog() {
        FailureDialog dialog = new FailureDialog();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("failure");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        dialog.show(ft, "failure");
    }

    @Override
    public void submitProject() {
        String firstNameText = firstName.getText().toString();
        String lastNameText = lastName.getText().toString();
        String email = emailAddress.getText().toString();
        String github = githubLink.getText().toString();

        ApiService service = ApiClient.
                getRetrofit("https://docs.google.com/forms/d/e/").
                create(ApiService.class);
        Call<Void> submitCall = service.submitForm(email, firstNameText, lastNameText, github);

        submitCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    openSuccessDialog();
                    clearFields();
                } else {
                    openFailureDialog();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                openFailureDialog();
            }
        });
    }

    public void clearFields() {
        firstName.setText("");
        lastName.setText("");
        emailAddress.setText("");
        githubLink.setText("");
    }
}