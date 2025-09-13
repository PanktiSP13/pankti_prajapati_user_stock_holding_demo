# Portfolio Holdings Android App

## Overview

This is a modern Android application built with **Jetpack Compose** that displays a user's portfolio
holdings with real-time profit/loss calculations. The app follows **Clean Architecture** principles
and implements **MVVM pattern** with **Hilt dependency injection**.

## Features Implemented

###  Core Functionality

- **Portfolio Holdings Display**: Shows individual stock holdings with symbol, quantity, LTP, and
  P&L
- **Real-time Calculations**: Automatically calculates:
    - Current value (LTP × Quantity)
    - Investment amount (Average Price × Quantity)
    - Profit/Loss (Current Value - Investment)
    - Today's P&L ((Close - LTP) × Quantity)
- **Portfolio Summary**: Expandable bottom sheet showing total portfolio metrics
- **Currency Formatting**: All amounts displayed in Indian Rupee format (₹)

#### **Clean Architecture Layers:**

1. **Presentation Layer** (`presentation/`)
    - UI components
    - Theme and styling

2. **Domain Layer** (`domain/`)
    - Business logic and use cases
    - Data models with computed properties
    - Repository interfaces
    - UI state definitions

3. **Data Layer** (`data/`)
    - Repository implementations
    - Network API integration
    - Network utilities

#### **Key Technologies:**

- **Jetpack Compose**: Modern declarative UI framework
- **Hilt**: Dependency injection framework
- **Retrofit**: HTTP client for API calls
- **Coroutines & Flow**: Asynchronous programming and reactive streams
- **StateFlow**: State management in ViewModels

## Project Structure

```
app/src/main/java/com/pinu/pankti_prajapapati_demo_project/
├── data/
│   ├── network/           # API interfaces and network setup
│   └── repositoryImpl/    # Repository implementations
├── domain/
│   ├── model/            # Data models
│   ├── repository/       # Repository interfaces
│   ├── states/           # UI state definitions
│   └── viewmodels/       # ViewModels
├── presentation/
│   ├── theme/            # App theming
│   └── ui/
│       ├── components/   # Reusable UI components
│       ├── screens/      # Screen composables
│       └── utils/        # UI utilities
└── di/                   # Dependency injection modules
```

