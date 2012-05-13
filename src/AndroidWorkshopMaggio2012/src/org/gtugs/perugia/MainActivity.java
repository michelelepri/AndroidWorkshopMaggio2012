package org.gtugs.perugia;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private WebView webView;
	private EditText urlBox;
	private MainActivity activity = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;

		// Richiede la feature per mostrare la barra di progressione
		requestWindowFeature(Window.FEATURE_PROGRESS);

		setContentView(R.layout.main);
		setProgressBarVisibility(true);

		webView = (WebView) findViewById(R.id.webview);
		webView.setWebViewClient(new WebViewClient() {
			// Carica i link nell'app anziché nel browser standard
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			// Notifica gli errori di caricamento tramite TOAST
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Toast.makeText(activity, "Oh no! " + description,
						Toast.LENGTH_SHORT).show();
			}
		});

		webView.setWebChromeClient(new WebChromeClient() {
			// Barra del titolo come progressione nel caricamento della pagina
			public void onProgressChanged(WebView view, int progress) {
				activity.setProgress(progress * 100);
			}

			// Gestisce gli alert Javascript
			public boolean onJsAlert(WebView view, String url, String message,
					final android.webkit.JsResult result) {
				Toast.makeText(activity.getApplicationContext(), message, 3000)
						.show();
				result.confirm();
				return true;
			};
		});

		// Viene abilitato il javascript
		webView.getSettings().setJavaScriptEnabled(true);

		urlBox = (EditText) findViewById(R.id.url);
		urlBox.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					switch (keyCode) {
					case KeyEvent.KEYCODE_ENTER:
						webView.loadUrl(urlBox.getText().toString());
						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(urlBox.getWindowToken(), 0);
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
		webView.loadUrl(urlBox.getText().toString());
	}

	/*
	 * Metodi per gestire la WebView
	 */

	public void vai(View v) {
		webView.loadUrl(urlBox.getText().toString());
	}

	public void ricarica(View v) {
		webView.reload();
	}

	public void ferma(View v) {
		webView.stopLoading();
	}

	public void inDietro(View v) {
		webView.goBack();
	}

	public void inAvanti(View v) {
		webView.goForward();
	}
}
