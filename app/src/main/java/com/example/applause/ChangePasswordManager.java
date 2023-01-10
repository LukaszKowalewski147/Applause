package com.example.applause;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ChangePasswordManager extends AppCompatDialogFragment {

    private EditText oldPasswordTxt;
    private EditText newPasswordTxt;
    private EditText confirmPasswordTxt;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.change_password_dialog, null);

        builder.setView(view)
                .setTitle("Zmień hasło")
                .setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Zatwierdź", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        managePasswordChange();
                    }
                });

        oldPasswordTxt = view.findViewById(R.id.old_password_input);
        newPasswordTxt = view.findViewById(R.id.new_password_input);
        confirmPasswordTxt = view.findViewById(R.id.password_check_input);

        return builder.create();
    }

    private void managePasswordChange() {
        JSONCommunicator jsonCommunicator = new JSONCommunicator(getActivity());
        String currentPassword = jsonCommunicator.getCurrentPassword();
        String oldPasswordTyped = oldPasswordTxt.getText().toString();
        String newPassword = newPasswordTxt.getText().toString();
        String confirmPassword = confirmPasswordTxt.getText().toString();

        if (currentPassword.equals(oldPasswordTyped)) {
            if (!newPassword.equals(currentPassword)) {
                if (newPassword.equals(confirmPassword)) {
                    jsonCommunicator.updatePassword(newPassword);
                } else {
                    Toast.makeText(getActivity(), "Nowe hasła różnią się", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Nowe hasło musi różnić się od starego", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Nieprawidłowe stare hasło", Toast.LENGTH_SHORT).show();
        }
    }
}
