package Lab5

import com.cra.figaro.language.{Apply, Chain, Flip}
import com.cra.figaro.library.atomic.discrete
import com.cra.figaro.library.compound.{*, OneOf, RichCPD}

class Ex3 {
  //Am incercat sa fac si ex3 cu 2 carti dar am o eroare la cartea a 2-a a jucatorului 2 "overload Chain"
  /*val cards = List(5, 4, 3, 2, 1)

  val player1Card = discrete.Uniform(cards:_*)
  val player1Card2 = Chain(player1Card, (card: Int) => discrete.Uniform(cards.filter(_ != card):_*))

  val player2Card = Chain(player1Card,player1Card2, (card: Int,card2: Int) => discrete.Uniform(cards.filter(_ != card):_*,cards.filter(_ != card2):_*))
  val player2Card2 = Chain(player1Card,player1Card2,player2Card, (card: Int,card2: Int,card3 :Int) => discrete.Uniform(cards.filter(_ != card):_*,cards.filter(_ != card2):_*,cards.filter(_ != card3):_*))

  val player1Bet1 =RichCPD(player1Card,player1Card2,
    (OneOf(5, 4, 3),OneOf(5, 4, 3)) -> Flip(0.9),
    (*,*) -> Flip(0.4))

  val player2Bet =RichCPD(player2Card, player1Bet1,player2Card2
    (OneOf(5, 4), *,OneOf(5,4)) -> Flip(0.9),
    (*, OneOf(false),*) -> Flip(0.5),
    (*, *,*) -> Flip(0.1))
  val player1Bet2 =Apply(player1Card, player1Bet1, player2Bet,(card: Int, bet11: Boolean, bet2: Boolean) =>
    !bet11 && bet2 && (card == 5 || card == 4))

  val player1Gain =Apply(player1Card, player2Card, player1Bet1, player2Bet, player1Bet2,(card1: Int, card2: Int, bet11: Boolean,bet2: Boolean, bet12: Boolean) =>
    if (!bet11 && !bet2) 0.0
    else if (bet11 && !bet2) 1.0
    else if (!bet11 && bet2 && !bet12) -1.0
    else if (card1 > card2) 2.0
    else -2.0)*/
}
