package com.veldic.ingyeogg.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.veldic.ingyeogg.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import com.veldic.ingyeogg.models.MatchDto

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MatchAdapter(viewModel)
        matches_recyclerView.layoutManager = LinearLayoutManager(this)
        matches_recyclerView.adapter = adapter

        search_button.setOnClickListener {
            viewModel.getSumbySumName(summoner_edit_text.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewModel.summoner = it
                    level_text.text = viewModel.summoner.summonerLevel.toString()
                    viewModel.getMatchesByAccount(it.accountId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            it.matches.forEach {
                                viewModel.getMatch(it.gameId)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe({
                                        adapter.matches.add(it)
                                        Log.d("MATCH", "got match info!" + adapter.matches.size)
                                        adapter.notifyDataSetChanged()
                                    },
                                        {
                                            Log.d("MATCH", "Something went wrong", it)
                                        })
                            }
                        }, {
                            Log.d("MATCHES", "Something went wrong", it)
                        })

                }, {
                    Log.d("SUMMONER", "Something went wrong", it)
                })
        }
    }


}
