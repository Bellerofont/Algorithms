package mcts.montecarlo;

import mcts.tictactoe.Board;

import mcts.tictactoe.Position;
import mcts.tree.Node;
import mcts.tree.Tree;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonteCarloTreeSearchTest {
    private Tree gameTree;
    private MonteCarloTreeSearch mcts;


    @BeforeEach
    void setUp() {
        gameTree = new Tree();
        mcts = new MonteCarloTreeSearch();
    }

    @AfterEach
    void tearDown() {
    }

    @RepeatedTest(100)
    public void givenEmptyBoard_whenSimulateInterAIPlay_thenGameDraw() {
        int boardSize = 3;
        Board board = new Board(boardSize);

        int player = Board.P1;
        int totalMoves = boardSize * boardSize;
        for (int i = 0; i < totalMoves; i++) {
            board = mcts.findNextMove(board, player);
            if (board.checkStatus() != -1) {
                break;
            }
            player = 3 - player;
        }
        int winStatus = board.checkStatus();
        assertEquals(winStatus, Board.DRAW);
    }

    @Test
    public void givenStats_whenGetUCTForNode_thenUCTMatchesWithManualData() {
        double uctValue = 15.79;
        assertEquals(UCT.uctValue(600, 300, 20), uctValue, 0.01);
    }

    @Test
    public void giveninitBoardState_whenGetAllPossibleStates_thenNonEmptyList() {
        State initState = gameTree.getRoot().getState();
        List<State> possibleStates = initState.getAllPossibleStates();
        assertTrue(possibleStates.size() > 0);
    }

    @Test
    public void givenEmptyBoard_whenPerformMove_thenLessAvailablePossitions() {
        Board board = new Board();
        int initAvailablePositions = board.getEmptyPositions().size();
        board.performMove(Board.P1, new Position(1, 1));
        int availablePositions = board.getEmptyPositions().size();
        assertTrue(initAvailablePositions > availablePositions);
    }

    @Test
    public void givenEmptyBoard_whenLevel1VsLevel3_thenLevel3WinsOrDraw() {
        Board board = new Board();
        MonteCarloTreeSearch mcts1 = new MonteCarloTreeSearch();
        mcts1.setLevel(1);
        MonteCarloTreeSearch mcts3 = new MonteCarloTreeSearch();
        mcts3.setLevel(3);

        int player = Board.P1;
        int totalMoves = Board.DEFAULT_BOARD_SIZE * Board.DEFAULT_BOARD_SIZE;
        for (int i = 0; i < totalMoves; i++) {
            if (player == Board.P1)
                board = mcts3.findNextMove(board, player);
            else
                board = mcts1.findNextMove(board, player);

            if (board.checkStatus() != -1) {
                break;
            }
            player = 3 - player;
        }
        int winStatus = board.checkStatus();
        assertTrue(winStatus == Board.DRAW || winStatus == Board.P1);
    }

    @Test
    public void addMinScore() {
        Node node = new Node();
        node.getState().addScore(10);
        assertEquals(10, node.getState().getWinScore());
        node.getState().setWinScore(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, node.getState().getWinScore());
    }

    @RepeatedTest(10)
    public void nextStepCustom() {
        int[][] ar = {{2, 1, 0},{0, 2, 0},{1, 0, 1}};
        Board board = new Board(ar);
        Board newBoard = mcts.findNextMove(board, 2);
        assertEquals(2, newBoard.getBoardValues()[2][1]);
    }

    @RepeatedTest(10)
    public void simulateRandomPlayout() {
        int[][] ar = {{2, 1, 0},{0, 2, 0},{1, 0, 1}};
        Board board = new Board(ar);
        State state = new State(board);
        state.setPlayerNo(1);
        Node rootNode = new Node(state);
        Node node = mcts.selectPromisingNode(rootNode);
        mcts.expandNode(node);
        Node nodeToExplore = node.getRandomChildNode();
        int r = mcts.simulateRandomPlayout(nodeToExplore);
        mcts.backPropogation(nodeToExplore, r);
        System.out.println(node);
    }

}
