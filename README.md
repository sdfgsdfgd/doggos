# Kaan's Doggo App made for Compare the Market


This is the Android Mobile App built for **Compare the Market**

Thank you Jordan and others for reviewing this project and the time you spend reading/reviewing it.



# Overview

This is a single Activity application that uses Kotlin Jetpack components (navigation, livedata, lifecycles, KTX, view/data bindings, GSON for parsing JSON,  
picasso for retrieval of images from URLs, moshi for extra parsing convenience),  MVVM design pattern (Clean Architecture),  Dagger2, RxJava, Espresso for testing, RecyclerView, ConstraintLayout.

Under the hood, except for image retrieval and moshi, they're all native libraries and this is why everything works in a flawless, stable way.

I had limited time so there is room for a lot of improvements.

# Architecture

## Package Structure

Dependencies are listed at the end of this page.

The 3 main packages are:

- `data`: API requests and responses, database access (currently there is no data layer, as there was no need for an API for this project yet)
- `domain`: business logic / cache / cleans up the data from the data layer
- `ui`: displays the data from `domain`

## Navigation
Android Jetpack navigation component is used.

## Data Binding and View Binding

Data and view bindings are in **bindings** folder.

For image retrieval, **Picasso** is used within a Data binding.

Also for Dynamic addition of **Categories** TextViews for each news, **setCategories** binding is used.


## Data Binding in RecyclerView

- RecyclerView bindings are part of my personal bindings, like native convenience macros that help build a recyclerview in a minimal and efficient way without the need for an adapter.
- It's purpose is to simplify the use of data binding with the RecyclerView. It can be found in `databinding.recyclerview`.

In a basic set up, the view model provides the following properties for binding:

- `layoutProvider: (T) -> Int`: a closure that takes in a model object type and returns the equivalent layout resource
- `itemDiff: DiffUtil.ItemCallback<T>`: a diff implementation that compares model object to determine changes to the list, the framework can automatically update the recycler view content based on the result of a diff check
- `items: LiveData<List<T>>`: the list of model objects

The app mostly uses the setup described above, however, other configurations are available, see `RecyclerViewBindingAdapter.kt`.


## Test
UI testing was done manually with Espresso. For now, the Orientation changes are tested - in Portrait mode we check whether we see the images with the right IDs and layouts.

With this we can test against accurate display of orientation changes and the LayoutManager.


## Other

### List of Dependencies

```kotlin
dependencies {
    kapt "com.android.databinding:compiler:3.1.4"

    // Lifecycle Libraries
    def lifecycle_version = "2.2.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"

    // Jetpack Components
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation 'androidx.core:core-ktx:1.3.2'
    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'com.google.android.material:material:1.2.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'androidx.navigation:navigation-fragment-ktx:2.3.2'
    implementation 'androidx.navigation:navigation-ui-ktx:2.3.2'

    // Dagger
    implementation "com.google.dagger:dagger:2.28.3"
    implementation "com.google.dagger:dagger-android:2.28.3"
    implementation "com.google.dagger:dagger-android-support:2.28.3"
    kapt "com.google.dagger:dagger-android-processor:2.28.3"
    kapt "com.google.dagger:dagger-compiler:2.28.3"

    // JSON Parsing via GSON
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation "org.jetbrains.kotlinx:kotlinx-serialization-runtime:1.0-M1-1.4.0-rc-218"

    // Picasso - Image retrieval
    implementation 'com.squareup.picasso:picasso:2.71828'

    // Network
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"
    implementation "com.squareup.retrofit2:adapter-rxjava2:2.9.0"
    implementation "com.squareup.retrofit2:converter-moshi:2.9.0"

    // Multi-threading
    implementation "io.reactivex.rxjava2:rxandroid:2.1.1"
    implementation "io.reactivex.rxjava2:rxjava:2.2.20"

    // Tests
    debugImplementation('androidx.fragment:fragment-testing:1.1.0') {
        exclude group: 'androidx.test', module: 'core'
    }

    // Instrumentation test
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
    androidTestImplementation 'androidx.test:runner:1.3.0'
    androidTestImplementation 'androidx.test:core:1.3.0'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test:rules:1.3.0'

    androidTestImplementation 'junit:junit:4.13.1'
    androidTestImplementation 'org.mockito:mockito-core:3.7.7'
} ```