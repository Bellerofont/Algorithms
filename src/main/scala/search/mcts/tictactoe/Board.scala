package search.mcts.tictactoe

case class Board(boardSize: Int, boardValues: Vector[Vector[Int]], totalMoves: Int = 0) {

  def emptyPositions: IndexedSeq[Position] =
    for {
      i <- 0 until boardSize
      j <- 0 until boardSize if boardValues(i)(j) == 0
    } yield Position(i, j)

  def performMove(player: Int, position: Position): Board =
    this.copy(
      boardValues = boardValues.updated(position.x, boardValues(position.x).updated(position.y, player)),
      totalMoves = totalMoves + 1
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

    val result = (rows :++ columns :+ diag1 :+ diag2).find(_ != 0)

    (result, emptyPositions.isEmpty) match {
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

object Board {
  def apply(boardSize: Int): Board                   = new Board(boardSize, Vector.fill(boardSize, boardSize)(0))
  def apply(boardValues: Vector[Vector[Int]]): Board = new Board(boardValues.length, boardValues)
  def apply(): Board                                 = new Board(3, Vector.fill(3, 3)(0))
}
