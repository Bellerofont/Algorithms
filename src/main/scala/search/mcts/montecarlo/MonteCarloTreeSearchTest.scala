package search.mcts.montecarlo

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.{Nested, RepeatedTest, Test}
import search.mcts.tictactoe._
import search.mcts.tree.Node

import scala.collection.mutable.ArrayBuffer

object MonteCarloTreeSearchTest {

  @Nested
  class ticTacToeTest {

    @RepeatedTest(100)
    def fullTest(): Unit = {
      var board: Board = TicTacToeBoard()
      var player       = 1
      var round        = 1
      while (board.checkStatus == InProgress) {
        println(s"${">" * 25} ROUND $round ${"<" * 25}")
        round += 1
        board = MonteCarloTreeSearch.findNextMove(board, player)
        player = State.getOpponent(player)
      }
      val winStatus    = board.checkStatus
      assertEquals(Draw, winStatus)
    }

    @Test
    def findNextMove(): Unit = {
      val board  = TicTacToeBoard()
      val player = 1
      MonteCarloTreeSearch.findNextMove(board, player)
    }

    @Test
    def testUCT(): Unit = {
      val uctValue = 15.79
      assertEquals(UCT.uctValue(600, 300, 20), uctValue, 0.01)
    }

    @Test
    def performMoveEmptyBoard(): Unit = {
      var board                  = TicTacToeBoard()
      val initAvailablePositions = board.emptyPositions.size
      board = board.performMove(P1.status, Position(1, 1))
      val availablePositions     = board.emptyPositions.size
      assertEquals(8, availablePositions)
      assertTrue(initAvailablePositions > availablePositions)
    }

    @Test
    def getAllPossibleStates(): Unit = {
      val initState      = State(2, TicTacToeBoard())
      val possibleStates = initState.getAllPossibleStates
      assertEquals(9, possibleStates.length)
      assertTrue(possibleStates.forall(_.playerNo == 1))
      assertTrue(possibleStates.forall(_.board.asInstanceOf[TicTacToeBoard].boardValues.flatten.contains(1)))
    }

    @Test
    def expandNode(): Unit = {
      val emptyBoard = TicTacToeBoard()
      val rootNode   = Node(State(1, emptyBoard), None, ArrayBuffer.empty[Node])
      MonteCarloTreeSearch.expandNode(rootNode)
      assertEquals(9, rootNode.childArray.length)
      assertTrue(rootNode.childArray.forall(_.parent.contains(rootNode)))
      assertTrue(rootNode.childArray.forall(_.state.playerNo != rootNode.state.playerNo))
    }

    @Test
    def backPropagateRoot(): Unit = {
      val emptyBoard = TicTacToeBoard()
      val rootNode   = Node(State(1, emptyBoard), None, ArrayBuffer.empty[Node])
      assertEquals(0, rootNode.state.visitCount)
      MonteCarloTreeSearch.backPropagation(rootNode, 1)
      assertEquals(1, rootNode.state.visitCount)
    }

    @Test
    def simulateRandomPlayout(): Unit = {
      val emptyBoard = TicTacToeBoard()
      val rootNode   = Node(State(2, emptyBoard), None, ArrayBuffer.empty[Node])
      val node       = MonteCarloTreeSearch.selectPromisingNode(rootNode)
      MonteCarloTreeSearch.expandNode(node)
      println(node)
      println(MonteCarloTreeSearch.simulateRandomPlayout(node, 2))
    }

    @Test
    def backPropagateChildNode(): Unit = {
      val emptyBoard = TicTacToeBoard()
      val rootNode   = Node(State(2, emptyBoard), None, ArrayBuffer.empty[Node])

      MonteCarloTreeSearch.expandNode(rootNode)
      val playoutResult = MonteCarloTreeSearch.simulateRandomPlayout(rootNode.childArray.head, 2)
      MonteCarloTreeSearch.backPropagation(rootNode.childArray.head, playoutResult.status)
      assertEquals(1, rootNode.state.visitCount)

      MonteCarloTreeSearch.expandNode(rootNode.childArray.head)
      val playoutResult2 = MonteCarloTreeSearch.simulateRandomPlayout(rootNode.childArray.head.childArray.head, 1)
      MonteCarloTreeSearch.backPropagation(rootNode.childArray.head.childArray.head, playoutResult2.status)
      assertEquals(2, rootNode.state.visitCount)
    }

    @Test
    def checkStatus(): Unit = {
      val boardDraw       = TicTacToeBoard(3, Vector(Vector(1, 2, 1), Vector(1, 2, 2), Vector(2, 1, 1)))
      assertEquals(Draw, boardDraw.checkStatus)
      val boardP1Win      = TicTacToeBoard(3, Vector(Vector(1, 2, 1), Vector(1, 1, 2), Vector(2, 2, 1)))
      assertEquals(P1, boardP1Win.checkStatus)
      val boardP2Win      = TicTacToeBoard(3, Vector(Vector(1, 1, 2), Vector(1, 2, 1), Vector(2, 1, 2)))
      assertEquals(P2, boardP2Win.checkStatus)
      val boardInProgress = TicTacToeBoard(3, Vector(Vector(0, 0, 1), Vector(0, 2, 2), Vector(1, 0, 0)))
      assertEquals(InProgress, boardInProgress.checkStatus)
      val P1WinExtra      = TicTacToeBoard(3, Vector(Vector(2, 1, 0), Vector(2, 2, 0), Vector(1, 1, 1)))
      assertEquals(P1, P1WinExtra.checkStatus)
    }

    @Test
    def setScore(): Unit = {
      val node = Node(State(1, TicTacToeBoard()), None, ArrayBuffer.empty[Node])
      node.addScore(10)
      assertEquals(10, node.state.winScore)
      node.setScore()
      assertEquals(Int.MinValue, node.state.winScore)
    }

    @Test
    def addMinScoreToParent(): Unit = {
      val node = Node(State(1, TicTacToeBoard()), None, ArrayBuffer.empty[Node])
      MonteCarloTreeSearch.expandNode(node)
      node.childArray.head.parent.get.addScore(Int.MinValue)
      println(node)
    }

    @RepeatedTest(10)
    def findNextStepCustomStart1(): Unit = {
      val board    = TicTacToeBoard(
        3,
        Vector(
          Vector(2, 1, 0),
          Vector(0, 2, 0),
          Vector(1, 0, 1)
        )
      )
      val newBoard = MonteCarloTreeSearch.findNextMove(board, 2)
      assertEquals(2, newBoard.asInstanceOf[TicTacToeBoard].boardValues(2)(1))
    }
  }
}
