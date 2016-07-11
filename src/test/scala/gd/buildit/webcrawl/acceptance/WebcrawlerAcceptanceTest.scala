package gd.buildit.webcrawl.acceptance

import gd.buildit.webcrawl.JunitFunSuite
import gd.buildit.webcrawl.core.Webcrawler

class WebcrawlerAcceptanceTest extends JunitFunSuite {
  private val webcrawler = new Webcrawler()

  test("crawl through page produces site map") {
    val expectedOutput =
      """
        |Visiting: file://html/index.html
        |Found link: /html/page1.html
        |Found image: /img/test.jpg
        |Found link: /html/page2.html
        |---
        |Visiting: file://html/page1.html
        |Found link: /html/page2.html
        |Found link: /html/index.html
        |---
        |Visiting: file://html/page2.html
        |--- """.stripMargin

    webcrawler.crawl("file://html/index.html") must be(expectedOutput)
  }
}
