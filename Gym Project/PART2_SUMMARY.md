# Gym Management System - Project Part 2

## Overview
This document summarizes the refactoring and enhancements made to the Gym Management System for Project Part 2 of CS 3331 - Advanced Object-Oriented Programming at The University of Texas at El Paso.

## Key Changes and Improvements

### 1. Flexible CSV Column Handling (Strategy Pattern)
**File: CsvIO.java**

The CSV I/O system has been refactored to support flexible column ordering in input files. This means CSV files with columns in any order will now be parsed correctly.

**Implementation Details:**
- Created `createHeaderMap()` method that builds a map of column names to indices
- Implemented `getColumnValue()` method that safely retrieves values using the header map
- Updated `loadUsers()`, `loadSessions()`, and `loadPlans()` to use the flexible column mapping
- Applied the **Strategy Pattern** where column mapping strategy is independent of parsing logic

**Benefits:**
- Input files no longer require a specific column order
- Users can add/remove columns without breaking the system
- More robust and maintainable code

**Example:**
```
Original format: ID,First Name,Last Name,Username,Password,User Type,Membership,Start Date,End Date,Speciality
Also works:     User Type,Password,Last Name,First Name,Username,ID,Speciality,Membership,Start Date,End Date
```

### 2. Custom Exception Class
**File: GymException.java**

Created a custom exception class to handle gym-specific errors separately from generic Java exceptions.

```java
public class GymException extends Exception {
    public GymException(String message) {  }
    public GymException(String message, Throwable cause) {  }
}
```

**Usage:** This can be thrown in critical operations to provide more meaningful error handling.

### 3. Comprehensive JavaDoc Documentation
All Java files have been updated with detailed Javadoc comments including:

- **Class-level documentation** explaining purpose, inheritance relationships, and design patterns
- **Field documentation** with explanations of each attribute
- **Method documentation** with parameters, return values, and descriptions
- **Version information** (2.0) for Part 2

**Examples:**
- `DataStore`: Documents the static arrays and their purposes
- `CsvIO`: Explains the flexible column mapping strategy
- `WorkoutSession`: Describes composition with ArrayList for member management
- All entity classes document their relationships (inheritance, composition, aggregation)

### 4. Enhanced Object-Oriented Programming (OOP) Principles

#### Inheritance
- `Member`, `Trainer`, `Administrator` extend `Person`
- Share common attributes (username, password, firstName, lastName)
- Override behavior as needed through specialized attributes

#### Composition/Aggregation
- `WorkoutSession` uses ArrayList<String> to manage enrolled members
- `CsvIO` handles complex file I/O without requiring tight coupling

#### Associations
- `WorkoutSession` references `Trainer` by username
- `Member` references `MembershipPlan` by name
- `Auth` manages associations between users and credentials

#### Abstraction
- `Identifiable` interface provides common identification contract
- `ActivityLogger` abstracts logging implementation
- `CsvIO` abstracts data persistence

#### Polymorphism
- `Identifiable.getUsername()` implemented by Member, Trainer, Administrator
- `Person` base class allows polymorphic handling of different user types

### 5. New JUnit Test Cases

Three new comprehensive test cases have been created:

#### A. MemberPlanTest.java
Tests membership plan functionality:
- `testMemberWithNoPlan()`: Verifies members can exist without a plan
- `testMemberWithPlan()`: Confirms plan assignment works
- `testMemberUpgradePlan()`: Tests plan upgrade functionality

#### B. SessionEnrollmentTest.java
Tests session enrollment and capacity management:
- `testSessionWithAvailableCapacity()`: Verifies session capacity tracking
- `testMemberEnrollInSession()`: Tests member enrollment
- `testSessionAtCapacity()`: Confirms capacity constraints are enforced

#### C. CsvIOFlexibleColumnTest.java
Tests the flexible column handling feature:
- `testLoadUsersWithDifferentColumnOrder()`: Verifies correct parsing with reordered columns
- `testSaveAndReloadUsers()`: Tests save/reload cycle maintains data integrity
- `testLoadSessionsWithDifferentColumnOrder()`: Confirms session loading works with flexible columns

**Total Test Coverage:**
- Part 1 tests (3): AuthLoginTest, AuthUsernameTakenTest, PlanCrudTest
- Part 2 tests (3): MemberPlanTest, SessionEnrollmentTest, CsvIOFlexibleColumnTest
- **Total: 6 test classes**

### 6. Code Quality and Refactoring

#### Comments and Documentation
- Added meaningful comments throughout all methods
- Documented assumptions and design decisions
- Included algorithm and complexity notes

#### Error Handling
- Replaced generic exception handling with specific error messages
- All parse errors are logged but don't crash the system
- Graceful degradation for malformed CSV files

#### Data Structures
- Appropriate use of arrays for fixed-size collections
- ArrayList for dynamic member enrollment in sessions
- HashMap for flexible CSV column mapping

#### Naming Conventions
- Consistent naming following Google Java Style Guide
- Clear, descriptive method and variable names
- Consistent camelCase for methods and variables

### 7. Key Files Updated

| File | Changes |
|------|---------|
| CsvIO.java | Complete refactor with flexible column handling, Strategy pattern, comprehensive Javadoc |
| RunGym.java | Updated header, enhanced comments for complex logic |
| Auth.java | Added detailed Javadoc for all authentication methods |
| Member.java | Added Javadoc for membership-related fields |
| Trainer.java | Added getters documentation and field descriptions |
| Administrator.java | Added class-level and method-level Javadoc |
| WorkoutSession.java | Documented composition pattern with ArrayList |
| MembershipPlan.java | Added field documentation |
| Person.java | Base class documentation explaining inheritance |
| DataStore.java | Detailed field documentation for all arrays |
| ActivityLogger.java | Added logging method documentation |
| Identifiable.java | Interface contract documentation |
| All others | Updated headers to Part 2 and date |

### 8. New Files Created

- **GymException.java**: Custom exception class for gym-specific errors
- **MemberPlanTest.java**: JUnit tests for membership plan functionality
- **SessionEnrollmentTest.java**: JUnit tests for session enrollment
- **CsvIOFlexibleColumnTest.java**: JUnit tests for flexible CSV parsing
- **GymUsersData.csv**: Sample user data with standard column order
- **GymSessions.csv**: Sample session data
- **GymPlans.csv**: Sample membership plan data

## Design Pattern Implementation

### Strategy Pattern (CSV Loading)
The flexible column handling in CsvIO implements the Strategy Pattern:
- **Context**: CsvIO class
- **Strategy Interface**: Column mapping strategy (createHeaderMap/getColumnValue)
- **Concrete Strategy**: HashMap-based column indexing
- **Benefit**: Columns can be in any order without changing parsing logic

## Compilation Status
✅ **All core classes compile successfully without errors:**
- RunGym.java
- Auth.java
- CsvIO.java
- DataStore.java
- Person.java
- Member.java
- Trainer.java
- Administrator.java
- WorkoutSession.java
- MembershipPlan.java
- ActivityLogger.java
- Identifiable.java
- GymException.java
- Log.java

## Testing Notes

JUnit tests require the JUnit 5 library to compile and run:
```bash
javac -cp junit-jupiter-api-5.x.x.jar *.java
java -cp .:junit-jupiter-api-5.x.x.jar:junit-platform-console-standalone-1.x.x.jar \
  org.junit.platform.console.ConsoleLauncher --scan-classpath
```

## Future Enhancements

Potential improvements for consideration:
1. Implement transaction support for CSV operations
2. Add database backend as alternative to CSV
3. Enhance member enrollment with date/time tracking
4. Add payment processing for membership plans
5. Implement more sophisticated session scheduling
6. Add email notifications for enrollments
7. Create user interface (GUI or web-based)

## Summary

Project Part 2 successfully refactored the codebase with:
- ✅ Flexible CSV column handling using Strategy pattern
- ✅ Custom exception handling
- ✅ Comprehensive Javadoc documentation
- ✅ 3 additional JUnit test cases (6 total)
- ✅ Enhanced OOP principles (inheritance, composition, abstraction, polymorphism)
- ✅ Industry-standard coding style and comments
- ✅ Robust error handling
- ✅ Sample data files for testing

All code follows the Google Java Style Guide and maintains backward compatibility with Part 1 functionality.

---

**Team:** Christian Lopez-Matulessy, Dante Morales, Cesar Trevizo  
**Date:** December 1, 2025  
**Course:** CS 3331 - Advanced Object-Oriented Programming  
**Instructor:** Dr. Bhanukiran Gurijala
