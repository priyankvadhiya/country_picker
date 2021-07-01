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

	        implementation 'com.github.priyankvadhiya:country_picker:1.0.2'
    }

Step 3: Start CountryPicker

    CountryPicker.build {
            this.context = context //pass your context
            this.clickOnCountryItemResult = this //for callback
            this.fragmentManager = supportFragmentManager //for show screen
            this.language = "en" //default = en
    }.show()


## License

    Copyright 2020 Priyank Vadhiya

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
