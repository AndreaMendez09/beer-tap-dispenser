# README

## Project Structure

The project follows a Hexagonal Architecture pattern. This architectural style emphasizes separation of concerns and modularity by dividing the application into multiple layers

```
.
      ├── shared                       => Context shared utils
      │     ├── dtos                   => Shared dtos
      │     └── mapper                 => Shared DTOs mappers
      ├── app                          => Business logic layer
      │   └──Dispenser service
      ├── domain                       => Domain packages
      │   ├── actions                  => Domain Interfaces/services ports to decouple operations
      │   ├── mapper                   => DTOs mappers 
      │   ├── entity                   => Domain entities
      │   └── exception                => Domain exceptions
      └── infra                        => I/O and persistence logic
          ├── api                      => App Controllers
          │   ├── req                  => Request custom dtos
          │   ├── res                  => Response custom dtos
          │   └── Controller
          └── data                     => Repositories implementations SQL
              └── repo              
          
         
```
### Code Formatting

Formats source code using the google-java-format tool.

## External Dependencies

The following external dependencies have been added to the project:

- **Flyway DB**: Was chosen for managing database schema migrations.It provides version control for database schemas and ensures that all database instances are kept up-to-date with the latest changes.
    - Dependency: `implementation 'org.flywaydb:flyway-core'`

- **Log4j 2**: Logging framework for log messages.
    - Dependencies:
        - `implementation 'org.apache.logging.log4j:log4j-api:2.14.1'`
        - `implementation 'org.apache.logging.log4j:log4j-core:2.17.1'`

## Running the API

To run the API, follow these steps:

1. **Clone the Repository**: Clone the project repository from the source repository.

2. **Configure Database**: Ensure that the database configuration in `application.yml` is correctly set up according to your environment.



## Data Model Explanation


### dispenser Table

The `dispenser` table represents the main entity in the system, which tracks dispenser usage information. Here's a breakdown of its columns:

- `id`: Primary key of the dispenser, automatically generated using the SERIAL data type.
- `opened_at`: Timestamp indicating when the dispenser was opened.
- `closed_at`: Timestamp indicating when the dispenser was closed.
- `flow_volume`: Decimal value representing the flow volume of the dispenser.

### dispenser_log Table

The `dispenser_log` table stores log entries for dispenser usage. It contains the following columns:

- `id`: Primary key of the log entry, automatically generated using the SERIAL data type.
- `entity_id`: Foreign key referencing the `id` column of the `dispenser` table, establishing a relationship between log entries and dispensers.
- `opened_at`: Timestamp indicating when the dispenser was opened.
- `closed_at`: Timestamp indicating when the dispenser was closed.
- `flow_volume`: Decimal value representing the flow volume of the dispenser.

### Trigger Function and Trigger

A trigger function named `trigger_dispenser_update_log` is defined to manage data insertion into the `dispenser_log` table. It is executed after an update operation on the `dispenser` table. The function checks if the dispenser was closed (i.e., `NEW.closed_at IS NULL`) and if it was previously opened (`OLD.opened_at IS NOT NULL`). If both conditions are met, a new log entry is inserted into the `dispenser_log` table.

The `dispenser_update_log` trigger is associated with the `dispenser` table and executes the `trigger_dispenser_update_log` function after each update operation on the table, facilitating automatic logging of dispenser usage.

