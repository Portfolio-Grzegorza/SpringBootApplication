# Exercise App
A spring Boot application for managing users, exercise lists and exercise items.
Data is stored in a MySQL database and can be viewed using phpMyAdmin.

### Features:
- User registration with email and password
- One user can have multiple exercise lists
- Each list contains multiple exercise items
- Automatic data initialization at startup

### Technologies Used:
- Java 21
- Spring Boot 3.5
- Spring Data JPA
- MySQL
- Hibernate ORM
- XAMPP (Apache + MySQL)
- phpMyAdmin (for DB inspection)

### How to start
1. Instal XAMPP (v3.3.0 recommended)
2. Start Services in XAMPP
- Open XAMPP Control Panel
- Click Start next to:
  - Apache
  - MySQL
3. Access phpMyAdmin
- In the XAMPP panel, click Admin next to MySQL
- This opens phpMyAdmin in your browser: http://localhost/phpmyadmin
4. Create Database
- Click "New"
- Create a new database: exercise_app
- Leave default collation and click Create
5. Configure application.properties
Ensure your Spring Boot project has the following settings:
   spring.datasource.url=jdbc:mysql://localhost:3306/exercise_app?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=  # your MySQL password, default is empty
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
6. Running the application
   1. Open Maven
   2. Reload All Maven Projects
   3. Open and run clean in Lifecycle folder
   4. Open and run install in Lifecycle folder
   5. Build and run the Spring Boot project from your IDE (IntelliJ)
   6. On startup:
      - Tables will be created automatically
      - Sample user and exercise data will be inserted
      - Console will show SQL logs
7. Viewing Data in Browser
- Open XAMPP and click Admin next to MySQL or visit: http://localhost/phpmyadmin
- Open exercise_app database
- Browse tables: exercise_user, exercise_list, item
