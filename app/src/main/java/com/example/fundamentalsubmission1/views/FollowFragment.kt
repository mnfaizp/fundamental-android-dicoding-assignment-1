package com.example.fundamentalsubmission1.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fundamentalsubmission1.R
import com.example.fundamentalsubmission1.adapters.UserAdapter
import com.example.fundamentalsubmission1.api.ApiHelper
import com.example.fundamentalsubmission1.api.RetrofitBuilder
import com.example.fundamentalsubmission1.models.User
import com.example.fundamentalsubmission1.viewmodels.FollowersViewModel
import com.example.fundamentalsubmission1.viewmodels.FollowingViewModel
import com.example.fundamentalsubmission1.viewmodels.ViewModelFactory

class FollowFragment : Fragment() {

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_USER_ID = "user_id"

        @JvmStatic
        fun newInstance(index: Int, username: String) =
            FollowFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                    putString(ARG_USER_ID, username)
                }
            }
    }

    private lateinit var viewModelFollowing: FollowingViewModel
    private lateinit var viewModelFollowers: FollowersViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_follow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_follow)

        val section = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        val username = arguments?.getString(ARG_USER_ID)

        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = UserAdapter(arrayListOf())
        recyclerView.adapter = adapter

        showRecycler(section!!, username!!)
    }

    private fun showRecycler(section: Int, username: String) {
        Log.d("TAG", username)
        if (section == 1) {
            viewModelFollowers = ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))
                .get(FollowersViewModel::class.java)
            
            viewModelFollowers.getFollowers(username).observe(this, {
                it?.let { resource ->
                    resource.data?.let { users -> getList(users) }
                }
            })
        } else {
            viewModelFollowing = ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))
                .get(FollowingViewModel::class.java)

            viewModelFollowing.getFollowing(username).observe(this, {
                it?.let { resource ->
                    resource.data?.let { users -> getList(users) }
                }
            })
        }

    }
    
    private fun getList(users: ArrayList<User>) {
        adapter.apply { 
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}