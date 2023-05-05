package com.example.uploadimage

import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("idImage")
    var idImage: String,
    @SerializedName("nomImage")
    var nomImage: String,
    @SerializedName("image")
    var image: String
)
