package com.dam.repasoultimodiadani

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import androidx.appcompat.widget.SwitchCompat

class OtroFragmento :Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_p_m_d_m, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }




    /**
     * Cambia la imagen de un `ImageView` según el estado booleano recibido.
     *
     * @param cambio Si es `true`, se asigna la imagen de "encendido" (`R.drawable.sw_on`).
     *               Si es `false`, se asigna la imagen de "apagado" (`R.drawable.sw_off`).
     * @param image  El `ImageView` al que se aplicará el cambio. Si es `null`, no se realiza ninguna acción.
     */
    private fun cambiarImagen(cambio: Boolean, image: ImageView?) {
        if(cambio){
//            image?.setImageResource(R.drawable.sw_on)
        }else{
//            image?.setImageResource(R.drawable.sw_off)
        }
    }


    /**
     * Verifica si todos los switches están activados. Si todos están activados,
     * activa el switch maestro (`sw_all`). De lo contrario, lo desactiva.
     */
    private fun checkSwitch() {
        //TODO implementa este metodo
    }


    /**
     * Configura el estado de los tres switches al valor proporcionado.
     *
     * @param b Si es `true`, todos los switches se marcan como seleccionados.
     *          Si es `false`, se desmarcan.
     */
    private fun checkAll(b: Boolean) {

    }
}
