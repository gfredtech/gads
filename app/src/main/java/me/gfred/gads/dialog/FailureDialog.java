package me.gfred.gads.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import me.gfred.gads.R;

public class FailureDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.dialog_failure, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setView(view);
        alertDialog.show();

        return alertDialog;
    }
}
