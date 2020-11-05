package Lab6

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.compound.If

object Ex1 {
	def main(args: Array[String]) {
		def tennis(probP1ServeWin: Double, probP1Winner: Double, probP1Error: Double, probP2ServeWin: Double, probP2Winner: Double, probP2Error: Double): Element[Boolean] = {
			//servirea
			def rally(firstShot: Boolean, player1: Boolean): Element[Boolean] = {
				/*
				probabilitatea ca un player sa castige
				 */
				val pWinner = if (firstShot && player1) probP1ServeWin //daca e prima servire si player1 serveste => probabilitatea ca p1 sa castige
				else if (firstShot && !player1) probP2ServeWin //daca e prima servire si player2 serveste => probabilitatea ca player2 sa castige
				else if (player1) probP1Winner //nu e prima lov din joc dar e randul p1 => prob p1
				else probP2Winner //nu e prima lov din joc dar e randul p2 => prob p2
				/*
				probabilitatea unei erori
				 */
				val pError = if (player1) probP1Error //daca e randul p1 => prob erorii = prob ca player1 sa faca eroarea resp.
				else probP2Error //daca e randul p2 => prob erorii = prob ca player2 sa faca eroarea resp.

				val winner = Flip(pWinner) //prob ca un jucator sa castige
				val error = Flip(pError)  //prob eroare (fileu,nu se nimereste mingea)
				If(winner, Constant(player1),  //p1 castiga
					If(error, Constant(!player1), //p1 face o eroare
						rally(false, !player1))) // daca nu a avut loc o eroare si inca nu s-a castigat se joaca in continuare
			}
			//
			def game(p1Serves: Boolean, p1Points: Element[Int], p2Points: Element[Int]): Element[Boolean] = {
				// p1WinsPoint=1 daca a castigat , =0 daca a facut o eroare , apoi pe baza acestei variabile calculam noile punctaje pentru p1 si p2
				val p1WinsPoint = rally(true, p1Serves)

				val newP1Points = Apply(p1WinsPoint, p1Points, (wins: Boolean, points: Int) => if (wins) points + 1 else points) //cresterea punctelor pentru player1
				val newP2Points = Apply(p1WinsPoint, p2Points, (wins: Boolean, points: Int) => if (wins) points else points + 1) //cresterea punctelor pentru player2
				val p1WinsGame = Apply(newP1Points, newP2Points, (p1: Int, p2: Int) => p1 >= 4 && p1 - p2 >= 2) //player1 castiga jocul (mai mult de 4 puncte si diferenta de 2 pct)
				val p2WinsGame = Apply(newP2Points, newP1Points, (p2: Int, p1: Int) => p2 >= 4 && p2 - p1 >= 2) //player2 castiga jocul (mai mult de 4 puncte si diferenta de 2 pct)
				val gameOver = p1WinsGame || p2WinsGame  //game over daca unul dintre jucatori castiga
				If(gameOver, p1WinsGame, game(p1Serves, newP1Points, newP2Points)) //daca inca nu s-a terminat jocul , se joaca in continuare
			}
			//definirea a 2 seturi , 5 meciuri castigate
			def play(p1Serves: Boolean, p1Sets: Element[Int], p2Sets: Element[Int],p1Games: Element[Int], p2Games: Element[Int]): Element[Boolean] =
				{
					val p1WinsGame = game(p1Serves, Constant(0), Constant(0))
					/*
					Daca player1 castiga meciul si are 5 meciuri castigate atunci refacem nr de meciuri pentru player1=0 , daca nu are deja 5 meciuri castigate ii crestem
					numarul de meciuri castigate de player1 , (a castigat p2) daca player2 are 5 meciuri castigate , atunci refacem nr de meciuri castigate de p1=0
					daca nu are deja 5 meciuri castigate p2 , numarul de meciuri castigate pentru p1 ramane la fel
					 */
					val newP1Games = Apply(p1WinsGame, p1Games, p2Games,(wins: Boolean, p1: Int, p2: Int) =>
						if (wins) { if (p1 >= 5) 0 else p1 + 1 }
						else  { if (p2 >= 5) 0 else p1 })
					/*
					Daca player1 castiga , daca player 1 are 5 meciuri castigate refacem nr de meciuri castigate pentru p2=0, daca nu are deja 5 meciuri castigate numarul
					de meciuri castigate pentru p2 raman la fel.Daca nu castiga player1 => player2 a castigat meciul => daca are deja 5 meciuri castigate refacem nr de meciuri
					pentru p2 castigate=0 , daca nu are deja 5 meciuri crestem nr de meciuri.
					 */
					val newP2Games = Apply(p1WinsGame, p1Games, p2Games,(wins: Boolean, p1: Int, p2: Int) =>
						if (wins) { if (p1 >= 5) 0 else p2 }
						else { if (p2 >= 5) 0 else p2 + 1 })
					/*
					Daca castiga player1 meciul si player1 are 5 meciuri castigate => crestem nr de seturi castigate pentru player1, else nu modificam nr de seturi
					*/
					val newP1Sets =  Apply(p1WinsGame, p1Games, p1Sets,(wins: Boolean, games: Int, sets: Int) =>
						if (wins && games == 5) sets + 1
						else sets)
					/*
					Daca nu castiga player1 meciul si player2 are 5 meciuri castigate => crestem nr de seturi castigate pentru player2, else nu modificam nr de seturi
					*/
					val newP2Sets =  Apply(p1WinsGame, p2Games, p2Sets,(wins: Boolean, games: Int, sets: Int) =>
						if (!wins && games == 5) sets + 1
						else sets)

					val matchOver =  Apply(newP1Sets, newP2Sets, (p1: Int, p2: Int) =>p1 >= 2 || p2 >= 2) // match over daca un player are >=2 seturi
					If(matchOver,Apply(newP1Sets, (sets: Int) => sets >= 2), //
						play(!p1Serves, newP1Sets, newP2Sets, newP1Games, newP2Games)) // se joaca in continuare , serveste p2
				}
			play(true, Constant(0), Constant(0), Constant(0), Constant(0)) // se incepe jocul , primul player serveste si fiecare jucator are 0 seturi si 0 meciuri castigate
		} //end of tenis



		} //end of main
} //end of ex1