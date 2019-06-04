package jp.co.eastem.sample.model

import com.google.gson.annotations.SerializedName

open class SampleBaseEntity {

    //ここはAPIの仕様に合わせる
    private val RESULT_SUCCESS = 0 //0が成功コードの場合
    private val RESULT_FAILURE = -1 // 失敗
    private val RESULT_NOT_INITIALIZED = -255 // 未設定

    private val ERROR_CODE_SUCCESS = 0 //0が成功コードの場合
    private val ERROR_CODE_NOT_INITIALIZED = RESULT_NOT_INITIALIZED

    // result
    @SerializedName("result")
    var result  = Int.MAX_VALUE
    val isResultSuccess: Boolean
        get() = result == RESULT_SUCCESS
    val resultCodeStr: String
        get() {
            when (this.result) {
                RESULT_SUCCESS -> return "SUCCESS"
                RESULT_FAILURE -> return "FAILURE" //エラーコードに対応するエラー内容
                Int.MAX_VALUE -> return "NOT_INITIALIZED"
            }
            return "UNDEFINED"
        }

    @SerializedName("error_code")
    var error_code = ERROR_CODE_NOT_INITIALIZED
}

//メモ情報の登録
class RegMemoEntity : SampleBaseEntity() {

}

//メモ一覧情報の取得
class MemoEntity : SampleBaseEntity() {
    @SerializedName("memo_list")
    var list: List<MemoValueEntity>? = null
}

class MemoValueEntity : SampleBaseEntity() {

    @SerializedName("memo_id")
    var memo_id: Long = 0

    @SerializedName("memo_text")
    var memo_text: String? = null

    @SerializedName("memo_title")
    var memo_title: String? = null


    @SerializedName("memo_image")
    var memo_image: String? = null
}

//メモの削除
class delMemoEntity : SampleBaseEntity() {

}

