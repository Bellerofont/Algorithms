package search.mcts.tictactoe

case class TicTacToeBoard(boardSize: Int, boardValues: Vector[Vector[Int]]) extends Board {

  def emptyPositions: IndexedSeq[Position] =
    for {
      i <- 0 until boardSize
      j <- 0 until boardSize if boardValues(i)(j) == 0
    } yield Position(i, j)

  def performMove(player: Int, position: Position): TicTacToeBoard =
    this.copy(
      boardValues = boardValues.updated(position.x, boardValues(position.x).updated(position.y, player))
    )

  def checkStatus: BoardStatus = {
    val rows = for {
      i <- boardValues
      r  = checkForWin(i)
    } yield r

    val columns = for {
      i <- boardValues.transpose
      r  = checkForWin(i)
    } yield r

    val diag1 = checkForWin(for {
      i <- 0 until boardSize
    } yield boardValues(i)(i))

    val diag2 = checkForWin(for {
      i <- 0 until boardSize
    } yield boardValues(i)(boardSize - 1 - i))

    val result = rows :++ columns :+ diag1 :+ diag2

    (result.find(_ != 0), emptyPositions.isEmpty) match {
      case (Some(1), _) => P1
      case (Some(2), _) => P2
      case (_, true)    => Draw
      case _            => InProgress
    }
  }

  private def checkForWin(row: Iterable[Int]): Int =
    if (row.forall(x => x == row.head)) row.head else 0

  def printBoard(): Unit =
    println(boardValues.map(_.mkString(" ")).mkString("\n"))

  def printBoardFlat(): Unit =
    println(s"BOARD: ${boardValues.flatten.mkString(" ")}")
}

object TicTacToeBoard {
  def apply(boardSize: Int): TicTacToeBoard                   = TicTacToeBoard(boardSize, Vector.fill(boardSize, boardSize)(0))
  def apply(boardValues: Vector[Vector[Int]]): TicTacToeBoard = TicTacToeBoard(boardValues.length, boardValues)
  def apply(): TicTacToeBoard                                 = TicTacToeBoard(3, Vector.fill(3, 3)(0))
}
