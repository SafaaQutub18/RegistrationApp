package com.safaa.registrationapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.bumptech.glide.Glide
import com.safaa.registrationapp.Model.User
import com.safaa.registrationapp.R
import com.safaa.registrationapp.databinding.ActivitySignInBinding
import com.safaa.registrationapp.databinding.ActivitySignUpBinding
import com.safaa.registrationapp.services.DatabaseHelper
import java.net.URL

class SignUpActivity : AppCompatActivity() {
    lateinit var binding:ActivitySignUpBinding
    private val dbHelper by lazy { DatabaseHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(URL("https://media0.giphy.com/media/g0at9mLkvigqstLy3z/source.gif"))
            .into(binding.rocketGif)

        animation()

        binding.apply {
            upBtn.setOnClickListener {
                val userName = nameET.text.toString()
                val location = locatET.text.toString()
                val phonep = phoneET.text.toString()
                val userPassword = passET.text.toString()

                if(userName!= "" && location!= "" && phonep!= ""&& userPassword != "") {
                    if(dbHelper.readUserData(userName) == null) {
                        var newUser = User(userName, location, phonep, userPassword)
                        dbHelper.saveData(newUser)

                        val intent = Intent(this@SignUpActivity, DetailsActivity::class.java)
                        intent.putExtra("newUser",newUser)
                        startActivity(intent)

                    }
                    else
                        Toast.makeText(this@SignUpActivity, "Username exists", Toast.LENGTH_SHORT).show()
                }
                else
                    Toast.makeText(this@SignUpActivity, "All fields required", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onClick(view : View) {
        val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
        startActivity(intent)
    }


    private fun animation() {
        val cardV_anim = AnimationUtils.loadAnimation(this,R.anim.animation)
        binding.cardView.startAnimation(cardV_anim)
    }
}