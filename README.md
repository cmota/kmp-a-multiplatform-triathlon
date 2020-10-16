# A Multiplatform Triathlon

Kotlin Multiplatform is here! 🙌

I've created a codelab where you can follow step by step the development of android and iOS application using Kotlin Multiplatform:

👉 [Source Code](https://github.com/cmota/kmp-a-multiplatform-triathlon)

👉 [Presentation](https://speakerdeck.com/cmota/a-multiplatform-triathlon)


## Set up the environment

There are a couple of options here depending on your own preference. Recently, after KMM plugin was released to Android Studio I've changed my setup to:
* Android Studio 4.1 with KMM plugin.
* Xcode 11.7.

You can also use AppCode instead of Xcode or Intellij IDEA instead of Android Studio.

## Getting Started

### Installing CocoaPods

On Mac is really simple to install CocoaPods, just open the terminal and execute the following command:

```
sudo gem install cocoapods
```

### Compiling the project

Now that you've got your environment ready, it's time to compile the project! Don't forget to download:


1. Clone the project locally

```
git clone https://github.com/cmota/kmp-a-multiplatform-triathlon.git
```

2. Now open **Starter Project** on Android Studio (with KMM plugin installed).

3. You'll need to wait until gradle runs all the tasks. You can get up and stretch your legs a bit, this is going to take some minutes.


4. Compile and run the app. 

5. Now it's to compile for iOS! First, go to the iosApp inside the **Starter Project** folder on the command line.

```
cd kmp-a-multiplatform-triathlon/
cd Starter\ Project/
cd iosApp
```

6. Enter pod install to install all the of its dependencies

```
pod install
```

7. Now that everything is installed let's open the iosApp.xcworkspace with Xcode.

**Note:** There are two project files inside the iosApp folder. It's easier to distinguish by the icon, the one that you need to open is the one that is white (extension .xcworkspace).

8. Now click on compile and run and let's see the app running on the iOS simulator/ iPhone!

9. To compile for the web just enter the following command on terminal:

```
./gradlew web:run --continuous
```

## Libraries
- [ktor](https://github.com/ktorio/ktor)
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization)
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [kotlinx.datetime](https://github.com/Kotlin/kotlinx-datetime)
- [sqldelight](https://github.com/cashapp/sqldelight)
- [multiplatform-settings](https://github.com/russhwolf/multiplatform-settings)


## Screens

<h3 align="center">
  <img src="images/screens_android.png" alt="Android App Screens" />
  <img src="images/screens_ios.png" alt="iOS App Screens" />
  <img src="images/screens_web.png" alt="Web Screens" />
</h3>


## Other Resources

I've created a codelab where you can follow step by step the development of android and iOS application using Kotlin Multiplatform:

👉 [Codelab](https://cmota.github.io/kmp-codelabs/)

👉 [Source Code](https://github.com/cmota/kmm-a-multiplatform-adventure)

👉 [Presentation](https://speakerdeck.com/cmota/a-multiplatform-adventure)
