package com.example.monthcalenders.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.monthcalenders.MainActivity
import com.example.monthcalenders.adapter.VPAdapter
import com.example.monthcalenders.databinding.FragmentStartBinding
import com.example.monthcalenders.models.UsersItem
import com.example.monthcalenders.utils.onTextChangeFlow
import com.example.monthcalenders.viewModel.ApiViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import java.util.*

class StartFragment : Fragment() {
    private lateinit var binder: FragmentStartBinding
    private var linearLayoutManager: LinearLayoutManager? = null
    private lateinit var adapter:VPAdapter
   lateinit var viewModel: ApiViewModel
   private var searchList:MutableList<UsersItem> = arrayListOf()
   private var apiList:List<UsersItem> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel=(activity as MainActivity).viewModel
        binder=FragmentStartBinding.inflate(inflater,container,false)
        return binder.root
    }

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binder.rvUsers.layoutManager = linearLayoutManager
        adapter = VPAdapter(parentFragment)
        binder.rvUsers.adapter = adapter
        viewModel.safeSearchNewsCall()
        viewModel.registerLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            apiList = it
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            binder.etSearch.onTextChangeFlow()
                .debounce(700)
                .distinctUntilChanged()
                .collect {
                    if (it.isBlank().not()) {
                        searchList= arrayListOf()
                        for (item in apiList)
                            if (item.name?.lowercase(Locale.getDefault())?.contains(it.lowercase(Locale.getDefault()))  == true)
                                searchList.add(item)
                        adapter.submitList(searchList)
                        if (searchList.isEmpty()){
                            binder.noName.text="'$it' not found!"
                            binder.noName.visibility=View.VISIBLE
                            binder.rvUsers.visibility=View.GONE
                        }
                        else {
                            binder.noName.visibility=View.GONE
                            binder.rvUsers.visibility=View.VISIBLE
                        }
                    }
                    else{
                        adapter.submitList(apiList)
                        searchList= arrayListOf()
                    }
                }
        }
    }
}