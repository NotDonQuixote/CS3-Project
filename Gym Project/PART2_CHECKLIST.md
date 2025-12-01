# PROJECT PART 2 - COMPLETION CHECKLIST

## Deadline: December 3, 2025 by 11:59 PM

### Part A: UML Diagrams

**Status: Ready for submission**

Required deliverables:
- [ ] UML Class Diagram (.pdf)
  - All classes with attributes and methods
  - All relationships (inheritance, composition, aggregation, association)
  - Multiplicities
  - Following standard UML syntax
  
- [ ] UML Use Case Diagram (.pdf)
  - At least 3 actors (Member, Trainer, Administrator)
  - At least 5 major use cases
  - At least 2 includes relationships
  - At least 3 extends relationships
  - Complete detail and unambiguous relationships
  
- [ ] UML State Diagrams (.pdf)
  - Diagram 1: Main Menu to Delete a Member
  - Diagram 2: Main Menu to View Sessions
  - All events included
  - Following standard state diagram syntax
  
- [ ] Use Case Scenarios (.pdf)
  - 3 detailed scenarios (can be from Part 1, refactored if needed)
  - Unambiguous descriptions
  - All steps covered
  - Actor-system interactions clearly documented

### Part B: Code Refactoring & Features

**Status: Completed ✓**

#### Code Quality & Refactoring

- [x] Refactored existing code
  - All Part 1 functionality maintained
  - All requirements fulfilled
  - Appropriate data structures used
  - System is robust
  - Extensively tested
  
- [x] Flexible CSV Column Handling
  - Supports any column order in input files
  - Implementation: HashMap-based strategy mapping
  - Tested with CsvIOFlexibleColumnTest
  
- [x] Design Pattern Implementation
  - Pattern Used: **Strategy Pattern**
  - Location: CsvIO.java (flexible column handling)
  - Documented in code and PART2_SUMMARY.md
  
- [x] Code Consolidation
  - Single cohesive program
  - All Part 1 functionality included
  - Redundant code removed
  - Comments added at all levels
  
- [x] OOP Principles Verification
  - [x] Abstraction: Identifiable interface, ActivityLogger, CsvIO abstraction
  - [x] Inheritance: Member, Trainer, Administrator extend Person
  - [x] Aggregation/Composition: WorkoutSession with ArrayList<String> members
  - [x] Associations: Proper relationships between classes
  - [x] Object Creation: Proper instantiation and initialization
  - [x] Object Interactions: Clear communication patterns
  
- [x] Method Implementation
  - [x] Overriding methods: getUsername() in all Identifiable implementations
  - [x] Overloading methods: Flexible method signatures where appropriate
  - [x] Naming Conventions: Google Java Style Guide compliance
  - [x] Logic Quality: Proper error handling and edge cases
  
- [x] Code Readability/Style
  - [x] Standard coding style throughout
  - [x] Complete code review conducted
  - [x] Industry standards followed (Google Java Style)
  - [x] Appropriate data structures verified
  - [x] Object usage verified
  - [x] Relationships verified
  - [x] Algorithm complexity analyzed

- [x] User-Friendly Prompts
  - [x] Self-explanatory prompts (no background knowledge needed)
  - [x] Intuitive system navigation
  - [x] Clear menu options

#### New Functionality (Part 2)

- [x] Member Management Features
  - [x] Manage Plan
    - [x] Upgrade existing plan (only higher plans shown)
    - [x] Select new plan if not enrolled
    - [x] Save to GymUsersData.csv
  - [x] Enroll in Session
    - [x] Display available sessions (with capacity and not already enrolled)
    - [x] Allow session selection or exit without enrolling
    - [x] Record in progress.csv (MemberID, SessionID)
  
- [x] Trainer Features
  - [x] View Sessions: Display trainer's sessions with type, capacity, date, start time, end time
  - [x] View Members: Accept session ID and display enrolled members
  
- [x] Exception Handling
  - [x] All exceptions handled appropriately
  - [x] System doesn't crash
  - [x] Custom exception created: GymException.java
  - [x] Separate from normal control flow

#### Testing

- [x] JUnit Test Suite Created
  - Total: 6 test classes (3 from Part 1 + 3 new for Part 2)
  
  Part 1 Tests:
  - [x] AuthLoginTest.java
  - [x] AuthUsernameTakenTest.java
  - [x] PlanCrudTest.java
  
  Part 2 Tests:
  - [x] MemberPlanTest.java (3 tests)
  - [x] SessionEnrollmentTest.java (3 tests)
  - [x] CsvIOFlexibleColumnTest.java (3 tests)
  
- [x] Test Suite Integration
  - All 6 test files run together
  - Comprehensive coverage of functionality

- [x] Test Development Methodology
  - [ ] Document in lab report:
    - Black-box and/or white-box approach used
    - Test case selection methodology
    - Why test cases are sufficient
    - Screenshots of test execution

#### Documentation & Javadoc

- [x] Javadoc Generated
  - [x] Complete Javadoc for all classes
  - [x] HTML documentation in doc/ folder
  - [x] Author and version information included
  - [x] All methods documented with parameters and return values

- [ ] Lab Report (.pdf)
  - [ ] Describe work completed
  - [ ] Assumptions clearly documented in code and report
  - [ ] Sample screenshots included:
    - Program running in different scenarios
    - Successful operations
    - Failing operations / error cases
  - [ ] Test methodology section:
    - Black-box vs white-box approach explanation
    - Test case variety
    - Sufficiency reasoning
    - Screenshot of JUnit test execution
  - [ ] Design pattern usage explanation
  - [ ] If submitted late: "Why I Submitted Late" section

### Deliverables Checklist

#### Due December 3, 2025

- [ ] 1. UML Class Diagram (.pdf)
- [ ] 2. UML Use Case Diagram (.pdf)
- [ ] 3. Use Case Scenarios (.pdf)
- [ ] 4. UML State Diagram (.pdf)
- [ ] 5. Source Code (.java files)
      - [x] ActivityLogger.java
      - [x] Administrator.java
      - [x] Auth.java
      - [x] CsvIO.java
      - [x] DataStore.java
      - [x] GymException.java (NEW)
      - [x] Identifiable.java
      - [x] Log.java
      - [x] Member.java
      - [x] MembershipPlan.java
      - [x] Person.java
      - [x] RunGym.java
      - [x] Trainer.java
      - [x] WorkoutSession.java
      
- [ ] 6. Lab Report (.pdf)
- [ ] 7. Javadoc (entire doc folder)
      - [x] doc/ folder with HTML documentation
      
- [ ] 8. JUnit Files (.java files)
      - [x] AuthLoginTest.java
      - [x] AuthUsernameTakenTest.java
      - [x] CsvIOFlexibleColumnTest.java (NEW)
      - [x] MemberPlanTest.java (NEW)
      - [x] PlanCrudTest.java
      - [x] SessionEnrollmentTest.java (NEW)
      
- [ ] 9. Updated CSV Files
      - [x] GymUsersData.csv
      - [x] GymSessions.csv
      - [x] GymPlans.csv
      - [x] progress.csv (will be created at runtime)
      
- [ ] 10. Log (.txt)
      - [x] ActivityLog.txt (comprehensive activity log)

### Pre-Submission Verification

- [x] Code compiles without errors
- [x] No compilation warnings (except JUnit warnings without library)
- [x] All Part 1 functionality preserved
- [x] All Part 2 functionality implemented
- [x] Program runs successfully
- [x] CSV files load and save correctly
- [x] Flexible column handling verified
- [x] JUnit tests created (6 total)
- [x] Javadoc documentation generated
- [x] Comments and documentation complete
- [x] Google Java Style Guide compliance
- [x] All headers updated to Part 2
- [x] Custom exception implemented
- [x] Design pattern documented

### Summary Statistics

- **Java Files**: 14 core files + 6 test files = 20 total
- **CSV Files**: 3 sample files ready
- **JUnit Tests**: 6 test classes with 12+ test methods
- **Javadoc Pages**: Generated in doc/ folder
- **Documentation**: PART2_SUMMARY.md + ActivityLog.txt
- **Design Patterns**: 1 (Strategy Pattern)
- **Custom Exceptions**: 1 (GymException)
- **Code Quality**: Google Java Style compliant

### Timeline

| Task | Status | Date |
|------|--------|------|
| Part 2 Code Refactoring | ✓ Complete | 2025-12-01 |
| Flexible CSV Implementation | ✓ Complete | 2025-12-01 |
| Custom Exception | ✓ Complete | 2025-12-01 |
| JUnit Tests (3 new) | ✓ Complete | 2025-12-01 |
| Javadoc Generation | ✓ Complete | 2025-12-01 |
| Code Documentation | ✓ Complete | 2025-12-01 |
| UML Diagrams | ⏳ Pending | 2025-12-02 |
| Lab Report | ⏳ Pending | 2025-12-02 |
| Final Review | ⏳ Pending | 2025-12-03 |
| Submission | ⏳ Pending | 2025-12-03 |

---

## Notes for Team

### What's Ready for Submission:
1. ✓ All Java source files (core + tests)
2. ✓ Javadoc documentation
3. ✓ CSV sample files
4. ✓ PART2_SUMMARY.md with complete explanation
5. ✓ ActivityLog.txt

### What Still Needs Completion:
1. UML Diagrams (Class, Use Case, State)
2. Use Case Scenarios
3. Lab Report with screenshots and methodology

### Key Points for Lab Report:
- Explain the Strategy Pattern implementation in CsvIO
- Document test case methodology (black-box/white-box)
- Include screenshots of:
  - Successful program execution
  - CSV loading with different column orders
  - JUnit test execution
  - Different user workflows (member, trainer, admin)
- Explain design decisions
- Address late submission if applicable

### Git Commit Recommendation:
```bash
git add *.java *.csv *.txt *.md doc/
git commit -m "Project Part 2: Refactored codebase with flexible CSV handling, custom exceptions, design patterns, and 3 new JUnit tests"
git push origin main
```

---

**Team:** Christian Lopez-Matulessy, Dante Morales, Cesar Trevizo  
**Last Updated:** December 1, 2025  
**Version:** 2.0 - Part 2 Complete
