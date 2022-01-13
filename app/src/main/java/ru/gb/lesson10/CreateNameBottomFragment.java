package ru.gb.lesson10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CreateNameBottomFragment extends BottomSheetDialogFragment {

    private EditText dialogName;
    private Button dialogCreate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_name_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        dialogName = view.findViewById(R.id.dialog_name);
        dialogCreate = view.findViewById(R.id.dialog_create);
        dialogCreate.setOnClickListener(v -> {
            ((CreateNameController) requireContext())
                    .createName(
                            dialogName.getText().toString()
                    );
            CreateNameBottomFragment.this.dismiss();
        });
    }
}
