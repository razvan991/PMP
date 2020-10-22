package Lab4 

import com.cra.figaro.language._
import com.cra.figaro.library.compound._
import com.cra.figaro.algorithm.factored._

object HelloWorldsidesBed{
    // Model
    val sunnyToday = Flip(0.2)
    val wrongSide=Flip(0.5)
    val greetingToday = If(wrongSide,
        Constant("Oh no, not again"),
        If(sunnyToday,
        Select(0.6 -> "Hello, world!", 0.4 -> "Howdy, universe!"),
        Select(0.2 -> "Hello, world!", 0.8 -> "Oh no, not again")))

    val sunnyTomorrow = If(sunnyToday, Flip(0.8), Flip(0.05))
    val greetingTomorrow = If(sunnyTomorrow,
        Select(0.6 -> "Hello, world!", 0.4 -> "Howdy, universe!"),
        Select(0.2 -> "Hello, world!", 0.8 -> "Oh no, not again"))


    def main(args: Array[String]) {
        //println("Today's greeting is \"Hello, world!\" " + "with probability " + VariableElimination.probability(greetingToday, "Hello, world!") + ".")


        greetingToday.observe("Oh no, not again")
        println("If today's greeting is \"Oh no, not again\", today’s " + "weather is sunny with probability " + VariableElimination.probability(sunnyToday, true) + ".")
        //println("If today's greeting is \"Oh no, not again\", " + "tomorrow's greeting will be \"Oh no, not again\" " +"with probability " + VariableElimination.probability(greetingTomorrow, "Oh no, not again") + ".")
    }
} 