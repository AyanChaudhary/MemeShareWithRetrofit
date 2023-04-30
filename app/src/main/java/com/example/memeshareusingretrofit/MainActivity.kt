package com.example.memeshareusingretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.memeshareusingretrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding:com.example.memeshareusingretrofit.databinding.ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= com.example.memeshareusingretrofit.databinding.ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var url="https://meme-api.com/gimme"

        getData()
        binding.btnNext.setOnClickListener{
            getData()
        }
    }

    private fun getData() {

        binding.progressBar.visibility= View.VISIBLE
        RetrofitInstance.apiInterface.getData().enqueue(object : Callback<responceDataClass?> {
            override fun onResponse(
                call: Call<responceDataClass?>,
                response: Response<responceDataClass?>
            ) {
                binding.tvTitle.text=response.body()?.title
                binding.tvAuthor.text=response.body()?.author
                Glide.with(this@MainActivity).load(response.body()?.url).into(binding.ivMemeImage)
                binding.progressBar.visibility= View.GONE
            }

            override fun onFailure(call: Call<responceDataClass?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility= View.GONE

            }
        })
    }
}