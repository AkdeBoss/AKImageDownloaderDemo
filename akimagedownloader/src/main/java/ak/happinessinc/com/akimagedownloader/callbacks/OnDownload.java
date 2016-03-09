package ak.happinessinc.com.akimagedownloader.callbacks;

import android.net.Uri;

/**
 * Created by Ananthakrishna on 09-03-2016.
 */
public interface OnDownload {
    void onImageDownloadComplete(Uri uri);
    void onDownloadError(String error);
}
