package Lab8

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
class Departments{
	val health=Select(0.5->"healthy",0.5->"unhealthy")
}
abstract class researchAndDevelopment extends Departments {
	val budget:Element[Int]
}
abstract class production extends Departments {
	val product:Element[String]
}
abstract class sales extends Departments {
	val identifyClient:Element[Boolean]
}
abstract class humanResources extends Departments {
	val promotingEmployee:Element[Boolean]
	val recruitEmployee:Element[Boolean]
}
abstract class Finance extends Departments {
	val funds:Element[Int]
	val profit:Element[Int]
}

object Ex1 {
	def main(args: Array[String]) {

	}
}