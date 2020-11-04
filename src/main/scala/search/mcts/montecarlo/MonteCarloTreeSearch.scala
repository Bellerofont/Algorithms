package search.mcts.montecarlo

import search.mcts.tictactoe.{Board, BoardStatus, InProgress}
import search.mcts.tree.{Node, Tree}

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

object MonteCarloTreeSearch {
  private val WIN_SCORE = 4
  private val level     = 2

  @tailrec
  final def selectPromisingNode(rootNode: Node): Node =
    if (rootNode.childArray.isEmpty) rootNode
    else selectPromisingNode(UCT.findBestNodeWithUCT(rootNode))

  def expandNode(node: Node): Unit = {
    val newNodes = node.state.getAllPossibleStates.map { state =>
      Node(state, Some(node), ArrayBuffer.empty[Node])
    }
    node.addChildren(newNodes)
  }

  @tailrec
  final def backPropagation(nodeToExplore: Node, playerNo: Int): Unit = {
    nodeToExplore.incrementVisit()
    if (nodeToExplore.state.playerNo == playerNo) nodeToExplore.addScore(WIN_SCORE)
    if (nodeToExplore.parent.isDefined) backPropagation(nodeToExplore.parent.get, playerNo)
  }

  def simulateRandomPlayout(node: Node, opponent: Int): BoardStatus = {
    val tempNode = node.copy()
    var boardStatus = tempNode.state.board.checkStatus
    var tempState   = tempNode.state
    if (boardStatus.status == opponent) {
      tempNode.parent.get.setScore()
    } else
      while (boardStatus == InProgress) {
        tempState = tempState.togglePlayer()
        tempState = tempState.randomPlay()
        boardStatus = tempState.board.checkStatus
      }
    boardStatus
  }

  private def getMillisForCurrentLevel = 2 * (level - 1) + 1

  def findNextMove(board: Board, playerNo: Int): Board = {
    val start = System.currentTimeMillis()
    val end   = start + 60 * getMillisForCurrentLevel

    val opponent = State.getOpponent(playerNo)
    val state    = State(opponent, board)
    val tree     = Tree(Node(state, None, ArrayBuffer.empty[Node]))

    while (System.currentTimeMillis() < end) {
      var promisingNode = selectPromisingNode(tree.root)
      if (promisingNode.state.board.checkStatus == InProgress) expandNode(promisingNode)

      if (promisingNode.childArray.nonEmpty) promisingNode = promisingNode.getRandomChildNode
      val playoutResult = simulateRandomPlayout(promisingNode, opponent)

      backPropagation(promisingNode, playoutResult.status)
    }

    val winner = tree.root.getChildWithMaxScore
    tree.root = winner
    winner.state.board.printBoard()
    println()
    winner.state.board
  }

}
