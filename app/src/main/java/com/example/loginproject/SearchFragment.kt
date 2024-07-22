package com.example.loginproject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginproject.data.searchdata.GetSearchResponse
import com.example.loginproject.databinding.ActivityHomeActivityBinding
import com.example.loginproject.databinding.FragmentSearchBinding
import com.example.loginproject.productadapter.searchadapter.SearchAdapter
import com.example.loginproject.remote.ApiClient
import com.example.loginproject.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment : Fragment() {
  lateinit var binding: FragmentSearchBinding
  lateinit var productBinding: ActivityHomeActivityBinding
  lateinit var adapter: SearchAdapter
  val apiService =ApiClient.retrofit.create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        productBinding = ActivityHomeActivityBinding.bind(requireActivity().findViewById(R.id.main))
        super.onViewCreated(view, savedInstanceState)

        binding.search.setOnClickListener {
            loadSearchProducts()
        }
        binding.cancelSearch.setOnClickListener {
            onDestroyView()
            startActivity(Intent(requireContext(),HomeActivity::class.java))
        }

    }

    private fun loadSearchProducts() {
        val txtsearch = binding.etSearch.text.trim()
        val call: Call<GetSearchResponse> = apiService.searchProducts(txtsearch.toString())

        call.enqueue(object: Callback<GetSearchResponse>{
            override fun onResponse(call: Call<GetSearchResponse>,response: Response<GetSearchResponse>) {
                if(!response.isSuccessful){
                    Toast.makeText(requireContext(),"not successfull", Toast.LENGTH_LONG).show()
                }

                val searchproducts = response.body()
                if(searchproducts == null){
                    Toast.makeText(requireContext(),"empty response from server", Toast.LENGTH_LONG).show()
                }

                productBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                if (searchproducts != null) {
                    adapter = SearchAdapter(searchproducts.products)
                    productBinding.recyclerView.adapter = adapter
                }

            }

            override fun onFailure(p0: Call<GetSearchResponse>, p1: Throwable) {
                Toast.makeText(requireContext(),"not successfull", Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}