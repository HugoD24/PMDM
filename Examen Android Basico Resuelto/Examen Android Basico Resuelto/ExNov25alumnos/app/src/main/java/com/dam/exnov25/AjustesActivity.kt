package com.dam.exnov25

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.materialswitch.MaterialSwitch
import org.w3c.dom.Text

class AjustesActivity : AppCompatActivity() {

    private lateinit var swLactosa: MaterialSwitch
    private lateinit var swFrutosSecos: MaterialSwitch
    private lateinit var swGluten: MaterialSwitch
    private lateinit var swAll: MaterialSwitch
    private lateinit var muestra: TextView
    private lateinit var etNombre: EditText
    private lateinit var rgDieta: RadioGroup
    private lateinit var rbSeleccionado: RadioButton
    private lateinit var cbEmergencia: CheckBox
    private lateinit var tcResumen: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ajustes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        swLactosa=findViewById(R.id.swLactosa)
        swFrutosSecos=findViewById(R.id.swFruSec)
        swGluten=findViewById(R.id.swGluten)
        swAll=findViewById(R.id.swAll)
        etNombre=findViewById(R.id.etNombre)
        rgDieta=findViewById(R.id.radioGroupDieta)
        cbEmergencia=findViewById(R.id.checkSeleccion)
        tcResumen=findViewById(R.id.tvResumen)

        swAll.setOnClickListener {
            marcarTodos(swAll.isChecked)
        }

        swLactosa.setOnCheckedChangeListener { _, isChecked ->

            verificarSwitches()
            generarResumen()
        }
        swFrutosSecos.setOnCheckedChangeListener { _, isChecked ->

            verificarSwitches()
            generarResumen()
        }
        swGluten.setOnCheckedChangeListener { _, isChecked ->

            verificarSwitches()
            generarResumen()
        }


        etNombre.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable) {
                tcResumen.text=generarResumen()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
        })
        rgDieta.setOnCheckedChangeListener { _, isChecked ->
            tcResumen.text=generarResumen()
        }
        cbEmergencia.setOnCheckedChangeListener { _, isChecked ->
            tcResumen.text=generarResumen()
        }


    }

    /**
     * Verifica si todos los switches (no maestro) están activados. Si todos están activados,
     * activa el switch maestro (`swAll`). De lo contrario, lo desactiva.
     */
    fun verificarSwitches() {


        if (swGluten.isChecked && swLactosa.isChecked && swFrutosSecos.isChecked){
            swAll.isChecked=true
        }
        else{
            swAll.isChecked=false
        }
        tcResumen.text=generarResumen()
    }

    /**
     * Configura el estado de los tres MaterialSwitch al valor proporcionado.
     *
     * @param b Si es `true`, todos los switches se marcan como seleccionados.
     *          Si es `false`, se desmarcan.
     */
    private fun marcarTodos(b: Boolean) {
        if (swAll.isChecked){
            swLactosa.isChecked=true
            swGluten.isChecked=true
            swFrutosSecos.isChecked=true
        }
        else{
            swLactosa.isChecked=false
            swGluten.isChecked=false
            swFrutosSecos.isChecked=false
        }


    }


    /**
     * Actualiza tvResumen con la información marcada. Se facilita el string de las alergias seleccionadas.
     * Falta recuperar el resto de componentes y mostrarlo.
     * @return Devuelve un string con el texto a mostrar
     */
    private fun generarResumen(): String {
        val nombre=etNombre.text
        muestra=findViewById(R.id.tvResumen)
        //codigo que genera String con las alergias seleccionadas
        val alergias = mutableListOf<String>()
        if (swLactosa.isChecked) alergias.add("lactosa")
        if (swFrutosSecos.isChecked) alergias.add("frutos secos")
        if (swGluten.isChecked) alergias.add("gluten")
        val alergiaTexto = if (alergias.isEmpty()) "no tiene alergias" else "tiene alergia a ${alergias.joinToString(" y ")}"
        muestra.setText(alergiaTexto)
        rbSeleccionado=findViewById(rgDieta.checkedRadioButtonId)
        val dieta=rbSeleccionado.text
        val emergencia=if (cbEmergencia.isChecked) "Llamar ambulancia" else ""

        return  "$nombre $alergiaTexto y tiene dieta $dieta. $emergencia"

    }
}