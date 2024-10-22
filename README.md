# Car Rental Management System

This project is developed to manage car rental operations. Users can manage cars, brands, and models, make reservations, and track these reservations.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributors](#contributors)
- [License](#license)

## Installation

To set up the project locally, follow these steps:

1. Clone the repository:
    ```bash
    git clone https://github.com/YOUR-USERNAME/YOUR-REPOSITORY.git
    cd YOUR-REPOSITORY
    ```

2. Install the necessary dependencies (e.g., if using Maven):
    ```bash
    mvn install
    ```

3. Set up and configure the database:
    - Configure the database connection settings in the `src/main/resources/application.properties` file.
    - Create the necessary tables and insert sample data.

## Usage

To run the project:

1. Start the application:
    ```bash
    mvn spring-boot:run
    ```

2. Open the following URL in your web browser:
    ```
    http://localhost:8080
    ```

## Project Structure

This project includes the following main classes and packages:

### Business
- `BrandManager`
- `CarManager`
- `ModelManager`
- `ReservationManager`
- `UserManager`

### DAO
- `BrandDao`
- `CarDao`
- `ConnectionDB`
- `ModelDao`
- `ReservationDao`
- `UserDao`

### Entity
- `Brand`
- `Car`
- `Model`
- `Reservation`
- `User`

### View
- `AdminView`
- `BrandForm`
- `BrandFrame`
- `CarForm`
- `CarListFrame`
- `LoginFrame`
- `MainMenuFrame`
- `ModelForm`
- `ModelFrame`
- `ReservationForm`
- `ReservationFrame`

## License

This project is licensed under the MIT License. For more information, see the `LICENSE` file.



