package search.mcts.montecarlo

import search.mcts.tictactoe.Board

import scala.util.Random

class State(var playerNo: Int, val board: Board = new Board) {

  var winScore: Double = 0
  var visitCount = 0

  def randomPlay(): Unit = {
   board.performMove(playerNo, Random.shuffle(board.emptyPositions).head)
  }

  def getAllPossibleStates: IndexedSeq[State] = {
    this.board.emptyPositions.map { position =>
      val player = getOpponent
      val newState = new State(player)
      newState.board.performMove(playerNo, position)
      newState
    }
  }

  def incrementVisit(): Unit = visitCount += 1

  def addScore(score: Double): Unit = winScore += score

  def getOpponent: Int = State.getOpponent(this.playerNo)

  def togglePlayer(): Unit = playerNo = getOpponent
}

object State {
  def getOpponent(playerNo: Int): Int = 3 - playerNo
}
