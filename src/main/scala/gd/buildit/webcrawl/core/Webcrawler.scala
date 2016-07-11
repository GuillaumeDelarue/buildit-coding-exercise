package gd.buildit.webcrawl.core

class Webcrawler(val httpClient: HttpClient, val tagFinder: TagFinder) {
  private var host = ""
  private var visitedPaths: Set[String] = Set.empty

  def crawl(startingUrl: String): String = {
    val (host, path) = readStartingUrl(startingUrl)
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

    val thisLinkSummary = s"Visiting: $host$sanitisedPath" +
      images.map(image => s"\nFound image: ${image.trim}").mkString("") +
      links.map(link => s"\nFound link: ${link.trim}").mkString("") +
      "\n" +
      "---\n"

    links
      .flatMap(sanitiseLinkAndIgnoreExternal)
      .foldLeft(thisLinkSummary)((string, link) => string + recursivelyVisitInternalLink(link))
  }

  private def readStartingUrl(urlAsString: String): (String, String) = {
    val url = new java.net.URL(urlAsString)
    (url.getProtocol + "://" + url.getHost + (if (url.getPort == -1) "" else ":" + url.getPort), url.getPath)
  }

  private def sanitiseLinkAndIgnoreExternal(link: String): Option[String] =
    if (!link.startsWith("http")) Option(link)
    else if (link.startsWith(host)) Option(link.replaceFirst(host, ""))
    else None
}
