import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._

// To use this example:
//
// 1. run `sbt`
// 2. run the `console` command within `sbt`
// 3. enter `Example.image.draw`

object Pcurves {

  val dot = Image.circle(5).lineWidth(3).lineColor(Color.crimson)
  val squareDots =
    dot.at(0, 0).
      on(dot.at(0, 100)).
      on(dot.at(100, 100)).
      on(dot.at(100, 0))

  val image: Image = circle(10)

  def main(args: Array[String]): Unit = {
    image.draw
  }
}