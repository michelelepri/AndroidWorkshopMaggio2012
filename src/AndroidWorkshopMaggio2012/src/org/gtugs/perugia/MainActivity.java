package org.gtugs.perugia;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
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
		
		// Viene abilitato il javascript
		_webView.getSettings().setJavaScriptEnabled(true);

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
		_url.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					switch (keyCode) {
					case KeyEvent.KEYCODE_ENTER:
						_webView.loadUrl(_url.getText().toString());
						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(_url.getWindowToken(), 0);
						return true;
					default:
						break;
					}
				}
				return false;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();

		// Ricarico l'url
		_webView.loadUrl(_url.getText().toString());
	}
	
	/*
	 * Metodi per gestire la WebView
	 */

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
