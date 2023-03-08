package model

sealed trait Field

case class StringField(field: String) extends Field
case class IntField(field: BigInt) extends Field
case class ImageField(url: String, height: BigInt) extends Field
