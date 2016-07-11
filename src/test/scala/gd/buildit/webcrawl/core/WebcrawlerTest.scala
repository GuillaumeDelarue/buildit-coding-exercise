package gd.buildit.webcrawl.core

import gd.buildit.webcrawl.JunitFunSuite
import org.mockito.Mockito._

class WebcrawlerTest extends JunitFunSuite {
  private val httpClient = mock[HttpClient]
  private val webcrawler = new Webcrawler(httpClient)

  test("crawl through simple page with no content") {
    when(httpClient.get("starting url")).thenReturn("nothing to handle")

    val expectedOutput = "Visiting: starting url"
    webcrawler.crawl("starting url") must be(expectedOutput)
  }
}
