package com.kimi.wallet.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.kimi.wallet.R
import com.kimi.wallet.data.Pass

/**
 * Created by Kimi.Peng on 2020/8/13.
 */
class PassAdapter(private val clickListener: (Pass, TextView) -> Unit): PagedListAdapter<Pass, PassViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pass>() {
            override fun areItemsTheSame(oldItem: Pass, newItem: Pass): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Pass, newItem: Pass): Boolean {
                return oldItem == newItem
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pass, parent, false)
        return PassViewHolder(view)
    }

    override fun onBindViewHolder(holder: PassViewHolder, position: Int) {
       getItem(position)?.let {
           holder.bindData(pass = it, clickListener = clickListener)
       }
    }

    fun getPass(position: Int): Pass?{
        return getItem(position)
    }
}