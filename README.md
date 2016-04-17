# scala-servlet

Just fooling around with Servlet and Scala...

### Description
A tiny http toolkit written in Scala for developing HTTP based services on top of a Java servlet.

It supports both synchronous and asynchronous servlet  request handling and does not rely on any HTTP or REST library.

The implemented approach is somewhat inspired by the Play Framework with the notions of Action controller and router.

Check the example package to see how to use the toolkit.

### Limitations

No support for URI parameters parsing (e.g. /user/{userId}). Only query parameters are managed.
