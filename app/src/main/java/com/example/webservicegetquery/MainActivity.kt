package com.example.webservicegetquery


import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


class MainActivity : AppCompatActivity() {
    internal lateinit var contentTV: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    interface NewsService {
        @GET("top-headlines")
        fun getNews(@Query("apiKey") apiKey: String, @Query("country") country: String, @Query("category") cat: String): Call<Void>

    }

    fun LoadData(view: View) {

        val NEWS_API_URL = "http://newsapi.org/v2"
        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/").build()
        val newsService = retrofit.create(NewsService::class.java)
        newsService.getNews("4afafad986e2458d95ab9eab3e71bcce", "us", "business")
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    contentTV!!.text = response.body()!!.toString()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {

                }
            })
    }
}
