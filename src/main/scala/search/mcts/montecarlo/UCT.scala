package search.mcts.montecarlo

import search.mcts.tree.Node

object UCT {

  private val squareRoot2 = Math.sqrt(2)

  def uctValue(totalVisit: Int, nodeWinScore: Double, nodeVisit: Int): Double =
    if (nodeVisit == 0) Int.MaxValue
    else nodeWinScore / nodeVisit + squareRoot2 * Math.sqrt(Math.log(totalVisit) / nodeVisit)

  def findBestNodeWithUCT(node: Node): Node = {
    node.childArray.maxBy(c => uctValue(node.state.visitCount, c.state.winScore, c.state.visitCount))
  }
}
