package ak.happinessinc.com.akimagedownloaderdemo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import ak.happinessinc.com.akimagedownloader.callbacks.OnDownload;
import ak.happinessinc.com.akimagedownloader.utilities.ImageDownloader;


public class MainActivity extends AppCompatActivity implements OnDownload {
    EditText urlEditText;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        urlEditText=(EditText) findViewById(R.id.mainActivity_urlEditText);
        imageView=(ImageView) findViewById(R.id.imageView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageDownloader downloader= new ImageDownloader(MainActivity.this);
                if(urlEditText.getText() !=null) {
                    downloader.startDownload(urlEditText.getText().toString());
                    Snackbar.make(view, "Downloading image", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onImageDownloadComplete(Uri uri) {
        imageView.setImageURI(uri);
    }

    @Override
    public void onDownloadError(String error) {
        Snackbar.make(findViewById(R.id.relative_layout), error, Snackbar.LENGTH_LONG).setAction("Action", null).
                setActionTextColor(ColorStateList.valueOf(Color.RED)).show();
    }
}
