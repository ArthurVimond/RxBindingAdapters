package fr.arthurvimond.rxbindingadapters.sample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by Arthur Vimond on 21/07/2018.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    val username: BehaviorSubject<String> = BehaviorSubject.createDefault("Arthur")
    val listVisible: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    private val compositeDisposable = CompositeDisposable()

    init {

        // Subscribe to username
        username.subscribe {
            // Do any needed logic
            Log.d("TAG", "username.onNext: $it")
        }.addTo(compositeDisposable)

        // Subscribe to username
        listVisible.subscribe {
            // Do any needed logic
            Log.d("TAG", "listVisible.onNext: $it")
        }.addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}