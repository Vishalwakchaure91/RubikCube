package org.viwa.Rubik;
import static org.viwa.Rubik.CubePiece.Color;
import java.util.Random;

/**
 * This class represents a full 3x3 Rubik's Cube, which has 6 sides, and pieces.
 * @author Vishal Wakchaure
 */
public class RubiksCube {
    //6 sides of the Cube.
    protected CubeSide frontSide;
    protected CubeSide rightSide;
    protected CubeSide leftSide;
    protected CubeSide upSide;
    protected CubeSide downSide;
    protected CubeSide backSide;
    
    //Enum representing a Cube Side's location
    public enum CubeSideLocation {
        FRONT, RIGHT, LEFT, UP, DOWN, BACK
    }

    /**
     * Contructs a default Rubik's Cube with primary color as WHITE. Rubik Cube's algorithms are based on primary color selected.
     */
    public RubiksCube() {
        this(Color.WHITE);
    }

    /**
     * Construct a Rubik's Cube with specified primary color. Rubik Cube's algorithms are based on primary color selected.
     * @param color
     */
    public RubiksCube(Color color) {
        frontSide = new CubeSide(color);
        initCubeSides();
    }

    /**
     * Setting cube sides in this order if we hold the cube - White side in front facing us.
     * Left to white is Red Side, and to left is Orange. Up side is Blue and down side is Green. Back Side is Yellow.
     * 
     * All other sides are considered according to this initial configuration.
     */
    private void initCubeSides() {
        
        switch(frontSide.color) {
            case WHITE:
            {
                rightSide = new CubeSide(Color.RED);
                leftSide = new CubeSide(Color.ORANGE);   
                upSide = new CubeSide(Color.BLUE);
                downSide = new CubeSide(Color.GREEN);
                backSide = new CubeSide(Color.YELLOW);
            }
            break;
            case ORANGE:
            {
                rightSide = new CubeSide(Color.WHITE);
                leftSide = new CubeSide(Color.YELLOW);
                upSide = new CubeSide(Color.BLUE);
                downSide = new CubeSide(Color.GREEN);
                backSide = new CubeSide(Color.RED);
            }
            break;
            case RED:
            {
                rightSide = new CubeSide(Color.YELLOW);
                leftSide = new CubeSide(Color.WHITE);
                upSide = new CubeSide(Color.BLUE);
                downSide = new CubeSide(Color.GREEN);
                backSide = new CubeSide(Color.ORANGE);
            }
            break;
            case YELLOW:
            {
                rightSide = new CubeSide(Color.ORANGE);
                leftSide = new CubeSide(Color.RED);
                upSide = new CubeSide(Color.BLUE);
                downSide = new CubeSide(Color.GREEN);
                backSide = new CubeSide(Color.WHITE);
            }
            break;
            case GREEN:
            {
                rightSide = new CubeSide(Color.RED);
                leftSide = new CubeSide(Color.ORANGE);
                upSide = new CubeSide(Color.WHITE);
                downSide = new CubeSide(Color.YELLOW);
                backSide = new CubeSide(Color.BLUE);
            }
            break;
            case BLUE:
            {
                rightSide = new CubeSide(Color.RED);
                leftSide = new CubeSide(Color.ORANGE);
                upSide = new CubeSide(Color.YELLOW);
                downSide = new CubeSide(Color.WHITE);
                backSide = new CubeSide(Color.GREEN);
            }
            break;
        }
        frontSide.setParentCube(this);
        rightSide.setParentCube(this);
        leftSide.setParentCube(this);
        upSide.setParentCube(this);
        downSide.setParentCube(this);
        backSide.setParentCube(this);

        frontSide.initCubeSidePieces();
        rightSide.initCubeSidePieces();
        leftSide.initCubeSidePieces();
        upSide.initCubeSidePieces();
        downSide.initCubeSidePieces();
        backSide.initCubeSidePieces();
    }

    /**
     * Rotates cube's current front side clockwise.
     */
    public void rotateFrontSideClockwise() {
        frontSide.rotateSideClockwise();
    }

    /**
     * Rotates cube's current front side clockwise 'n' times.
     * 
     * @param count number of rotations
     */
    public void rotateFrontSideClockwise(int count) {
        frontSide.rotateSideClockwise(count);
    }


    /**
     * Rotates cube's current front side clockwise.
     */
    public void rotateFrontSideAntiClockwise() {
        frontSide.rotateSideAntiClockwise();
    }

    /**
     * Rotates cube clockwise on X axies (Rotation with respect to cube's Top side).
     */
    public void rotateCubeXClockwise() {
        upSide.rotateMatrixClockwise();
        downSide.rotateMatrixAntiClockwise();

        CubeSide side = rightSide;
        rightSide = backSide;
        backSide = leftSide;
        leftSide = frontSide;
        frontSide = side;
    }

    /**
     * Rotates cube clockwise on X axies (Rotation with respect to cube's Top side), 'n' times.
     * 
     * @param count number of rotations.
     */
    public void rotateCubeXClockwise(int count) {
        for(int i=0;i<count;i++) {
            rotateCubeXClockwise();
        }
    }

    /**
     * Rotates cube anti clockwise on X axies (Rotation with respect to cube's Top side).
     */
    public void rotateCubeXAntiClockwise() {
        
        upSide.rotateMatrixAntiClockwise();
        downSide.rotateMatrixClockwise();

        CubeSide side = rightSide;
        rightSide = frontSide;
        frontSide = leftSide;
        leftSide = backSide;
        backSide = side;
    }

    /**
     * Rotates cube anti clockwise on X axies (Rotation with respect to cube's Top side), 'n' times.
     * 
     * @param count number of rotations
     */
    public void rotateCubeXAntiClockwise(int count) {
        for(int i=0;i<count;i++) {
            rotateCubeXAntiClockwise();
        }
    }

    /**
     * Rotates cube clockwise on Y Axies (Rotation with respect to cube's left side).
     */
    public void rotateCubeYClockwise() {
        leftSide.rotateMatrixClockwise();
        rightSide.rotateMatrixAntiClockwise();
        backSide.rotateMatrixClockwise(2);
        downSide.rotateMatrixClockwise(2);  
        
        CubeSide side = frontSide;
        frontSide = upSide;
        upSide = backSide;
        backSide = downSide;
        downSide = side;
    }

    /**
     * Rotates cube clockwise on Y Axies (Rotation with respect to cube's left side), 'n' times.
     * 
     * @param count number of rotations.
     */
    public void rotateCubeYClockwise(int count) {
        for(int i=0;i<count;i++) {
            rotateCubeYClockwise();
        }
    }

    /**
     * Rotates cube anti clockwise on Y Axies (Rotation with respect to cube's left side).
     */
    public void rotateCubeYAntiClockwise() {
        rightSide.rotateMatrixClockwise();
        leftSide.rotateMatrixAntiClockwise();
        backSide.rotateMatrixClockwise(2);
        upSide.rotateMatrixClockwise(2);

        CubeSide side = frontSide;
        frontSide = downSide;
        downSide = backSide;
        backSide = upSide;
        upSide = side;
    }

    /**
     * Rotates cube anti clockwise on Y Axies (Rotation with respect to cube's left side), 'n' times.
     * 
     * @param count Number of times.
     */
    public void rotateCubeYAntiClockwise(int count) {
        for(int i=0;i<count;i++) {
            rotateCubeYAntiClockwise();
        }
    }

    /**
     * Rotates cube anti clockwise on Z Axies (Vertically).
     * 
     */
    public void rotateCubeZClockwise() {
        
        rightSide.rotateMatrixClockwise();
        upSide.rotateMatrixClockwise();
        leftSide.rotateMatrixClockwise();
        downSide.rotateMatrixClockwise();
        backSide.rotateMatrixAntiClockwise();
        frontSide.rotateMatrixClockwise();
        
        CubeSide side = upSide;
        upSide = leftSide;
        leftSide = downSide;
        downSide = rightSide;
        rightSide = side;

    }

    /**
     * Rotates cube clockwise on Z Axies (Rotation with respect to cube's front side).
     */
    public void rotateCubeZClockwise(int count) {
        for(int i=0;i<count;i++) {
            rotateCubeZClockwise();
        }
    }

    /**
     * Rotates cube anti clockwise on Z Axies (Rotation with respect to cube's front side).
     */
    public void rotateCubeZAntiClockwise() {
        rightSide.rotateMatrixAntiClockwise();
        upSide.rotateMatrixAntiClockwise();
        leftSide.rotateMatrixAntiClockwise();
        downSide.rotateMatrixAntiClockwise();
        backSide.rotateMatrixClockwise();
        frontSide.rotateMatrixAntiClockwise();

        CubeSide side = upSide;
        upSide = rightSide;
        rightSide = downSide;
        downSide = leftSide;
        leftSide = side;
    }

    public void rotateCubeZAntiClockwise(int count) {
        for(int i=0;i<count;i++) {
            rotateCubeZAntiClockwise();
        }
    }

    public void rotateToSide(Color color) {
        if(frontSide.color == color) {
            return;
        }

        if(rightSide.color == color) {
            rotateCubeXClockwise();
        } else if(leftSide.color == color) {
            rotateCubeXAntiClockwise();
        } else if(upSide.color == color) {
            rotateCubeYClockwise();
        } else if(downSide.color == color) {
            rotateCubeYAntiClockwise();
        } else if(backSide.color == color) {
            rotateCubeXClockwise(2);
        }
    }

    public CubeSide getSide(Color color) {
        if(downSide.color == color){
            return downSide;
        } else if(upSide.color == color){
            return upSide;
        } else if(leftSide.color == color){
            return leftSide;
        } else if(rightSide.color == color){
            return rightSide;
        } else {
            return frontSide;
        }
    }

    public CubeSideLocation getSideLocation(Color color) {
        if(downSide.color == color){
            return CubeSideLocation.DOWN;
        } else if(upSide.color == color){
            return CubeSideLocation.UP;
        } else if(leftSide.color == color){
            return CubeSideLocation.LEFT;
        } else if(rightSide.color == color){
            return CubeSideLocation.RIGHT;
        } else {
            return CubeSideLocation.FRONT;
        }
    }

    public void scrambleCube() {
        /**
         * Rotations: 
         * 1. RotateCubeXClockwise
         * 2. RotateCubeXAntiClockwise
         * 3. RotateCubeYClockwise
         * 4. RotateCubeYAntiClockwise
         * 5. RotateCubeZClockwise
         * 6. RotateCubeZAntiClockwise
         * 
         * Operations:
         * 1. rotateFrontSideClockwise
         * 2. rotateFrontSideAntiClockwise
         */

        Random rand = new Random();
        int operationCount = rand.nextInt(20)+50;

        int prevOperation = 0;
        for(int i=0;i<operationCount;i++) {
            int rotationOption = rand.nextInt(6) + 1;

            // if prev operation is clockwise rotation and current operation is anti clockwise rotation, 
            // then it cancels prev operation. So we do previous operation again.
            if(prevOperation%2 == 1 && rotationOption == prevOperation+1) {
                rotationOption = prevOperation;
            }

            switch(rotationOption) {
                case 1: {
                    System.out.println(" - -rotate Cube X Clockwise");
                    rotateCubeXClockwise();
                } 
                break;
                case 2: {
                    System.out.println(" - -rotate Cube X Anti Clockwise");
                    rotateCubeXAntiClockwise();
                }
                break;
                case 3: {
                    System.out.println(" - -rotate Cube Y Clockwise");
                    rotateCubeYClockwise();
                }
                break;
                case 4: {
                    System.out.println(" - -rotate Cube Y Anti Clockwise");
                    rotateCubeYAntiClockwise();
                }
                break;
                case 5: {
                    System.out.println(" - -rotate Cube Z Clockwise");
                    rotateCubeZClockwise();
                }
                break;
                case 6:{
                    System.out.println(" - -rotate Cube Z Anti Clockwise");
                    rotateCubeZAntiClockwise();
                }
                break;
            }

            int rotateSideOption = rand.nextInt(2)+1;

            if(rotateSideOption ==1) {
                System.out.println(" - -rotate Frontside Clockwise");
                rotateFrontSideClockwise();
            } else {
                System.out.println(" - -rotate Frontside Anti Clockwise");
                rotateFrontSideAntiClockwise();
            }
        }
    }

    public String toString() {
        StringBuilder outString = new StringBuilder();
        outString.append("=====================RUBIK'S CUBE======================\n\n");
        outString.append("Front Side:\n");
        outString.append(frontSide);
        outString.append("Right Side:\n");
        outString.append(rightSide);
        outString.append("Left Side:\n");
        outString.append(leftSide);
        outString.append("Up Side:\n");
        outString.append(upSide);
        outString.append("Down Side:\n");
        outString.append(downSide);
        outString.append("Back Side:\n");
        outString.append(backSide);
        return outString.toString();
    }

    public static void main(String args[]) {
        RubiksCube rubik = new RubiksCube();

        System.out.println("=============This is a demostration of Rubik Cube's representation implementation.=============");
        System.out.println("=============Cube is initialized to solved state.==============================================\n");
        System.out.println("\n=============After scrambling the cube it maintains its state properly.========================\n");
        System.out.println(rubik);
        
        System.out.println("=============Now we scramble the cube.==============");
        rubik.scrambleCube();
        System.out.println("=============Cube after scrambling it randomly======");
        System.out.println(rubik);
        System.out.println("=========If we follow reverse order of scrambling operations, we get a solved cube=============");
    }
}
