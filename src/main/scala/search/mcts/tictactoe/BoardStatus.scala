package search.mcts.tictactoe

sealed trait BoardStatus {
  def status: Int
}

case object InProgress extends BoardStatus { val status: Int = -1 }
case object Draw       extends BoardStatus { val status: Int = 0 }
case object P1         extends BoardStatus { val status: Int = 1 }
case object P2         extends BoardStatus { val status: Int = 2 }
