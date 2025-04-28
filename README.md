# community-fridge-backend
# 🧊 Community Fridge Management System

A web application that connects people who have surplus food with those in need through a community fridge network. This project aims to reduce food waste and promote community sharing.

## 🚀 Features

- Users can sign up as:
  - **Donors** to share food
  - **Volunteers** to manage fridges and logistics
  - **Admins** to oversee operations
- List available food donations
- Reserve food for pickup
- Manage community fridge locations
- Notifications for new donations or pickups

## 🛠 Tech Stack

- **Backend**: Java, Spring Boot, JPA, Hibernate
- **Frontend**: React, Next Js
- **Database**: MySQL / PostgreSQL
- **Security**: Spring Security

## 🧑‍💻 Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL or PostgreSQL

### Setup

```bash
git clone https://github.com/yourusername/community-fridge-backend.git
cd community-fridge-backend
./mvnw spring-boot:run

flowchart TD
  subgraph Auth
    AuthService --> UserRepository
    AuthService --> JwtService
    AuthService --> AuthenticationManager
    UserRepository --> User
    LoginRequestDTO
    RegisterRequestDTO
    AuthResponseDTO
  end

  subgraph Food Management
    FoodItemService --> FoodItemRepository
    FoodItemService --> UserRepository
    FoodItemDTO
    FoodItemRepository --> FoodItem
    FoodItem --> User
  end

  subgraph Fridge Inventory
    FridgeItemService --> FridgeItemRepository
    FridgeItemService --> FoodItemRepository
    FridgeItemService --> FridgeRepository
    FridgeItemDTO
    FridgeItemRepository --> FridgeItem
    FridgeItem --> FoodItem
    FridgeItem --> Fridge
  end

  subgraph Fridge Management
    FridgeService --> FridgeRepository
    FridgeDTO
    FridgeRepository --> Fridge
  end

  subgraph Pickup Requests
    PickupRequestService --> PickupRequestRepository
    PickupRequestService --> FoodItemRepository
    PickupRequestService --> UserRepository
    PickupRequestRepository --> PickupRequest
    PickupRequest --> FoodItem
    PickupRequest --> User
  end

