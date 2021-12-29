package com.safaa.registrationapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.bumptech.glide.Glide
import com.safaa.registrationapp.Model.User
import com.safaa.registrationapp.R
import com.safaa.registrationapp.databinding.ActivitySignInBinding
import com.safaa.registrationapp.services.DatabaseHelper
import java.net.URL

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    private val dbHelper by lazy { DatabaseHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(URL("https://media0.giphy.com/media/g0at9mLkvigqstLy3z/source.gif"))
            .into(binding.rocketGif)

        animation()


        binding.apply {
            inBtn.setOnClickListener {
                val name = nameET.text.toString()
                val password = passET.text.toString()

                if(name!= "" && password != "") {
                    val c_user = dbHelper.readUserData(name)
                    if (c_user != null) {
                        //check on password
                        if(c_user.password == password) {
                            val intent = Intent(this@SignInActivity, DetailsActivity::class.java)
                            intent.putExtra("newUser", c_user)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this@SignInActivity, "wrong password", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@SignInActivity, "Username doesn't exists", Toast.LENGTH_SHORT).show()
                        Log.d("signIn", "\"Username doesn't exists\"")
                    }
                }
                else {
                    Log.d("signIn", "\"All fields required\"")
                    Toast.makeText(this@SignInActivity, "All fields required", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }


    }
    fun onClick(view : View) {
        val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun animation() {
        val cardV_anim = AnimationUtils.loadAnimation(this,R.anim.animation)
        binding.cardView.startAnimation(cardV_anim)
    }
}