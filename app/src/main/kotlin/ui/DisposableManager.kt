package ui

import di.ActivityScope
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@ActivityScope
open class DisposableManager @Inject internal constructor() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun add(vararg disposables: Disposable) {
        compositeDisposable.addAll(*disposables)
    }

    fun dispose() {
        compositeDisposable.clear()
    }
}
