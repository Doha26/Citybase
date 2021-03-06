<p align="center">
    <img width="90" height="90" src="https://i.ibb.co/qWx3NHL/ic-launcher-round.png">
</p>


# CityBase

>A mobile application that implement a fast runtime search algorithm to filter  
> a large json file containing json object where each object represent a city.  
> I decided to build a MVVM kotlin application named `CityBase`.
 
 ## Results
- [x] Splashscreen
- [x] Scrollable List displaying all cities in the Json file
- [x] Search feature with case insensitive
- [x] City Detail displayed on a google Map
- [x] Binary search Algorithm with good time efficiency
- [x] Documentation
- [x] Responsive and support Device rotation
- [x] Implemented Unit test to ensure that app behave as expected


##  Preview
![App-demo](./app/src/demo/demo2.png)

### Used

 - [Gson](https://github.com/google/gson)
 - [Kotlin](https://kotlinlang.org/)
 - [Kotlin DSL Plugin ( to manage dependencies )](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
 - [Android Jetpack Librairies(LiveData, ViewModel,DataBinding, Layout, Fragment)](https://developer.android.com/jetpack)
 - [Coroutines (For asynchronous background processing)](https://kotlinlang.org/docs/reference/coroutines-overview.html)
 - [Junit4 (For unit testing))](https://junit.org/junit4/)

 ## Get Started
 
 #### 1. Clone the Repo
 
 On the command prompt run the following commands
 ```sh
 $ git clone https://github.com/Doha26/Citybase.git
 
 $ import project Citybase in Android Studio IDE
 
 $ Accept SDK configuration to support your local SDK Location 
 
$ Go to app/src/debug/res/value/google_maps_api.xml and replace `YOUR_GOOGLE_MAP_KEY_HERE` with your Google maps Api key

$ Sync and build the project

 ```

  ## Dev environment
  - Android Studio 4.0.1 (Stable version)
  - JDK 1.8
  - Gradle 4.0.0

## Test Results
  - 13 of 13 Tests passed

![TestResult](./app/src/demo/tests.png)

 ### Author

*	[Pavel Foujeu](mailto:foujeupavel@gmail.com)  
   [![Linkedin: pavel-foujeu-8a8992142](https://img.shields.io/badge/-Pavel%20Foujeu%20-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/pavel-foujeu-8a8992142/)](https://www.linkedin.com/in/pavel-foujeu-8a8992142/)
   [![GitHub Doha26](https://img.shields.io/github/followers/Doha26?label=follow&style=social)](https://github.com/Doha26)


 
 ### Done with React-native
 *	[Instagram Clone ](https://github.com/Doha26/Instagram-clone)
 *	[Facebook challenge ](https://github.com/Doha26/Facebook-React-native)

