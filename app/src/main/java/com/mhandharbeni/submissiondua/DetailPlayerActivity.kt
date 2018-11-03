package com.mhandharbeni.submissiondua

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.mhandharbeni.submissiondua.model.PlayerItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class DetailPlayerActivity : AppCompatActivity() {
    private lateinit var player: PlayerItem
    private lateinit var ivPlayer:ImageView
    private lateinit var itemTxtWeight:TextView
    private lateinit var itemTxtHeight:TextView
    private lateinit var itemTxtPosisi:TextView
    private lateinit var itemTxtDeskripsi:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        val intent = intent

        player = intent.getParcelableExtra("player")
        ivPlayer = find(R.id.ivPlayer)
        itemTxtWeight = find(R.id.itemTxtWeight)
        itemTxtHeight = find(R.id.itemTxtHeight)
        itemTxtPosisi = find(R.id.itemTxtPosisi)
        itemTxtDeskripsi = find(R.id.itemTxtDeskripsi)

        if (!player.poster.isNullOrEmpty()) {
            Picasso.get().load(player.poster).into(ivPlayer)
        }
        itemTxtWeight.text = player.berat
        itemTxtHeight.text = player.tinggi
        itemTxtPosisi.text = player.posisi
        itemTxtDeskripsi.text = player.deskripsi
    }
}
