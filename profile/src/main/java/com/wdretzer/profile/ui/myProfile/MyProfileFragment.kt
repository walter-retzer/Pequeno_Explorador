package com.wdretzer.profile.ui.myProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wdretzer.profile.databinding.FragmentMyProfileBinding


class MyProfileFragment : Fragment() {

    private lateinit var binding: FragmentMyProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {

    }
}