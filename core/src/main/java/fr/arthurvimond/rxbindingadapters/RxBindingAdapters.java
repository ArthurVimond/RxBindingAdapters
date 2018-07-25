package fr.arthurvimond.rxbindingadapters;

import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by Arthur Vimond on 21/07/2018.
 */
public class RxBindingAdapters {

    @BindingAdapter("rxClick")
    public static void rxClick(View view, Subject<Empty> subject) {
        view.setOnClickListener(view1 -> subject.onNext(Empty.VOID));
    }

    @BindingAdapter("rxLongClick")
    public static void rxLongClick(View view, Subject<Empty> subject) {
        view.setOnLongClickListener(view1 -> {
            subject.onNext(Empty.VOID);
            return true;
        });
    }

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

    @BindingAdapter("rxChecked")
    public static void rxChecked(CompoundButton compoundButton, final BehaviorSubject<Boolean> subject) {

        // Initial value
        compoundButton.setChecked(subject.getValue());

        // Switch checked changes
        compoundButton.setOnCheckedChangeListener((compoundButton1, checked) -> subject.onNext(checked));
    }

    @BindingAdapter({"rxItem", "rxItems"})
    public static void rxItem(Spinner spinner, BehaviorSubject<String> subject, List<String> items) {

        // Adapter
        ArrayAdapter adapter = new ArrayAdapter(spinner.getContext(),
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Initial value
        int position = adapter.getPosition(subject.getValue());
        spinner.setSelection(position);

        // Selected item changes
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                if (item != null && subject.getValue() != item) {
                    subject.onNext(item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

}
