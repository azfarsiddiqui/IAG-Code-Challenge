# IAG-Code-Challenge

<b>Dev enviroment:</b>
- Android Studio 2.2.2
- Build tools 25.0.2

<b>External libraries used:</b>
- <b>ButterKnife:</b> Used for injecting views in activity layout and view holders
- <b>Retrofit:</b> Used for conveniently executing (and cancelling) network requests
- <b>RxJava:</b> Used for subscribing for and observing API response and act upon it

<b>Key points:</b>
- API base url defined in app's build.gradle to make it vary conveniently, based on the build variant
- Policy Data Layer created to deal with all the Policy APIs to keep things segregated
- Policy entity has some convenient wrapper methods like 'needsRenewal()'. So that "Renewal" string is not compared from other activities/adapters.
- AppNetworkObserver serves as the base Observer to deal with network responses. Currently being used to throw an error toast whenever network request fails.
- IAGApplication class is there, but does not really have to be
- PolicyEntityTest is a JUnit test which tests sorting order required, and the expected icon resource ids for diff insurance types 
- PolicyListActivityTest is an instrumentation test. It tests if the API response was deseriailzed successfully or not, test orientation change to verify app does not crash. Also tests, RecyclerView visibility
