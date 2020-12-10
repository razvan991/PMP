package Lab10

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._

object Ex1 {
	def main(args: Array[String]) {
		val steps=10
		val capital:Array[Int]=Array.fill(steps)(0)
		val investment:Array[Int]=Array.fill(steps)(0)
		val profit:Array[Int]=Array.fill(steps)(0)

		capital(0)=100000
		for(step <-1 until steps){
				investment(step)=10/100*capital(step-1)
				profit(step)=Apply(investment(steps),(i:Int)=>
					if(i>7500) 25/100*i else if (i>5000 && i<7500) 10/100*i else -1/100*i
				)
				capital(step)=capital(step-1)+(profit(step)-investment(step))
		}

	}
}