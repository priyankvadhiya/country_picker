package com.priyank.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.priyank.countrypicker.CountryPicker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CountryPicker.ClickOnCountryItemResult {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            CountryPicker.build {
                this.context = this@MainActivity
                this.clickOnCountryItemResult = this@MainActivity
                this.fragmentManager = supportFragmentManager
                this.language = "en"
            }.show()
        }
    }

    override fun onClickCountry(country: CountryPicker.Country) {
        Toast.makeText(this,country.name,Toast.LENGTH_SHORT).show()
    }
}
