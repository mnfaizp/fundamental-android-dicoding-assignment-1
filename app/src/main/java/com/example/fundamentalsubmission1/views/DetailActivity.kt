package com.example.fundamentalsubmission1.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fundamentalsubmission1.R
import com.example.fundamentalsubmission1.adapters.SectionPagerAdapter
import com.example.fundamentalsubmission1.databinding.ActivityDetailBinding
import com.example.fundamentalsubmission1.models.UserDetail
import com.example.fundamentalsubmission1.viewmodels.DetailUserViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.following,
            R.string.followers
        )
    }

    private lateinit var userid: String
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail User"

        userid = intent.getStringExtra(EXTRA_USER)!!

        initViewModel(userid)
        initTabLayout(userid)
    }

    private fun initTabLayout(username: String) {
        val sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager: ViewPager2 = binding.vpThis
        sectionPagerAdapter.getUsername(username)
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = binding.tabFollow
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }

    private fun initView(users: UserDetail){
        with(binding) {
            tvReceivedUsername.text = users.login
            tvReceivedName.text = users.name
            tvReceivedFollowers.text = users.followers.toString()
            tvReceivedFollowing.text = users.following.toString()
            tvReceivedLocation.text = users.location
            tvReceivedRepository.text = users.public_repos.toString()
        }

        Glide.with(applicationContext)
            .load(users.avatar_url)
            .apply(RequestOptions().override(150, 150))
            .into(binding.imgReceivedAvatar)
    }

    private fun initViewModel(userid: String){
        val viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)
        viewModel.getUserObserver().observe(this, {
            if (it != null){
                initView(it)
            } else {
                Toast.makeText(this, "Something wrong when loading", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.apiCallWithId(userid)
        binding.pbDetail.visibility = View.INVISIBLE
    }


}