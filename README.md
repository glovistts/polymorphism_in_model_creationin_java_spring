# Inheritance and Auditing with JPA Entities in Spring Boot

This project showcases the implementation of inheritance in JPA entities, leveraging Spring Data JPA's auditing capabilities to track creation and updates of entities. The project defines a `BaseEntity` with common fields and audit annotations, allowing derived entities to automatically inherit those fields.

![My Image](https://github.com/glovistts/polymorphism_in_model_creationin_java_spring/blob/master/Dark%20Navy%20%26%20Green%20Geometric%20Modern%20Computer%20Programmer%20What%20is%20Programming%20for%20Instagram%20Post%20(1).png)

## Features

- **Inheritance with JPA Entities**: Demonstrates how to use `@MappedSuperclass` for inheritance, so common fields and behavior can be shared across multiple entities.
- **Auditing with Spring Data JPA**: Tracks `createdAt`, `updatedAt`, `createdBy`, and `updatedBy` fields using the `@CreatedDate`, `@LastModifiedDate`, `@CreatedBy`, and `@LastModifiedBy` annotations.
- **Versioning and Status Management**: Includes fields for optimistic locking (`@Version`) and a custom status field (`RecordStatus`).
- **Hibernate Envers Integration**: Auditing is implemented using Hibernate Envers to track changes and maintain entity history.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Entities](#entities)
- [Auditing Setup](#auditing-setup)
- [License](#license)

![My Image](https://github.com/glovistts/polymorphism_in_model_creationin_java_spring/blob/master/OrderEntity.png)


## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/inheritance-auditing-jpa-springboot.git
    ```
2. Navigate to the project directory:
    ```bash
    cd inheritance-auditing-jpa-springboot
    ```
3. Build the project using Maven:
    ```bash
    mvn clean install
    ```
4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## Usage

### Entities

1. **BaseEntity**: 
    An abstract class that holds common fields like `id`, `createdAt`, `updatedAt`, `createdBy`, `updatedBy`, `version`, and `status`. It uses annotations like `@MappedSuperclass` and `@Audited` to allow inheritance and auditing.

    ```java
    @MappedSuperclass
    @Audited
    @Getter
    @Setter
    public abstract class BaseEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @CreatedDate
        @Column(name = "created_at", updatable = false)
        private LocalDateTime createdAt;

        @LastModifiedDate
        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        @Column(name = "created_by", updatable = false)
        private String createdBy;

        @Column(name = "updated_by")
        private String updatedBy;

        @Version
        private Long version;

        @Column(name = "status")
        private RecordStatus status;

        @PrePersist
        protected void onCreate() {
            this.createdAt = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
        }

        @PreUpdate
        protected void onUpdate() {
            this.updatedAt = LocalDateTime.now();
        }
    }
    ```

2. **ProductEntity**:
    Inherits from `BaseEntity`, with additional fields for product-specific information like `name`, `description`, and `price`.

    ```java
    @Entity
    @Table(name = "products")
    @Getter
    @Setter
    public class ProductEntity extends BaseEntity {
        private String name;
        private String description;
        private Double price;
    }
    ```

3. **OrderEntity**:
    Extends `BaseEntity` with fields specific to orders, such as `orderNumber`, `customerId`, and `totalAmount`.

    ```java
    @Entity
    @Table(name = "orders")
    @Getter
    @Setter
    public class OrderEntity extends BaseEntity {
        private String orderNumber;
        private Long customerId;
        private Double totalAmount;
    }
    ```

### Auditing Setup

To enable auditing, the following configurations are included:

1. Add the Hibernate Envers dependency in `pom.xml`:

    ```xml
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-envers</artifactId>
    </dependency>
    ```

2. Enable JPA auditing in the main application class:

    ```java
    @SpringBootApplication
    @EnableJpaAuditing
    public class MyApplication {
        public static void main(String[] args) {
            SpringApplication.run(MyApplication.class, args);
        }
    }
    ```

3. Create an `AuditorAware` implementation for populating `createdBy` and `updatedBy` fields (example uses a placeholder):

    ```java
    public class SpringSecurityAuditorAware implements AuditorAware<String> {
        @Override
        public Optional<String> getCurrentAuditor() {
            // Replace with actual logic to fetch the current user
            return Optional.of("AdminUser");
        }
    }
    ```

4. Register the `AuditorAware` bean in a configuration class:

    ```java
    @Configuration
    public class AuditConfig {
        @Bean
        public AuditorAware<String> auditorProvider() {
            return new SpringSecurityAuditorAware();
        }
    }
    ```

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
