package com.dam.appmonstruo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity2 : AppCompatActivity() {

private lateinit var radioGroup: RadioGroup
private lateinit var resultadoRadioGroup: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_2)



//bindeo
        radioGroup = findViewById(R.id.radioGroup)
        val spinner : Spinner = findViewById(R.id.spinner)
        resultadoRadioGroup=findViewById(R.id.resultadoRadioGroup)





// Spinner // array para darle las opciones al espiner
        val opciones =arrayOf(" ","Activity 1","Activity 2", "Activity 3")

        //adaptaddor del spinner
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adaptador

//metodo del listener del espiner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                val temaSeleccionado = parent.getItemAtPosition(position).toString()

                if( temaSeleccionado.equals(opciones.get(1))){
                    val intent1 = Intent(this@Activity2, MainActivity::class.java) // nombre de tu activity
                    startActivity(intent1)
                }
                if( temaSeleccionado.equals(opciones.get(2))){
                    val intent2 = Intent(this@Activity2, Activity2::class.java)
                    startActivity(intent2)
                }
                if( temaSeleccionado.equals(opciones.get(3))){
                    val intent2 = Intent(this@Activity2, Activity3::class.java)
                    startActivity(intent2)
                }


                Toast.makeText(this@Activity2, " $temaSeleccionado", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        //radiogroup
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioSeleccionado = findViewById<RadioButton>(checkedId)
            resultadoRadioGroup.text = "Has elegido: ${radioSeleccionado.text}"
        }





        }
    }
