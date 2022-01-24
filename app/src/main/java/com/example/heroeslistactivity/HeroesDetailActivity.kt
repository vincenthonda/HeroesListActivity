package com.example.heroeslistactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.heroeslistactivity.databinding.ActivityHeroesDetailBinding

class HeroesDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroesDetailBinding

    companion object{
        val EXTRA_HERO = "The Hero"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHeroesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hero = intent.getParcelableExtra<Hero>(EXTRA_HERO)
        binding.textViewDetailName.text = hero?.name
        binding.imageViewDetailHeroPic.setImageDrawable(getDrawable(resources.getIdentifier(hero?.image, "drawable", packageName)))
        binding.textViewDetailDesc.text = hero?.description
        binding.textViewDetailRanking.text = hero?.ranking.toString()
        binding.textViewDetailSuperpower.text = hero?.superpower
    }
}