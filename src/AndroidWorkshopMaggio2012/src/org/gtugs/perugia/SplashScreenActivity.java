/**
 * 
 */
package org.gtugs.perugia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author retek http://michelelepri.blogspot.com
 * 
 */
public class SplashScreenActivity extends Activity
{

   // flag per determinare se è attivo o meno lo splash
   protected boolean _attivo     = true;

   // durata dello splash
   protected int     _tempoSpash = 5000;

   /*
    * (non-Javadoc)
    * 
    * @see android.app.Activity#onCreate(android.os.Bundle)
    */
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);

      setContentView(R.layout.splash_screen);

      // thread per far vedere la spash screen
      Thread splashTread = new Thread() {

         @Override
         public void run()
         {
            try
            {
               int tempoPassato = 0;
               while (_attivo && (tempoPassato < _tempoSpash))
               {
                  sleep(100);
                  if (_attivo)
                  {
                     tempoPassato += 100;
                  }
               }
            }
            catch (InterruptedException e)
            {
               // non facciamo nulla, andrebbe almeno loggato qualcosa comunque

            }
            finally
            {
               finish();
               
               // lancio l'activity
               Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
               startActivity(i);
            }
         }
      };
      splashTread.start();
   }
}
