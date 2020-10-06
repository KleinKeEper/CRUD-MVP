package com.example.crud_mvp.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.crud_mvp.IView.IStudentView;
import com.example.crud_mvp.R;

public class CreateDialog extends AppCompatDialogFragment {

    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextStudentCode;

    private IStudentView iStudentView;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Register Student")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Save ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String name = editTextName.getText().toString();
                                String lastName = editTextLastName.getText().toString();
                                String studentCode = editTextStudentCode.getText().toString();
                                iStudentView.createStudent(name, lastName, studentCode);
                            }
                });

        editTextName = view.findViewById(R.id.edit_name);
        editTextLastName = view.findViewById(R.id.edit_lastName);
        editTextStudentCode = view.findViewById(R.id.edit_student_code);

        return builder.create();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            iStudentView = (IStudentView) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + "should implements Interface IView");
        }
    }


}
