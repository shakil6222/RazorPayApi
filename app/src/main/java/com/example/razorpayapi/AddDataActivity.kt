package com.example.razorpayapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddDataActivity : AppCompatActivity() {


    private lateinit var addUserId: EditText
    private lateinit var addUserName: EditText
    private lateinit var addUserContact: EditText
    private lateinit var addEmail: EditText
    private lateinit var submitButton: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        addUserId = findViewById(R.id.addUserId_editText)
        addUserName = findViewById(R.id.addUserName_editText)
        addEmail = findViewById(R.id.addEmail_editText)
        addUserContact = findViewById(R.id.addUserContact_editText)
        submitButton = findViewById(R.id.submit_button)


        submitButton.setOnClickListener {
            addUser()
        }

    }

    @SuppressLint("CheckResult")
    fun addUser() {
        val name = addUserName.text.toString()
        val email = addEmail.text.toString()
        val contact = addUserContact.text.toString()
        val postData = PostDataModel(
            name,
            email,
            contact, "1", "12ABCDE2356F7GH",
            Notes(notes_key_1 = "Tea, Earl Grey, Hot", notes_key_2 = "Tea, Earl Grey… decaf.")
        )
        val apiKey = "rzp_test_hP77hXzhOubfvK"
        val apiPassword = "g7cFXlWXnzP4wdX5LJJKwwps"

        val authatation = "Basic" + android.util.Base64.encodeToString(
            "$apiKey:$apiPassword".toByteArray(),
            android.util.Base64.NO_WRAP
        )
        val hashMap = HashMap<String, String>()
        hashMap["Authorization"] = authatation

        RezorPayApi.createRetrofit().postCustemer(hashMap, postData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Toast.makeText(this, "${result.count}", Toast.LENGTH_SHORT).show()
                finish()
            }, { error ->
                Toast.makeText(this, "${error.message}", Toast.LENGTH_LONG).show()
            })
    }
}