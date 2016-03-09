package ak.happinessinc.com.akimagedownloader.utilities;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import ak.happinessinc.com.akimagedownloader.callbacks.OnDownload;

/**
 * Created by Ananthakrishna on 09-03-2016.
 */
public class ImageDownloader {
    OnDownload downloadCallBack;
    public ImageDownloader(OnDownload onDownload){
        this.downloadCallBack=onDownload;
    }
    public void startDownload(String url) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                File direct = new File(Environment.getExternalStorageDirectory()+ "/AkImageDownloads");
                if (!direct.exists()) {
                    direct.mkdirs();
                }
                try {
                       URL downloadUrl = new URL(params[0]);
                    HttpURLConnection httpConn = (HttpURLConnection) downloadUrl.openConnection();
                    int responseCode = httpConn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        File file = new File(Environment.getExternalStorageDirectory() + "/AkImageDownloads/", "Akdownload" + new Date().getTime() + ".png");
                        InputStream inputStream = httpConn.getInputStream();
                        FileOutputStream outputStream = new FileOutputStream(file);
                        int bytesRead;
                        byte[] buffer = new byte[4096];
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        outputStream.close();
                        inputStream.close();
                        return file.getPath();

                    } else {

                        downloadCallBack.onDownloadError("No file to download. Server replied HTTP code: " + responseCode);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    downloadCallBack.onDownloadError(e.toString());
                }

                return null;

        }

        @Override
        protected void onPostExecute (String s){
                if(s!=null)
            downloadCallBack.onImageDownloadComplete(Uri.parse(s));
        }
    }.execute(url);
}
}