package pathfinding.dijkstra

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

case class Path(start: Int, end: Int)

object Dijkstra {
  val distance: mutable.Map[Int, Int] = mutable.Map[Int, Int]()
  val path: mutable.Map[Int, Int]     = mutable.Map[Int, Int]()

  def shortestPath(graph: Map[Int, ArrayBuffer[(Int, Int)]], start: Int, end: Int): Int = {
    val priorityQueue: mutable.PriorityQueue[(Int, Int)] =
      mutable.PriorityQueue[(Int, Int)]()(Ordering.by((_: (Int, Int))._2).reverse)
    distance += start -> 0
    path += start     -> 0
    priorityQueue.enqueue((start, distance(start)))

    while (priorityQueue.nonEmpty) {
      val (current, dist) = priorityQueue.dequeue()
      if (current == end) priorityQueue.clear()
      else
        for (neighbor <- graph(current))
          if (!distance.contains(neighbor._1) || distance(neighbor._1) > dist + neighbor._2) {
            distance += neighbor._1 -> (dist + neighbor._2)
            path += neighbor._1     -> current
            priorityQueue.enqueue((neighbor._1, distance(neighbor._1)))
          }
    }

    distance(end)
  }

  def buildGraph(source: Source): Map[Int, ArrayBuffer[(Int, Int)]] = {
    val edges                                                = source.getLines().toList.tail
    val graph: mutable.HashMap[Int, ArrayBuffer[(Int, Int)]] = mutable.HashMap.empty
    for (edge <- edges.map(s => s.split(" "))) {
      val from   = edge.head.toInt
      val to     = edge(1).toInt
      val weight = edge(2).toInt
      if (graph.contains(from))
        graph(from) += to -> weight
      else graph += from  -> ArrayBuffer((to, weight))
      true
    }
    graph.toMap
  }

  def printPath(start: Int, end: Int): Unit = {
    println("path:")
    var current = end
    while (path(current) != 0) {
      print(s"$current <-")
      current = path(current)
    }
    println(start)
  }

  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("testData/USA-FLA.txt")
    val graph  = buildGraph(source)
    source.close()
    val start  = 100562
    val end    = 1070345
    println("start = " + start + " end = " + end)
    println("Shortest distance = " + shortestPath(graph, start, end))
    printPath(start, end)
  }

}
