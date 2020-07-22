# mToDo

A simple todo Android App where we can add and can view our todo details - (Kotlin, Dagger 2, Architecture Components, MVVM, Material Components, Intro slides, clean architecture, Room, Stetho, Viewpager2,Text Drawable, RX java).



## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Dagger 2](https://dagger.dev/) - Dependency Injection Framework
- [Room] (https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase) - Room is a persistence library, part of the Android Jetpack for local database.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.

# Package Structure
    
    com.android.microblogapp    # Root Package
    .
    ├── data                # For data handling.
    │   ├── local           # Room Data Handlers     
            ├── db          # Room Data Handlers     
    |   │       ├── dao     # Room database table creation.
                ├── entity  # Room database table componenet.
        └── model           # POJO classes  
    │   └── repository      # Single source of data.
    |
    ├── model               # Model classes
    |
    ├── di                  # Dependency Injection             
    │   ├── builder         # Activity Builder
    │   ├── component       # DI Components       
    │   └── module          # DI Modules
    |
    ├── ui                  # Activity/View layer
    │   ├── base            # Base View
    │   ├── home            # home screen
    │   └── intro           # Intro Activity & ViewModel
    |   │   ├── adapter     # Adapter for RecyclerView
    |   │   ├── viewmodel   # ViewHolder for RecyclerView
        └── todoadd         # ToDo Add Details Activity & ViewModel
    |   │   ├── adapter     # Adapter for RecyclerView
    |   │   ├── viewmodel   # ViewHolder for RecyclerView
        └── todoview         # ToDo Add Details Activity & ViewModel
    |   │   ├── adapter     # Adapter for RecyclerView
    |   │   ├── viewmodel   # ViewHolder for RecyclerView
    |
    └── utils               # Utility Classes / Common classes / Rx / display

## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)
