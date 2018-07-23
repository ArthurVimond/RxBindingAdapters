package fr.arthurvimond.rxbindingadapters.sample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.util.Log
import fr.arthurvimond.rxbindingadapters.Empty
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

/**
 * Created by Arthur Vimond on 21/07/2018.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    val username: BehaviorSubject<String> = BehaviorSubject.createDefault("Arthur")
    val listVisible: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(true)
    val favoriteLanguage: BehaviorSubject<String> = BehaviorSubject.createDefault("Kotlin")
    val reverseText: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    val buttonClicks: PublishSubject<Empty> = PublishSubject.create()

    private val compositeDisposable = CompositeDisposable()

    init {

        // Subscribe to username
        username.subscribe {
            // Do any needed logic
            Log.d("TAG", "username.onNext: $it")
        }.addTo(compositeDisposable)

        // Subscribe to listVisible
        listVisible.subscribe {
            // Do any needed logic
            Log.d("TAG", "listVisible.onNext: $it")
        }.addTo(compositeDisposable)

        // Subscribe to favoriteLanguage
        favoriteLanguage.subscribe {
            // Do any needed logic
            Log.d("TAG", "favoriteLanguage.onNext: $it")
        }.addTo(compositeDisposable)

        // Subscribe to reverseText
        reverseText.subscribe {
            // Do any needed logic
            Log.d("TAG", "reverseText.onNext: $it")
        }.addTo(compositeDisposable)

        // Subscribe to buttonClicks
        buttonClicks.subscribe {
            // Do any needed logic
            Log.d("TAG", "buttonClicks.onNext: $it")
        }.addTo(compositeDisposable)
    }

    fun getLanguageList(): List<String> {
        return listOf("Kotlin", "Java", "Swift", "Dart", "JavaScript")
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}