Project should not require any special instructions to build.
1. Open in your favorite IDE
2. mvn install to pull in all dependencies
3. Make sure you are compiling and running with Java 8
4. run the application locally in your IDE
5. Call all of the different REST methods from a tool like postman to test

Regarding unit testing, for the purpose of this project, I created a hybrid unit test/ end-to-end test because it was an easy way to test the REST portion of the code along with the only part of the code that is truly unit-testable, the function that creates the call list from a List of PhoneNumbers. This function was tested by making REST calls to insert a bunch of records and then calling the contact-list url to generate the list.

I also added a more traditional unit test for the call-list function.

I did not provide separate tests for the other functions since I found myself essentially testing the behavior of Spring itself
