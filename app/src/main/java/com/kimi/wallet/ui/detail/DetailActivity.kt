package com.kimi.wallet.ui.detail

import android.icu.util.LocaleData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils

import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kimi.wallet.R
import com.kimi.wallet.data.Pass
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.tv_PassType
import kotlinx.android.synthetic.main.activity_detail.tv_purchaseTime
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var simpleDate: SimpleDateFormat
    var activateTime = 0L
    var expirationTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Change Top-left icon
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

        val factory = PassDetailViewModelFactory(this, intent.getLongExtra(EXTRA_PASS_ID, 0L))
        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
        simpleDate = SimpleDateFormat("Y MMM d, h:mm a", Locale.getDefault())


        viewModel.pass.observe(this, Observer(this::init))

    }


    private fun init(pass: Pass?) {

        pass?.let {pass ->

            tv_PassType.text = "${pass.passType} Pass"
            tv_purchaseTime.text = "Purchase Time: \n${simpleDate.format(pass.purchaseTime)}"


            if (pass.isActivate) {
                activate.visibility = View.INVISIBLE
                tv_activation.text = "ActivateTime: \n${simpleDate.format(pass.activationTime)}"
                tv_expiration.text = "ExpirationTime: \n${simpleDate.format(pass.expirationTime)}"
                tv_status.text = if (System.currentTimeMillis() > pass.expirationTime) {
                    "Expirated"
                } else {
                    "In Activate"
                }
            } else {
                val passBehavior = if (TextUtils.equals("Day", pass.passType)) {
                    DatePass()
                } else {
                    HoursPass()
                }

                passBehavior.execute(pass)

                activate.setOnClickListener {
                    viewModel.update(pass, activateTime, expirationTime)
                }
            }


        }

    }


    private inner class HoursPass : PassBehavior {
        override fun execute(pass: Pass) {

            tv_duration.text = "Duration: ${pass.passDuration} Hours"

            activateTime = System.currentTimeMillis()
            expirationTime = activateTime + pass.passDuration * 1000 * 3600

            tv_activation.text = "ActivateTime Now - \n${simpleDate.format(activateTime)}"
            tv_expiration.text = "ExpirationTime \n${simpleDate.format(expirationTime)}"
            tv_status.text = "Not yet Activate"

        }
    }

    private inner class DatePass : PassBehavior {
        override fun execute(pass: Pass) {
            tv_duration.text = "Duration: ${pass.passDuration} Days"

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val date = calendar.get(Calendar.DATE) + pass.passDuration
            calendar.clear()
            calendar.set(year, month, date)

            activateTime = System.currentTimeMillis()
            expirationTime = calendar.getTimeInMillis()
            tv_activation.text = "ActivateTime Now - \n${simpleDate.format(activateTime)}"
            tv_expiration.text = "ExpirationTime \n${simpleDate.format(expirationTime)}"
            tv_status.text = "Not yet Activate"

        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        @JvmStatic
        val EXTRA_PASS_ID = "EXTRA_PASS_ID"
    }

    interface PassBehavior {
        fun execute(pass: Pass)
    }


}
