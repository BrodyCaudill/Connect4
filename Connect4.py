from copy import deepcopy


# The ConnectFour class contains all the functions required
# for evaluating game state and changing the game state
# Create the ConnectFour Board
# make a ConnectFour object

class ConnectFour:

    def __init__(self):
        self.board = [['_', '_', '_', '_', '_', '_', '_'],
                      ['_', '_', '_', '_', '_', '_', '_'],
                      ['_', '_', '_', '_', '_', '_', '_'],
                      ['_', '_', '_', '_', '_', '_', '_'],
                      ['_', '_', '_', '_', '_', '_', '_'],
                      ['_', '_', '_', '_', '_', '_', '_']]

    # Print the ConnectFour board
    def printboard(self):
        for i in self.board:
            print("|", end='')
            for j in i:
                print("_", end='')
                print(j, end="_|")
            print()
        print()
        print("|", end='')
        for i in range(1, 8):
            print("_", end='')
            print(i, end="_|")
        print()

    # Given a column number and player character
    # place the players token at the first empty slot bottom up
    def move(self, col, player):
        for row in reversed(range(6)):
            if self.board[row][col - 1] == '_':
                self.board[row][col - 1] = player
                return self.board
        print("invalid move, please try again.")
        self.move(col, player)

    # Asks for input for a players desired move
    # Validation is only done for full columns or out of range columns
    def getplayermove(self, player, move):
        col = int(input("Next move: "))
        while (col < 1 or 7 < col) or (self.board[0][col - 1] != '_'):
            col = int(input("invalid move, please try again: "))
        move = col
        self.move(col, player)

    # draw checks to see if all spaces on the board are occupied and
    # therefore no more moves can be made
    def draw(self):
        for i in range(8):
            if self.board[0][i] == '_':
                return False
        return True

    # Given a player, winner checks to see if that player has won the game
    # by getting four pieces in a row anywhere on the board
    def winner(self, player, col):

        for i in reversed(range(3, 6)):
            if self.board[i][col] == player and self.board[i - 1][col] == player and self.board[i - 2][col] == player \
                    and self.board[i - 3][col] == player:
                return True

        for i in reversed(range(6)):
            for j in range(4):
                if self.board[i][j] == player and self.board[i][j + 1] == player and self.board[i][j + 2] == player \
                        and self.board[i][j + 3] == player:
                    return True

        for i in reversed(range(3, 6)):
            for j in range(4):
                if self.board[i][j] == player and self.board[i - 1][j + 1] == player and \
                        self.board[i - 2][j + 2] == player and self.board[i - 3][j + 3] == player:
                    return True

        for i in reversed(range(3, 6)):
            for j in range(3, 7):
                if self.board[i][j] == player and self.board[i - 1][j - 1] == player and \
                        self.board[i - 2][j - 2] == player and self.board[i - 3][j - 3] == player:
                    return True
        return False

    # Returns the number of two and three pieces within a span of 4 horizontal spaces
    def gethorizontals(self, player):
        numthrees = 0
        numtwos = 0
        for i in reversed(range(6)):
            for j in range(4):
                numpieces = 0
                numempty = 0
                if self.board[i][j] == player:
                    numpieces += 1
                if self.board[i][j + 1] == player:
                    numpieces += 1
                if self.board[i][j + 2] == player:
                    numpieces += 1
                if self.board[i][j + 3] == player:
                    numpieces += 1
                if self.board[i][j] == '_':
                    numempty += 1
                if self.board[i][j + 1] == '_':
                    numempty += 1
                if self.board[i][j + 2] == '_':
                    numempty += 1
                if self.board[i][j + 3] == '_':
                    numempty += 1
                if numpieces == 3 and numempty == 1:
                    numthrees += 1
                if numpieces == 2 and numempty == 2:
                    numtwos += 1
        return (numtwos, numthrees)

    # Returns the number of two and three pieces within a span of 4 vertical spaces
    def getverticals(self, player):
        numthrees = 0
        numtwos = 0
        for i in reversed(range(3, 6)):
            for j in range(7):
                numpieces = 0
                numempty = 0
                if self.board[i][j] == player:
                    numpieces += 1
                if self.board[i - 1][j] == player:
                    numpieces += 1
                if self.board[i - 2][j] == player:
                    numpieces += 1
                if self.board[i - 3][j] == player:
                    numpieces += 1
                if self.board[i][j] == '_':
                    numempty += 1
                if self.board[i - 1][j] == '_':
                    numempty += 1
                if self.board[i - 2][j] == '_':
                    numempty += 1
                if self.board[i - 3][j] == '_':
                    numempty += 1
                if numpieces == 3 and numempty == 1:
                    numthrees += 1
                if numpieces == 2 and numempty == 2:
                    numtwos += 1
        return (numtwos, numthrees)

    # Returns the number of two and three pieces within a span of 4 right diagonal spaces
    def getrightdiags(self, player):
        numthrees = 0
        numtwos = 0
        for i in reversed(range(3, 6)):
            for j in range(4):
                numpieces = 0
                numempty = 0
                if self.board[i][j] == player:
                    numpieces += 1
                if self.board[i - 1][j + 1] == player:
                    numpieces += 1
                if self.board[i - 2][j + 2] == player:
                    numpieces += 1
                if self.board[i - 3][j + 3] == player:
                    numpieces += 1
                if self.board[i][j] == '_':
                    numempty += 1
                if self.board[i - 1][j + 1] == '_':
                    numempty += 1
                if self.board[i - 2][j + 2] == '_':
                    numempty += 1
                if self.board[i - 3][j + 3] == '_':
                    numempty += 1
                if numpieces == 3 and numempty == 1:
                    numthrees += 1
                if numpieces == 2 and numempty == 2:
                    numtwos += 1
        return (numtwos, numthrees)

    # Returns the number of two and three pieces within a span of 4 left diagonal spaces
    def getleftdiags(self, player):
        numthrees = 0
        numtwos = 0
        for i in reversed(range(3, 6)):
            for j in range(3, 7):
                numpieces = 0
                numempty = 0
                if self.board[i][j] == player:
                    numpieces += 1
                if self.board[i - 1][j - 1] == player:
                    numpieces += 1
                if self.board[i - 2][j - 2] == player:
                    numpieces += 1
                if self.board[i - 3][j - 3] == player:
                    numpieces += 1
                if self.board[i][j] == '_':
                    numempty += 1
                if self.board[i - 1][j - 1] == '_':
                    numempty += 1
                if self.board[i - 2][j - 2] == '_':
                    numempty += 1
                if self.board[i - 3][j - 3] == '_':
                    numempty += 1
                if numpieces == 3 and numempty == 1:
                    numthrees += 1
                if numpieces == 2 and numempty == 2:
                    numtwos += 1
        return (numtwos, numthrees)

    # Returns the score of a state without a winner by counting the number
    # of two and three pieces in a span of four spaces and multiplying
    # number of two pieces by 5 and 3 pieces by 20
    def evalstate(self):
        score = 0
        add2 = 0
        add3 = 0
        add2, add3 = self.gethorizontals('O')
        score += add2 * 5
        score += add3 * 20
        add2, add3 = self.gethorizontals('X')
        score -= add2 * 5
        score -= add3 * 20
        add2, add3 = self.getverticals('O')
        score += add2 * 5
        score += add3 * 20
        add2, add3 = self.getverticals('X')
        score -= add2 * 5
        score -= add3 * 20
        add2, add3 = self.getrightdiags('O')
        score += add2 * 5
        score += add3 * 20
        add2, add3 = self.getrightdiags('X')
        score -= add2 * 5
        score -= add3 * 20
        add2, add3 = self.getleftdiags('O')
        score += add2 * 5
        score += add3 * 20
        add2, add3 = self.getleftdiags('X')
        score -= add2 * 5
        score -= add3 * 20

        return score

    # This function returns the move made and score of that move
    # Alpha beta pruning with mini max is used to search through possible moves
    def getcompmove(self, state, alpha, beta, player, depth, last):
        newboard = ConnectFour()
        newboard.board = deepcopy(state)
        winR = newboard.winner('O', last)
        winY = newboard.winner('X', last)

        if depth == 0:
            return (newboard.evalstate(), 0)
        if winR:
            return (1000, 0)
        if winY:
            return (-1000, 0)
        if self.draw():
            return (0, 0)

        if player:
            maxval = -1000000
            moves = 0
            for i in range(1, 8):
                if state[0][i - 1] == '_':
                    newboard.board = deepcopy(state)
                    newboard.move(i, 'O')
                    checkmove, trash = newboard.getcompmove(newboard.board, alpha, beta, False, depth - 1, i - 1)
                    if checkmove > maxval:
                        maxval = checkmove
                        moves = i
                    alpha = max(alpha, maxval)
                    if beta <= maxval:
                        break
            return (maxval, moves)

        else:
            minval = 1000000
            move = 0
            for i in range(1, 8):
                if state[0][i - 1] == '_':
                    newboard.board = deepcopy(state)
                    newboard.move(i, 'X')
                    checkmove, trash = newboard.getcompmove(newboard.board, alpha, beta, True, depth - 1, i - 1)
                    if checkmove < minval:
                        minval = checkmove
                        move = i
                    beta = min(beta, minval)
                    if minval <= alpha:
                        break
            return (minval, move)


# Initialize the board, get who starts first, and game difficulty
# difficulty is how deep the search goes
player1 = 'X'
player2 = 'O'
game = ConnectFour()
game.printboard()
print()
turn = int(input("Enter number for starting player. 0: computer 1: user "))
diff = int(input("Enter number for difficulty. 1: easy 2: medium 3: hard ")) * 2
last = 0

# Gets moves for the game until there is a winner or a draw
while not game.winner(player1, last -1) and not game.winner(player2, last-1) and not game.draw():
    if turn % 2:
        game.getplayermove(player1, last)
        turn += 1
    else:
        trash, last = game.getcompmove(game.board, -10000, 10000, True, diff, last -1)
        game.move(last, player2)
        print("Computer move:", last)
        turn -= 1
    game.printboard()
    print()

# Print result of the game
if game.winner(player1, last - 1):
    print("X Wins!")
elif game.winner(player2, last - 1):
    print("O Wins!")
else:
    print("Draw!")
