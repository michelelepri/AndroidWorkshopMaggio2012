package org.gtugs.perugia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class SplashScreenActivity extends Activity {
	// Flag per determinare se è attivo o meno lo splash
	private boolean _attivo = true;

	// Durata dello splash
	private int _tempoSpash = 5000;

	private Thread splashTread = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		// Thread per far vedere la spash screen
		splashTread = new Thread() {
			@Override
			public void run() {
				try {
					int tempoPassato = 0;
					while (_attivo && (tempoPassato < _tempoSpash)) {
						sleep(100);
						tempoPassato += 100;
					}
				} catch (InterruptedException e) {
					Log.d("Splash", e.getMessage());
				} finally {
					finish();
					// Avvia l'Activity principale
					Intent i = new Intent(SplashScreenActivity.this,
							MainActivity.class);
					startActivity(i);
				}
			}
		};

		splashTread.start();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			_attivo = false;
		}
		return super.onTouchEvent(event);
	}
}
