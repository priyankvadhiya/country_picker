# Country And Currency Picker For Android

[![](https://jitpack.io/v/priyankvadhiya/CountryPicker.svg)](https://jitpack.io/#priyankvadhiya/CountryPicker)


<img src="https://github.com/priyankvadhiya/country_picker/blob/master/device-2020-01-05-210824.png" width="250">


## Usage

Step:1 Add it in your root build.gradle at the end of repositories

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
    }

Step 2: Add the dependency

    dependencies {
    
  		//Recommended
        	implementation 'com.google.android.material:material:1.0.0'

	        implementation 'com.github.priyankvadhiya:country_picker:1.0'
    }

Step 3: Start CountryPicker

    val countryPickerDialog = CountryPickerDialog()
    countryPickerDialog.listener(this)
    countryPickerDialog.show(supportFragmentManager, "CountryPicker")
