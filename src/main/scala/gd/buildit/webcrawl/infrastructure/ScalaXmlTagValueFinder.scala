package gd.buildit.webcrawl.infrastructure

import gd.buildit.webcrawl.core.TagFinder

import scala.xml.{Node, XML}

class ScalaXmlTagValueFinder extends TagFinder {

  override def findTags(text: String, tagName: String, attributeToFetch: String): Seq[String] =
    (XML.loadString(text) \\ tagName).flatMap(findAttribute(_, attributeToFetch))

  private def findAttribute(node: Node, attributeToFetch: String): Option[String] =
    node.attributes.get(attributeToFetch).map(_.head.text)
}
