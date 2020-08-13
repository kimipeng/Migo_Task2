package com.kimi.wallet.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kimi.wallet.R
import com.kimi.wallet.util.DataViewModelFactory
import kotlinx.android.synthetic.main.activity_add_pass.*

class AddPassActivity : AppCompatActivity() {

    lateinit var addPassViewModel: AddPassViewModel

    var passType: String = ""
    var passDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pass)


        // Init ViewModel
        addPassViewModel = ViewModelProvider(this, DataViewModelFactory(this)).get(AddPassViewModel::class.java)
        addPassViewModel.saved.observe(this, Observer {
            it.getContentIfNotHandled()?.let { savefinish ->
                if (savefinish) {
                    onBackPressed()
                } else {
                    Toast.makeText(this, "Please Check PassType Or PassDuration don't empty or Zero.", Toast.LENGTH_LONG).show()
                }
            }
        })


        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.day ->{
                    Log.d("kimi", "You select Day Pass")
                    passType = "Day"
                }

                R.id.hour -> {
                    Log.d("kimi", "You select hour Pass")
                    passType = "Hour"
                }
            }
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    if (it.isNotEmpty()) {
                        passDuration = Integer.parseInt(s.toString());
                    } else {
                        passDuration = 0
                    }
                }
            }
        })

    }

    fun onSave(view: View) {
        addPassViewModel.save(passType, passDuration)
    }

}
