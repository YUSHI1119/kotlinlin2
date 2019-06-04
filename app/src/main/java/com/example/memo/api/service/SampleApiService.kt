package jp.co.eastem.sample.api.service


//これがサーバの１つ手前

import jp.co.eastem.sample.model.MemoEntity
import jp.co.eastem.sample.model.RegMemoEntity
import jp.co.eastem.sample.model.delMemoEntity
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Single

    interface SampleApiService {

        //メモ情報の登録
        @FormUrlEncoded //URLを作るやつ
        @POST("/api/api.php?req=reg_memo")
        abstract fun regMemo(
            @Field("memo_text") text: String,
            @Field("memo_title") title: String
        ): Single<RegMemoEntity>

        //メモ一覧情報の取得
        @FormUrlEncoded
        @POST("/api/api.php?req=get_memo")
        abstract fun getMemo(
        ): Single<MemoEntity>


        //メモの削除(自分で実装してみよう
        @FormUrlEncoded
        @POST("/api/api.php?req=del_memo")
        abstract fun delMemo(
            @Field("memo_id") id: Int
        ): Single<delMemoEntity>

    }
