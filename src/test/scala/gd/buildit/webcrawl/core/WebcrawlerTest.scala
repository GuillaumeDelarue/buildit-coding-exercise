package gd.buildit.webcrawl.core

import gd.buildit.webcrawl.JunitFunSuite
import org.mockito.Mockito._

class WebcrawlerTest extends JunitFunSuite {
  private val httpClient = mock[HttpClient]
  private val tagFinder = mock[TagFinder]
  private val webcrawler = new Webcrawler(httpClient, tagFinder)

  test("crawl through simple page with no content") {
    when(httpClient.get("starting url")).thenReturn("nothing to handle")
    when(tagFinder.findTags("nothing to handle", "a", "href")).thenReturn(Seq())
    when(tagFinder.findTags("nothing to handle", "img", "src")).thenReturn(Seq())

    val expectedOutput = "Visiting: starting url\n---\n"
    webcrawler.crawl("starting url") must be(expectedOutput)
  }

  test("crawl through simple page with images and list them") {
    when(httpClient.get("starting url")).thenReturn("content with images")
    when(tagFinder.findTags("content with images", "a", "href")).thenReturn(Seq())
    when(tagFinder.findTags("content with images", "img", "src")).thenReturn(Seq("image1", "image2"))

    val expectedOutput =
      """Visiting: starting url
        |Found image: image1
        |Found image: image2
        |---
        |""".stripMargin
    webcrawler.crawl("starting url") must be(expectedOutput)
  }
}
