# SuperCart Android Application

SuperCart is an advanced Android application developed in Kotlin, employing the Model-View-ViewModel (MVVM) architecture. This application provides users with a seamless and sophisticated shopping experience, incorporating features such as MVVM, Room database, Retrofit for API communication, Fragments, Menus, DialogBoxes, navigation graph, tab layout, scroll view, view pager, shared view model, and Picasso for image loading.

## Table of Contents
- [Overview](#overview)
- [Architecture](#architecture)
- [Features](#features)
- [Screenshots](#screenshots)
- [Installation](#installation)
- [Dependencies](#dependencies)


## Overview
SuperCart is designed to offer an efficient and user-friendly shopping experience on Android devices. The application supports functionalities like user authentication, product browsing, detailed product descriptions, cart management, and order placement, all implemented using modern development practices.

## Architecture
SuperCart is built using the MVVM architecture, ensuring a clear separation of concerns and promoting modularity and testability. The key components of the architecture include:

- **Model**: Represents the data layer, including local data managed by Room and remote data fetched via Retrofit.
- **View**: Represents the UI layer, consisting of activities and fragments responsible for displaying data and handling user interactions.
- **ViewModel**: Serves as a bridge between the Model and the View, holding UI-related data and handling business logic. Shared ViewModels facilitate data sharing between multiple fragments.

## Features
- **MVVM Architecture**: Ensures a clean separation of concerns, enhancing code modularity and maintainability.
- **Navigation Component**: Simplifies navigation within the app and supports advanced navigation patterns.
- **Tab Layout and ViewPager**: Provides an intuitive tabbed navigation experience.
- **ScrollView**: Enables vertically scrollable content.
- **Shared ViewModel**: Facilitates data sharing between fragments, ensuring a cohesive user experience.
- **Room Database**: Efficiently manages local data storage with SQL-like queries.
- **Retrofit**: Handles network requests seamlessly, supporting RESTful API interactions.
- **Picasso**: Manages image loading and caching efficiently, reducing memory usage and improving performance.
- **Fragments**: Utilized for a modular and flexible UI design.
- **Menus and Dialogs**: Enhance user interactions and provide contextual options.

## Screenshots
![cart](https://github.com/user-attachments/assets/11db39f0-381e-4c6e-8a68-20a2abe3a7f8)
![dashboard](https://github.com/user-attachments/assets/6d8c3e27-d44f-426b-ba64-4e98ce3a6cc8)
![details](https://github.com/user-attachments/assets/7607f212-e3f7-4bd1-93aa-79711c3a4a76)
![login](https://github.com/user-attachments/assets/a4effc95-726c-429b-b544-7cbe28489d8b)
![logout](https://github.com/user-attachments/assets/8886ff06-f304-47d8-9c88-b3766f6e88f4)
![menu](https://github.com/user-attachments/assets/069ea198-e362-426b-8b70-de24bd5b231a)
![register](https://github.com/user-attachments/assets/ff67df78-84c0-4af8-8f21-4d9cd4737461)
![search](https://github.com/user-attachments/assets/559204a5-01cd-4845-bd86-b01bf8f2505d)

## Dependencies
 ```bash
dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation 'androidx.core:core-ktx:1.6.0'
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.0'
    
    // Navigation Component
    implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'
    implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'
    
    // ViewModel and LiveData
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.3.1'
    
    // Room Database
    implementation 'androidx.room:room-runtime:2.3.0'
    kapt 'androidx.room:room-compiler:2.3.0'
    implementation 'androidx.room:room-ktx:2.3.0'
    
    // Retrofit for network calls
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    // Picasso for image loading
    implementation 'com.squareup.picasso:picasso:2.71828'
    
    // Testing
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
}
```
## Installation

To install and run the SuperCart app, follow these detailed steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Nitya-gattu/ShoppingCart.git
