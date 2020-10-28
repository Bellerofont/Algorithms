package search.mcts.montecarlo

import search.mcts.tictactoe.{Board, InProgress}

object MonteCarloTreeSearchTest extends App {
  var board  = Board()
  var player = 1
  while (board.checkStatus == InProgress) {
    board = MonteCarloTreeSearch.findNextMove(board, player)
    player = State.getOpponent(player)
  }
  println(board.checkStatus)
  board.printBoard()
}
