package com.example.swipepeople.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.swipepeople.R
import com.example.swipepeople.databinding.FragmentPeopleBinding
import com.example.swipepeople.util.DataState
import com.example.swipepeople.view.adapter.PeopleAdapter
import com.example.swipepeople.viewmodel.PeopleViewModel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeableMethod
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PeopleFragment : BaseFragment(), CardStackListener {

    private var _binding: FragmentPeopleBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: PeopleViewModel by activityViewModels()
    private var adapter: PeopleAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPeopleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = CardStackLayoutManager(context, this).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }

        binding.apply {
            stackView.layoutManager = layoutManager
            stackView.itemAnimator.apply {
                if (this is DefaultItemAnimator) {
                    supportsChangeAnimations = false
                }
            }
        }

        getAllPeople()
    }

    private fun getAllPeople() = launch {
        viewModel.apply {
            getAllPeople()
            peopleLiveData.observe(viewLifecycleOwner, Observer {
                if (it is DataState.Loading) {

                }
                if (it is DataState.Error) {

                }
                if (it is DataState.Success) {
                    adapter = PeopleAdapter(it.data.results)
                    binding.stackView.adapter = adapter
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }
}