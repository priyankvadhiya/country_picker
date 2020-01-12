# Country And Currency Picker For Android

[![](https://jitpack.io/v/priyankvadhiya/country_picker.svg)](https://jitpack.io/#priyankvadhiya/country_picker)

<img src="https://github.com/priyankvadhiya/country_picker/blob/master/device-2020-01-05-210824.png" width="250">


## Usage

Step:1 Add it in your root build.gradle at the end of repositories

    allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
    }

Step 2: Add the dependency

    dependencies {
  		//Recommended
        	implementation 'com.google.android.material:material:1.0.0'

	        implementation 'com.github.priyankvadhiya:country_picker:1.0.1'
    }

Step 3: Start CountryPicker

    CountryPicker.build {
            this.context = context //pass your context
            this.clickOnCountryItemResult = this //for callback
            this.fragmentManager = supportFragmentManager //for show screen
            this.language = "en" //default = en
    }.show()
