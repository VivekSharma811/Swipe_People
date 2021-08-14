package com.example.swipepeople.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swipepeople.R
import com.example.swipepeople.databinding.FragmentArchivedPeopleBinding
import com.example.swipepeople.view.adapter.ArchivedUserAdapter
import com.example.swipepeople.viewmodel.PeopleViewModel
import kotlinx.coroutines.launch

class ArchivedPeopleFragment : BaseFragment() {

    private var _binding: FragmentArchivedPeopleBinding? = null
    private val binding
        get() = _binding!!

    private var adapter: ArchivedUserAdapter? = null
    private val viewModel: PeopleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArchivedPeopleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvArchivedPeople.apply {
            layoutManager = LinearLayoutManager(context)
        }

        getArchivedPeople()
    }

    private fun getArchivedPeople() = launch {
        viewModel.apply {
            getArchivedUsers()
            archivedUsersLiveData.observe(viewLifecycleOwner, Observer {
                adapter = ArchivedUserAdapter(it)
                binding.rvArchivedPeople.adapter = adapter
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}