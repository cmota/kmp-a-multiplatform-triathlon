package com.cmota.playground.triathlon.androidApp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cmota.playground.triathlon.androidApp.R
import com.cmota.playground.triathlon.androidApp.databinding.FragmentSettingsBinding
import com.cmota.playground.triathlon.shared.PlatformSettings.settingsRepository


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.toolbar.title = getString(R.string.navigation_settings)
        setup()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setup() {
        binding.cbOnline.isChecked = settingsRepository.shouldShowOnlyOnlineConferences()
        binding.cbOnline.setOnCheckedChangeListener { _, isChecked ->
            settingsRepository.onlyOnlineConferences(isChecked)
        }
    }
}