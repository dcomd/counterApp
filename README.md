## Running the Application

1. Clone repository
2. Start server
   ```
   $ cd server
   $ npm install
   $ npm start
   ```
3. Run Android project in Android Studio

## Architecture Explanation


### :app

This is the presentation layer, it is responsible for what the user sees.

- **Fragment**: The Fragment is responsible to listen to the user inputs and its ViewModel outputs.
- **ViewModel**: It expects the Fragment inputs and calls the UseCases, from _domain_ module, then, it can output the data to the Fragments via LiveData observers. All the ViewModels in this project also have a Navigator.
- **Navigator**: It navigates or pops to other fragments.

---

### :domain

This is the domain layer, it holds the business rules of the applications and it is a pure java/kotlin module.

- **UseCase**: It is responsible for the business rules on the application, it talks to the repositories by dependency inversion or to another use cases.

---

### :data

This is the data layer, it does not contain any business rules, it is responsible to get data from local or remote data sources.

- **Repository**: It is just a composition of local or remote data sources, the interfaces on those are from the _domain_ module.
- **DataSource**: It is responsible to talk with the remote server or local database.

