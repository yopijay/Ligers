package com.example.ligers;

import android.app.Dialog;
import android.content.Context;

import java.util.EventListener;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CustomMessage {

    static SweetAlertDialog confirmMessage(final Context context, int type, String title, String content, String confirm, String cancel, final Dialog dialog) {

         return new SweetAlertDialog(context, type)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText(confirm)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(final SweetAlertDialog exitAlertDialog) {
                        exitAlertDialog.dismissWithAnimation();
                        dialog.dismiss();
                    }
                })
                .setCancelButton(cancel, new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog closeAlertDialog) {
                        closeAlertDialog.dismissWithAnimation();
                    }
                });
    }
}
