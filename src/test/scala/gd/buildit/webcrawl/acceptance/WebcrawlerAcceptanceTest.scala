package gd.buildit.webcrawl.acceptance

import gd.buildit.webcrawl.JunitFunSuite
import gd.buildit.webcrawl.core.Webcrawler
import gd.buildit.webcrawl.infrastructure.{DefaultHttpClient, ScalaXmlTagValueFinder}

class WebcrawlerAcceptanceTest extends JunitFunSuite {
  private val webcrawler = new Webcrawler(new DefaultHttpClient(), new ScalaXmlTagValueFinder())

  test("crawl through page produces site map") {
    val expectedOutput =
      """
        |Visiting: file://html/index.html
        |Found image: /img/test.jpg
        |Found link: /html/page1.html
        |Found link: /html/page2.html
        |---
        |Visiting: file://html/page1.html
        |Found link: /html/page2.html
        |Found link: /html/index.html
        |---
        |Visiting: file://html/page2.html
        |Found image: /img/test.jpg
        |--- """.stripMargin

    webcrawler.crawl("file://html/index.html") must be(expectedOutput)
  }
}
