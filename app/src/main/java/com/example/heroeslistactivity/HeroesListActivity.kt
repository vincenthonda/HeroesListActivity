package com.example.heroeslistactivity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.heroeslistactivity.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HeroesListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var adapter : HeroAdapter

    companion object {
        val TAG = "HeroesListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Load the heroes.json into a List<Hero> using Gson
        val inputStream = resources.openRawResource(R.raw.heroes)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        val type = object : TypeToken<List<Hero>>() { }.type
        val heroesList = gson.fromJson<List<Hero>>(jsonString, type)
        Log.d(TAG, "onCreate: \n$heroesList")

        // Create our adapter and fill the recycler view
        adapter = HeroAdapter(heroesList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView_heroesList.layoutManager = LinearLayoutManager(this)

    }
}}