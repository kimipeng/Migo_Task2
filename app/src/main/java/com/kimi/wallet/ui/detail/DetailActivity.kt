package com.kimi.wallet.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kimi.wallet.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }


    companion object {

        @JvmStatic
        val EXTRA_PASS_ID = "EXTRA_PASS_ID"
    }
}
