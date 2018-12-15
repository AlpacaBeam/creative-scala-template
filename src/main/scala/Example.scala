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
object Example {
  val complementaryTriangles = ((triangle(200,200).fillColor(Color.red)) beside
 (triangle(200,200).fillColor(Color.red.spin(15.degrees))) below
 (triangle(200,200).fillColor(Color.red.spin(30.degrees)))
 )
  val target = circle(50).fillColor(Color.red).on(
				circle(100).fillColor(Color.white).on(
					circle(150).fillColor(Color.red)
				)
			)
  
  val stand = rectangle(300,60).fillColor(Color.green).below(
				rectangle(50,10).fillColor(Color.brown).below(
					rectangle(10,50)
					)
				)
	
  val standOnTarget = target.above(stand)
  
  val box = Image.rectangle(80,80).
    lineWidth(5.0).
	lineColor(Color.royalBlue.spin(30.degrees)).
	fillColor(Color.royalBlue)
  
  def boxes(color: Color): Image = {
    val box = 
	  Image.rectangle(80,80).
	    lineWidth(5.0).
		lineColor(color.spin(30.degrees)).
		fillColor(color)
	
	box beside box beside box beside box beside box
  }
  
  val aBox = Image.rectangle(40,40).fillColor(Color.royalBlue)
  def boxes(count: Int): Image = 
	count match {
	  case 0 => Image.empty
	  case n => aBox.beside(boxes(n-1))
	  }
  
	val aCircle: Image = circle(40)
  def cross(count: Int): Image = {
		count match {
			case 0 => aCircle
			case n => (aCircle beside (aCircle above cross(n-1) above aCircle) beside aCircle)
		}
  
	}
  
  val image = boxes(Color.paleGoldenrod)
  def main(args: Array[String]): Unit = {
    image.draw
  }
}
