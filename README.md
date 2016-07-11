# buildIt-coding-exercise

* Simple Webcrawler
* Project done using Scala/Scalatest on 11/7/2016
* IntelliJ IDEA EAP 2016.2 preview used for IDE
* Gradle used for dependency management

* Using (partial) Acceptance-TDD: I wrote acceptance criteria first, which drove the code (first commit has the first acceptance criteria)
* Then used TDD to drive development

Problems/limitations to solve:
* Output is currently fixed to string
* Synchronous - not the best performance for links-heavy sites
* Does not handle timeout (default Source get method never times out!)

Potential improvements:
* Instead of string, return a tree of objects, and some handler trait to do what's necessary with them
* Make asynchronous: Use Akka with actors / Futures
* Use Akka-stream for reactive programming
* Better handling of GET: use Play WS-API as a Web client