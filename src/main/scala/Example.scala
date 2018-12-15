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
  
  
  def boxes(count: Int): Image = {
		val aBox = Image.rectangle(40,40).fillColor(Color.royalBlue)

		def loop(count: Int) =
		count match {
			case 0 => Image.empty
			case n => aBox.beside(loop(n-1))
			}
		
		loop(count)
	}

	val aCircle: Image = circle(40)
  def cross(count: Int): Image = {
		count match {
			case 0 => aCircle
			case n => (aCircle beside (aCircle above cross(n-1) above aCircle) beside aCircle)
		}
  
	}
  


	def chessBoard(count: Int): Image = {
		val chessBoardUnit: Image = (rectangle(40,40).fillColor(Color.red).beside(
																	rectangle(40,40).fillColor(Color.black))).above(
																rectangle(40,40).fillColor(Color.black).beside(
																	rectangle(40,40).fillColor(Color.red)
																		)
																	)
		
		def loop(count: Int): Image =
			count match {
				case 0 => chessBoardUnit
				case n => val unit = loop(n-1)
									(unit beside unit) above (unit beside unit)
			}
		
		loop(count)
	}

	def sierpinski(count: Int): Image = {
		val aTriangle: Image = triangle(40,45).lineColor(Color.purple).lineWidth(2)
		val base: Image = aTriangle above (aTriangle beside aTriangle)

		count match {
			case 0 => base
			case n => val unit = sierpinski(n-1)
								unit above (unit beside unit)
		}	
	}

def growingBoxes(count: Int, size: Int): Image =
  count match {
    case 0 => Image.empty
    case n => Image.rectangle(size, size) beside growingBoxes(n-1, size + 10)
  }

def gradientBoxes(count: Int): Image = {
	val aBox: Image = rectangle(80,80).fillColor(Color.purple.spin((count*15).degrees)).lineColor(Color.red.spin((count*15).degrees)).lineWidth(4)

	count match {
		case 0 => aBox
		case n => aBox beside gradientBoxes(n-1)
	}
}

def concentricCircles(count: Int): Image = {
	val aCircle = circle(50 + (count * 10)).lineColor(Color.royalBlue.spin((10*count).degrees)).lineWidth(5)
	
	count match {
		case 0 => Image.empty
		case n => concentricCircles(n-1) on aCircle
	}
}

  val image = boxes(Color.paleGoldenrod)
  def main(args: Array[String]): Unit = {
    image.draw
  }
}
