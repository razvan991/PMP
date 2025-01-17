package Lab2

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._

object Ex1 {
	def main(args: Array[String]) {

		val student1 = new Student("Razvan","Cazacu",1,Array(("matematica",5),("informatica",7)))

		student1.addMaterie("info",3)
		student1.printMaterii()
		student1.getNota("info")

		/*val test = Constant("Test")

		val algorithm = Importance(1000, test)
		algorithm.start()
		
		println(algorithm.probability(test, "Test"))*/
	}
	abstract class Persoana(val nume:String,val prenume:String){

	}

	class Student(override val nume:String,override val prenume:String,var an:Integer,var materii : Array[(String,Integer)]) extends Persoana(nume,prenume){


		def setNota(materie:String,nota:Integer): Unit ={
		/*for(a<-materii){
			if(materie==a._1)  a._2=nota       //reassignment to val
		}*/
		}
		def getNota(materie:String): Unit ={
			for (a<- materii){
				if (materie == a._1) println(a._2)
			}
		}
		def addMaterie(materie: String, nota: Integer): Unit ={
			materii= materii :+ (materie,nota)
		}
		def printMaterii(): Unit ={
			for (a <- materii) {
				println(a) }
		}
	}
	class Profesor(override val nume:String,override val prenume:String,var materie:String) extends Persoana(nume,prenume){

	}
}