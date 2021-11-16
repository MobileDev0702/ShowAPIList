package com.codechallenge.apilist.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.codechallenge.apilist.R
import com.codechallenge.apilist.model.ItemsViewModel

class DetailActivity : AppCompatActivity() {

    private var itemData: ItemsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setApiDetails()
        setClickBackButton()
    }

    private fun setApiDetails() {
        itemData = intent.getSerializableExtra("ItemData") as ItemsViewModel?
        var apiTextView: TextView = findViewById(R.id.tv_api)
        var descriptionTextView: TextView = findViewById(R.id.tv_description)
        var authTextView: TextView = findViewById(R.id.tv_auth)
        var httpsTextView: TextView = findViewById(R.id.tv_https)
        var corsTextView: TextView = findViewById(R.id.tv_cors)
        var linkTextView: TextView = findViewById(R.id.tv_link)

        apiTextView.text = itemData!!.api
        descriptionTextView.text = itemData!!.description
        authTextView.text = itemData!!.auth
        httpsTextView.text = itemData!!.https
        corsTextView.text = itemData!!.cors
        linkTextView.text = itemData!!.link

        linkTextView.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@DetailActivity, LinkDetailActivity::class.java).apply {
                putExtra("Link", itemData!!.link)
            }
            startActivity(intent)
        })
    }

    private fun setClickBackButton() {
        var backBtn: ImageView = findViewById(R.id.iv_back)
        backBtn.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
}