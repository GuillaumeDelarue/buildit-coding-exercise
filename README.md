# buildIt-coding-exercise

* Simple Webcrawler
* Project done using Scala/Scalatest on 11/7/2016
* IntelliJ IDEA EAP 2016.2 preview used for IDE
* Gradle used for dependency management

* Using (partial) Acceptance-TDD: I wrote acceptance criteria first (WebcrawlerAcceptanceTest.scala), which drove the code (first commit has the first acceptance criteria)
* Then used TDD to drive development
* I've separated core domain logic and infrastructure (specific implementations with dependencies), following the "octogon" implementation, aka ports and adaptors
* Domain classes are in Item.scala

See WebcrawlerAcceptanceTest for usage

Problems/limitations to solve:
* Output is currently fixed to string
* Using regexp to parse HTML is NOT a good idea. But that works in simple case
* Does not work for absolute URLs for now
* Synchronous - not the best performance for links-heavy sites
* Does not handle timeout (default Source get method never times out!)

Potential improvements:
* Instead of string, return a tree of Items, and inject some some handler trait to do what's necessary with them (one implementation will be ConsoleOutputHandler.scala)
* Use a proper HTML scrapper? JSoup or something existing (instead of regexp parser)
* Make asynchronous: Use Akka with actors / Futures
* Use Akka-stream for reactive programming
* Better handling of GET: use Play WS-API as a Web client