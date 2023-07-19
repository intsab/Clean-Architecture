# Project Title

Briefly describe your Android project here, highlighting its key features and functionalities.

## Table of Contents

- [Introduction](#introduction)
- [Architecture](#architecture)
- [Libraries and Technologies](#libraries-and-technologies)
- [Setup and Installation](#setup-and-installation)
- [Features](#features)


## Introduction

In this section, provide a more detailed introduction to your project. Explain its purpose, main functionalities, and any unique aspects that make it stand out. Also, mention the goals of your project and the problem it aims to solve.

## Architecture

Explain the architecture you've used in your Android application. You mentioned Clean Architecture with MVVM, so you can elaborate on that. Discuss the separation of concerns, how data flows through different layers, and the advantages it provides.

## Libraries and Technologies

List and briefly describe the major libraries and technologies used in your project. Here are some of the libraries you mentioned:

- **MVVM**: Describe how MVVM (Model-View-ViewModel) pattern is utilized in your project and its benefits in terms of maintainability and testability.

- **Dagger 2**: Explain how Dagger 2 is used for dependency injection and how it helps in managing object creation and dependency relationships.

- **Coroutines**: Describe how Coroutines are utilized for handling asynchronous operations, such as network calls, in a more concise and structured way.

- **Retrofit**: Explain how Retrofit is used for making network requests and handling API communication.

- **Glide**: Describe how Glide is used for efficient image loading and caching in your application.

- **Navigation Graph**: Explain how Navigation Graph is used for managing navigation between different fragments and activities in your app.

- **Data Binding**: Describe how Data Binding is used to bind UI components in layouts directly to data sources and how it simplifies UI updates.

- **Custom Views**: If you've created any custom views, explain their purpose and usage.

Feel free to add any other libraries or technologies you're using in your project.

## Features

### Currency Conversion

- The app provides a user-friendly interface for converting currencies.
- Users can select the source currency and the target currency from a list of supported currencies.
- Upon entering the desired amount, the app instantly displays the converted amount.
- Users can easily switch between different currencies to get real-time conversion rates.

### Real-time Exchange Rates

- The app fetches the latest exchange rates through API calls from a reliable source.
- Users can be confident that the rates they see are up-to-date and accurate.
- Exchange rates are automatically updated at regular intervals to ensure currency conversions are always based on the latest data.

### Offline Mode

- The app stores the most recent exchange rates in a local database.
- When users are offline or have limited internet connectivity, they can still perform currency conversions using the last fetched rates.
- The app intelligently switches to offline mode when the device is not connected to the internet, providing a seamless user experience.

### Automatic Rate Updates

- The app features an automatic rate update mechanism, which refreshes the exchange rates in the background.
- This ensures that users always have access to the most current rates without any manual intervention.
- Automatic rate updates also help in conserving data usage and battery life.

### User-friendly Interface

- The app's intuitive interface allows users to convert currencies effortlessly.
- Clear and concise design elements make navigation and conversion a breeze.
- Interactive visual elements provide a delightful user experience.

### Personalization

- Users can customize the app by selecting their preferred base currency for conversions.
- The app remembers the user's settings for future use, creating a personalized experience.

### Background Sync

- The app efficiently synchronizes exchange rates in the background, ensuring users always have access to the latest data whenever they open the app.
- Background syncs are designed to be non-intrusive, allowing users to continue using the app seamlessly.


