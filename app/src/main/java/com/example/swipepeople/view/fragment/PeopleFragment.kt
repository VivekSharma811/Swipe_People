package com.example.swipepeople.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.swipepeople.data.model.ArchivedUser
import com.example.swipepeople.data.model.User
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
    private lateinit var peopleAdapter: PeopleAdapter
    private var archivedUser: ArchivedUser? = null

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
            fab.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(PeopleFragmentDirections.actionArchivedPeople())
            }
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
                    showProgress()
                }
                if (it is DataState.Error) {
                    dismissProgress()
                    Toast.makeText(context, it.exception, Toast.LENGTH_SHORT).show()
                }
                if (it is DataState.Success) {
                    dismissProgress()
                    it.data.results.let { userList ->
                        archivedUser = ArchivedUser(
                            getName(userList.get(0).user),
                            userList.get(0).user.picture,
                            userList.get(0).user.phone
                        )
                        peopleAdapter = PeopleAdapter(userList)
                        binding.stackView.adapter = peopleAdapter
                        peopleAdapter.notifyDataSetChanged()
                    }
                }
            })
        }
    }

    private fun getName(user: User): String {
        user.name.let { return "${it.title} ${it.first} ${it.last}" }
    }

    private fun archivePeople() = launch {
        archivedUser?.let {
            viewModel.archiveUser(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {
        if (direction == Direction.Left) {
            getAllPeople()
        }
        if (direction == Direction.Right) {
            archivePeople()
            getAllPeople()
        }
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