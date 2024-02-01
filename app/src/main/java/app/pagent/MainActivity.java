package app.pagent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;


public class MainActivity extends AppCompatActivity {
    private WebView mywebView;

    private AppOpenAd mappOpenAd;

    private boolean isshowingad= false;
    private void showopenads(){
        AdRequest adRequest = new AdRequest.Builder().build();

        AppOpenAd.load(this, "ca-app-pub-3732416706239165/4859908653",
                adRequest, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                new AppOpenAd.AppOpenAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                    }

                    @Override
                    public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                        super.onAdLoaded(appOpenAd);
                        mappOpenAd = appOpenAd;

                        if(!isshowingad) {
                            mappOpenAd.show(MainActivity.this);
                            isshowingad=true;

                        }

                    }
                });
    }
    @Override
    protected void onResume() {
        super.onResume();

        if(!isshowingad) {
            showopenads();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        mywebView=(WebView) findViewById(R.id.webview);
        mywebView.setWebViewClient(new WebViewClient());
        mywebView.loadUrl("https://plotperty.com");
        WebSettings webSettings=mywebView.getSettings();

        webSettings.setJavaScriptEnabled(true);

    }
    public class mywebClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view,url,favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public void onBackPressed(){
        if(mywebView.canGoBack()){
            mywebView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}