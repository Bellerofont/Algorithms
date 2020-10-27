package search.mcts.montecarlo

import search.mcts.tictactoe.Board

case class State(board: Board, playerNo: Int, visitCount: Int, winScore: Double) {
  def getAllPossibleStates: List[State] = List.empty[State]
}

object State {

  def randomPlay(): Unit = ???
}
