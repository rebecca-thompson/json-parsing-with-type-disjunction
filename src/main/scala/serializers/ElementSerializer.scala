package serializers

import model.{CartoonElement, CartoonFields, DefaultElement, DefaultFields, Element}
import net.liftweb.json.JsonAST.{JField, JObject, JString, JValue}
import net.liftweb.json.{DefaultFormats, Extraction, Formats, MappingException, Serializer, TypeInfo}

object ElementSerializer extends Serializer[Element] {
  implicit val format: Formats = DefaultFormats
  private val ElementClass = classOf[Element]

  override def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Element] = {
    case(TypeInfo(ElementClass, _), json) => json match {
      case JObject(JField("elementName", JString(elementName)) :: JField("fields", fields) :: Nil) =>
          elementName match {
            case "cartoon" =>
              CartoonElement(fields.extract[CartoonFields])
            case _ =>
              DefaultElement(elementName, DefaultFields(fields.extract[Map[String, String]]))
          }
      case x => throw new MappingException("Can't convert " + x + " to Element")
    }
  }

  override def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case default: DefaultElement =>
      JObject(
        JField("elementName", JString(default.elementName)),
        JField("fields", Extraction.decompose(default.getUnderlyingFields))
      )
    case cartoon: CartoonElement =>
      JObject(
        JField("elementName", JString(cartoon.elementName)),
        JField("fields", Extraction.decompose(cartoon.fields))
      )
  }
}
