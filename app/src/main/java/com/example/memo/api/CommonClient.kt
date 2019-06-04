package jp.co.eastem.sample.api

import rx.Observable
import rx.Single
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

object CommonClient {

    private val RETRY_COUNT = 3
    private val RETRY_DELAY = 2

    fun <T> retry(observable: Single<T>): Single<T> {
        return observable
            .subscribeOn(Schedulers.io())
            .retryWhen { attempts ->
                attempts.zipWith(Observable.range(1, RETRY_COUNT + 1)) { error, i -> i }
                    .flatMap { i: Int ->
                        if (i <= RETRY_COUNT) {
                            return@flatMap Observable.timer((RETRY_DELAY * i).toLong(), TimeUnit.SECONDS)
                        }
                        return@flatMap attempts.flatMap<Long>({ Observable.error(it) })
                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}
