package xyz.megundo.rainbowhello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat
import com.google.android.things.pio.Gpio

private val TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {
    var redLED:Gpio?=null
    val blinkHandler=Handler()

    val blinkRunnable=object:Runnable{
        override fun run() {

            redLED?.also {gpio->
                gpio.value=!gpio.value

                blinkHandler.postDelayed(this,1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        redLED=RainbowHat.openLedRed()

        blinkHandler.postDelayed(blinkRunnable,1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        blinkHandler.removeCallbacks(blinkRunnable)
        redLED?.close()
    }

}
