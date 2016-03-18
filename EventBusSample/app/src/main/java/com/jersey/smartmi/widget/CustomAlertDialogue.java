package com.jersey.smartmi.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.jersey.smartmi.R;


/**
 * Created by Vishnu on 19/11/15.
 */
public class CustomAlertDialogue {
    public static Dialog customProgressdialogue(Context context) {
        Dialog dialogTransparent = new Dialog(context, android.R.style.Theme_Black);
        View view = LayoutInflater.from(context).inflate(
                R.layout.custom_progress, null);
        dialogTransparent.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogTransparent.getWindow().setBackgroundDrawableResource(
                R.color.transparent);
        dialogTransparent.setContentView(view);
        return dialogTransparent;
    }

    public static void customNetworkAlert(Context context, String msg) {
//        String message = context.getResources().getString(R.string.network_error);
//        if (msg.equals("token"))
//            message = context.getResources().getString(R.string.invalid_token);
//        else if (msg.equals("report_export"))
//            message = context.getResources().getString(R.string.no_file_found);
//        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //setting custom layout to dialog
//        dialog.setContentView(R.layout.alert_dialog_layout);
//        //dialog.setTitle("Custom Dialog");
//
//        //adding text dynamically
//        TextView txt = (TextView) dialog.findViewById(R.id.txt_errordata);
//        txt.setText(message);
//
//
//        //adding button click event
//        Button dismissButton = (Button) dialog.findViewById(R.id.btnalert_Ok);
//        dismissButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
    }

    public static void simpleAlertDialogue(Context context, String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        String message = msg;
//        if (msg.equals("token")) {
//            message = context.getResources().getString(R.string.invalid_token);
//            alertDialog.setTitle("Login Failed");
//        } else if (msg.equals("download_error"))
//            message = context.getResources().getString(R.string.download_error);
//        else if (msg.equals(""))
//            message = context.getResources().getString(R.string.network_error);
        //  alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, context.getResources().getString(R.string.ok_txt),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public static void customErrordialogue(Context context, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        //  alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, context.getResources().getString(R.string.ok_txt),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
