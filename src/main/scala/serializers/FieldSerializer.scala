package serializers

import model.{Field, ImageField, IntField, StringField}
import net.liftweb.json.JsonAST.{JInt, JString, JValue}
import net.liftweb.json.{Formats, JField, JObject, MappingException, Serializer, TypeInfo}

object FieldSerializer extends Serializer[Field] {
  override def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Field] = {
    case (TypeInfo(_, _), json) => json match {
      case JString(text) => StringField(text)
      case JInt(number) => IntField(number)
      case JObject(JField("url", JString(url)) ::
        JField("height", JInt(height)) :: Nil) =>
          ImageField(url, height)
      case x => throw new MappingException("Can't convert " + x + " to Field")
    }
  }

  override def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case s: StringField => JString(s.field)
    case i: IntField => JInt(i.field)
  }
}
