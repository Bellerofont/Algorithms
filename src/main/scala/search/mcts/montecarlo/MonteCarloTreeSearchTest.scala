package search.mcts.montecarlo

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test
import search.mcts.tictactoe.{Board, Draw, InProgress, P1, P2, Position}
import search.mcts.tree.Node

import scala.collection.mutable.ArrayBuffer


object MonteCarloTreeSearchTest {

  @Test
  def fullTest(): Unit = {
    var board = Board()
    var player = 1
    while (board.checkStatus == InProgress) {
      board = MonteCarloTreeSearch.findNextMove(board, player)
      player = State.getOpponent(player)
    }
    val winStatus = board.checkStatus
    board.printBoard()
    assertEquals(Draw, winStatus)
  }

  @Test
  def testUCT(): Unit = {
    val uctValue = 15.79
    assertEquals(UCT.uctValue(600, 300, 20), uctValue, 0.01)
  }

  @Test
  def givenEmptyBoard_whenPerformMove_thenLessAvailablePositions(): Unit = {
    var board = Board()
    val initAvailablePositions = board.emptyPositions.size
    board = board.performMove(P1.status, Position(1, 1))
    val availablePositions = board.emptyPositions.size
    assertEquals(8, availablePositions)
    assertTrue(initAvailablePositions > availablePositions)
  }

  @Test
  def givenInitBoardState_whenGetAllPossibleStates_thenNonEmptyList(): Unit = {
    val initState = new State(1, Board())
    val possibleStates = initState.getAllPossibleStates
    assertEquals(9, possibleStates.length)
    assertTrue(possibleStates.nonEmpty)
  }

  @Test
  def expandNode(): Unit = {
    val emptyBoard = Board()
    val rootNode = Node(new State(1, emptyBoard), None, ArrayBuffer.empty[Node])
    MonteCarloTreeSearch.expandNode(rootNode)
    assertEquals(9, rootNode.childArray.length)
    assertTrue(rootNode.childArray.forall(_.parent.contains(rootNode)))
  }

  @Test
  def backPropagateRoot(): Unit = {
    val emptyBoard = Board()
    val rootNode = Node(new State(1, emptyBoard), None, ArrayBuffer.empty[Node])
    assertEquals(0, rootNode.state.visitCount)
    MonteCarloTreeSearch.backPropagation(rootNode, 1)
    assertEquals(1, rootNode.state.visitCount)
  }

  @Test
  def simulateRandomPlayout(): Unit = {
    val emptyBoard = Board()
    val rootNode = Node(new State(1, emptyBoard), None, ArrayBuffer.empty[Node])
  }

//  def backPropagateChildNode(): Unit = {
//    val emptyBoard = Board()
//    val rootNode = Node(new State(1, emptyBoard), None, ArrayBuffer.empty[Node])
//    MonteCarloTreeSearch.expandNode(rootNode)
//    MonteCarloTreeSearch.backPropagation()
//  }

  @Test
  def checkStatus(): Unit = {
    val boardDraw = Board(3, Vector(
      Vector(1, 2, 1),
      Vector(1, 2, 2),
      Vector(2, 1, 1)))
    assertEquals(Draw, boardDraw.checkStatus)
    val boardP1Win = Board(3, Vector(
      Vector(1, 1, 1),
      Vector(1, 2, 2),
      Vector(2, 2, 1)))
    assertEquals(P1, boardP1Win.checkStatus)
    val boardP2Win = Board(3, Vector(
      Vector(1, 1, 2),
      Vector(1, 2, 1),
      Vector(2, 1, 2)))
    assertEquals(P2, boardP2Win.checkStatus)
    val boardInProgress= Board(3, Vector(
      Vector(0, 0, 1),
      Vector(0, 2, 2),
      Vector(1, 0, 0)))
    assertEquals(InProgress, boardInProgress.checkStatus)
  }
}
