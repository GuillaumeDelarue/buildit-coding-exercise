package gd.buildit.webcrawl.infrastructure

import gd.buildit.webcrawl.core.TagFinder

class RegexTagFinder extends TagFinder {
  override def findTags(text: String, tagName: String, attributeToFetch: String): Seq[String] = ???
}
