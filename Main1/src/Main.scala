class Connect4(var board: Array[Array[Char]] =
               Array(Array('_', '_', '_', '_', '_', '_', '_'),
                 Array('_', '_', '_', '_', '_', '_', '_'),
                 Array('_', '_', '_', '_', '_', '_', '_'),
                 Array('_', '_', '_', '_', '_', '_', '_'),
                 Array('_', '_', '_', '_', '_', '_', '_'),
                 Array('_', '_', '_', '_', '_', '_', '_'))) {

  def printBoard(): Unit = {
    for (i <- 0 until 6) {
      print('|')
      for (j <- 0 until 7) {
        print("_")
        print(board(i)(j))
        print("_|")
      }
      println()
    }
    println()
    print('|')
    for (i <- 1 until 8) {
      print("_")
      print(i)
      print("_|")
    }
    println()
  }

  def move(col: Int, player: Char): Array[Array[Char]] = {
    for (i <- 5 to 0 by -1) {
      if (board(i)(col - 1) == '_') {
        board(i)(col - 1) = player
        return board
      }
    }
    return board
  }

  def getplayermove(player: Char): Int = {
    print("Next move: ")
    var col = scala.io.StdIn.readInt()
    while ((col < 1 || 7 < col) || (board(0)(col - 1) != '_')) {
      print("Invalid move. Try again: ")
      col = scala.io.StdIn.readInt()
    }
    this.move(col, player)
    return col
  }

  def draw(): Boolean = {
    for (i <- 0 until 7) {
      if (board(0)(i) == '_')
        return false
    }
    return true
  }

  def winner(player: Char, col: Int): Boolean = {
    if (col == 7)
      print("weird")
    for (i <- 5 until 2 by -1) {
      if (board(i)(col) == player && board(i - 1)(col) == player && board(i - 2)(col) == player && board(i - 3)(col) == player)
        return true
    }

    for (i <- 5 to 0 by -1; j <- 0 until 4) {
      if (board(i)(j) == player && board(i)(j + 1) == player && board(i)(j + 2) == player && board(i)(j + 3) == player)
        return true
    }

    for (i <- 5 to 3 by -1; j <- 0 until 4) {
      if (board(i)(j) == player && board(i - 1)(j + 1) == player && board(i - 2)(j + 2) == player && board(i - 3)(j + 3) == player)
        return true
    }

    for (i <- 5 to 3 by -1; j <- 3 until 7) {
      if (board(i)(j) == player && board(i - 1)(j - 1) == player && board(i - 2)(j - 2) == player && board(i - 3)(j - 3) == player)
        return true
    }
    return false
  }

  def gethorizontals(player: Char): (Int, Int) = {
    var numthrees = 0
    var numtwos = 0

    for (i <- 5 to 0 by -1; j <- 0 to 3) {
      var numpieces = 0
      var numempty = 0
      if (board(i)(j) == player)
        numpieces += 1
      if (board(i)(j + 1) == player)
        numpieces += 1
      if (board(i)(j + 2) == player)
        numpieces += 1
      if (board(i)(j + 3) == player)
        numpieces += 1
      if (board(i)(j) == '_')
        numempty += 1
      if (board(i)(j + 1) == '_')
        numempty += 1
      if (board(i)(j + 2) == '_')
        numempty += 1
      if (board(i)(j + 3) == '_')
        numempty += 1
      if (numpieces == 3 && numempty == 1)
        numthrees += 1
      if (numpieces == 2 && numempty == 2)
        numtwos += 1
    }
    return (numtwos, numthrees)
  }

  def getverticals(player: Char): (Int, Int) = {
    var numthrees = 0

    var numtwos = 0
    for (i <- 5 to 3 by -1; j <- 0 to 6) {
      var numpieces = 0
      var numempty = 0
      if (board(i)(j) == player)
        numpieces += 1
      if (board(i - 1)(j) == player)
        numpieces += 1
      if (board(i - 2)(j) == player)
        numpieces += 1
      if (board(i - 3)(j) == player)
        numpieces += 1
      if (board(i)(j) == '_')
        numempty += 1
      if (board(i - 1)(j) == '_')
        numempty += 1
      if (board(i - 2)(j) == '_')
        numempty += 1
      if (board(i - 3)(j) == '_')
        numempty += 1
      if (numpieces == 3 && numempty == 1)
        numthrees += 1
      if (numpieces == 2 && numempty == 2)
        numtwos += 1
    }
    return (numtwos, numthrees)
  }

  def getrightdiags(player: Char): (Int, Int) = {
    var numthrees = 0

    var numtwos = 0
    for (i <- 5 to 3 by -1; j <- 0 to 3) {
      var numpieces = 0
      var numempty = 0
      if (board(i)(j) == player)
        numpieces += 1
      if (board(i - 1)(j + 1) == player)
        numpieces += 1
      if (board(i - 2)(j + 2) == player)
        numpieces += 1
      if (board(i - 3)(j + 3) == player)
        numpieces += 1
      if (board(i)(j) == '_')
        numempty += 1
      if (board(i - 1)(j + 1) == '_')
        numempty += 1
      if (board(i - 2)(j + 2) == '_')
        numempty += 1
      if (board(i - 3)(j + 3) == '_')
        numempty += 1
      if (numpieces == 3 && numempty == 1)
        numthrees += 1
      if (numpieces == 2 && numempty == 2)
        numtwos += 1
    }
    return (numtwos, numthrees)
  }

  def getleftdiags(player: Char): (Int, Int) = {
    var numthrees = 0
    var numtwos = 0
    for (i <- 5 to 3 by -1; j <- 3 to 6) {
      var numpieces = 0
      var numempty = 0
      if (board(i)(j) == player)
        numpieces += 1
      if (board(i - 1)(j - 1) == player)
        numpieces += 1
      if (board(i - 2)(j - 2) == player)
        numpieces += 1
      if (board(i - 3)(j - 3) == player)
        numpieces += 1
      if (board(i)(j) == '_')
        numempty += 1
      if (board(i - 1)(j - 1) == '_')
        numempty += 1
      if (board(i - 2)(j - 2) == '_')
        numempty += 1
      if (board(i - 3)(j - 3) == '_')
        numempty += 1
      if (numpieces == 3 && numempty == 1)
        numthrees += 1
      if (numpieces == 2 && numempty == 2)
        numtwos += 1
    }
    return (numtwos, numthrees)
  }

  def evalstate(): Int = {
    var score = 0

    val (oh2, oh3) = gethorizontals('O')
    score += 5 * oh2
    score += 20 * oh3

    val (xh2, xh3) = gethorizontals('X')
    score -= 5 * xh2
    score -= 20 * xh3

    val (ov2, ov3) = getverticals('O')
    score += 5 * ov2
    score += 20 * ov3

    val (xv2, xv3) = getverticals('X')
    score -= 5 * xv2
    score -= 20 * xv3

    val (or2, or3) = getrightdiags('O')
    score += 5 * or2
    score += 20 * or3

    val (xr2, xr3) = getrightdiags('X')
    score -= 5 * xr2
    score -= 20 * xr3

    val (ol2, ol3) = getleftdiags('O')
    score += 5 * ol2
    score += 20 * ol3

    val (xl2, xl3) = getleftdiags('X')
    score -= 5 * xl2
    score -= 20 * xl3

    return score
  }
  def copymyarray(copied: Array[Array[Char]]){ //: Array[Array[Char]] = {
    //var copied = Array.ofDim[Char](6,7)
    for(i <- 0 to 5; j <- 0 to 6){
      board(i)(j) = copied(i)(j)
    }
  }
  def revert(last: Int) {
    var i = 0
    while (board(i)(last - 1) == '_'){
      i += 1
    }
    board(i)(last - 1) = '_'
  }
  def getcompmove(state: Array[Array[Char]], alpha: Int, beta: Int, player: Boolean, depth: Int, last: Int): (Int, Int) = {
    var newboard = new Connect4()
    newboard.copymyarray(state)

    var winR = newboard.winner('O', last)
    var winY = newboard.winner('X', last)

    if (depth == 0) {
      var score = newboard.evalstate()
      return (score, 0)
    }
    if (winR)
      return (1000 + depth, 0)
    if (winY)
      return (-1000 - depth, 0)
    if (newboard.draw())
      return (0, 0)

    if (player) {
      var maxval = -1000000

      var moves = 0
      var i = 1
      var alph = alpha
      while (i < 8 && !(beta <= maxval)) {
        if (state(0)(i - 1) == '_') {
          newboard.copymyarray(state)
          newboard.move(i, 'O')
          var (checkmove, trash) = newboard.getcompmove(newboard.board, alph, beta, false, depth - 1, i - 1)
          if (checkmove > maxval) {
            maxval = checkmove
            moves = i
          }
          alph = math.max(alph, maxval)
        }
        //revert(i)
        i += 1
      }
      return (maxval, moves)
    }
    else {
      var minval = 1000000
      var move = 0
      var i = 1
      var bet = beta
      while (i < 8 && !(minval <= alpha)) {
        if (state(0)(i - 1) == '_') {
          newboard.copymyarray(state)
          newboard.move(i, 'X')
          var (checkmove, trash) = newboard.getcompmove(newboard.board, alpha, bet, true, depth - 1, i - 1)
          if (checkmove < minval)
          {
            minval = checkmove
            move = i
          }
          bet = math.min(bet, minval)
        }
        //revert(i)
        i += 1
      }
      return (minval, move)
    }
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    var player1 = 'X'
    var player2 = 'O'
    var game = new Connect4()
    game.printBoard()
    println()
    print("Enter number for starting player. 0: computer 1: user ")
    var turn = scala.io.StdIn.readInt()
    print("Enter number for difficulty. 1: easy 2: medium 3: hard ")
    var diff = scala.io.StdIn.readInt() * 2
    var last = 1
    while (!game.winner(player1, last - 1) && !game.winner(player2, last - 1) && !game.draw()) {
      if (turn % 2 == 0) {
        last = game.getplayermove(player1)
        turn += 1
      }
      else {
        var (trash, keep) = game.getcompmove(game.board, -10000, 10000, true, diff, last - 1)
        last = keep
        game.move(last, player2)
        println("Computer move: " + last)
        turn -= 1
      }
      game.printBoard()
      println()

    }
    if (game.winner(player1, last - 1)) {
      print("X Wins!")
    }
    else if(game.winner(player2, last - 1))
      print("O Wins!")
    else
      print("Draw!")
  }

}