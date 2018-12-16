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

  def parametricCircle(angle: Angle): Point =
    Point.polar(200, angle)

  def sample(start: Angle, samples: Int): Image = {
      //Angle.one is one complete turn. I.e. 360 degrees
      val step = Angle.one / samples
      val dot = triangle(10,10)
      def loop(count: Int): Image = {
          val angle = step * count
          count match {
              case 0 => Image.empty
              case n =>
                dot.at(parametricCircle(angle).toVec) on loop(n - 1)
          }
      }

      loop(samples)
  }

  def rose(angle: Angle) = Point.polar((angle * 7).cos * 200, angle)

  def concentricShapes(count: Int, singleShape: Int => Image): Image =
    count match {
        case 0 => Image.empty
        case n => singleShape(n) on concentricShapes(n-1, singleShape)
  }

  val blackCircles: Image = concentricShapes(10, (n: Int) => Image.circle(50 + 5*n))

  def redCircle(n: Int): Image = Image.circle(50 + 5*n).lineColor(Color.red)

  val redCircles: Image = concentricShapes(10, redCircle _)

  def colorCircle(n: Int): Image = Image.circle(80 + 10 * n).lineColor(Color.violet.spin((10*n).degrees)).lineWidth(7)
  val colorCircles: Image = concentricShapes(40, colorCircle _)

  val image: Image = circle(10)
  def main(args: Array[String]): Unit = {
    image.draw
  }
}