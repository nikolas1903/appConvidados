package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.viewmodel.GuestFormViewModel
import com.example.convidados.R
import com.example.convidados.service.constants.GuestConstants

class GuestFormActivity : AppCompatActivity() {

    private lateinit var mViewModel: GuestFormViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        loadData()

        val rbPresent = findViewById<RadioButton>(R.id.rb_present)
        rbPresent.isChecked = true
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt(GuestConstants.GUESTID)
            mViewModel.load(mGuestId)
        }
    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        mViewModel.guest.observe(this, Observer {
            val etName = findViewById<EditText>(R.id.et_name)
            etName.setText(it.name)
            if (it.presence) {
                val rbPresent = findViewById<RadioButton>(R.id.rb_present)
                rbPresent.isChecked = true
            } else {
                val rbAbsent = findViewById<RadioButton>(R.id.rb_absent)
                rbAbsent.isChecked = true
            }
        })
    }

    private fun setListeners() {
        val btnSave = findViewById<Button>(R.id.btn_save)

        btnSave.setOnClickListener {

            val etName = findViewById<EditText>(R.id.et_name)
            val name = etName.text.toString()

            val rbPresent = findViewById<RadioButton>(R.id.rb_present)
            val presence = rbPresent.isChecked

            mViewModel.save(mGuestId, name, presence)
        }
    }


}