package com.example.razorpayapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CustemerUpdateActivity : AppCompatActivity() {
    private lateinit var updateName: EditText
    private lateinit var updateEmail: EditText
    private lateinit var updateContact: EditText
    private lateinit var updateButton: AppCompatButton

    private var custemerId: String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custemer_update)

        updateName = findViewById(R.id.updateUserName_editText)
        updateEmail = findViewById(R.id.updateEmail_editText)
        updateContact = findViewById(R.id.updateUserContact_editText)
        updateButton = findViewById(R.id.updateSubmit_button)

        custemerId = intent.getStringExtra("custemer_Id")

        updateButton.setOnClickListener {
            updateData()
        }
    }


    @SuppressLint("CheckResult")
    private fun updateData() {
        val updateCustemerName = updateName.text.toString()
        val updateCustemerEmail = updateEmail.text.toString()
        val updateCustemercontact = updateContact.text.toString()
        val apiKeyName = "rzp_test_hP77hXzhOubfvK"
        val apiSicret = "g7cFXlWXnzP4wdX5LJJKwwps"

        val autharization = "Basic" + android.util.Base64.encodeToString(
            "$apiKeyName:$apiSicret".toByteArray(),
            android.util.Base64.NO_WRAP
        )

        val updateCuustemer = HashMap<String, String>()
        updateCuustemer["Authorization"] = autharization

        val updateCustemeritem =
            CustemerUpdateModel(updateCustemerName, updateCustemerEmail, updateCustemercontact)

        val updateApi = RezorPayApi.createRetrofit()
        updateApi.putCustemerUpdate(
            updateCuustemer,
            custemerId.toString(),
            updateCustemeritem
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ _ ->
                Toast.makeText(this, "Custemer Update Success", Toast.LENGTH_SHORT).show()
                finish()
            },
                { error ->
                    error.printStackTrace()
                    Toast.makeText(this, "update item failed ${error.message}", Toast.LENGTH_LONG)
                        .show()
                })

    }


}