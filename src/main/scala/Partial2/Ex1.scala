package Partial2

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.language.{Select, Apply, Constant, Element, Chain, Universe, Flip}
import com.cra.figaro.library.compound.{If, CPD, RichCPD, OneOf, *, ^^}
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.algorithm.factored.beliefpropagation.BeliefPropagation

object Ex1 {
	//starea de start , 50% insorit , 30% innorat , 20%ploios
	class Start (sor:Insorit,nor:Innorat,plo:Ploios){
		sor.sameState=Flip(0.5)
		nor.sameState=Flip(0.3)
		plo.sameState=Flip(0.2)
	}
	//pentru fiecare stare am descris probabilitatea sa ramana in aceeasi stare
	//si probabilitatea sa iau umbrela
	//Daca e insorit -> 30% sa fie innorat , 10% sa fie ploios in urm ora
	class Insorit(nor:Innorat,plo:Ploios){
		var sameState = Flip(0.6)
		val iauUmbrela= if(sameState,Flip(0.15),Flip(0.85))
		nor.sameState=if(sameState,Flip(0.3),Flip(0.7))
		plo.sameState=if(sameState,Flip(0.1),Flip(0.9))
	}
	//Daca e innorat -> 15% sa fie insorit , 35% sa fie ploios in urm ora
	class Innorat(sor:Insorit,plo:Ploios){
		var sameState = Flip(0.5)
		val iauUmbrela= if(sameState,Flip(0.65),Flip(0.35))
		sor.sameState=if(sameState,Flip(0.15),Flip(0.85))
		plo.sameState=if(sameState,Flip(0.35),Flip(0.65))
	}
	//Daca e ploios -> 15% sa fie insorit , 40% sa fie innorat in urm ora
	class Ploios(sor:Insorit,nor:Innorat){
		var sameState = Flip(0.45);
		val iauUmbrela= if(sameState,Flip(0.75),Flip(0.25))
		sor.sameState=if(sameState,Flip(0.15),Flip(0.85))
		nor.sameState=if(sameState,Flip(0.4),Flip(0.6))
	}
	//pentru monitorizare
	def main(args: Array[String]) {
		val hours=10
		for {hours <- 1 until hours}{
			println()
		}

	}
}