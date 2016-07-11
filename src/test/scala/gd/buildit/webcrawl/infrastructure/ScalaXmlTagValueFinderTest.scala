package gd.buildit.webcrawl.infrastructure

import gd.buildit.webcrawl.JunitFunSuite

class ScalaXmlTagValueFinderTest extends JunitFunSuite {
  private val tagFinder = new ScalaXmlTagValueFinder()

  test("empty list when no attribute") {
    val htmlContentWithNoLink = "<body><img src=\"image\"/></body>"
    tagFinder.findTags(htmlContentWithNoLink, "a", "href") must be(Seq.empty)
  }

  test("extract single attribute from HTML") {
    val htmlContentWithOneLink = "<body><a href=\"link\"/></body>"
    tagFinder.findTags(htmlContentWithOneLink, "a", "href") must be(Seq("link"))
  }

  test("extract multiple attributes from HTML") {
    val htmlContentWithTwoLinks = "<body><a href=\"link1\"/><a href=\"link2\"/></body>"
    tagFinder.findTags(htmlContentWithTwoLinks, "a", "href") must be(Seq("link1", "link2"))
  }
}
