package com.example.heroeslistactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heroeslistactivity.databinding.ActivityHeroesListBinding
import com.example.heroeslistactivity.databinding.ActivityHeroesDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HeroesListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHeroesListBinding
    lateinit var adapter : HeroAdapter

    companion object {
        val TAG = "HeroesListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroesListBinding.inflate(layoutInflater)
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
        binding.recyclerViewHeroesList.adapter = adapter
        binding.recyclerViewHeroesList.layoutManager = LinearLayoutManager(this)



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_list_sorting, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.item_sorting_byName -> {
                adapter.dataSet = adapter.dataSet.sortedBy {
                    it.name.compareTo("test")
                }
                adapter.notifyDataSetChanged()
                true
            }
            R.id.item_sorting_byRank -> {
                adapter.dataSet = adapter.dataSet.sortedBy {
                    it.ranking
                }
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}