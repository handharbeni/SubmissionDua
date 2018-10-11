package com.mhandharbeni.submissiondua.model.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.mhandharbeni.submissiondua.model.sqlite.FavouriteTable.Companion.FIELD_DATE_FIXTURES
import com.mhandharbeni.submissiondua.model.sqlite.FavouriteTable.Companion.FIELD_ID_FIXTURES
import com.mhandharbeni.submissiondua.model.sqlite.FavouriteTable.Companion.FIELD_SCORE_AWAY
import com.mhandharbeni.submissiondua.model.sqlite.FavouriteTable.Companion.FIELD_SCORE_HOME
import com.mhandharbeni.submissiondua.model.sqlite.FavouriteTable.Companion.FIELD_TITLE_AWAY
import com.mhandharbeni.submissiondua.model.sqlite.FavouriteTable.Companion.FIELD_TITLE_HOME
import com.mhandharbeni.submissiondua.model.sqlite.FavouriteTable.Companion.TABLE_NAME
import org.jetbrains.anko.db.*

class SqliteFavourite(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "SubmissionDuaDB", null, 3){

    companion object {

        private var instance: SqliteFavourite? = null


        @Synchronized
        fun getInstance(ctx: Context) : SqliteFavourite{
            if (instance == null){
                instance = SqliteFavourite(ctx)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(TABLE_NAME, true,
                FIELD_ID_FIXTURES to TEXT,
                FIELD_DATE_FIXTURES to TEXT,
                FIELD_TITLE_HOME to TEXT,
                FIELD_TITLE_AWAY to TEXT,
                FIELD_SCORE_HOME to TEXT,
                FIELD_SCORE_AWAY to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(TABLE_NAME, true)
        onCreate(db)
    }

}