package com.dam.exnov25

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.w3c.dom.Text

const val ALUMNO = "Jose David"



class MainActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var nReceta: TextView
    private lateinit var time: TextView
    private lateinit var porciones: TextView
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var btNotas: Button
    private lateinit var viewPagerAdaptador: ViewPagerAdaptador

    override fun onOptionsItemSelected(item: MenuItem)= when(item.itemId) {
        R.id.action_buscar -> {
            val intent= Intent(this, AjustesActivity::class.java)
            startActivity(intent)
            true
        }
        else ->{
        true
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }

        spinner=findViewById(R.id.spinner)
        val datosParaElSpinner = arrayOf(
            mockRecipes[0].name,
            mockRecipes[1].name,
            mockRecipes[2].name
        )
        //INICIO tu código
        setSupportActionBar(findViewById(R.id.toolbarPrincipal))
        nReceta=findViewById(R.id.txt_nombre_receta)
        time=findViewById(R.id.txt_tiempo)
        porciones=findViewById(R.id.txt_porciones)

        btNotas=findViewById(R.id.btn_ver_detalle)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item,datosParaElSpinner)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=adaptador
        spinner.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view:
            View?, position: Int, id: Long) {
                val pos = parent.selectedItemPosition

                bindearDatos(pos)



            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }



        viewPagerAdaptador= ViewPagerAdaptador(this, 2)
        viewPager = findViewById(R.id.viewpager)
        tabLayout = findViewById(R.id.appbartabs)
        viewPager.adapter= viewPagerAdaptador
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Ingredientes"
                }

                1 -> {
                    tab.text = "Pasos"
                }

            }
        }.attach()

        //FIN tu código


        supportActionBar?.title = "Recetas de $ALUMNO"
    }






    /**
     * Paso 1: Este metodo enlaza toda la información de la receta al layout
     * Paso 2: Lanza la actividad [NotasActivity] y le envia las notas de la receta
     * Paso 3: Envia los datos a los fragmentos con la lista de ingredientes y los pasos de la receta.
     * Utiliza tu adaptador para acceder al fragmento y enviar a traves de un metodo del fragmento el
     * MutableList<String> con el array
     * Ejemplo: viewPagerAdaptador.getIngredientesFragment().setIngredientes(mockRecipes[item].ingredients)
     *
     */
    fun bindearDatos(item: Int) {
        var miReceta=mockRecipes[item]

        nReceta.text=miReceta.name
        time.text=miReceta.time.toString()+ getString(R.string.txt_tiempo)
        porciones.text=miReceta.servings.toString()+getString(R.string.txt_porciones)
        btNotas.setOnClickListener {
            val intent= Intent(this, NotasActivity::class.java)
            intent.putExtra("notas", miReceta.notes)
            startActivity(intent)
        }

        //INICIO envio de datos a los fragmentos

        viewPagerAdaptador.getIngredientesFragment().setIngredientes(miReceta.ingredients)
        viewPagerAdaptador.getPasosFragment().setPasos(miReceta.steps)




        //FIN envio de datos a los fragmentos



    }

    // Lista de datos simulados para alimentar activity_main y los RecyclerView de los fragmentos.
    private val mockRecipes = listOf(
        Receta(
            id= 1,
            name = "Paella Valenciana",
            time = 60,
            servings = 4,
            image = "...",
            ingredients = mutableListOf("1 kg Arroz Bomba", "500g Pollo y Conejo", "200g Judía Verde", "Azafrán", "Aceite de Oliva"),
            steps = mutableListOf("1. Sofría la carne y las verduras.", "2. Añada el agua y el azafrán, deje hervir.", "3. Incorpore el arroz y cocine por 18 min.", "4. Deje reposar por 5 minutos antes de servir."),
            notes = "Utilice leña para un sabor ahumado auténtico. El caldo debe ser de calidad."
        ),
        Receta(
            id= 2,
            name = "Gazpacho Andaluz",
            time = 15,
            servings = 6,
            image = "...",
            ingredients = mutableListOf("1 kg Tomates maduros", "1 Pimiento", "1 Pepino", "Pan duro (remojado)", "Aceite de Oliva", "Vinagre de Jerez"),
            steps = mutableListOf("1. Lave y trocee todas las verduras.", "2. Triture en la licuadora.", "3. Pase la mezcla por un colador.", "4. Enfríe por al menos 2 horas."),
            notes = "Es ideal servirlo muy frío."
        ),
        Receta(
            id=3,
            name = "Tortilla de Patatas",
            time = 40,
            servings = 4,
            image = "...",
            ingredients = mutableListOf("6 huevos grandes", "1 kg de patatas", "1 Cebolla (opcional)", "Aceite de oliva", "Sal"),
            steps = mutableListOf("1. Pela y corta las patatas y la cebolla.", "2. Fríe las patatas a fuego lento.", "3. Bate los huevos con sal.", "4. Cuaja la tortilla."),
            notes = "La clave está en no dejar que se seque demasiado el centro."
        )
    )

}