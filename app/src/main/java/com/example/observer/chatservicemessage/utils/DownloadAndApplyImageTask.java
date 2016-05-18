package com.example.observer.chatservicemessage.utils;

import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by observer on 05/13/2016.
 */
public class DownloadAndApplyImageTask extends AsyncTask<String, Integer, Void> {

    private ImageView mImageView;

    public DownloadAndApplyImageTask(ImageView imageView) {
        mImageView = imageView;
    }

    @Override
    protected Void doInBackground(String... params) {

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mImageView.setImageResource(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
