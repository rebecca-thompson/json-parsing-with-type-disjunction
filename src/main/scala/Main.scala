import model.Element
import net.liftweb.json.{DefaultFormats, Formats, parse}
import net.liftweb.json.Serialization.write
import serializers.ElementSerializer

object Main {
  implicit val formats: Formats = DefaultFormats + ElementSerializer
  def main(args: Array[String]): Unit = {
    val defaultElement =
      """{
        "elementName": "Doug",
        "fields": {
          "greeting": "Hello",
          "id": 1234,
        }
      }""".stripMargin
    val parsedDefaultElement = parse(defaultElement).extract[Element]
    val legacyElementJson: String = write(parsedDefaultElement)
    println(legacyElementJson)

    val cartoonElement =
      """{
        "elementName": "cartoon",
        "fields": {
          "greeting": "Hello",
          "id": 1234,
          "image": {
            "url": "link-to-image",
            "height": 234
          }
        }
      }""".stripMargin
      val parsedCartoonElement = parse(cartoonElement).extract[Element]
      val cartoonElementJson: String = write(parsedCartoonElement)
      println(cartoonElementJson)
  }
}
