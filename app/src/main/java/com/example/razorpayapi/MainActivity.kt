package com.example.razorpayapi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {


    private var arrayList = ArrayList<Item>()
    private lateinit var adapter: UserAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var floatingButton: FloatingActionButton

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup RecyclerView
        recyclerView = findViewById(R.id.listItem_recycleView)
        floatingButton = findViewById(R.id.addButton_floatingActionButton)

        floatingButton.setOnClickListener {
            startActivity(Intent(this, AddDataActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        getData()
    }


    // It's better to fetch these values securely rather than hardcoding them.
    @SuppressLint("CheckResult")
    fun getData() {
        val apiKey = "rzp_test_hP77hXzhOubfvK"
        val apiPassword = "g7cFXlWXnzP4wdX5LJJKwwps"

        // Correctly format the credentials string.
        val credentials = "$apiKey:$apiPassword"
        val authHeader = "Basic" + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)

        val handelarMap = HashMap<String, String>()
        handelarMap["Authorization"] = authHeader


        RezorPayApi.createRetrofit().getCustemer(handelarMap).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe { result ->
                Toast.makeText(this, "${result.count}", Toast.LENGTH_SHORT).show()
                val allData = result.items
                arrayList.clear()
                if (!allData.isNullOrEmpty()) {
                    arrayList.addAll(allData)
                    adapter = UserAdapter(this, arrayList)
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = adapter

                } else {
                    Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
