package Lab5

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.atomic.discrete
import com.cra.figaro.language.Chain
import com.cra.figaro.library.compound.{RichCPD, OneOf, *}
import com.cra.figaro.language.{Flip, Constant, Apply}
import com.cra.figaro.algorithm.factored.VariableElimination

object Ex1 {
	def main(args: Array[String]) {
		val cards = List(5, 4, 3, 2, 1)

		val player1Card = discrete.Uniform(cards:_*)
		val player2Card = Chain(player1Card, (card: Int) => discrete.Uniform(cards.filter(_ != card):_*))

		val player1Bet1 =RichCPD(player1Card,
			OneOf(5, 4, 3) -> Flip(0.9),
			* -> Flip(0.4))

		val player2Bet =RichCPD(player2Card, player1Bet1,
			(OneOf(5, 4), *) -> Flip(0.9),
			(*, OneOf(false)) -> Flip(0.5),
			(*, *) -> Flip(0.1))
		val player1Bet2 =Apply(player1Card, player1Bet1, player2Bet,(card: Int, bet11: Boolean, bet2: Boolean) =>
			!bet11 && bet2 && (card == 5 || card == 4))

		val player1Gain =Apply(player1Card, player2Card, player1Bet1, player2Bet, player1Bet2,(card1: Int, card2: Int, bet11: Boolean,bet2: Boolean, bet12: Boolean) =>
			if (!bet11 && !bet2) 0.0
			else if (bet11 && !bet2) 1.0
			else if (!bet11 && bet2 && !bet12) -1.0
			else if (card1 > card2) 2.0
			else -2.0)

		//Ex 4 a
		/*
		player1Card.observe(4)
		player1Bet1.observe(true)
		val alg1 = VariableElimination(player1Gain)
		alg1.start()
		alg1.stop()
		println("Expected gain for betting:" + alg1.mean(player1Gain))
		player1Bet1.observe(false)
		val alg2 = VariableElimination(player1Gain)
		alg2.start()
		alg2.stop()
		println("Expected gain for passing:" + alg2.mean(player1Gain))
		player1Card.unobserve()
		player1Bet1.unobserve()*/
		//Ex 4 b
		/*
		player2Card.observe(3)
		player1Bet1.observe(true)
		player2Bet.observe(true)
		val alg3 = VariableElimination(player1Gain)
		alg3.start()
		alg3.stop()
		println("Expected gain for betting:" + alg3.mean(player1Gain))
		player2Bet.observe(false)
		val alg4 = VariableElimination(player1Gain)
		alg4.start()
		alg4.stop()
		println("Expected gain for passing:" + alg4.mean(player1Gain))*/
		//Ex 5
		println("Player 1 card is Ace of Spades ,player 2 card is King of Spades ,player 1 bet and player 2 bet ")
		player1Card.observe(5)
		player2Card.observe(4)
		player1Bet1.observe(true)
		player2Bet.observe(true)
		val alg1 = VariableElimination(player1Gain)
		alg1.start()
		alg1.stop()
		println("Expected gain for betting:" + alg1.mean(player1Gain))
		player1Bet1.observe(false)
		val alg2 = VariableElimination(player1Gain)
		alg2.start()
		alg2.stop()
		println("Expected gain for passing:" + alg2.mean(player1Gain))
		player1Card.unobserve()
		player2Card.unobserve()
		player1Bet1.unobserve()
		player2Bet.unobserve()

		println("Player 1 card is Queen of Heart ,player2 card is King of Heart , player 1 fold and player 2 bet")
		player1Card.observe(1)
		player2Card.observe(3)
		player1Bet1.observe(false)
		player2Bet.observe(true)
		val alg3 = VariableElimination(player1Gain)
		alg3.start()
		alg3.stop()
		println("Expected gain for betting:" + alg3.mean(player1Gain))
		player2Bet.observe(false)
		val alg4 = VariableElimination(player1Gain)
		alg4.start()
		alg4.stop()
		println("Expected gain for passing:" + alg4.mean(player1Gain))
		player2Card.unobserve()
		player1Bet1.unobserve()
		player2Bet.unobserve()

		println("Player 1 card is King of Spades ,player 2 card is Ace of Spades , player 1 bet and player 2 bet")
		player1Card.observe(4)
		player2Card.observe(5)
		player1Bet1.observe(true)
		player2Bet.observe(true)
		val alg5 = VariableElimination(player1Gain)
		alg5.start()
		alg5.stop()
		println("Expected gain for betting:" + alg5.mean(player1Gain))
		player1Bet1.observe(false)
		val alg6 = VariableElimination(player1Gain)
		alg6.start()
		alg6.stop()
		println("Expected gain for passing:" + alg6.mean(player1Gain))
		player1Card.unobserve()
		player2Card.unobserve()
		player1Bet1.unobserve()
		player2Bet.unobserve()
	}
}