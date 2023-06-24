package com.airwrk.androidtriviaquizgame.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airwrk.androidtriviaquizgame.R
import com.airwrk.androidtriviaquizgame.databinding.FragmentQuizBinding


/**
 * This fragment will display the question
 * along with a timeout
 */

class QuizFragment : Fragment() {

    private var _binding:FragmentQuizBinding? = null
    private val binding
        get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuizBinding.inflate(layoutInflater)

        //Init block




        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

}