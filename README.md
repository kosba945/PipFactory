# Pipe Factory Management Mobile Application

A comprehensive mobile application for managing pipe manufacturing factory operations, built with Android Jetpack Compose and modern architecture patterns.

## Features

### 🔐 Authentication & User Management
- JWT-based authentication
- Role-based access control (Admin, Factory Manager, Sales Manager, Production Supervisor, Warehouse Staff)
- Secure password hashing

### 📦 Inventory Management
- Add, edit, and delete inventory items
- Track raw materials and finished products
- Low stock alerts
- Bilingual support (Arabic/English)
- Real-time quantity updates

### 🏭 Production Management
- Create and manage production orders
- Track material usage
- Production cost calculation
- Order status tracking

### 💰 Sales & Invoicing
- Create invoices with customer details
- Automatic invoice numbering
- PDF export functionality
- Profit calculation
- Customer management

### 🚚 Delivery Management
- Link deliveries to invoices
- Track delivery status
- Driver and vehicle information
- Delivery cost tracking

### 💸 Expense Management
- Categorized expense tracking
- Labor, rent, utilities, materials expenses
- Date-based filtering
- Expense reporting

### 📊 Reports & Analytics
- Inventory reports
- Production reports
- Sales analytics
- Profit/loss calculations
- Export to CSV/PDF

### 🔄 Data Synchronization
- Cloud server synchronization
- Offline support with local Room database
- Automatic sync when connected
- Conflict resolution

## Technical Stack

- **Frontend**: Android Jetpack Compose
- **Architecture**: MVVM with Repository pattern
- **Dependency Injection**: Hilt
- **Local Database**: Room
- **Networking**: Retrofit + OkHttp
- **Authentication**: JWT
- **PDF Generation**: iText
- **Navigation**: Navigation Compose
- **State Management**: StateFlow/Flow

## Project Structure

```
app/src/main/java/com/example/pipfactorymanagement/
├── data/
│   ├── database/          # Room database and DAOs
│   ├── model/            # Data models and entities
│   ├── network/          # API services and networking
│   └── repository/       # Repository implementations
├── di/                   # Dependency injection modules
├── ui/
│   ├── components/       # Reusable UI components
│   ├── navigation/       # Navigation setup
│   ├── screens/          # Screen composables
│   └── theme/           # App theming
└── MainActivity.kt       # Main activity
```


## Configuration

### API Configuration
Update the base URL in `NetworkModule.kt`:
```kotlin
private const val BASE_URL = "https://your-api-server.com/api/"
```

### Database
The app uses Room database with automatic migrations. Database version is currently set to 1.

## Features in Development

- [ ] Barcode/QR code scanning
- [ ] Advanced reporting dashboard
- [ ] Push notifications
- [ ] Multi-language support
- [ ] Dark theme
- [ ] Data backup/restore

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support and questions, please contact the development team.

