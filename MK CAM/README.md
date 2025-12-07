# MK CAM - Professional Android Camera App

MK CAM is a clean, modular, offline-first Android camera application built with Kotlin, CameraX, and Jetpack Compose. It features on-device AI enhancements and optional cloud processing via user-provided API keys.

## Features
- **Pro Camera**: Manual controls, RAW/JPEG/WebP, Burst, Timer.
- **AI Enhancement**: On-device TFLite models for noise reduction, sharpening.
- **Cloud Processing**: Integration with Gemini/Reva via secure API keys.
- **Star Stacking**: Advanced night-sky stacking and alignment.
- **Presets**: XML-based preset system for filters and generation prompts.
- **Privacy**: Offline-first. No data leaves the device without explicit consent.

## Project Structure
- `:app`: Main Android application (UI, Navigation, DI).
- `:core`: Business logic, Domain models, Interfaces.
- `:ai`: TFLite runtime, Cloud API clients, Preset management.
- `:ui`: Shared Compose UI components and Theme.

## Setup Instructions

### Prerequisites
- Android Studio Iguana or newer.
- JDK 17.
- Android SDK 34.

### Building
1. Open the project in Android Studio.
2. Sync Gradle with Project.
3. Run `gradlew assembleDebug` to build the APK.

### Adding API Keys
1. Launch the app.
2. Go to **Settings > API Keys**.
3. Enter your provider key (e.g., Gemini).
4. Enable "Cloud Processing" consent when prompted during usage.

## Developer Notes

### Model Replacement
The app includes TFLite model stubs in `:ai`. To use real models:
1. Place your `.tflite` files in `ai/src/main/assets/`.
2. Update `TFLiteEnhancer.kt` to load the specific model file.

### Adding Presets
Presets can be added via XML or the `prompts.txt` file.
Sample XML format:
```xml
<preset>
    <name>Cinematic Glow</name>
    <prompt>Soft glow, warm highlights...</prompt>
    <strength>0.6</strength>
</preset>
```

## Credits
Owner: Mahim
Built with ❤️ using Android Jetpack.
