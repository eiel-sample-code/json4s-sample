package json4s.sample

import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization._
import org.scalatest.{ DiagrammedAssertions, FunSpec }

class Case1Test extends FunSpec with DiagrammedAssertions with Case1Json {
  describe("Case1"){
    it("json文字列への変換確認") {
      val str = write(Case1(hogeId = 1L, moguId = 2))
      assert(str == """{"hoge_id":"1","mogu_id":2}""")
      val json = parse(str)
      assert(json \\ "hoge_id" == JString("1"))
      assert(json \\ "mogu_id" == JInt(2))
    }
  }
}
