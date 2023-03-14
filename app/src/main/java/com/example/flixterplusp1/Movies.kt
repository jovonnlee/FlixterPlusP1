package com.example.flixterplusp1

import com.google.gson.annotations.SerializedName

class Movies {
    @SerializedName("original_title")
    var movieTitle: String? = null

    @SerializedName("overview")
    var movieDescription: String? = null

    @SerializedName("poster_path")
    var movieImage: String? = null
}