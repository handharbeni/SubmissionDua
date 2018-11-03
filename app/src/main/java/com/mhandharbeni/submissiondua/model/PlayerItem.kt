package com.mhandharbeni.submissiondua.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerItem (
        @SerializedName("idTeam")
        var idTeam:String?,

        @SerializedName("idPlayer")
        var idPlayer:String?,

        @SerializedName("strPlayer")
        var namePlayer:String?,

        @SerializedName("strPosition")
        var posisi:String?,

        @SerializedName("strCutout")
        var profil:String?,

        @SerializedName("strDescriptionEN")
        var deskripsi:String?,

        @SerializedName("strHeight")
        var tinggi:String?,

        @SerializedName("strWeight")
        var berat:String?,

        @SerializedName("strFanart1")
        var poster:String?
):Parcelable