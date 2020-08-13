package com.kimi.wallet.ui.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimi.wallet.data.Pass
import kotlinx.android.synthetic.main.item_pass.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Kimi.Peng on 2020/8/13.
 */
class PassViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


    private val simpleDate = SimpleDateFormat("MMM d Y, h:mm a", Locale.getDefault())

    val passType = itemView.tv_PassType
    val purchaseTime = itemView.tv_purchaseTime
    val status = itemView.tv_passStatus


    fun bindData(pass: Pass, clickListener: (Pass, TextView) -> Unit) {

        itemView.setOnClickListener { clickListener(pass, passType) }

        passType.text = "${pass.passType} Pass"
        purchaseTime.text = "Purchase Time: \n${simpleDate.format(pass.purchaseTime)}"

        status.text = if (pass.isActivate){

            if (System.currentTimeMillis() > pass.expirationTime) {
                "Expirated"
            } else {
                "In Activate"
            }

        } else {
            "Not yet Activate"
        }

    }
}