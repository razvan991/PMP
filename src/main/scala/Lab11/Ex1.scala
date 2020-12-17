package Lab11

import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.factored.beliefpropagation.BeliefPropagation
import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.compound._

object Ex1 {
	val isPresident=Flip(0.000000025)
	val leftHanded=CPD(isPresident,
		false->Flip(0.1),
		true->Flip(0.5)
	)
	val wentToHarvard=CPD(isPresident,
		false->Flip(0.0005),
		true->Flip(0.15)
	)
	def main(args: Array[String]) {

		leftHanded.observe(true)
		val alg=VariableElimination(isPresident)
		alg.start()
		println("[VE] Probability becoming president (lefthanded): " + alg.probability(isPresident,true))
		alg.kill()
		leftHanded.unobserve()

		wentToHarvard.observe(true)
		val alg1=VariableElimination(isPresident)
		alg1.start()
		println("[VE] Probability becoming president (went to Harvard): " + alg1.probability(isPresident,true))
		alg1.kill()
		wentToHarvard.unobserve()

		leftHanded.observe(true)
		wentToHarvard.observe(true)
		val alg2=VariableElimination(isPresident)
		alg2.start()
		println("[VE] Probability becoming president (lefthanded + went to Harvard): " + alg2.probability(isPresident,true))
		alg2.kill()
		leftHanded.unobserve()
		wentToHarvard.unobserve()

		leftHanded.observe(true)
		val alg3=Importance(10000,isPresident)
		alg3.start()
		println("[Importance] Probability becoming president (lefthanded): "+alg3.probability(isPresident,true))
		alg3.kill()
		leftHanded.unobserve()

		wentToHarvard.observe(true)
		val alg4=Importance(10000,isPresident)
		alg4.start()
		println("[Importance] Probability becoming president (went to Harvard): " +alg4.probability(isPresident,true))
		alg4.kill()
		wentToHarvard.unobserve()

		leftHanded.observe(true)
		wentToHarvard.observe(true)
		val alg5=Importance(10000,isPresident)
		alg5.start()
		println("[Importance] Probability becoming president (lefthanded + went to Harvard): " +alg5.probability(isPresident,true))
		alg5.kill()
		leftHanded.unobserve()
		wentToHarvard.unobserve()

		leftHanded.observe(true)
		val algorithm = BeliefPropagation(10, isPresident)
		algorithm.start()
		println("BP: Probability becoming president (lefthanded): " + algorithm.probability(isPresident,true))
		algorithm.kill()
		leftHanded.unobserve()

		wentToHarvard.observe(true)
		val algorithm1 = BeliefPropagation(10, isPresident)
		algorithm1.start()
		println("BP: Probability becoming president (went to Harvard): " + algorithm1.probability(isPresident,true))
		algorithm1.kill()
		wentToHarvard.unobserve()

		leftHanded.observe(true)
		wentToHarvard.observe(true)
		val algorithm2 = BeliefPropagation(10, isPresident)
		algorithm2.start()
		println("BP: Probability becoming president (lefthanded + went to Harvard): " + algorithm2.probability(isPresident,true))
		algorithm2.kill()
		leftHanded.unobserve()
		wentToHarvard.unobserve()
	}
}