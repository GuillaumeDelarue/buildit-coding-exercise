package gd.buildit.webcrawl

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, FunSuite, MustMatchers}

@RunWith(classOf[JUnitRunner])
abstract class JunitFunSuite extends FunSuite with MustMatchers with BeforeAndAfterEach with MockitoSugar