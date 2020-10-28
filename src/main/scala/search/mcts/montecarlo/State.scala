package search.mcts.montecarlo

import search.mcts.tictactoe.{Board, Position}

import scala.util.Random

class State(var playerNo: Int, var board: Board) {

  var winScore: Double = 0
  var visitCount = 0

  def randomPlay(): Unit = {
   performMove(Random.shuffle(board.emptyPositions).head)
  }

  def performMove(position: Position): Unit = {
    board = board.performMove(playerNo, position)
  }

  def getAllPossibleStates: IndexedSeq[State] = {
    val player = getOpponent
    board.emptyPositions.map { position =>
      val newState = new State(player, board)
      newState.performMove(position)
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
