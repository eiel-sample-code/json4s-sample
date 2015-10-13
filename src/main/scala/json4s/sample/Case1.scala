package json4s.sample

import org.json4s._
import org.json4s.native.JsonMethods.parse
import org.json4s.native.Serialization.write

case class Case1(hogeId: Long, moguId: Int)

// JSONとクラスでフィールド名が一致しない場合 & Long の場合Stringに
object Case1 extends App {
  import FieldSerializer._
  val Case1FieldSerializer = FieldSerializer[Case1](
    renameTo("hogeId", "hoge_id") orElse renameTo("moguId", "mogu_id"),
    renameFrom("hoge_id", "hogeId") orElse renameFrom("mogu_id", "moguId"))

  val longCustomSerializer = new CustomSerializer[Long](formats => (
    {
      case JString(x) => x.toLong
    },{
      case x: Long => JString(x.toString)
    }))

  implicit val formats = DefaultFormats + Case1FieldSerializer + longCustomSerializer

  val str = write(Case1(1L,2))
  println(str)
  var json = parse(str)
  println(json \\ "hoge_id" == JString("1"))
  println(json \\ "mogu_id" == JInt(2))
}