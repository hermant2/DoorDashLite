The following DoorDashLite project was built using Dagger2, RxJava2, Retrofit2, OkHttp3, and
Material Components.

Typically, I wouldn't modularize an application that is this small, but did so due to the
instructions that were provided for the project. That being said, I split the project up into four
different modules.

1. `app` - The main final application
2. `commonui` - Placed application styles and reusable view-related code in here (AbsItemsAdapter,
custom widgets, etc.). I did this in case different applications were created by the organization.
(i.e. a restaurant app, a consumer app, and a driver app), they could all pull their styles from the
same module to maintain a consistent look and feel across all applications.
3. `network` - Contains all code relating to APIs and HTTP clients. This is where Retrofit services
live, along with the respective API Response/Request models (currently the app only includes a single
response model).
4. `utilities` - This is where I decided to place all generic extension/helper functions that might
be used across all modules. It currently just includes extension functions for RxJava and Resources.

The application itself follows a Model-View-Presenter architecture. The View notifies the Presenter
of events. The Presenter interacts with the data source, and uses ModelMapper classes to transform
Response models into UI models. The Presenter is also responsible for telling the View specifically
what to do.

I'm excited to hear back

Trey
