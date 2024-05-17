package ar.edu.ejercicio6

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextNombre = findViewById<EditText>(R.id.editTextNombre)
        val editTextDonaciones = findViewById<EditText>(R.id.editTextDonaciones)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val textViewResultado = findViewById<TextView>(R.id.textViewResultado)

        btnCalcular.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val donaciones = editTextDonaciones.text.toString().toDoubleOrNull()

            if (donaciones != null) {
                val compensacion = calcularCompensacion(donaciones)
                textViewResultado.text = "El colaborador $nombre recibe $compensacion"
            } else {
                textViewResultado.text = "Por favor, ingrese una cantidad válida de donaciones"
            }
        }
    }

    private fun calcularCompensacion(donaciones: Double): String {
        return when {
            donaciones > 25000 -> "compensación A"
            donaciones in 15000.0..25000.0 -> "compensación B"
            donaciones in 5000.0..15000.0 -> "compensación C"
            else -> "ninguna compensación"
        }
    }
}
