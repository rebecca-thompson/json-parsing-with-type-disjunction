package model

trait Element {
  val elementName: String
  val fields: ElementFields
}
case class DefaultElement(elementName: String, fields: DefaultFields) extends Element {
  def getUnderlyingFields: Map[String, String] = fields.fields
}
case class CartoonElement(fields: CartoonFields) extends Element {
  val elementName = "cartoon"
}
