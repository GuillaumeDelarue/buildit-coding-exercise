package gd.buildit.webcrawl.infrastructure

import gd.buildit.webcrawl.core.HttpClient

import scala.io.Source

class DefaultHttpClient extends HttpClient {
  override def get(url: String): String = Source.fromURL(url).mkString
}
