# buildIt-coding-exercise

* Simple Webcrawler
* Project done using Scala on 11/7/2016
* IntelliJ IDEA EAP 2016.2 preview used for IDE
* Gradle used for dependency management

* Using (partial) Acceptance-TDD: I wrote acceptance criteria first (WebcrawlerAcceptanceTest.scala), which drove the code (first commit has the first acceptance criteria)
* (For the acceptance test to pass, we'd need to deploy the resource test website to an actual web server)
* I've used TDD to drive the development (check commit steps in the GitHub project)
* I've separated core domain logic and infrastructure (specific implementations with dependencies), following the "hexagonal architecture" implementation, aka ports and adapters
  For reference see: http://alistair.cockburn.us/Hexagonal+architecture
* The main Unit-test (WebcrawlerTest.scala) uses Mockito to mock all the infrastructure dependencies
* For now, only image resources are listed - it can be improved to add all kind of resources

See WebcrawlerAcceptanceTest for usage

Problems/limitations to solve:
* Output is currently fixed to string
* Does not work for absolute URLs for now
* Synchronous - not the best performance for links-heavy sites
* Does not handle timeout (default Source get method never times out!)

Potential improvements:
* Instead of string, return a tree of Items, and inject some some handler trait to do what's necessary with them (one implementation will be ConsoleOutputHandler.scala)
* Use a proper HTML scrapper? JSoup or something existing (instead of scala blunt XML parser)
* Make asynchronous: Use Akka with actors / Futures
* Use Akka-stream for reactive programming
* Better handling of GET: use Play WS-API as a Web client