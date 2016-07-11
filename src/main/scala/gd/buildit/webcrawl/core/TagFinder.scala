package gd.buildit.webcrawl.core

trait TagFinder {

  def findTags(text: String, tagName: String, attributeToFetch: String): Seq[String]
}
