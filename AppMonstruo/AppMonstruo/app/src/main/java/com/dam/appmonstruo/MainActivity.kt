package com.dam.appmonstruo

import android.content.ClipData
import android.content.Intent
import android.media.RouteListingPreference
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    private lateinit var primerNumero: EditText
    private lateinit var segundoNumero: EditText
    private lateinit var sumar: Button
    private lateinit var restar: Button
    private lateinit var multiplicar: Button
    private lateinit var dividir: Button
    private lateinit var resultado: TextView
    private lateinit var resultadoNumero: TextView
    private lateinit var checkBox: CheckBox
    private lateinit var click: TextView
    private lateinit var switch: Switch
    private lateinit var textoSwitch: TextView

//todos estos metodos del menu van fuera del oncreate/////////////////////////


        // metodo que hace que funcione la flecha del toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }


    //iniciar el menu de la toolbar los 3 puntos
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
// inflamos la vista
        setContentView(R.layout.activity_main)
//bindeo
        primerNumero = findViewById(R.id.primerNumero)
        segundoNumero = findViewById(R.id.segundoNumero)
        sumar = findViewById(R.id.suma)
        restar = findViewById(R.id.resta)
        multiplicar = findViewById(R.id.multi)
        dividir = findViewById(R.id.divi)
        resultado = findViewById(R.id.resultado)
        checkBox = findViewById(R.id.checkBox)
        click = findViewById(R.id.click)
        switch = findViewById(R.id.switch1)
        textoSwitch = findViewById(R.id.textoSwich)
        //bindeo de solo una linea
        var botonActividad2: Button = findViewById(R.id.actividad2)





        //iniciamos la toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Pantalla de inicio "





        //botones de calculadora
        sumar.setOnClickListener {
            val suma: Double
            suma =
                primerNumero.text.toString().toDouble() + segundoNumero.text.toString().toDouble()
            resultado.text = "$suma"
        }
        restar.setOnClickListener {
            val resta: Double
            resta =
                primerNumero.text.toString().toDouble() - segundoNumero.text.toString().toDouble()
            if (primerNumero != null && segundoNumero != null) {
                resultado.text = "$resta"
            }
        }
        multiplicar.setOnClickListener {
            val multiplicar: Double
            multiplicar =
                primerNumero.text.toString().toDouble() * segundoNumero.text.toString().toDouble()
            if (primerNumero != null && segundoNumero != null) {
                resultado.text = "$multiplicar"
            }
        }
        dividir.setOnClickListener {
            val dividir: Double
            dividir =
                primerNumero.text.toString().toDouble() / segundoNumero.text.toString().toDouble()
            if (primerNumero != null && segundoNumero != null) {
                resultado.text = "$dividir"

                Toast.makeText(this, "$dividir", Toast.LENGTH_SHORT).show()
            }
        }






        //checkbox
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                click.text = " el checkBox esta marcado"

                Toast.makeText(this, "toas activado", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(this, "toas desactivado", Toast.LENGTH_SHORT).show()
                click.text = " el checkBox esta desmarcado"
            }
        }




        //switch
        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                textoSwitch.text = " Switch marcado"
                Toast.makeText(this, "Switch marcado", Toast.LENGTH_SHORT).show()

            } else {
                textoSwitch.text = "Switch desmarcado"
                Toast.makeText(this, "Switch desmarcado", Toast.LENGTH_SHORT).show()

            }
        }
//boton random
        botonActividad2.setOnClickListener {
            val intent = Intent(this, Activity5::class.java)
            startActivity(intent)

        }

    }

    // metodo del listener de los item del menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //switch que hace que dependiendo del ID que pulses haga intent a un lado u a otro
        return when (item.itemId) {
            R.id.configuracion -> {
                Toast.makeText(this, "Abriendo configuración...", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Activity4::class.java)
                startActivity(intent)
                true
            }
            R.id.action_info -> {
                val intent = Intent(this, Activity2::class.java)
                startActivity(intent)
            true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}









