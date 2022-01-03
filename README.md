Validation framework support the following basic operations:
1. Notification mechanism when data is not valid
    - Summary of announcements
    - There are different ways to display notifications on the interface
2. Manipulation of setting valid by code
    - Self-test with additional code
3. Automatic valid setting operation through data binding declaration
    - Examples of using attributes in .NET
4. Can combine validations together for the same data type
    - For example, check for empty strings, strings only full of characters, etc.
5. Support regular expression matching
6. Allows creating custom validation
Validation framework is built on object classes with appropriate methods
    - Can use reflection or attribute (C#) or annotation (Java) to read information
describe the binding of the data fields.
    - Build basic classes that support validation for basic data types
    - Build processing classes to perform validation
