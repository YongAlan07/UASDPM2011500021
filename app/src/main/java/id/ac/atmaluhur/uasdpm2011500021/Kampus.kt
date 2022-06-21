package id.ac.atmaluhur.uasdpm2011500021

import android.content.*
import android.database.Cursor
import android.database.sqlite.*

class Kampus(context: Context): SQLiteOpenHelper(context, "campuss", null, 1){
    var nidn = ""
    var namadsn = ""
    var jbt = ""
    var golonganpkt = ""
    var pendidikan = ""
    var keahlian = ""
    var prgmstudi = ""

    private val tabel = "lecturer"

    override fun onCreate(db: SQLiteDatabase?) {
        val sql
                = """create table $tabel(
            NIDN char(10) primary key,
            nama_dosen varchar(50) not null,
            Jabatan varchar(15) not null,
            golongan_pangkat varchar(30) not null,
            Pendidikan char(2) not null,
            Keahlian varchar(30) not null,
            Program_Studi varchar(50) not null
            )
        """.trimIndent()
        db?.execSQL(sql)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val sql
                = "drop table if exists $tabel"
        db?.execSQL(sql)
    }

    fun simpan(): Boolean{
        val db = writableDatabase
        val cv = ContentValues()
        with(cv){
            put("NIDN", nidn)
            put("nama_dosen", namadsn)
            put("Jabatan", jbt)
            put("golongan_pangkat", golonganpkt)
            put("Pendidikan", pendidikan)
            put("Keahlian", keahlian)
            put("Program_Studi", prgmstudi)
        }
        val cmd = db.insert(tabel, null, cv)
        db.close()
        return cmd != -1L
    }

    fun ubah(kode: String): Boolean {
        val db = writableDatabase
        val cv = ContentValues()
        with(cv){
            put("nama_dosen", namadsn)
            put("Jabatan", jbt)
            put("golongan_pangkat", golonganpkt)
            put("Pendidikan", pendidikan)
            put("Keahlian", keahlian)
            put("Program_Studi", prgmstudi)

        }
        val cmd = db.update(tabel, cv, "NIDN = ?", arrayOf(kode))
        db.close()
        return cmd != -1
    }

    fun hapus(kode: String): Boolean {
        val db = writableDatabase
        val cmd = db.delete(tabel, "NIDN = ?", arrayOf(kode))
        return cmd != -1
    }

    fun tampil(): Cursor{
        val db = writableDatabase
        val reader = db.rawQuery("select * from $tabel", null)
        return reader
    }
}