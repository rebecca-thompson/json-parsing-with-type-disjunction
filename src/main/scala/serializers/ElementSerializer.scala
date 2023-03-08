package serializers

import model.{Element, Field}
import net.liftweb.json.JsonAST.{JField, JObject, JString, JValue}
import net.liftweb.json.{DefaultFormats, Extraction, Formats, MappingException, Serializer, TypeInfo}

object ElementSerializer extends Serializer[Element] {
  implicit val format: Formats = DefaultFormats + FieldSerializer
  private val ElementClass = classOf[Element]

  override def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Element] = {
    case(TypeInfo(ElementClass, _), json) => json match {
      case JObject(JField("name", JString(s)) ::
        JField("fields", JObject(f)) :: Nil) =>
          Element(s, f.map(field => field.name -> field.value.extract[Field]).toMap)
      case x => throw new MappingException("Can't convert " + x + " to Element")
    }
  }
  override def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case e: Element =>
      JObject(
        JField("name", JString(e.elementName)),
        JField("fields", JObject(e.fields.map(field => JField(field._1, Extraction.decompose(field._2))).toList))
      )
  }
}
