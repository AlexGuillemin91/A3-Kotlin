package fr.iim.a3_kotlin_project

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import org.w3c.dom.Text
import java.lang.RuntimeException

class LoginFragment : Fragment() {

    private lateinit var Listener: LoginListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LoginListener) {
            Listener = context
        }
        else {
            throw RuntimeException("$context needs to implements LoginListener interface")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_send).setOnClickListener {
            val email = view.findViewById<EditText>(R.id.login_email).text.toString()
            val emailField = view.findViewById<EditText>(R.id.login_email)
            var isGood = true
            if (!emailField.text.matches(Regex("^[a-z0-9A-Z._%+-]+@[a-z0-9A-Z.-]+\\.[a-zA-Z]{2,}$"))) {
                emailField.error = getString(R.string.error_email_regex)
                isGood = false
            }

            if (emailField.text.isEmpty()) {
                emailField.error = getString(R.string.error_mail_empty)
                isGood = false
            }

            val passwordField = view.findViewById<EditText>(R.id.login_password)
            if (!passwordField.text.matches(Regex("^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$"))) {
                passwordField.error = "put a stronger password"
                isGood = false
            }

            if (passwordField.text.isEmpty()) {
                passwordField.error = getString(R.string.error_password_empty)
                isGood = false
            }

            val checkedBox = view.findViewById<CheckBox>(R.id.login_checkbox)
            checkedBox.error = null
            if (!checkedBox.isChecked) {
                checkedBox.error = getString(R.string.error_checkbox)
                isGood = false
            }

            if (isGood == true) {
                Listener.OnClickListener(email)
            }
        }
    }

    interface LoginListener {
        fun OnClickListener(email: String)
    }
}