package com.hercan.widgetexample.core.network.dto


import com.google.gson.annotations.SerializedName

data class JokeResponse(
    @SerializedName("category") val category: String?,
    @SerializedName("delivery") val delivery: String?,
    @SerializedName("error") val error: Boolean?,
    @SerializedName("flags") val flags: Flags?,
    @SerializedName("id") val id: Int?,
    @SerializedName("lang") val lang: String?,
    @SerializedName("safe") val safe: Boolean?,
    @SerializedName("setup") val setup: String?,
    @SerializedName("type") val type: String?
) {
    data class Flags(
        @SerializedName("explicit") val explicit: Boolean?,
        @SerializedName("nsfw") val nsfw: Boolean?,
        @SerializedName("political") val political: Boolean?,
        @SerializedName("racist") val racist: Boolean?,
        @SerializedName("religious") val religious: Boolean?,
        @SerializedName("sexist") val sexist: Boolean?
    )
}