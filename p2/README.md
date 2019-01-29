# Phase 2 Starting Project

This is a base project for starting phase 2.

It contains a Gradle multi-project similar to the one used in lab 9.

## The Sub-Projects

* `common` --- Domain classes.
* `customer-ajax` --- The AJAX client for creating customer accounts.  This is based on the AngularJS starting project from lab 3.
* `customer-service` --- The customer accounts service project from phase 1.
* `sales-service` --- The sales service from phase 1.
* `router` --- The router project.

These projects all have the appropiate libraries added to them.

### Migrating the Phase 1 Projects

It will make things easier for you to have your phase 1 projects in the same multi-project as the phase 2 projects.  Multi-projects are easier to work with, and you will need to be able to share the domain classes across several projects.

Copy the contents of the `src` folders from your phase 1 projects into the appropriate `src` folder of the phase 2 project.  If you have tests in your phase 1 projects then also copy the content of thes  `tests` folders.

#### Main Classes

The `build.gradle` file for each of the two phase 1 services projects has a `mainClass` attribute that is hard-coded to `server.Server`.  If the name of your main classes in your phase 1 services do not match this then change the `mainClass` attribute (near the bottom of each file) in the `build.gradle` to match your server class name.

This means that you can run a service by right clicking it --- you don't even need to have it open.  This is much faster than poking around in your various projects looking for the main classes.

#### Domain Classes

There are two `Customer` domain classes --- one in each of the two services.  You should refactor/rename the customer accounts  service version of the class to `CustomerAccount` so that it is easy to tell which class is which.

The domain classes need to be shared between the service and router projects.  To avoid having multiple copies of the domain classes you should move the domain classes from the service projects into the `common` project.

#### Test the Phase 1 Services

Your phase 1 services should still function properly from inside the multi-project after this migration.  Run and test your services to verify that everything works properly.
