package fr.arthurvimond.rxbindingadapters;

import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Arthur Vimond on 21/07/2018.
 */
public class RxBindingAdapters {

    @BindingAdapter("rxText")
    public static void rxText(EditText editText, final BehaviorSubject<String> subject) {

        // Initial value
        editText.setText(subject.getValue());

        // Text changes
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                subject.onNext(editable.toString());
            }
        });
    }

}
