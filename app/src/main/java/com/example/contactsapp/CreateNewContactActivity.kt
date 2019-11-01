package com.example.contactsapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class CreateNewContactActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etWebsite: EditText
    private lateinit var etLocation: EditText
    private lateinit var ivSmile: ImageView
    private lateinit var ivNeutral: ImageView
    private lateinit var ivUndissapointed: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_contact)

        etName = findViewById(R.id.etName)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        etWebsite = findViewById(R.id.etWebsite)
        etLocation = findViewById(R.id.etLocation)

        ivSmile = findViewById(R.id.ivSmile)
        ivSmile.setOnClickListener { onClickEvent(R.drawable.smile) }

        ivNeutral = findViewById(R.id.ivNeutral)
        ivNeutral.setOnClickListener { onClickEvent(R.drawable.neutral) }

        ivUndissapointed = findViewById(R.id.ivUndissapointed)
        ivUndissapointed.setOnClickListener { onClickEvent(R.drawable.dissatisfied) }
    }

    private fun checkForAllDataExist(fieldsArray: Array<EditText>): Boolean {

        for (field: EditText in fieldsArray) {
            if (field.text.toString().isEmpty()) {
                return false
            }
        }
        return true
    }

    private fun onClickEvent(pictureName: Int) {

        if (!checkForAllDataExist(arrayOf(etName, etPhoneNumber, etWebsite, etLocation))) {
            Toast.makeText(this@CreateNewContactActivity, "Please, enter all fields!",
                Toast.LENGTH_SHORT).show()
        } else {
            val name: String = etName.text.toString()
            val phoneNumber: String = etPhoneNumber.text.toString()
            val website: String = etWebsite.text.toString()
            val location: String = etLocation.text.toString()

            val intent = Intent()
            intent.putExtra("name", name)
            intent.putExtra("phoneNumber", phoneNumber)
            intent.putExtra("website", website)
            intent.putExtra("location", location)
            intent.putExtra("pictureName", pictureName)
            setResult(Activity.RESULT_OK, intent)

            this@CreateNewContactActivity.finish()
        }

    }
}
