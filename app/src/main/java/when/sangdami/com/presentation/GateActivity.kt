package `when`.sangdami.com.presentation

import `when`.sangdami.com.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class GateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gate)

        Handler().postDelayed({
            val intent = Intent(this@GateActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}