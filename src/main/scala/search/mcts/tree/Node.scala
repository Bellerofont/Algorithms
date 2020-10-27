package search.mcts.tree

import search.mcts.montecarlo.State

case class Node(state: State, parent: Node, childArray: List[Node] = List.empty[Node])
