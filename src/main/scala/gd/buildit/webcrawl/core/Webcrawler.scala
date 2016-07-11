package gd.buildit.webcrawl.core

class Webcrawler(val httpClient: HttpClient) {

  def crawl(startingUrl: String): String = {
    val content = httpClient.get(startingUrl)
    s"Visiting: $startingUrl"
  }
}
