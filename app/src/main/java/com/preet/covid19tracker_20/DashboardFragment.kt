package com.preet.covid19tracker_20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*


class DashboardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_main, container, false)


    }

    override fun onResume() {
        super.onResume()
        btnKnowMore.setOnClickListener {
            navController.navigate(R.id.action_dashBoardFragment_to_moreInfoFragment3)
        }
        infobtn.setOnClickListener {
            navController.navigate(R.id.action_dashBoardFragment_to_infoFragment)
        }
    }

}


