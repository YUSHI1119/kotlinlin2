package jp.co.eastem.sample.api

import jp.co.eastem.sample.api.service.SampleApiService
import jp.co.eastem.sample.model.MemoEntity
import jp.co.eastem.sample.model.RegMemoEntity
import jp.co.eastem.sample.model.delMemoEntity
import rx.Single

class SampleApiClient(private val sampleApiService: SampleApiService) {

    //メモ情報の登録
    fun regMemo(title: String, text: String): Single<RegMemoEntity> {
        return CommonClient.retry(sampleApiService.regMemo(text, title))
            .flatMap { ret ->
                return@flatMap Single.just(ret)
            }
    }

    //メモ一覧情報の取得
    fun getMemo(): Single<MemoEntity> {
        return CommonClient.retry(sampleApiService.getMemo())
            .flatMap { ret ->
                return@flatMap Single.just(ret)
            }
    }

    //メモの削除(自分で実装してみよう)
    fun delMemo(id: Int): Single<delMemoEntity>{
        return CommonClient.retry(sampleApiService.delMemo(id))
            .flatMap { ret ->
                return@flatMap Single.just(ret)
            }

    }
}
