package com.freemovies.firetv.ui.splash

import androidx.fragment.app.activityViewModels
import com.freemovies.firetv.R
import com.freemovies.firetv.base.ui.DataBindingFragment
import com.freemovies.firetv.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class SplashFragment :
    DataBindingFragment<FragmentSplashBinding>(R.layout.fragment_splash) {
    private val viewModel: SplashViewModel by activityViewModels()

    override fun onBindView(binding: FragmentSplashBinding) {
        binding.viewModel = viewModel
        viewModel.dismiss.observe(viewLifecycleOwner) {
            navigateFurther()
        }
    }

    private fun navigateFurther() {
        navController.popBackStack()
        navController.graph.setStartDestination(R.id.home_fragment)
        navController.navigate(SplashFragmentDirections.actionToHomeFragment())
    }
}