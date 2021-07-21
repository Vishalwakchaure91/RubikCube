package org.viwa.Rubik;
import static org.viwa.Rubik.CubePiece.Color;

/**
 * This class represents a complete one cube side.
 * 
 * @author Vishal Wakchaure
 */
public class CubeSide {

    //Primary color of the side.
    Color color;

    //Matrix of cube pieces of this cube side.
    protected CubePiece pieces[][];

    //Matrix of colors of the pieces of this cube side.
    protected Color data[][];

    //Parent Cube
    private RubiksCube cube;

    /**
     * Constructs Cube Side of WHITE color belonging to parent Cube.
     * @param cube
     */
    public CubeSide(RubiksCube cube) {
        this(Color.WHITE);
        this.cube = cube;
    }

    /**
     * Constructs Cube Side of WHITE color.
     */
    public CubeSide() {
        this(Color.WHITE);
        this.cube = null;
    }

    /**
     * Constructs Cube Side of specified color belonging to parent Cube.
     * @param cube
     * @param color
     */
    public CubeSide(RubiksCube cube, Color color) {
        this(color);
        this.cube = cube;
    }

    /**
     * Constructs Cube Side of specified color.
     * @param color
     */
    public CubeSide(Color color) {
        this.color = color;
        this.pieces = new CubePiece[3][3];
        this.data = new Color[3][3];

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                this.data[i][j] = color;
            }
        }
        this.cube = null;
    }

    /**
     * Returns parent Cube.
     * @return
     */
    public RubiksCube getParentCube() {
        return cube;
    }

    /**
     * Sets parent cube
     * 
     * @param cube
     */
    public void setParentCube(RubiksCube cube) {
        this.cube = cube;
    }

    /**
     * Gets right side of this side in the parent cube.
     * 
     * @return
     */
    public CubeSide getRightSide() {
        if(this.color == cube.frontSide.color 
            || this.color == cube.upSide.color 
            || this.color == cube.downSide.color){
            return cube.rightSide;
        } else if(this.color == cube.rightSide.color){
            return cube.backSide;
        } else if(this.color == cube.leftSide.color){
            return cube.frontSide;
        } else {
            return cube.leftSide;
        }
    }

    /**
     * Gets left side if this side in the parent cube.
     * 
     * @return
     */
    public CubeSide getLeftSide() {
        if(this.color == cube.frontSide.color 
            || this.color == cube.upSide.color 
            || this.color == cube.downSide.color){
            return cube.leftSide;
        } else if(this.color == cube.rightSide.color){
            return cube.frontSide;
        } else if(this.color == cube.leftSide.color){
            return cube.backSide;
        } else {
            return cube.rightSide;
        }
    }

    /**
     * Gets Up side of this side in the parent cube.
     * 
     * @return
     */
    public CubeSide getUpSide() {
        if(this.color == cube.frontSide.color 
            || this.color == cube.rightSide.color 
            || this.color == cube.leftSide.color
            || this.color == cube.backSide.color){
            return cube.upSide;
        } else if(this.color == cube.downSide.color){
            return cube.frontSide;
        } else {
            return cube.backSide;
        }
    }

    /**
     * Gets Down side of this side in the parent cube.
     * 
     * @return
     */
    public CubeSide getDownSide() {
        if(this.color == cube.frontSide.color 
            || this.color == cube.rightSide.color 
            || this.color == cube.leftSide.color
            || this.color == cube.backSide.color){
            return cube.downSide;
        } else if(this.color == cube.downSide.color){
            return cube.backSide;
        } else {
            return cube.frontSide;
        }
    }

    /**
     * Gets the Back side of this side in the parent cube.
     * @return
     */
    public CubeSide getBackSide() {
        if(this.color == cube.frontSide.color){
            return cube.backSide;
        } else if(this.color == cube.downSide.color){
            return cube.upSide;
        } else if(this.color == cube.upSide.color){
            return cube.downSide;
        } else if(this.color == cube.leftSide.color){
            return cube.rightSide;
        } else if(this.color == cube.rightSide.color){
            return cube.leftSide;
        } else {
            return cube.frontSide;
        }
    }

    /**
     * Gets edge pieces of this side maintained in a linked list.
     * 
     * @return
     */
    public CubePiece getEdgePieces() {
        return pieces[0][1];
    }

    /**
     * Gets edge pieces of this side of specified color maintained in linked list.
     * 
     * @param color
     * @return
     */
    public CubePiece getEdgePieces(Color color) {
        CubePiece newList = null;
        CubePiece piece = pieces[0][1];
        do {
            if(piece.getColors().contains(color)) {
                if(newList == null) {
                    newList = new CubePiece(piece);
                } else {
                    newList.setNext(new CubePiece(piece));
                }
            }
        } while(piece!=pieces[0][1]);
        return newList;
    }

    /**
     * Gets corner pieces of this side maintained in a linked list.
     * 
     * @return
     */
    public CubePiece getCornerPieces() {
        return pieces[0][0];
    }

    /**
     * Setting up cube pieces of this side along with shared pieces with shared sides.
     */
    public void initCubeSidePieces() {
        CubeSide upSide = getUpSide();
        CubeSide leftSide = getLeftSide();
        CubeSide rightSide = getRightSide();
        CubeSide downSide = getDownSide();

        pieces[0][0] = new CubePiece(color, leftSide.color, upSide.color);
        pieces[0][2] = new CubePiece(color, upSide.color, rightSide.color);
        pieces[2][0] = new CubePiece(color, downSide.color, leftSide.color);
        pieces[2][2] = new CubePiece(color, rightSide.color, downSide.color);

        //We are maintaining a 'Circular Linked List' of Corner Pieces.
        pieces[0][0].setNext(pieces[0][2]).setNext(pieces[2][0]).setNext(pieces[2][2]).setNext(pieces[0][0]);

        pieces[0][1] = new CubePiece(color, upSide.color);
        pieces[1][2] = new CubePiece(color, rightSide.color);
        pieces[2][1] = new CubePiece(color, downSide.color);
        pieces[1][0] = new CubePiece(color, leftSide.color);
        pieces[1][1] = new CubePiece(color);

        //We are maintaining a 'Circular Linked List' of Edge Pieces.
        pieces[0][1].setNext(pieces[1][2]).setNext(pieces[2][1]).setNext(pieces[1][0]).setNext(pieces[0][1]);
    }

    /**
     * Rotates the matrix of this side clockwise, representing side is being rotated clockwise.
     */
    public void rotateMatrixClockwise() {
        CubePiece temp = pieces[0][0];
        pieces[0][0] = pieces[2][0];
        pieces[2][0] = pieces[2][2];
        pieces[2][2] = pieces[0][2];
        pieces[0][2] = temp;

        temp = pieces[0][1];
        pieces[0][1] = pieces[1][0];
        pieces[1][0] = pieces[2][1];
        pieces[2][1] = pieces[1][2];
        pieces[1][2] = temp;

        //rotating data grid

        Color color = data[0][0];
        data[0][0] = data[2][0];
        data[2][0] = data[2][2];
        data[2][2] = data[0][2];
        data[0][2] = color;

        color = data[0][1];
        data[0][1] = data[1][0];
        data[1][0] = data[2][1];
        data[2][1] = data[1][2];
        data[1][2] = color;
    }

    /**
     * Rotates the matrix of this side clockwise 'rotation' times, representing side is being rotated clockwise.
     */
    public void rotateMatrixClockwise(int rotation) {
        for(int i=0; i<rotation; i++) {
            rotateMatrixClockwise();
        }
    }

    /**
     * Rotates this side clockwise.
     */
    public void rotateSideClockwise() {
        rotateMatrixClockwise();
        adjustSideAfterFrontClockwiseRotate(); 
    }

    /**
     * Rotate this side clockwise, 'rotation' times.
     * 
     * @param rotation
     */
    public void rotateSideClockwise(int rotation) {
        for(int i=0; i<rotation; i++) {
            rotateSideClockwise();
        }
    }

    /**
     * Rotates matrix of this side, Anti clockwise, representing side is being rotated anti clockwise.
     */
    public void rotateMatrixAntiClockwise() {
        CubePiece temp = pieces[0][0];
        pieces[0][0] = pieces[0][2];
        pieces[0][2] = pieces[2][2];
        pieces[2][2] = pieces[2][0];
        pieces[2][0] = temp;
    
        temp = pieces[0][1];
        pieces[0][1] = pieces[1][2];
        pieces[1][2] = pieces[2][1];
        pieces[2][1] = pieces[1][0];
        pieces[1][0] = temp;

        Color color = data[0][0];
        data[0][0] = data[0][2];
        data[0][2] = data[2][2];
        data[2][2] = data[2][0];
        data[2][0] = color;
    
        color = data[0][1];
        data[0][1] = data[1][2];
        data[1][2] = data[2][1];
        data[2][1] = data[1][0];
        data[1][0] = color;
    }

    /**
     * Rotates matrix of this side, Anti clockwise, 'rotation' times, representing side is being rotated anti clockwise.
     * 
     * @param rotation
     */
    public void rotateMatrixAntiClockwise(int rotation) {
        for(int i=0; i<rotation; i++) {
            rotateMatrixAntiClockwise();
        }
    }

    /**
     * Rotate this side anti clockwise.
     */
    public void rotateSideAntiClockwise() {
        rotateMatrixAntiClockwise();
        adjustSideAfterFrontAntiClockwiseRotate();
    }

    /**
     * Adjust other shared sides and their pieces, when this side is rotated Clockwise.
     */
    private void adjustSideAfterFrontClockwiseRotate() {

        CubeSide upSide = getUpSide();
        CubeSide leftSide = getLeftSide();
        CubeSide rightSide = getRightSide();
        CubeSide downSide = getDownSide();

        CubePiece piece1 = upSide.pieces[2][0];
        CubePiece piece2 = upSide.pieces[2][1];
        CubePiece piece3 = upSide.pieces[2][2];

        upSide.pieces[2][0] = leftSide.pieces[2][2];
        upSide.pieces[2][1] = leftSide.pieces[1][2];
        upSide.pieces[2][2] = leftSide.pieces[0][2];

        leftSide.pieces[0][2] = downSide.pieces[0][0];
        leftSide.pieces[1][2] = downSide.pieces[0][1];
        leftSide.pieces[2][2] = downSide.pieces[0][2];
        
        downSide.pieces[0][0] = rightSide.pieces[2][0];
        downSide.pieces[0][1] = rightSide.pieces[1][0];
        downSide.pieces[0][2] = rightSide.pieces[0][0];

        rightSide.pieces[0][0] = piece1;
        rightSide.pieces[1][0] = piece2;
        rightSide.pieces[2][0] = piece3;

        //updating data matrix of adjacent Sides.

        Color color1 = upSide.data[2][0];
        Color color2 = upSide.data[2][1];
        Color color3 = upSide.data[2][2];

        upSide.data[2][0] = leftSide.data[2][2];
        upSide.data[2][1] = leftSide.data[1][2];
        upSide.data[2][2] = leftSide.data[0][2];

        leftSide.data[0][2] = downSide.data[0][0];
        leftSide.data[1][2] = downSide.data[0][1];
        leftSide.data[2][2] = downSide.data[0][2];
        
        downSide.data[0][0] = rightSide.data[2][0];
        downSide.data[0][1] = rightSide.data[1][0];
        downSide.data[0][2] = rightSide.data[0][0];

        rightSide.data[0][0] = color1;
        rightSide.data[1][0] = color2;
        rightSide.data[2][0] = color3;
    }

    
    /**
     * Adjust other shared sides and their pieces, when this side is rotated Anti Clockwise.
     */
    private void adjustSideAfterFrontAntiClockwiseRotate() {
        CubeSide upSide = getUpSide();
        CubeSide leftSide = getLeftSide();
        CubeSide rightSide = getRightSide();
        CubeSide downSide = getDownSide();
        
        CubePiece peice1 = upSide.pieces[2][0];
        CubePiece peice2 = upSide.pieces[2][1];
        CubePiece peice3 = upSide.pieces[2][2];

        upSide.pieces[2][0] = rightSide.pieces[0][0];
        upSide.pieces[2][1] = rightSide.pieces[1][0];
        upSide.pieces[2][2] = rightSide.pieces[2][0];

        
        rightSide.pieces[0][0] = downSide.pieces[0][2];
        rightSide.pieces[1][0] = downSide.pieces[0][1];
        rightSide.pieces[2][0] = downSide.pieces[0][0];
        
        downSide.pieces[0][0] = leftSide.pieces[0][2];
        downSide.pieces[0][1] = leftSide.pieces[1][2];
        downSide.pieces[0][2] = leftSide.pieces[2][2];

        leftSide.pieces[2][2] = peice1;
        leftSide.pieces[1][2] = peice2;
        leftSide.pieces[0][2] = peice3;

        //updating Data Matrix of adjecent Sides

        Color color1 = upSide.data[2][0];
        Color color2 = upSide.data[2][1];
        Color color3 = upSide.data[2][2];

        upSide.data[2][0] = rightSide.data[0][0];
        upSide.data[2][1] = rightSide.data[1][0];
        upSide.data[2][2] = rightSide.data[2][0];

        
        rightSide.data[0][0] = downSide.data[0][2];
        rightSide.data[1][0] = downSide.data[0][1];
        rightSide.data[2][0] = downSide.data[0][0];
        
        downSide.data[0][0] = leftSide.data[0][2];
        downSide.data[0][1] = leftSide.data[1][2];
        downSide.data[0][2] = leftSide.data[2][2];

        leftSide.data[2][2] = color1;
        leftSide.data[1][2] = color2;
        leftSide.data[0][2] = color3;
    }

    
    /**
     * Returns a string representation of this side's current data matrix.
     */
    public String toString() {
        StringBuilder outString = new StringBuilder();
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                outString.append(pieces[i][j].getColors()).append("\t");
            }
            outString.append("\n");
        }
        return outString.toString();
    }
}
