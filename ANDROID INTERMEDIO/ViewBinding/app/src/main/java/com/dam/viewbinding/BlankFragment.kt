package com.dam.viewbinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dam.viewbinding.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {
    private var _binding: FragmentBlankBinding? = null
    private val binding get() = _binding!!   // acceso seguro mientras la vista existe

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null   // evitar fugas de memoria
    }
}