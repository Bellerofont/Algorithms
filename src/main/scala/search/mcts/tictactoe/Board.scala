package search.mcts.tictactoe

trait Board {
  def performMove(player: Int, position: Position): Board
  def emptyPositions: IndexedSeq[Position]
  def checkStatus: BoardStatus
  def printBoard(): Unit
  def printBoardFlat(): Unit
}
