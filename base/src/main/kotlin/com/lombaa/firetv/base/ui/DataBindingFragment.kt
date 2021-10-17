package com.lombaa.firetv.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class DataBindingFragment<B : ViewDataBinding>(@LayoutRes private val layoutRes: Int) : Fragment() {

    protected abstract fun onBindView(binding: B)

    protected val navController by lazy { findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<B>(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        onBindView(binding)
        return binding.root
    }
}