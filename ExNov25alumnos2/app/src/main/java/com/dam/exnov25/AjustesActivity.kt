package com.dam.exnov25

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.materialswitch.MaterialSwitch

class AjustesActivity : AppCompatActivity() {

    private lateinit var swLactosa: MaterialSwitch
    private lateinit var swFrutosSecos: MaterialSwitch
    private lateinit var swGluten: MaterialSwitch
    private lateinit var swAll: MaterialSwitch
    private lateinit var etNombre: EditText
private lateinit var tvResumen: TextView
    private lateinit var tvDieta: TextView
    private lateinit var rbOmnivoro: RadioButton
    private lateinit var rbVegetariano: RadioButton
    private lateinit var rbVegano: RadioButton




    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





        enableEdgeToEdge()
        setContentView(R.layout.activity_ajustes)
        //bindeo
        swAll = findViewById(R.id.swAll)
        swGluten=findViewById(R.id.swGluten)
        swFrutosSecos=findViewById(R.id.swFruSec)
        swLactosa=findViewById(R.id.swLactosa)
        tvResumen=findViewById(R.id.tvResumen)
        etNombre=findViewById(R.id.etNombre)
        tvDieta=findViewById(R.id.tvDieta)
        rbOmnivoro=findViewById(R.id.rbOmnivoro)
        rbVegetariano=findViewById(R.id.rbVegetariano)
        rbVegano=findViewById(R.id.rbVegano)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        swAll.setOnClickListener {
            marcarTodos(swAll.isChecked)
            tvResumen.text=generarResumen()
        }


        swLactosa.setOnCheckedChangeListener { _, _ ->
            verificarSwitches()
            tvResumen.text = generarResumen()
        }

        swFrutosSecos.setOnCheckedChangeListener { _, _ ->
            verificarSwitches()
            tvResumen.text = generarResumen()
        }

        swGluten.setOnCheckedChangeListener { _, _ ->
            verificarSwitches()
            tvResumen.text = generarResumen()
        }

        rbOmnivoro.setOnClickListener {
            if (rbOmnivoro.isChecked){
                tvDieta.text= etNombre.text.toString()+"  es Omnivoro"
            }
        }
        rbVegetariano.setOnClickListener {
            if (rbVegetariano.isChecked){
                tvDieta.text=etNombre.text.toString()+"  es Vegetariano"
            }
        }
        rbVegano.setOnClickListener {
            if (rbVegano.isChecked){
                tvDieta.text=etNombre.text.toString()+ "  es Vegano"
            }
        }


    }

    /**
     * Verifica si todos los switches (no maestro) están activados. Si todos están activados,
     * activa el switch maestro (`swAll`). De lo contrario, lo desactiva.
     */
    fun verificarSwitches() {
            swAll.isChecked= swLactosa.isChecked && swGluten.isChecked && swFrutosSecos.isChecked
    }

    /**
     * Configura el estado de los tres MaterialSwitch al valor proporcionado.
     *
     * @param b Si es `true`, todos los switches se marcan como seleccionados.
     *          Si es `false`, se desmarcan.
     */
    private fun marcarTodos(b: Boolean) {
            swLactosa.isChecked=b
            swFrutosSecos.isChecked=b
            swGluten.isChecked=b
    }

    /**
     * Actualiza tvResumen con la información marcada. Se facilita el string de las alergias seleccionadas.
     * Falta recuperar el resto de componentes y mostrarlo.
     * @return Devuelve un string con el texto a mostrar
     */
    private fun generarResumen(): String {

        //codigo que genera String con las alergias seleccionadas
        val alergias = mutableListOf<String>()
        if (swLactosa.isChecked) alergias.add("lactosa")
        if (swFrutosSecos.isChecked) alergias.add("frutos secos")
        if (swGluten.isChecked) alergias.add("gluten")
        val alergiaTexto = if (alergias.isEmpty()) "no tiene alergias" else "tiene alergia a ${alergias.joinToString(" y ")}"
        val final = etNombre.text.toString()+" " + alergiaTexto
        return  final
    }
}