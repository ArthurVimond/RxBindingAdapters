package fr.arthurvimond.rxbindingadapters.sample

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import fr.arthurvimond.rxbindingadapters.sample.databinding.MainActivityBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // UI
    private val binding: MainActivityBinding by lazy {
        DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.activity_main)
    }

    // Data
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    // Rx
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set ViewModel
        binding.viewModel = viewModel

        observeProperties()

    }

    private fun observeProperties() {

        // Age value TextView
        viewModel.age
                .subscribe {
                    ageValueTextView.text = "$it"
                }.addTo(compositeDisposable)

        // Display list
        viewModel.listVisible
                .subscribe {
                    languageSpinner.visibility = if (it) View.VISIBLE else View.GONE
                }
                .addTo(compositeDisposable)

        // Result text
        viewModel.getResultText()
                .subscribe { resultTextView.text = it }
                .addTo(compositeDisposable)
    }

    override fun onDestroy() {
        disposeObservers()
        super.onDestroy()
    }

    private fun disposeObservers() {
        compositeDisposable.dispose()
    }

}
