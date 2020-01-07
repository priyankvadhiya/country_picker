package com.priyank.countrypicker

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.country_picker_dialog.view.*

/**
 * A simple [Fragment] subclass.
 */
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

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
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
        mAdapter = CountryPickerAdapter(getAllCountriesAndCurrencies(), object :
            CountryPickerAdapter.OnClickCountryName {
            override fun onClick(country: Country) {
                dismiss()
                if (::clickOnCountryItemResult.isInitialized) {
                    clickOnCountryItemResult.onClickCountry(country)
                } else {
                    Toast.makeText(context, "Please add Listener for callback.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
        view.recyclerCountryItem.adapter = mAdapter
    }

    fun listener(clickOnCountryItemResult: ClickOnCountryItemResult) {
        this.clickOnCountryItemResult = clickOnCountryItemResult
    }

    interface ClickOnCountryItemResult {
        fun onClickCountry(country: Country)
    }
}
