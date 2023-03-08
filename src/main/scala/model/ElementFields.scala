package model

trait ElementFields

case class DefaultFields(fields: Map[String, String] = Map()) extends ElementFields

case class ImageField(url: String, height: Int)
case class CartoonFields(greeting: Option[String], image: Option[ImageField]) extends ElementFields
