package search.mcts.tree

import java.util.UUID

import search.mcts.montecarlo.State

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

case class Node(var state: State, parent: Option[Node], childArray: ArrayBuffer[Node]) {
  def addChildren(newNodes: IndexedSeq[Node]): Unit = childArray.addAll(newNodes)

  val id: UUID = UUID.randomUUID()

  def getRandomChildNode: Node = Random.shuffle(childArray).head

  def getChildWithMaxScore: Node = childArray.maxBy(_.state.visitCount)

  def incrementVisit(): Unit = state = state.copy(visitCount = state.visitCount + 1)

  def addScore(score: Double): Unit = state = state.copy(winScore = state.winScore + score)

  override def toString: String = {
    s"""
       |Node: ${this.id}
       |Parent: ${this.parent.map(_.id).getOrElse(UUID.fromString("00000000-0000-0000-0000-000000000000"))}
       |ChildArray: ${this.childArray.mkString("<>", " + ", "><")}
       |State:
       |  Player: ${this.state.playerNo}
       |  WinScore: ${this.state.winScore}
       |  VisitCount: ${this.state.visitCount}
       |  Board: ${this.state.board}
       |  *****************************************
       |""".stripMargin}
}


