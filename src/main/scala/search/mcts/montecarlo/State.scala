package search.mcts.montecarlo

import search.mcts.tictactoe.{Board, Position}

import scala.util.Random

case class State(playerNo: Int, board: Board, winScore: Double = 0, visitCount: Int = 0) {

  def randomPlay(): State = {
   this.copy(board = performMove(Random.shuffle(board.emptyPositions).head))
  }

  def performMove(position: Position): Board = {
    board.performMove(playerNo, position)
  }

  def getAllPossibleStates: IndexedSeq[State] = {
    val player = getOpponent
    board.emptyPositions.map { position =>
      this.copy(playerNo = player, this.performMove(position))
    }
  }

  def getOpponent: Int = State.getOpponent(this.playerNo)

  def togglePlayer(): State = this.copy(playerNo = getOpponent)
}

object State {
  def getOpponent(playerNo: Int): Int = 3 - playerNo
}
