package org.gtugs.perugia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
   private WebView _webView;
   
   private EditText _url;
   
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        _webView = (WebView) findViewById(R.id.webview);
        _url = (EditText) findViewById(R.id.url);
    }
    
    /* (non-Javadoc)
    * @see android.app.Activity#onResume()
    */
   @Override
   protected void onResume()
   {
      super.onResume();
      
      // abilito il javascript
      _webView.getSettings().setJavaScriptEnabled(true);
      
      // carico l'url
      _webView.loadUrl(_url.getText().toString());
   }
   
   public void vai(View v)
   {
      _webView.loadUrl(_url.getText().toString());
   }
   
   public void ricarica(View v)
   {
      _webView.reload();
   }
   
   public void ferma(View v)
   {
      _webView.stopLoading();
   }
   
   public void inDietro(View v)
   {
      _webView.goBack();
   }
   
   public void inAvanti(View v)
   {
      _webView.goForward();
   }
}
