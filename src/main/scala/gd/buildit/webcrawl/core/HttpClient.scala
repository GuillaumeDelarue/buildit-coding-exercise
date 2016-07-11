package gd.buildit.webcrawl.core

trait HttpClient {
  def get(url: String): String
}
