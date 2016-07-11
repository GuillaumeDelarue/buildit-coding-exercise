package gd.buildit.webcrawl.core

class Webcrawler(val httpClient: HttpClient, val tagFinder: TagFinder) {

  def crawl(startingUrl: String): String = {
    val content = httpClient.get(startingUrl)
    val links = tagFinder.findTags(content, "a", "href")
    val images = tagFinder.findTags(content, "img", "src")

    s"Visiting: $startingUrl" +
      images.map(image => s"\nFound image: ${image.trim}").mkString("") +
      links.map(link => s"\nFound link: ${link.trim}").mkString("") +
      "\n" +
      "---\n"
  }
}
