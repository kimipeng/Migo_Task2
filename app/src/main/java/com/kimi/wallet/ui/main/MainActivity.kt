package com.kimi.wallet.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimi.wallet.R
import com.kimi.wallet.ui.add.AddPassActivity
import com.kimi.wallet.ui.detail.DetailActivity
import com.kimi.wallet.ui.detail.DetailActivity.Companion.EXTRA_PASS_ID
import com.kimi.wallet.util.DataViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PassViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddPassActivity::class.java)
            startActivity(intent)
        }


        viewModel = ViewModelProvider(this, DataViewModelFactory(this)).get(PassViewModel::class.java)

        val pixelSize = resources.getDimensionPixelSize(R.dimen.item_decoration_margin)
        recycler.addItemDecoration(ItemDecoration(pixelSize))

        val adapter = PassAdapter{ pass, transitionView ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)

            intent.putExtra(EXTRA_PASS_ID, pass.id)


            // Make TransitionAnimation when intent to other activity.
            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                transitionView,
                ViewCompat.getTransitionName(transitionView)!!
            )

            startActivity(intent, option.toBundle())

        }

        viewModel.allPass.observe(this, Observer {
            adapter.submitList(it)
        })

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapter



    }
}
