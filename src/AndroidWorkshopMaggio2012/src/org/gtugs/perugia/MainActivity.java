package org.gtugs.perugia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

		/*
		 * Override del metodo UrlLoading per far aprire i link selezionati
		 * nella WebView
		 */
		_webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});

		_url = (EditText) findViewById(R.id.url);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();

		// abilito il javascript
		_webView.getSettings().setJavaScriptEnabled(true);

		// carico l'url
		_webView.loadUrl(_url.getText().toString());
	}

	public void vai(View v) {
		_webView.loadUrl(_url.getText().toString());
	}

	public void ricarica(View v) {
		_webView.reload();
	}

	public void ferma(View v) {
		_webView.stopLoading();
	}

	public void inDietro(View v) {
		_webView.goBack();
	}

	public void inAvanti(View v) {
		_webView.goForward();
	}
}
