package Lab3 
// febra 6%  tusesti 4%
// febra + tuse -> covid
//5% din pop inf
//confirmat covid -> care e sansa sa faci febra/tuse ?
//nu aveti nici febra nici tuse care e sansa sa fii asimptomatic

import com.cra.figaro.algorithm.factored._
import com.cra.figaro.language._
import com.cra.figaro.library.compound._
object Ex1 {
	Universe.createNew()
	private val febra = Flip(0.06)
	private val tuse = Flip(0.04)

	private val alarm = CPD(febra, tuse,
		(false, false) -> Flip(0.001),
		(false, true) -> Flip(0.1),
		(true, false) -> Flip(0.7),
		(true, true) -> Flip(0.98))


	private val Covid = CPD(alarm,     false -> Flip(0.01),     true -> Flip(0.7))
	def main(args: Array[String]): Unit =
	{
		//Confirmat covid ->  sansa sa ai febra / tuse

		/*Covid.observe(true)
		val alg = VariableElimination(febra, tuse)
		alg.start()
		println("Probabilitatea sa ai febra : " + alg.probability(febra, true))
		println("Probabilitatea sa tusesti : " + alg.probability(tuse, true))
		alg.kill*/

		//Nu ai tuse / febra -> sansa sa fii asimptomatic
		// daca nu pun prima parte a ex in comentarii , imi da probabilitate 1% , daca e pus in comentariu imi da o valoare buna
		febra.observe(false)
		tuse.observe(false)
		val alg2 = VariableElimination(Covid)
		alg2.start()
		println("Probabilitatea sa ai covid (asimptomatic) : " + alg2.probability(Covid, true))
		alg2.kill
	}

}