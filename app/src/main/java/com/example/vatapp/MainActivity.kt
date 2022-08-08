package com.example.vatapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vatapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    // binding object
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root // reference layout file
        setContentView(view) // link layout


        binding.btnCalculateVat.setOnClickListener {
            calculateVat()
        }// End setOnClickListener


    }// End onCreate

    //@SuppressLint("StringFormatInvalid")
    private fun calculateVat() {
        val stringInTextField=binding.number.text.toString()
        val cost  =stringInTextField.toDouble()
        val SelectedId=binding.rgVatOption.checkedRadioButtonId
        val vatpercentage= when (SelectedId){
            R.id.rb_vat_10->0.10
            R.id.rb_vat_15->0.15
            else ->0.20
        }

        var vat = vatpercentage * cost
        var total = cost + vat
        // Switch value
        val roundVat = binding.switchRoundUp.isChecked

        if (roundVat){
            total = kotlin.math.ceil(total)
        }
        // Total Formatting
        val formatedTotal = NumberFormat.getCurrencyInstance().format(total)
        binding.txtCostTotal.text = getString(R.string.totall, formatedTotal)
    }

}// End MainActivity