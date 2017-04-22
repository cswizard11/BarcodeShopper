package com.teamenfuego.barcodeshopper.barcodeshopper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by natha on 4/21/2017.
 */

public class EntryBox extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Enter Item Info").setView(R.layout.entry_box);
        return builder.create();
    }
}
