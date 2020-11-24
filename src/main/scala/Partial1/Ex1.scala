package Partial1

import com.cra.figaro.algorithm.factored._
import com.cra.figaro.language._
import com.cra.figaro.library.compound._

object Ex1 {
	Universe.createNew()
	//10% prob sa uit sa setez alarma
	private val setAlarmForgot = Flip(0.1)
	private val wakeUpLate=CPD(setAlarmForgot,false->Flip(0.1),true->Flip(0.9))
	//prob ca autobuzul sa intarzie
	private val busIsLate=Flip(0.2)
	//ajung la timp la serviciu sau nu (depinde daca am uitat sa setez alarma si daca autobuzul intarzie, valorile din tabel )
	private val Work = CPD(setAlarmForgot,busIsLate,
		(false, false) -> Flip(0.9),
		(false,true) -> Flip(0.2),
		(true,false) -> Flip(0.3),
		(true,true) -> Flip(0.1)
	)
	def main(args: Array[String]): Unit =
	{
		//pentru 2a (prob sa ajung la serviciu avand in vedere ca am adormit (nu stim daca am setat alarma sau autobuzul intarzie))
		val alg1 = VariableElimination(Work)
		alg1.start()
		//prob sa ajung la serviciu stiind ca am adormit
		println("Probabilitatea sa ajung la serviciu la timp avand in vedere ca am adormit : " + alg1.probability(Work,true))
		alg1.kill

		//pentru 2b , am ajuns la timp la serviciu
		//ajungem la timp la serviciu
		Work.observe(true)
		val alg = VariableElimination(setAlarmForgot,busIsLate)
		alg.start()
		//afisam prob sa fi setat alarma , stiind ca am ajuns la serviciu la timp
		println("Probabilitatea sa fi setat alarma : " + alg.probability(setAlarmForgot, false))
		alg.kill

		//pentru 2c
		val alg2 = VariableElimination(wakeUpLate)
		alg2.start()
		//afisam prob sa ma trezesc tarziu
		println("Probabilitatea sa ma trezesc tarziu : " + alg2.probability(wakeUpLate, true))
		alg2.kill

	}
}