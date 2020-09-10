package me.gfred.gads.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import me.gfred.gads.R;

public class ConfirmDialog extends DialogFragment {

    DialogFragmentListener dialogFragmentListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View confirmPrompt = layoutInflater.inflate(R.layout.dialog_confirm, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();

        ImageView cancelImage = confirmPrompt.findViewById(R.id.cancel_image);
        Button yesButton = confirmPrompt.findViewById(R.id.yes_button);

        cancelImage.setOnClickListener(v -> {
            ConfirmDialog.this.getDialog().cancel();
        });

        yesButton.setOnClickListener(v -> {
            dialogFragmentListener.submitProject();
            dismiss();
        });

        alertDialog.setView(confirmPrompt);
        alertDialog.show();
        return alertDialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            dialogFragmentListener = (DialogFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogFragmentListener");
        }
    }
}
