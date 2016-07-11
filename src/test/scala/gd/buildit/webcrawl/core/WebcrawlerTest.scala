package gd.buildit.webcrawl.core

import gd.buildit.webcrawl.JunitFunSuite
import org.mockito.Mockito._

class WebcrawlerTest extends JunitFunSuite {
  private val httpContent = "httpContent"
  private val startingUrlHost = "http://host"
  private val startingUrlPath = "/path"
  private val startingUrl = startingUrlHost + startingUrlPath

  private val httpClient = mock[HttpClient]
  private val tagFinder = mock[TagFinder]
  private val webcrawler = new Webcrawler(httpClient, tagFinder)

  override def beforeEach() {
    reset(httpClient, tagFinder)
    when(httpClient.get(startingUrl)).thenReturn(httpContent)
    when(tagFinder.findTags(httpContent, "a", "href")).thenReturn(Seq())
    when(tagFinder.findTags(httpContent, "img", "src")).thenReturn(Seq())
  }

  test("crawl through simple page with no content") {
    val expectedOutput = s"Visiting: $startingUrl\n---\n"
    webcrawler.crawl(startingUrl) must be(expectedOutput)
  }

  test("crawl through simple page with images and list them") {
    when(tagFinder.findTags(httpContent, "img", "src")).thenReturn(Seq("image1", "image2"))

    val expectedOutput =
      s"""Visiting: $startingUrl
          |Found image: image1
          |Found image: image2
          |---
          |""".stripMargin
    webcrawler.crawl(startingUrl) must be(expectedOutput)
  }

  test("crawl through simple page with links, follow and list them") {
    when(tagFinder.findTags(httpContent, "a", "href")).thenReturn(Seq("link1"))
    when(httpClient.get(startingUrlHost + "/link1")).thenReturn("content from link1")
    when(tagFinder.findTags("content from link1", "a", "href")).thenReturn(Seq())
    when(tagFinder.findTags("content from link1", "img", "src")).thenReturn(Seq())

    val expectedOutput =
      s"""Visiting: $startingUrl
          |Found link: link1
          |---
          |Visiting: $startingUrlHost/link1
          |---
          |""".stripMargin
    webcrawler.crawl(startingUrl) must be(expectedOutput)
  }
}
