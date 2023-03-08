import model.{Element, Field}
import net.liftweb.json.{DefaultFormats, Formats, parse}
import net.liftweb.json.Serialization.write
import serializers.{ElementSerializer, FieldSerializer}

object Main {
  implicit val formats: Formats = DefaultFormats + FieldSerializer + ElementSerializer
  def main(args: Array[String]): Unit = {
    val testFields =
      """{
        "greeting": "Hello",
        "id": 1234,
      }""".stripMargin
    val deserializedJson = parse(testFields).extract[Map[String, Field]]
    val deserializedJsonString = write(deserializedJson)
    println(deserializedJsonString)

    val testElementWithStringFields =
      """{
        "name": "Doug",
        "fields": {
          "greeting": "Hello",
          "id": 1234,
        }
      }""".stripMargin
    val deserializedElement = parse(testElementWithStringFields).extract[Element]
    val deserializedElementString = write(deserializedElement)
    println(deserializedElementString)

    val testElementWithObjectFields =
      """{
        "name": "Doug",
        "fields": {
          "greeting": "Hello",
          "id": 1234,
          "image": {
            "url": "link-to-image",
            "height": 234
          }
        }
      }""".stripMargin
      val deserializedElementWithObjectField = parse(testElementWithObjectFields).extract[Element]
      val deserializedElementWithObjectFieldString = write(deserializedElementWithObjectField)
      println(deserializedElementWithObjectFieldString)
  }
}
