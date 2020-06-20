package com.preet.covid19tracker_20

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_info.*


class InfoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }


    override fun onResume() {
        super.onResume()

        mailbtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:preetanurag5@gmail.com") // only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, "preetanurag5@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "Regarding your Covid Tracker App")
            }
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)
            }

        }
        WAbtn.setOnClickListener {

            val url = "https://api.whatsapp.com/send?phone= +91 7631129471"
            try {
                val pm = requireContext().packageManager
                pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            } catch (e: PackageManager.NameNotFoundException) {
                Toast.makeText(activity, "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }

        }

        fbbtn.setOnClickListener {
            val facebookIntent = openFacebook(this.requireContext(), "100007064371190","preet.anurag.5")
            startActivity(facebookIntent)
            openFacebook(requireContext() , "100007064371190","preet.anurag.5")
        }

        instabtn.setOnClickListener {
            val uri = Uri.parse("http://instagram.com/_u/_.p._.r._.e._.e._.t._")
            val likeIng = Intent(Intent.ACTION_VIEW, uri)

            likeIng.setPackage("com.instagram.android")

            try {
                startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://instagram.com/_.p._.r._.e._.e._.t._/")
                    )
                )
            }
        }

        linkinbtn.setOnClickListener {

            val uri = Uri.parse("https://www.linkedin.com/in/preet-anurag-a7104416b/")
            val likeIng = Intent(Intent.ACTION_VIEW, uri)

            likeIng.setPackage("com.linkedin.android")

            try {
                startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/preet-anurag-a7104416b/")
                    )
                )
            }


        }
        gitbtn.setOnClickListener {

           val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://github.com/preetanurag"))
            startActivity(intent)
        }

        back_info_btn.setOnClickListener {
            findNavController().navigateUp()
        }

    }
    private fun openFacebook(context: Context, id: String, url: String): Intent? {
        return try {
            context.packageManager
                .getPackageInfo("com.facebook.katana", 0)
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("fb://profile/$id")
            )
        } catch (e: Exception) {
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.facebook.com/$url")
            )
        }
    }
}

