package com.wdretzer.profile.models

import androidx.annotation.DrawableRes


data class ProfileInformation(
    @DrawableRes
    val imageProfile: Int,
    var imageStatus: Boolean
)
