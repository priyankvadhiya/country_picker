package com.priyank.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.priyank.countrypicker.Country
import com.priyank.countrypicker.CountryPickerDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            val countryPickerDialog = CountryPickerDialog()
            countryPickerDialog.listener(object : CountryPickerDialog.ClickOnCountryItemResult {
                override fun onClickCountry(country: Country) {
                    Toast.makeText(this@MainActivity, country.name, Toast.LENGTH_SHORT).show()
                }
            })
            countryPickerDialog.show(supportFragmentManager, "")
        }
    }
}
