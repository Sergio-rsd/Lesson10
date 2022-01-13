package ru.gb.lesson10;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class CreateNameDialogFragment extends DialogFragment {

    private EditText dialogName;

//    private CreateNameController controller;
//
//    @Override
//    public void onAttach(@NonNull Context context) {
//        controller = (CreateNameController) context ;
//        super.onAttach(context);
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.name_dialog, null);
        EditText dialogName = dialogView.findViewById(R.id.dialog_name);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Enter a name");
        // builder.setMessage("Do you have a name ?");
        builder.setView(dialogView);
        builder.setCancelable(true);

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(requireContext(), "Answer is no", Toast.LENGTH_SHORT).show();
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = dialogName.getText().toString();
                ((CreateNameController)requireContext()).createName(name);
                dialogInterface.dismiss();
            }
        });

        return builder.create();
    }
}
