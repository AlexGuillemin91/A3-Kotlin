package fr.iim.a3_kotlin_project

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.RuntimeException

private const val ARG_EMAIL = "email"


class WelcomeFragment : Fragment() {

    private var email: String? = null
    private lateinit var mapListener: MapListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_EMAIL)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MapListener) {
            mapListener = context
        }
        else {
            throw RuntimeException("$context needs to implements mapListener interface")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.welcome_text)
        textView.text = getString(R.string.welcome, email)

        val location = view.findViewById<EditText>(R.id.location)

        val button = view.findViewById<Button>(R.id.send_location)
        button.setOnClickListener { mapListener.OnClickMapListener(location.text.toString()) }
    }

    interface MapListener {
        fun OnClickMapListener(location: String)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WelcomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(email : String) =
            WelcomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_EMAIL, email)
                }
            }
    }
}