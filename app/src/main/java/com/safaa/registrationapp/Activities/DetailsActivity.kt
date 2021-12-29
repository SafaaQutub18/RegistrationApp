package com.safaa.registrationapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.safaa.registrationapp.Model.User
import com.safaa.registrationapp.R
import com.safaa.registrationapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animation()

        val currentUser : User = intent.getSerializableExtra("newUser") as User

        binding.apply {
            nameET.setText("Name: "+  currentUser.name)
            locatET.setText("location: "+  currentUser.location)
            phoneET.setText("Phone: "+  currentUser.phone)
            passET.setText("Password: "+  currentUser.password)

            logOutBtn.setOnClickListener {
                val intent = Intent( this@DetailsActivity, SignInActivity::class.java)
                startActivity(intent)
            }

        }

    }
    private fun animation() {
        val cardV_anim = AnimationUtils.loadAnimation(this, R.anim.animation)
        binding.cardView.startAnimation(cardV_anim)
    }
}