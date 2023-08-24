package com.example.navigationcomponent

import kotlin.random.Random
import kotlin.random.nextInt

enum class Result{
    WIN, DRAW, LOSS
}

class JokenpoEngine(private val availablePlays: Array<String>) {

    var aiPlay = "Papel"
    private fun getPlay():String{
        val valueRandom = Random.nextInt(0,3)
        return availablePlays[valueRandom]
    }

    fun calculateResult(player: String): Result{
        aiPlay = getPlay()
        return when{
            player == aiPlay -> Result.DRAW
            player == "Pedra" && aiPlay == "Tesoura" -> Result.WIN
            player == "Pedra" && aiPlay == "Papel" -> Result.LOSS
            player == "Papel" && aiPlay == "Pedra" -> Result.WIN
            player == "Papel" && aiPlay == "Tesoura" -> Result.LOSS
            player == "Tesoura" && aiPlay == "Papel" -> Result.WIN
            else -> Result.LOSS
        }
    }

}