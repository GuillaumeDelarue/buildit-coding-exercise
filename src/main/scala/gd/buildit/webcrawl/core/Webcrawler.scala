package gd.buildit.webcrawl.core

import scala.annotation.tailrec

class Webcrawler(val httpClient: HttpClient, val tagFinder: TagFinder) {
  var host = ""

  def crawl(startingUrl: String): String = {
    val (host, path) = readUrl(startingUrl)
    println(s"HOST = $host, PATH = $path")
    this.host = host
    recursivelyVisitInternalLink(path)
  }

  private def recursivelyVisitInternalLink(path: String): String = {
    val pathWithSlash = if (path.startsWith("/")) path else s"/$path"
    val content = httpClient.get(host + pathWithSlash)
    val links = tagFinder.findTags(content, "a", "href")
    val images = tagFinder.findTags(content, "img", "src")

    val stringBuilder = new StringBuilder()
    stringBuilder.append(s"Visiting: $host$pathWithSlash" +
      images.map(image => s"\nFound image: ${image.trim}").mkString("") +
      links.map(link => s"\nFound link: ${link.trim}").mkString("") +
      "\n" +
      "---\n")

    links.foreach(link => stringBuilder.append(recursivelyVisitInternalLink(link)))
    stringBuilder.toString()
  }

  private def readUrl(urlAsString: String): (String, String) = {
    val url = new java.net.URL(urlAsString)
    (url.getProtocol + "://" + url.getHost + (if (url.getPort == -1) "" else ":" + url.getPort), url.getPath)
  }
}
