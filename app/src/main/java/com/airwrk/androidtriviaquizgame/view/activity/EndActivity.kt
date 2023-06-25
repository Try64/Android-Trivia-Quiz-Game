package com.airwrk.androidtriviaquizgame.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.airwrk.androidtriviaquizgame.R
import com.airwrk.androidtriviaquizgame.databinding.ActivityEndBinding
import com.airwrk.androidtriviaquizgame.model.History
import com.airwrk.androidtriviaquizgame.network.HistoryAdapter
import com.airwrk.androidtriviaquizgame.viewmodels.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EndActivity : AppCompatActivity() {

    private lateinit var binding:ActivityEndBinding
    lateinit var viewModel: QuizViewModel
    private var list = ArrayList<History>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEndBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[QuizViewModel::class.java]

        viewModel.getAllHistory()

        viewModel.historyLiveData.observe(this){
            list = it.toMutableList() as ArrayList<History>
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = HistoryAdapter(historyList = list, listener = HistoryAdapter.OnHistoryClickListener { position ->
                Toast.makeText(this@EndActivity,"History item no- ${position+1} clicked",Toast.LENGTH_LONG).show()
            })
        }

        binding.buttonFinish.setOnClickListener{
            Intent(this,MainActivity::class.java).apply {
                this.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(this)
            }
            finish()
        }


    }
}