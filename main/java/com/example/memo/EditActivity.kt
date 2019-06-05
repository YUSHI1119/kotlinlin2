package com.example.memo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.memo.api.SampleHelper
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_menu.*

//findviewByIDバイバイ
import kotlinx.android.synthetic.main.activity_edit.*
import kotlin.math.log



class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)


        button4.setOnClickListener {

            val title = editText2.text.toString()
            val text = editText3.text.toString()


            SampleHelper.getSampleApiClient(this).regMemo(title,text)
                .subscribe(
                        { value ->
                            Toast.makeText(this, "メモの登録成功", Toast.LENGTH_LONG).show()
                            val intent = Intent(this@EditActivity,CheckActivity::class.java).apply {
                                //                putExtra("")




                            }
                            startActivity(intent)
                        },
                        { t ->
                            Toast.makeText(this, "メモの登録失敗: ${t.localizedMessage}",  Toast.LENGTH_LONG).show()
                        })




        }
    }
}