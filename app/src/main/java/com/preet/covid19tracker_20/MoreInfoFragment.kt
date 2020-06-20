package com.preet.covid19tracker_20

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_more_info.*


class MoreInfoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more_info, container, false)
    }


    override fun onResume() {
        super.onResume()

        who_visit.setOnClickListener {
           val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://www.who.int/emergencies/diseases/novel-coronavirus-2019"))
            startActivity(intent)
        }

        mhfw_visit.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://www.mohfw.gov.in/"))
            startActivity(intent)
        }

        back_moreinfo_btn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}
