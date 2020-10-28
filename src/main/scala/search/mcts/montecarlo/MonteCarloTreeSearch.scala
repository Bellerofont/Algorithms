package search.mcts.montecarlo

import search.mcts.tictactoe.{Board, BoardStatus, InProgress}
import search.mcts.tree.{Node, Tree}

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

object MonteCarloTreeSearch {
  private val WIN_SCORE = 10
  private val level     = 3

  @tailrec
  final def selectPromisingNode(rootNode: Node): Node =
    if (rootNode.childArray.isEmpty) rootNode
    else selectPromisingNode(UCT.findBestNodeWithUCT(rootNode))

  def expandNode(node: Node): Unit = {
    val newNodes = node.state.getAllPossibleStates.map { state =>
      val newState = new State(state.getOpponent)
      new Node(newState, Some(node), ArrayBuffer.empty[Node])
    }
    node.childArray ++= newNodes
  }

  @tailrec
  final def backPropagation(nodeToExplore: Node, playerNo: Int): Unit = {
    nodeToExplore.state.incrementVisit()
    if (nodeToExplore.state.playerNo == playerNo) nodeToExplore.state.addScore(WIN_SCORE)
    if (nodeToExplore.parent.isDefined) backPropagation(nodeToExplore.parent.get, playerNo)
  }

  def simulateRandomPlayout(node: Node): BoardStatus = {
    var boardStatus = node.state.board.checkStatus
    val tempState   = node.state
    if (boardStatus.status == node.state.getOpponent) {
      node.parent.get.state.addScore(Int.MinValue)
      boardStatus
    } else {
      while (boardStatus == InProgress) {
        tempState.togglePlayer()
        tempState.randomPlay()
        boardStatus = tempState.board.checkStatus
      }
      boardStatus
    }

  }

  private def getMillisForCurrentLevel = 2 * (level - 1) + 1

  def findNextMove(board: Board, playerNo: Int): Board = {
    val start = System.currentTimeMillis()
    val end   = start + 60 * getMillisForCurrentLevel

    val opponent = State.getOpponent(playerNo)
    val state    = new State(opponent, board)
    val tree     = Tree(new Node(state, None, ArrayBuffer.empty[Node]))

    while (System.currentTimeMillis() < end) {
      val promisingNode = selectPromisingNode(tree.root)
      if (promisingNode.state.board.checkStatus == InProgress) expandNode(promisingNode)

      val playoutResult = simulateRandomPlayout(
        if (promisingNode.childArray.nonEmpty) promisingNode.getRandomChildNode else promisingNode
      )

      backPropagation(promisingNode, playoutResult.status)
    }

    val winner = tree.root.getChildWithMaxScore
    tree.root = winner
    winner.state.board
  }

}
