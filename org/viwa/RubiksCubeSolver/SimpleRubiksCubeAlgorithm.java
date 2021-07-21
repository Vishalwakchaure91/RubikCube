package org.viwa.RubiksCubeSolver;

import org.viwa.Rubik.RubiksCube;

/**
 * This interface defines procedure of simple beginner's algorithm for solving a 3x3 Rubik's Cube.
 */
public interface SimpleRubiksCubeAlgorithm {

    /**
    * Form a white Cross on the white side of the cube. 
    *
    * @param cube cube RubiksCube Object to be solved
    */
    public void formWhiteCross(RubiksCube cube);
    
    /**
    * Solve the cube for White Corner peices, so that the first layer will be complete. 
    *
    * @param cube cube RubiksCube Object to be solved
    */
    public void solveCubeForWhiteCornerPieces(RubiksCube cube);
    
    /** 
    * Solve the cube for second layer edge peices, so that the second layer will be complete. 
    *
    * @param cube cube RubiksCube Object to be solved
    */
    public void solveCubeForSecondLayerPieces(RubiksCube cube);

    /**
    * Flip the cube and now solve it for yellow cross on top using algorithm -
    * F -> R -> T -> R' -> T' -> F' 
    *
    * @param cube cube RubiksCube Object to be solved
    */
    public void solveCubeForYellowCross(RubiksCube cube);

    /**
     * Solve the yellow edge pieces using algorithm -
     * R -> T -> R' -> T -> R -> 2T' -> R'
     * 
     * @param cube cube RubiksCube Object to be solved
     */
    public void solveCubeForYellowEdgePieces(RubiksCube cube);

    /**
     * Solve the yellow corner pieces using algorithm -
     * T -> R -> T' -> L' -> T -> R' -> T' -> L
     * 
     * @param cube cube RubiksCube Object to be solved
     */
    public void solveCubeForYellowCornerPieces(RubiksCube cube);

    /**
     * Flip the cube and solve it to correct yellow corner peices using algorithm
     * R -> T -> R' -> T'
     * @param cube RubiksCube Object to be solved
     */
    public void correctYellowCornerPieces(RubiksCube cube);

    /**
     * This is default implementation of the Cube algorithm. 
     * It defines the '<emp>exact sequence</emp>' that needs to be followed to solve the cube with Simple Algorithm. 
     * 
     * @param cube RubiksCube Object to be solved
     */
    default void solveCube(RubiksCube cube) {
        formWhiteCross(cube);
        solveCubeForWhiteCornerPieces(cube);
        solveCubeForSecondLayerPieces(cube);
        solveCubeForYellowCross(cube);
        solveCubeForYellowEdgePieces(cube);
        solveCubeForYellowCornerPieces(cube);
        correctYellowCornerPieces(cube);
    }
}
