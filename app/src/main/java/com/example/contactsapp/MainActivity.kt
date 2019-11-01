package com.example.contactsapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    companion object {
        const val CREATE_NEW_CONTACT_ACTIVITY_UID = 0
    }

    private lateinit var btnSubmit: Button
    private lateinit var tvAppName: TextView
    private lateinit var ivPersonFace: ImageView
    private lateinit var tvPersonName: TextView
    private lateinit var ivCallLink: ImageView
    private lateinit var ivWebLink: ImageView
    private lateinit var ivMapLink: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivPersonFace = findViewById(R.id.ivPersonFace)
        tvPersonName = findViewById(R.id.tvPersonName)
        ivCallLink = findViewById(R.id.ivCallLink)
        ivWebLink = findViewById(R.id.ivWebLink)
        ivMapLink = findViewById(R.id.ivMapLink)
        btnSubmit = findViewById(R.id.btnCreateNewContact)
        tvAppName = findViewById(R.id.tvAppName)

        ivPersonFace.visibility = View.GONE
        tvPersonName.visibility = View.GONE
        ivCallLink.visibility = View.GONE
        ivWebLink.visibility = View.GONE
        ivMapLink.visibility = View.GONE

        btnSubmit.setOnClickListener {

            val intent = Intent(this@MainActivity, CreateNewContactActivity::class.java)
            startActivityForResult(intent, CREATE_NEW_CONTACT_ACTIVITY_UID)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CREATE_NEW_CONTACT_ACTIVITY_UID) {
            if (resultCode == Activity.RESULT_OK) {

                ivPersonFace.setImageResource(data!!.getIntExtra("pictureName", R.drawable.smile))
                tvPersonName.text = data.getStringExtra("name")

                ivPersonFace.visibility = View.VISIBLE
                tvPersonName.visibility = View.VISIBLE
                ivCallLink.visibility = View.VISIBLE
                ivWebLink.visibility = View.VISIBLE
                ivMapLink.visibility = View.VISIBLE

                ivCallLink.setOnClickListener {

                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + data.getStringExtra("phoneNumber")))
                    startActivity(intent)
                }

                ivWebLink.setOnClickListener {

                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.getStringExtra("website")))
                    startActivity(intent)
                }

                ivMapLink.setOnClickListener {

                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + data.getStringExtra("location")))
                    startActivity(intent)
                }
            }

            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }
}
