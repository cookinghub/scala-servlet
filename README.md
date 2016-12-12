# scala-servlet

Just fooling around with Scala and the Servlet model... for fun.

### Description
A tiny http toolkit written in Scala for developing HTTP based services on top of a Java servlet.

It supports both synchronous and asynchronous servlet request handling and does not rely on any HTTP or REST library.

The implemented approach is somewhat inspired by the Play Framework with the notions of Action controller and Router.

Check the example package to see some samples using the toolkit.

### Pre-requisites
 * Install [Scala 2.11.x](https://www.scala-lang.org/download/)
 * Install [SBT 0.13.x](http://www.scala-sbt.org/download.html)

### Usage  
 * Compile with SBT
```bash
$ sbt compile
```
 * Run with [Xsbt Web Plugin](https://github.com/earldouglas/xsbt-web-plugin)
```bash
$ sbt
$ > jetty:start
```
  * Note: use '~jetty:start' for some hot reload upon code modifications

### Limitations

Quite a few ;)

No support for URI parameters parsing (e.g. /users/{userId}). Instead use query string parameters (e.g. /users?userId={userId}).
