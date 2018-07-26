package fr.arthurvimond.rxbindingadapters;

import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
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

    @BindingAdapter("rxTouch")
    public static void rxTouch(View view, Subject<MotionEvent> subject) {
        view.setOnTouchListener((view1, motionEvent) -> {
            subject.onNext(motionEvent);
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

    @BindingAdapter("rxItem")
    public static void rxItem(RadioGroup radioGroup, BehaviorSubject<String> subject) {

        // Initial value
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton button = (RadioButton) radioGroup.getChildAt(i);
            if (button.getText().toString().equals(subject.getValue())) {
                button.setChecked(true);
            }
        }

        // RadioGroup checked changes
        radioGroup.setOnCheckedChangeListener((radioGroup1, checkedId) -> {
            RadioButton checkedButton = radioGroup1.findViewById(checkedId);
            String text = checkedButton.getText().toString();
            subject.onNext(text);
        });
    }

    @BindingAdapter({"rxProgress", "rxMin", "rxMax"})
    public static void rxProgress(SeekBar seekBar, BehaviorSubject<Integer> subject, int min, int max) {
        int step = max - min;
        rxProgressWithStep(seekBar, subject, min, max, step);
    }

    @BindingAdapter({"rxProgress", "rxMin", "rxMax", "rxStep"})
    public static void rxProgressWithStep(SeekBar seekBar, BehaviorSubject<Integer> subject,
                                          int min, int max, int step) {

        // Limit ranges
        if (subject.getValue() < min) {
            subject.onNext(min);
        }

        if (subject.getValue() > max) {
            subject.onNext(max);
        }

        int mappedProgress = (int) MathUtils.map(subject.getValue(), min, max, 0, step);

        // SeekBar progress changes
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int unmappedProgress = (int) MathUtils.map(progress, 0, step, min, max);
                    subject.onNext(unmappedProgress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Initial value
        seekBar.setMax(step);
        seekBar.setProgress(mappedProgress);
    }

}
