# RxBindingAdapters
A collection of BindingAdapters for direct integration of RxJava with the Data Binding library.

# Gradle Setup

Check that you have the `jcenter` repository in your Project's `build.gradle` file:

```groovy
repositories {
    // ...
    jcenter()    
}
```

Add the dependency in your Module's `build.gradle` file:

```groovy
dependencies {
    // ...
    implementation 'fr.arthurvimond.rxbindingadapters:core:0.2.0'
}
```

# BindingAdapter List

## View clicks

XML layout:

```xml
<Button
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:rxClick="@{viewModel.buttonClicks}" />
```

ViewModel:

```kotlin
class MainViewModel() : ViewModel() {
  
    val buttonClicks: PublishSubject<Empty> = PublishSubject.create()
    
    // ...
  
}
```

## View long clicks

XML layout:

```xml
<Button
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:rxLongClick="@{viewModel.buttonLongClicks}" />
```

ViewModel:

```kotlin
class MainViewModel() : ViewModel() {
  
    val buttonLongClicks: PublishSubject<Empty> = PublishSubject.create()
    
    // ...
  
}
```

## View touch events

XML layout:

```xml
<View
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:rxTouch="@{viewModel.touchEvents}" />
```

ViewModel:

```kotlin
class MainViewModel() : ViewModel() {
  
    val touchEvents: PublishSubject<MotionEvent> = PublishSubject.create()
    
    // ...
  
}
```

## EditText text changes

XML layout:

```xml
<android.support.design.widget.TextInputEditText
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:rxText="@{viewModel.username}" />
```

ViewModel:

```kotlin
class MainViewModel() : ViewModel() {
  
    val username: BehaviorSubject<String> = BehaviorSubject.createDefault("Arthur")
    
    // ...
  
}
```

## CompoundButton checked changes

XML layout:

```xml
<android.support.v7.widget.SwitchCompat
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:rxChecked="@{viewModel.enabled}" />
```

ViewModel:

```kotlin
class MainViewModel() : ViewModel() {
  
    val enabled: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(true)
    
    // ...
  
}
```

## RadioGroup selection changes

XML layout:

```xml
<RadioGroup
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:rxItem="@{viewModel.gender}">
    

    <RadioButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Male" />
        

    <RadioButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Female" />
        

</RadioGroup>
```

ViewModel:

```kotlin
class MainViewModel() : ViewModel() {
  
    val gender: BehaviorSubject<String> = BehaviorSubject.createDefault("Male")
    
    // ...
  
}
```

## Spinner selection changes

XML layout:

```xml
<android.support.v7.widget.AppCompatSpinner
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:rxItem="@{viewModel.favoriteLanguage}"
    app:rxItems="@{viewModel.getLanguageList()}" />
```

ViewModel:

```kotlin
class MainViewModel() : ViewModel() {
  
    val favoriteLanguage: BehaviorSubject<String> = BehaviorSubject.createDefault("Kotlin")
    
    fun getLanguageList(): List<String> {
        return listOf("Kotlin", "Java", "Swift", "Dart", "JavaScript")
    }
    
    // ...
  
}
```

# License

```
Copyright 2018 Arthur VIMOND

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```