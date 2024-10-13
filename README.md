# EcoMove Client and Reservation Management Module

## Project Overview
This module is part of the **EcoMove** platform, which allows customers to manage transport tickets for various means of travel, including flights, trains, and buses. It is a console-based system that enables customers to search and reserve tickets without authentication or payment processing.

## Features

### 1. Client Registration
- **Objective:** Register client information to allow ticket searches and reservations.
- **Functionality:**
  - Clients can register by providing:
    - First name
    - Last name
    - Email address
    - Phone number
  - If the client is already registered, the platform retrieves their information.
  - If the client does not exist, the platform will prompt them to create a profile.

### 2. Client Search
- **Objective:** Identify whether a client is already registered before searching or booking tickets.
- **Functionality:**
  - The system allows searching for a client by their basic information (e.g., name, email).
  - If the client does not exist, the system will offer to create a profile.

### 3. Ticket Search and Reservation
- **Objective:** Allow customers to search for and reserve transport tickets.
- **Functionality:**
  - Once a client is identified, they can search for tickets by providing:
    - Departure city
    - Destination city
    - Departure date
  - The platform displays available tickets with details such as:
    - Transport provider
    - Schedule and duration
    - Price
  - Clients can select and reserve tickets.

### 4. Reservation Management
- **Objective:** Track and manage customers' reserved tickets.
- **Functionality:**
  - Once a ticket is reserved, the reservation is saved under the client's profile.
  - Reservation details include:
    - Trip information
    - Transport companies
    - Ticket price
  - Clients can view their reservations at any time and cancel them if necessary.

## Technology Stack
This project utilizes the following technologies and concepts:
- **Java (JVM, JDK, JRE)**
- **UML** for system modeling
- **Java OOP** for object-oriented design
- **PostgreSQL** for database management
- **JDBC** for database connectivity
- **Java Time API** for handling date and time operations
- **Java Optional** to avoid null references
- **Java Stream API** for data processing
- **Java Collection API** (e.g., Map, HashMap) for data structures
- **Lambda expressions** and **functional interfaces** to simplify code
- **Anonymous and inner classes** to enhance modularity
