package search.mcts.montecarlo

import search.mcts.tictactoe.{Board, InProgress}

object MonteCarloTreeSearchTest extends App {
  var board = new Board()
  var player = 1
  var movesLeft = board.boardSize * board.boardSize
  while (movesLeft > 0 && board.checkStatus == InProgress) {
    board = MonteCarloTreeSearch.findNextMove(board, player)
    player = State.getOpponent(player)
    movesLeft -= 1
  }

  board.printBoard()
  println(movesLeft)
}
