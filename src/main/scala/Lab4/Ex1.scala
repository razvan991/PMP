package Lab4 

import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.atomic.discrete.FromRange

object Ex1 {
	def main(args: Array[String]): Unit = {
		//Exercitiul 4 (din carte , ex 2 din lab)
		/*
		val die1=FromRange(1,7)
		val die2=FromRange(1,7)
		val total = Apply(die1, die2, (i1: Int, i2: Int) => i1 + i2)
		println(VariableElimination.probability(total,11))
		*/
		//Exercitiul 5 (din carte , ex 3 din lab)
		/*
		val die1=FromRange(1,7)
		val die2=FromRange(1,7)
		val total = Apply(die1, die2, (i1: Int, i2: Int) => i1 + i2)
		total.addCondition((i:Int) => i>8)
		println(VariableElimination.probability(die1,6))
		*/
		//Exercitiul 6 (din carte , ex 4 din lab)
		/*
		def doubles ={
			val die1=FromRange(1,7)
			val die2=FromRange(1,7)
			die1 === die2
		}
		val jail = doubles && doubles && doubles
		println(VariableElimination.probability(jail,true))
	*/


	}
}