package com.example.givemeweather

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private val TAG = "MyApp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWeatherBtn.setOnClickListener {
            getWeather()
        }
    }
    fun getWeather(){
        Thread(Runnable {
            try {
                val doc = Jsoup.connect("https://yandex.ru/pogoda/barnaul").get()
                val tempDiv = doc.select("div.temp.fact__temp.fact__temp_size_s > span.temp__value.temp__value_with-unit").text()
                val windSpeed = doc.select("term.term_orient_v.fact__wind-speed").text()
                val atmosPressure = doc.select("div.term.term_orient_v.fact__pressure > div.term__value").text()
                runOnUiThread { tempTextView.text = tempDiv.toString()
                                windTextView.text = windSpeed.toString()
                                pressureTextView.text = atmosPressure.toString()}

            } catch (e: IOException) {
            }
        }).start()
    }

//temp fact__temp.fact__temp_size_s     div.term.term_orient_v.fact__wind-speed > div.term__value >


//    fun getWeather(view: View): Elements? {
//        val doc = Jsoup.connect("https://yandex.ru/pogoda/barnaul").get()
//        var tempDiv: Elements? = doc.getElementsByClass("temp fact__temp.fact__temp_size_s")
//        val tempDiv: Elements? = doc.getElementsByClass("temp fact__temp.fact__temp_size_s")
//        return tempDiv
//
//
//    }

}