package com.rp.rahmawatiputrianasari.research00.view;

/**
 * Created by rahmawatiputrianasari on 10/10/17.
 */

public interface BaseView {
    void showLoading();

    void dismissLoading();

    void loadContentError();

    void refreshContent();

    void showDialog(String title, String message, int action);

}
