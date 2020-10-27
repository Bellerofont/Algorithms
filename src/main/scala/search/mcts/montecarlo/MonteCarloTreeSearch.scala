package search.mcts.montecarlo

import search.mcts.tree.Node

import scala.annotation.tailrec

case class MonteCarloTreeSearch()

object MonteCarloTreeSearch {

  @tailrec
  def selectPromisingNode(rootNode: Node): Node = {
    if (rootNode.childArray.isEmpty) rootNode
    else selectPromisingNode(UCT.findBestNodeWithUCT(rootNode))
  }

  def expandNode(node: Node): Unit = {
    node.state.getAllPossibleStates.foreach {
      state => Node(state, node)
      node.childArray
    }
  }

}
