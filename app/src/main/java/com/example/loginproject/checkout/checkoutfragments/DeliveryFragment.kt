package com.example.loginproject.checkout.checkoutfragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginproject.R
import com.example.loginproject.checkout.CheckoutActivity
import com.example.loginproject.checkout.checkoutfragments.model.AddDeliveryAddress
import com.example.loginproject.checkout.checkoutfragments.model.local.AddressDatabase
import com.example.loginproject.checkout.checkoutfragments.view.AddressAdapter
import com.example.loginproject.checkout.checkoutfragments.viewmodel.AddressViewModel
import com.example.loginproject.checkout.checkoutfragments.viewmodel.SharedViewModel
import com.example.loginproject.checkout.checkoutfragments.viewmodel.ViewModelFactory
import com.example.loginproject.databinding.DialogbarAddressBinding
import com.example.loginproject.databinding.FragmentDeliveryBinding

class DeliveryFragment : Fragment() {

lateinit var binding: FragmentDeliveryBinding
val viewModel: AddressViewModel by activityViewModels() {
    ViewModelFactory(addressDb = AddressDatabase.getInstance(requireContext()))
}

    val sharedViewModel:SharedViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeliveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        binding.addAddress.setOnClickListener {

            val addressBinding = DialogbarAddressBinding.inflate(LayoutInflater.from(requireContext()))
            with(addressBinding){

                val dialog = AlertDialog.Builder(requireContext()).apply {
                    setView(addressBinding.root)
                    create()
                }.show()

                addressBinding.cancel.setOnClickListener {
                    dialog.dismiss()
                }

                addressBinding.save.setOnClickListener {
                    val title =  etTitle.text.toString().trim()
                    val address = etAddress.text.toString().trim()

                    val newAddress = AddDeliveryAddress(
                        id = 0,
                        userId = 1,
                        title = title,
                        address = address
                    )

                    viewModel.addAddress(newAddress)
                    postDeliveryAddress(newAddress)
                    dialog.dismiss()

                }

            }
        }

        binding.next.setOnClickListener {
            (activity as CheckoutActivity).navigateToNextPage()
        }
    }

    private fun postDeliveryAddress(newAddress: AddDeliveryAddress) {

        viewModel.getAddressResponse(newAddress)

    }

    private fun setUpObservers() {
        viewModel.error.observe(viewLifecycleOwner){
            androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .setMessage(it)
                .create()
                .show()
        }

        viewModel.getDeliveryResponse.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "success", Toast.LENGTH_LONG).show()
        }

        viewModel.allAddress.observe(viewLifecycleOwner){
            binding.recyclerView.adapter = AddressAdapter(it){
                address->
                sharedViewModel.selectedAddress(listOf(address.title, address.address))
            }
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        }



    }

}