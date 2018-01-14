package solver;

import ui.Hint;

import java.util.ArrayList;
import java.util.List;

// take in a list of column hints, list of row hints, and determine if the nonogram has a solution
public class Solver {

    public enum CellState {
        UNKNOWN,
        EMPTY,
        FILLED
    }

    private List<Hint> mRowHints;
    private List<Hint> mColumnHints;
    private CellState[][] mCellStates;

    public Solver(List<Hint> rowHints, List<Hint> columnHints) {

        mRowHints = rowHints;
        mColumnHints = columnHints;

        mCellStates = new CellState[mRowHints.size()][mColumnHints.size()];

        for (int i = 0; i < mRowHints.size(); i++) {
            for (int j = 0; j < mColumnHints.size(); j++) {
                mCellStates[i][j] = CellState.UNKNOWN;
            }
        }
    }

    public boolean solve() {
        while (true) {
            if (!solveStep()) {
                for (int i = 0; i < mCellStates.length; i++) {
                    for (int j = 0; j < mCellStates[0].length; j++) {
                        if (mCellStates[i][j] == CellState.UNKNOWN) {
                            System.out.println("Could not solve step");
                            return false;
                        }
                    }
                }

                System.out.println("Solved!");
                return true;


            }
        }
    }

    // look through all rows and all columns
    private boolean solveStep() {
        if (!reduceRow() && !reduceColumn()) {
            return false;
        }
        return true;
    }

    private boolean reduceRow() {
        boolean reduced = false;
        for (int i = 0; i < mRowHints.size(); i++) {
            if (reduce(mRowHints.get(i), mCellStates[i])) {
                reduced = true;
            }
        }
        return reduced;
    }

    private boolean reduceColumn() {
        boolean reduced = false;
        for (int i = 0; i < mColumnHints.size(); i++) {
            CellState[] line = new CellState[mRowHints.size()];
            for (int j = 0; j < mRowHints.size(); j++) {
                line[j] = mCellStates[j][i];
            }

            if (reduce(mColumnHints.get(i), line)) {
                reduced = true;
            }
        }
        return reduced;
    }

    private boolean reduce(Hint hint, CellState[] line) {
        List<CellState[]> possibleStates = getPossibleStates(hint, line);

        CellState[] mergedStates = possibleStates.get(0);

        // combine possible states
        for (CellState[] state : possibleStates) {
            for (int i = 0; i < state.length; i++) {
                if (mergedStates[i] != state[i]) {
                    mergedStates[i] = CellState.UNKNOWN;
                }
            }
        }

        // simplify line by combining with merged state
        boolean reduced = false;
        for (int j = 0; j < line.length; j++) {
            if (mergedStates[j] != CellState.UNKNOWN && line[j] == CellState.UNKNOWN) {
                line[j] = mergedStates[j];
                reduced = true;
            }
        }

        return reduced;
    }

    private List<CellState[]> getPossibleStates(Hint hint, CellState[] line) {
        List<CellState[]> possibleStates = new ArrayList<>();

        List<Integer> hintValues = hint.getValues();

        findState(hintValues, line, 0, possibleStates);

        return possibleStates;
    }

    private void findState(List<Integer> hintValues, CellState[] prevState, int startPosition, List<CellState[]> possibleStates) {
        if (hintValues.size() == 0) {
            possibleStates.add(prevState);
            return;
        }

        List<Integer> newValues = new ArrayList<>(hintValues);

        int valueToAdd = newValues.remove(0);

        for (int start = startPosition; start < prevState.length; start++) {
            boolean canFit = true;
            CellState[] nextState = prevState.clone();
            for (int valueLength = 0; valueLength < valueToAdd; valueLength++) {
                if (start + valueLength >= nextState.length || nextState[start + valueLength] == CellState.EMPTY) {
                    canFit = false;
                    nextState[start] = CellState.EMPTY;
                    break;
                } else {
                    nextState[start + valueLength] = CellState.FILLED;
                }
            }

            if (canFit) {
                findState(newValues, nextState, start + valueToAdd, possibleStates);
            }
        }
    }

}
