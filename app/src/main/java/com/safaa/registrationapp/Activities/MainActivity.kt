package com.safaa.registrationapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.safaa.registrationapp.databinding.ActivityMainBinding
import java.net.URL


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(URL("https://media0.giphy.com/media/g0at9mLkvigqstLy3z/source.gif"))
            .into(binding.rocketGif)

        binding.apply {
            inBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, SignInActivity::class.java)
                startActivity(intent)
            }

            upBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, SignUpActivity::class.java)
                startActivity(intent)
            }



        }
    }
}
