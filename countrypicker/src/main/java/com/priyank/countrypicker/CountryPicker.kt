package com.priyank.countrypicker

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.country_picker_dialog.view.*
import java.util.*


class CountryPicker(private var mContext: Context) {

    private lateinit var fragmentManager: FragmentManager
    private val countryPickerDialog = CountryPickerDialog()


    constructor(builder: Builder) : this(builder.context!!) {
        mContext = builder.context!!
        setLocale(builder.language)
        if (builder.clickOnCountryItemResult != null) {
            countryPickerDialog.listener(builder.clickOnCountryItemResult!!)
        }
        fragmentManager = builder.fragmentManager!!
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()

        fun getAllCountriesAndCurrencies(): ArrayList<CountryModel> {
            val list = arrayListOf<CountryModel>()
            list.addAll(Countries.COUNTRIES)
            return list
        }
    }

    class Builder {

        var context: Context? = null

        var clickOnCountryItemResult: ClickOnCountryItemResult? = null

        var fragmentManager: FragmentManager? = null

        var language: String = "en"

        fun build() = CountryPicker(this)
    }


    data class CountryModel(
        val code: String,
        val name: Int,
        val dialCode: String,
        val flag: Int,
        val currency: String,
        val currencySymbol: String
    )


    private fun setLocale(localeName: String) {
        if (localeName != "en") {
            val res: Resources = mContext.resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.setLocale(Locale(localeName.toLowerCase()))
            res.updateConfiguration(conf, dm)
        }
    }

    fun show() {
        if (::fragmentManager.isInitialized) {
            countryPickerDialog.show(fragmentManager, "CountryPickerDialog")
        }
    }

    class CountryPickerDialog : BottomSheetDialogFragment() {

        private lateinit var mAdapter: CountryPickerAdapter
        lateinit var clickOnCountryItemResult: ClickOnCountryItemResult

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.country_picker_dialog, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            setUpAdapter(view)
            view.edtSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    //
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    //
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    mAdapter.filter.filter(s.toString().trim())
                }
            })
        }

        override fun getTheme(): Int {
            return R.style.AppBottomSheetDialogTheme
        }

        override fun onStart() {
            super.onStart()
            val dialog = dialog
            var bottomSheet: View? = null
            if (dialog != null) {
                bottomSheet =
                    dialog.findViewById(R.id.design_bottom_sheet)
                bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            }
            val view = view
            view?.post {
                val parent = view.parent as View
                val params = parent.layoutParams as CoordinatorLayout.LayoutParams
                val behavior = params.behavior
                val bottomSheetBehavior = behavior as BottomSheetBehavior
                bottomSheetBehavior.peekHeight = view.measuredHeight
                (bottomSheet?.parent as View).setBackgroundColor(Color.TRANSPARENT)
            }
        }

        private fun setUpAdapter(view: View) {
            mAdapter =
                CountryPickerAdapter(
                    context!!,
                    getAllCountriesAndCurrencies(),
                    object :
                        CountryPickerAdapter.OnClickCountryName {
                        override fun onClick(country: CountryModel) {
                            dismiss()
                            if (::clickOnCountryItemResult.isInitialized) {
                                val countryData = Country(
                                    country.code,
                                    getString(country.name),
                                    country.dialCode,
                                    country.flag,
                                    country.currency,
                                    country.currencySymbol
                                )
                                clickOnCountryItemResult.onClickCountry(countryData)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please add Listener for callback.",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                    })
            mAdapter.setHasStableIds(true)
            view.recyclerCountryItem.adapter = mAdapter
        }

        fun listener(clickOnCountryItemResult: ClickOnCountryItemResult) {
            this.clickOnCountryItemResult = clickOnCountryItemResult
        }

    }

    interface ClickOnCountryItemResult {
        fun onClickCountry(country: Country)
    }

    data class Country(
        val code: String,
        val name: String,
        val dialCode: String,
        val flag: Int,
        val currency: String,
        val currencySymbol: String
    )

}



