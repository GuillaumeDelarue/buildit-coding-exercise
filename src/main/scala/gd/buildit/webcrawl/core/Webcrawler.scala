package gd.buildit.webcrawl.core

class Webcrawler(val httpClient: HttpClient, val tagFinder: TagFinder) {
  private var host = ""
  private var visitedPaths: Set[String] = Set.empty

  def crawl(startingUrl: String): String = {
    val (host, path) = reaStartingdUrl(startingUrl)
    this.host = host
    this.visitedPaths = Set.empty
    recursivelyVisitInternalLink(path)
  }

  private def recursivelyVisitInternalLink(path: String): String = {
    val sanitisedPath = if (path.startsWith("/")) path else s"/$path"
    if (visitedPaths.contains(sanitisedPath)) return ""

    visitedPaths = visitedPaths + sanitisedPath
    val content = httpClient.get(host + sanitisedPath)
    val links = tagFinder.findTags(content, "a", "href")
    val images = tagFinder.findTags(content, "img", "src")

    val stringBuilder = new StringBuilder()
    stringBuilder.append(s"Visiting: $host$sanitisedPath" +
      images.map(image => s"\nFound image: ${image.trim}").mkString("") +
      links.map(link => s"\nFound link: ${link.trim}").mkString("") +
      "\n" +
      "---\n")

    links.foreach(link => stringBuilder.append(recursivelyVisitInternalLink(link)))
    stringBuilder.toString()
  }

  private def reaStartingdUrl(urlAsString: String): (String, String) = {
    val url = new java.net.URL(urlAsString)
    (url.getProtocol + "://" + url.getHost + (if (url.getPort == -1) "" else ":" + url.getPort), url.getPath)
  }
}
