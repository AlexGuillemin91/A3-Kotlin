package fr.iim.a3_kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.TelecomManager.EXTRA_LOCATION

class MainActivity : AppCompatActivity(), LoginFragment.LoginListener, WelcomeFragment.MapListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun OnClickListener(email: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.login_form_fragment, WelcomeFragment.newInstance(email))
            .commitNow()
    }

    override fun OnClickMapListener(location: String) {
        val intent = Intent(this, MapsActivity::class.java).apply { putExtra(MapsActivity.EXTRA_LOCATION, location) }
        startActivity(intent)
    }
}