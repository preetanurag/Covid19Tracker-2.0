package com.preet.covid19tracker_20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_more_details.*
import kotlinx.android.synthetic.main.fragment_statistics.*


class MoreDetailsFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more_details, container, false)
    }

    override fun onResume() {
        super.onResume()

        back_moredetails_btn.setOnClickListener {
            findNavController().navigateUp()
        }
//        state_wise_btn.setOnClickListener {
//            navController.navigate(R.id.action_moreDetailsFragment_to_stateFragment)
//        }
//        dist_button.setOnClickListener {
//            navController.navigate(R.id.action_moreDetailsFragment_to_districtWiseFragment)
//        }

    }


}
