package search.mcts.tictactoe

import scala.collection.mutable.ArrayBuffer

class Board(val boardSize: Int = 3) {

  private val boardValues = ArrayBuffer.fill(boardSize, boardSize)(0)

  private var totalMoves = 0

  def emptyPositions: IndexedSeq[Position] =
    for {
      i <- 0 until boardSize
      j <- 0 until boardSize if boardValues(i)(j) == 0
    } yield Position(i, j)

  def performMove(player: Int, position: Position): Unit = {
    totalMoves += 1
    boardValues(position.x)(position.y) = player
  }

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

    val result = rows.addAll(columns).addOne(diag1).addOne(diag2).find(_ != 0)

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
}
