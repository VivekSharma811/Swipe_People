package com.example.swipepeople.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.swipepeople.R
import com.example.swipepeople.databinding.FragmentPeopleBinding
import com.example.swipepeople.util.DataState
import com.example.swipepeople.viewmodel.PeopleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PeopleFragment : BaseFragment() {

    private var _binding: FragmentPeopleBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: PeopleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPeopleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAllPeople()
    }

    private fun getAllPeople() = launch {
        viewModel.apply {
            getAllPeople()
            peopleLiveData.observe(viewLifecycleOwner, Observer {
                if( it is DataState.Loading) {

                }
                if( it is DataState.Error) {

                }
                 if (it is DataState.Success) {
                     Log.e("fgs", it.toString())
                 }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}