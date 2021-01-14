package Lab13

import com.cra.figaro.algorithm.factored.beliefpropagation.MPEBeliefPropagation
import com.cra.figaro.algorithm.factored.{MPEVariableElimination, VariableElimination}
import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.compound.If

object Ex1 {
	abstract class State {
		val confident: Element[Boolean]
		def possession: Element[Boolean] = If(confident, Flip(0.7), Flip(0.3))
	}
	class InitialState() extends State {
		val confident = Flip(0.4)
	}
	class NextState(current: State) extends State {
		val confident = If(current.confident, Flip(0.6), Flip(0.3))
	}
	def stateSequence(n: Int): List[State] = {
		if (n == 0) List(new InitialState())
		else {
			val last :: rest = stateSequence(n - 1)
			new NextState(last) :: last :: rest
			}
	}
	def timing(obsSeq: List[Boolean]): Double = {
		Universe.createNew() // ensures that each experiment is separate
		val stateSeq = stateSequence(obsSeq.length)
		for { i <- 0 until obsSeq.length } {
			stateSeq(i).possession.observe(obsSeq(obsSeq.length - 1 - i))
		}
		val alg = MPEVariableElimination()
		val time0 = System.currentTimeMillis()
		alg.start()
		val time1 = System.currentTimeMillis()
		(time1 - time0) / 1000.0
		}

	def main(args: Array[String]) {

	}
}