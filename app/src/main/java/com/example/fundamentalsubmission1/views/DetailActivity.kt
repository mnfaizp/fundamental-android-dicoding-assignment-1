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
import com.example.fundamentalsubmission1.api.ApiHelper
import com.example.fundamentalsubmission1.api.RetrofitBuilder
import com.example.fundamentalsubmission1.databinding.ActivityDetailBinding
import com.example.fundamentalsubmission1.models.UserDetail
import com.example.fundamentalsubmission1.utils.Status
import com.example.fundamentalsubmission1.viewmodels.DetailUserViewModel
import com.example.fundamentalsubmission1.viewmodels.ViewModelFactory
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
    private lateinit var viewModel: DetailUserViewModel
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "User Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        userid = intent.getStringExtra(EXTRA_USER)!!

        initViewModel()
        initObserver()
        initTabLayout(userid)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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

    private fun initViewModel(){
        viewModel = ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))
            .get(DetailUserViewModel::class.java)
    }

    private fun initObserver(){
        viewModel.getUser(userid).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.pbDetail.visibility = View.GONE
                        resource.data?.let { users -> initView(users) }
                    }

                    Status.LOADING -> {
                        binding.pbDetail.visibility = View.VISIBLE
                    }

                    Status.ERRORS -> {
                        binding.pbDetail.visibility = View.GONE
                        Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

}