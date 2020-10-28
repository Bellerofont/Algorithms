package search.mcts.tree

import search.mcts.montecarlo.State

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class Node(val state: State, val parent: Option[Node], val childArray: ArrayBuffer[Node]) {

  def getRandomChildNode: Node = Random.shuffle(childArray).head

  def getChildWithMaxScore: Node = childArray.maxBy(_.state.visitCount)
}


