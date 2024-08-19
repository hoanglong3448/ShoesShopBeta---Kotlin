package com.fp.shoesshop.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fp.shoesshop.MainActivity
import com.fp.shoesshop.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding : ActivityIntroBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityIntroBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            startActivity(Intent(this@IntroActivity,MainActivity::class.java))
        }

    }
}