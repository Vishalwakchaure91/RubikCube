package org.viwa.RubiksCubeSolver;

import org.viwa.Rubik.CubePiece;
import org.viwa.Rubik.CubeSide;
import org.viwa.Rubik.RubiksCube;
import org.viwa.Rubik.CubePiece.Color;
import org.viwa.Rubik.RubiksCube.CubeSideLocation;

public class RubiksCubeSolver implements SimpleRubiksCubeAlgorithm {
    private static RubiksCubeSolver instance;
    
    private RubiksCubeSolver() {
        
    }

    //Singleton Class Implementation
    public RubiksCubeSolver getInstance() {
        if(instance == null) {
            instance = new RubiksCubeSolver();
        }
        return instance;
    }

    // private void solveCubeForYellowCornerPieces(RubiksCube cube) {
    //     cube.rotateToSide(Color.WHITE);
    //     CubePiece edgePiece = cube.frontSide.getEdgePieces(Color.WHITE);

    //     while(edgePiece!=null) {
    //         rotateSideToEdgePieceColor(cube, edgePiece);
    //     }
    // }

    private void rotateSideToEdgePieceColor(RubiksCube cube, CubePiece edgePiece) {
        Color otherColor = edgePiece.getOtherColor(Color.WHITE);
        RubiksCube.CubeSideLocation dir = cube.getSideLocation(otherColor);
        int x = edgePiece.getLocation().x();
        int y = edgePiece.getLocation().y();

        if(x == 0 && y == 1) {
            if(dir == CubeSideLocation.DOWN) {
                cube.rotateFrontSideClockwise(2);
            } else if(dir ==CubeSideLocation.RIGHT) {
                cube.rotateFrontSideClockwise();
            } else if( dir ==CubeSideLocation.LEFT) {
                cube.rotateFrontSideAntiClockwise();
            }
        } else if(x == 1 && y == 1) {
            if(dir == CubeSideLocation.DOWN) {
                cube.rotateFrontSideClockwise();
            } else if(dir ==CubeSideLocation.UP) {
                cube.rotateFrontSideAntiClockwise();
            } else if( dir ==CubeSideLocation.LEFT) {
                cube.rotateFrontSideClockwise(2);
            }
        } else if(x == 2 && y == 1) {
            if(dir == CubeSideLocation.UP) {
                cube.rotateFrontSideClockwise(2);
            } else if(dir ==CubeSideLocation.RIGHT) {
                cube.rotateFrontSideAntiClockwise();
            } else if( dir ==CubeSideLocation.LEFT) {
                cube.rotateFrontSideClockwise();
            }
        } else if(x == 1 && y == 0) {
            if(dir == CubeSideLocation.UP) {
                cube.rotateFrontSideClockwise();
            } else if(dir ==CubeSideLocation.RIGHT) {
                cube.rotateFrontSideClockwise(2);
            } else if( dir ==CubeSideLocation.DOWN) {
                cube.rotateFrontSideAntiClockwise();
            }
        }   
    }

    private boolean isEdgePieceFliped(CubePiece edgPiece, CubeSide side) {
        //TODO
        // return side.data[edgPiece.getLocation().x][edgPiece.getLocation().y] != side.color;
        return false;
    }

    @Override
    public void formWhiteCross(RubiksCube cube) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void solveCubeForWhiteCornerPieces(RubiksCube cube) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void solveCubeForSecondLayerPieces(RubiksCube cube) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void solveCubeForYellowCross(RubiksCube cube) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void solveCubeForYellowEdgePieces(RubiksCube cube) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void solveCubeForYellowCornerPieces(RubiksCube cube) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void correctYellowCornerPieces(RubiksCube cube) {
        // TODO Auto-generated method stub   
    }
}