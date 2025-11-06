package com.dam.exnov25

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdaptador(activity: AppCompatActivity, var itemsCount: Int) : FragmentStateAdapter(activity) {

    //NO TOCAR
    //creación de objeto de ingredientesFragment. Comprueba si ya estaba creado para reutilizar. Si no, crea uno nuevo.
    private val ingredientesFragment= activity.supportFragmentManager.findFragmentByTag("f0") as? IngredientesFragment ?: IngredientesFragment()
    //creación de objeto de pasosFragment. Comprueba si ya estaba creado para reutilizar. Si no, crea uno nuevo.
    private val pasosFragment=activity.supportFragmentManager.findFragmentByTag("f1") as? PasosFragment ?: PasosFragment()

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0-> ingredientesFragment
            else -> pasosFragment

        }
    }

    /**
     * Obtiene la instancia del fragmento encargado de mostrar la lista de ingredientes.
     * @return [IngredientesFragment] asociado al adaptador.
     */
    fun getIngredientesFragment(): IngredientesFragment {
        return ingredientesFragment
    }

    /**
     * Obtiene la instancia del fragmento encargado de mostrar los pasos de la receta.
     * @return [PasosFragment] asociado al adaptador.
     */
    fun getPasosFragment(): PasosFragment {
        return pasosFragment
    }


}