package com.dam.exnov25

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

const val ALUMNO = "Alvaro"

private lateinit var toolbar: Toolbar
private lateinit var spinner: Spinner
private lateinit var ivPlato: ImageView
private lateinit var txt_tiempo: TextView
private lateinit var tvRaciones: TextView
private lateinit var tvNombreReceta: TextView
private lateinit var boton_notas: Button
private lateinit var tabLayout: TabLayout
private lateinit var viewPager2: ViewPager2

// ⭐ IMPORTANTE: hacémoslo global
private lateinit var viewPagerAdaptador: ViewPagerAdaptador

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajustes de ventanas (automático)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Datos
        val datosParaElSpinner = arrayOf(
            mockRecipes[0].name,
            mockRecipes[1].name,
            mockRecipes[2].name
        )

        // --------- BINDEO ---------
        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager)

        boton_notas = findViewById(R.id.btn_ver_detalle)
        ivPlato = findViewById(R.id.img_receta)
        tvNombreReceta = findViewById(R.id.txt_nombre_receta)
        txt_tiempo = findViewById(R.id.txt_tiempo)
        tvRaciones = findViewById(R.id.txt_porciones)

        spinner = findViewById(R.id.spinner)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // --------- SPINNER ---------
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            datosParaElSpinner
        )
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adaptador

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                bindearDatos(position)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        // --------- VIEWPAGER + TABS ---------
        viewPagerAdaptador = ViewPagerAdaptador(this, 2)
        viewPager2.adapter = viewPagerAdaptador

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = if (position == 0) "Ingredientes" else "Pasos"
        }.attach()





        supportActionBar?.title = "Recetas de $ALUMNO"
    }


    fun bindearDatos(item: Int) {
        val receta = mockRecipes[item]

        // Imagen placeholder
        ivPlato.setImageResource(R.drawable.outline_award_meal_24)

        // Nombre
        tvNombreReceta.text = receta.name

        // Tiempo
        txt_tiempo.text = "${receta.time} minutos"

        // Raciones
        tvRaciones.text = "${receta.servings} raciones"

        // ----- BOTÓN NOTAS -----
        boton_notas.setOnClickListener {
            val intent = Intent(this, NotasActivity::class.java)
            intent.putExtra("notas", receta.notes)
            startActivity(intent)
        }

        // ----- ENVÍO A LOS FRAGMENTOS -----
        viewPagerAdaptador.getIngredientesFragment()
            .setIngredientes(receta.ingredients)

        viewPagerAdaptador.getPasosFragment()
            .setPasos(receta.steps)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_ajustes -> {
                startActivity(Intent(this, AjustesActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // -------- LISTA DE RECETAS --------
    private val mockRecipes = listOf(
        Receta(
            id = 1,
            name = "Paella Valenciana",
            time = 60,
            servings = 4,
            image = "...",
            ingredients = mutableListOf("1 kg Arroz Bomba", "500g Pollo y Conejo", "200g Judía Verde", "Azafrán", "Aceite de Oliva","harina","conejo","pollo"),
            steps = mutableListOf("1. Sofría la carne...", "2. Añada el agua...", "3. Incorpore el arroz...", "4. Reposar 5 min."),
            notes = "Utilice leña para un sabor ahumado auténtico."
        ),
        Receta(
            id = 2,
            name = "Gazpacho Andaluz",
            time = 15,
            servings = 6,
            image = "...",
            ingredients = mutableListOf("Tomates", "Pimiento", "Pepino", "Pan remojado", "Aceite", "Vinagre"),
            steps = mutableListOf("1. Lave...", "2. Triture...", "3. Cuele...", "4. Enfríe 2 horas."),
            notes = "Ideal servirlo muy frío."
        ),
        Receta(
            id = 3,
            name = "Tortilla de Patatas",
            time = 40,
            servings = 4,
            image = "...",
            ingredients = mutableListOf("Huevos", "Patatas", "Cebolla", "Aceite", "Sal"),
            steps = mutableListOf("1. Pelar...", "2. Freír...", "3. Batir...", "4. Cuajar."),
            notes = "La clave es no secarla demasiado."
        )
    )
}
