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

# Usage examples

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