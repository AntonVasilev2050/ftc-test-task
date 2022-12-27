package com.avvsoft2050.ftctesttask.presentation.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.avvsoft2050.ftctesttask.data.model.Card
import com.avvsoft2050.ftctesttask.data.model.Country
import com.avvsoft2050.ftctesttask.databinding.ActivityCardsBinding
import com.avvsoft2050.ftctesttask.databinding.DialogCardDetailsBinding
import com.avvsoft2050.ftctesttask.di.CardsApplication
import com.avvsoft2050.ftctesttask.presentation.adapter.BinAdapter
import com.avvsoft2050.ftctesttask.presentation.viewmodel.CardsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.launch

class CardsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardsBinding
    var bin = ""
    private val viewModel: CardsViewModel by viewModels {
        CardsViewModel.CardsViewModelFactory((application as CardsApplication).repository)
    }
    private lateinit var binAdapter: BinAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binAdapter = BinAdapter(
            onClickAction = {
                showCardDetails(it)
            }
        )
        binding.recyclerViewBinSearchHistory.adapter = binAdapter
        setupActions()
        setupObservers()
    }

    private fun setupActions() {
        binding.buttonSearchBinInfo.setOnClickListener {
            bin = binding.editTextBin.text.toString()
            viewModel.viewModelScope.launch {
                viewModel.onSearchBinInfoButtonClicked(bin)
            }
        }
    }

    private fun setupObservers() {
        viewModel.cardLiveData.observe(this) {
            it?.let { showCardDetails(it) }

        }
        viewModel.infoMessageLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.allBins.observe(this) {
            binAdapter.submitList(it)
        }
    }

    private fun showCardDetails(card: Card?) {
        val cardDetails = BottomSheetDialog(this)
        val bindingCardDetails = DialogCardDetailsBinding.inflate(layoutInflater)
        cardDetails.setContentView(bindingCardDetails.root)
        with(bindingCardDetails) {
            textViewBinNumber.text = buildString {
                append("Data for BIN ")
                append(card?.bin)
                append(":")
            }
            if (card != null) {
                textViewScheme.text = card.scheme?.replaceFirstChar { it.titlecaseChar() }
                textViewType.text = card.type
                textViewPrepaid.text = when (card.prepaid) {
                    true -> "Yes"
                    false -> "No"
                    else -> "No data"
                }
                textViewBankName.text = buildString {
                    append(card.bank?.name ?: "No data")
                    append(", ")
                }
                textViewBankCity.text = card.bank?.city ?: "No data"
                textViewBankUrl.text = card.bank?.url ?: "No data"
                textViewBankUrl.setOnClickListener {
                    openWebsite(card.bank?.url)
                }
                textViewBankPhone.text = card.bank?.phone ?: "No data"
                textViewBankPhone.setOnClickListener { openPhone(card.bank?.phone) }
                textViewBrand.text = card.brand

                textViewCardNumberLength.text = card.cardNumber?.length.toString()
                textViewCardNumberLUHN.text = when (card.cardNumber?.luhn) {
                    true -> "Yes"
                    false -> "No"
                    else -> "No data"
                }
                textViewCountryAlph2.text = card.country?.alpha2 ?: "No data"
                textViewCountryName.text = card.country?.name ?: "No data"
                textViewCountryLatLong.text = buildString {
                    append("(latitude: ")
                    card.country?.let { append(it.latitude) }
                    append(", longitude: ")
                    card.country?.let { append(it.longitude) }
                    append(")")
                }
                textViewCountryLatLong.setOnClickListener { openLocation(card.country) }
                textViewCountryAlph2.setOnClickListener { openLocation(card.country) }
                textViewCountryName.setOnClickListener { openLocation(card.country) }
            }
        }
        cardDetails.show()
    }

    private fun openLocation(country: Country?) {
        val location = Uri.parse("geo:${country?.latitude},${country?.longitude}")
        val intent = Intent(Intent.ACTION_VIEW, location)
        intent.setPackage("com.google.android.apps.maps")
        checkAndStart(intent)
    }

    private fun openPhone(phone: String?) {
        val phoneNumberToDial = Uri.parse("tel:$phone")
        val intent = Intent(Intent.ACTION_DIAL, phoneNumberToDial)
        checkAndStart(intent)
    }

    private fun openWebsite(url: String?) {
        val webpage = Uri.parse("http://$url")
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        checkAndStart(intent)
    }

    private fun checkAndStart(intent: Intent) {
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }
    }
}