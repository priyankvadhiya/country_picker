# Country And Currency Picker For Android

[![](https://jitpack.io/v/priyankvadhiya/CountryPicker.svg)](https://jitpack.io/#priyankvadhiya/CountryPicker)


<img src="https://github.com/priyankvadhiya/countrypicker/blob/master/device-2020-01-05-210824.png" width="250">


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
		implementation 'com.github.priyankvadhiya:CountryPicker:1.0'
    }

Step 3: Start CountryPicker

    val countryPickerDialog = CountryPickerDialog()
    countryPickerDialog.listener(this)
    countryPickerDialog.show(supportFragmentManager, "CountryPicker")
