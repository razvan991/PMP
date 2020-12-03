package Lab9

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.collection._
import com.cra.figaro.library.compound.{If,^^,RichCPD,OneOf, * }
import com.cra.figaro.algorithm.filtering.ParticleFilter

//dificultate test
//learned
//scor depinde de sus
object markov {
	val chapters =10
	val initial =Universe.createNew()
	Constant(3)("score",initial)
	Constant(8)("learned",initial)
	Constant(5)("difficulty",initial)

	def transition(learned: Int,difficulty: Int): (Element[(Int,Int,Int)])={
		
	}

	def nextUniverse(previous: Universe): Universe ={
		val next=Universe.createNew()
		val previousSeated=previous.get[Int]("learned")
		val previousWaiting=previous.get[Int]("difficulty")
		val newState=Chain(previousSeated,previousWaiting,transition _)

		Apply(newState,(s: (Int,Int,Int)) -> s._1)("learned",next)
		Apply(newState,(s: (Int,Int,Int)) -> s._2)("score",next)
		Apply(newState,(s: (Int,Int,Int)) -> s._3)("difficulty",next)
		next
	}

	def main(args: Array[String]) {
		val arrivingObservation=List(Some(1),Some(1),Some(1),None,None,None,None,None,None,None,None,None)
		val alg=ParticleFilter(initial,nextUniverse,100)
		alg.start()
		for( time<-1 to 10){
			val evidence={
				arrivingObservation(time) match{
						case None => List()
						case Some(n) => List(NamedEvidence("score",Observation(n)))
				}
			}
			alg.advanceTime(evidence)
			print("Chapter "+time+": ")
			println("expected learning = "+alg.currentExpectation("score",(i:Int)=>i))
		}
	}
}