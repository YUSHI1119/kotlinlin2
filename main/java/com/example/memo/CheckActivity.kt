package com.example.memo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.memo.api.SampleHelper
import kotlinx.android.synthetic.main.activity_check.*


class CheckActivity : AppCompatActivity() {

    var test = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)

        //IDを取得するメソッドを書く
        // test = id


        del_button.setOnClickListener {

            SampleHelper.getSampleApiClient(this).delMemo(test)
                .subscribe(
                    { value ->
                        Toast.makeText(this, "メモの削除成功", Toast.LENGTH_LONG).show()
                    },
                    { t ->
                        Toast.makeText(this, "メモの削除失敗: ${t.localizedMessage}",  Toast.LENGTH_LONG).show()
                    })

        }
        edit_button.setOnClickListener {
            val intent = Intent(this@CheckActivity,EditActivity::class.java).apply {
                //          putExtra("")
            }
            startActivity(intent)

        }




        mail_button.setOnClickListener {


        }

    }
}