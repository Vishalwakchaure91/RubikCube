package org.viwa.Rubik;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a single Cube Piece.
 * Single cube piece can be a middle piece, edge piece or corner piece, having one or multiple colors.
 * 
 * @author Vishal Wakchaure
 * @version 1.0
 */

public class CubePiece {

    /**
     * Location stores x and y position of the piece in the matrix of current side of the cube.
     */
    public class Location {
        int x;
        int y;
        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x() {
            return this.x;
        }

        public int y() {
            return this.y;
        }
    }
    
    // we are maintaining a linked list for corner pieces and edge pieces.
    private CubePiece next = null;

    private Location location;

    // Enum specifies possible types of the piece.
    public static enum CubePieceType {
        MIDDLE, EDGE, CORNER
    }

    // Enum specifies possible colors of the piece.
    public static enum Color {
        WHITE, RED, YELLOW, ORANGE, BLUE, GREEN
    };

    // type of this peice.
    private CubePieceType pieceType;

    // List of colors of this piece.
    private List<Color> colors;

    /**
     * This constructor creates a piece of 'pieceType'
     * 
     * @param pieceType
     */
    public CubePiece(CubePieceType pieceType) {
        if(colors==null) {
            colors =new ArrayList<Color>();
        }

        this.pieceType = pieceType;
    }

    /**
     * This constructor creates a middle piece of the given color.
     * 
     * @param color
     */
    public CubePiece(Color color) {
        this.pieceType = CubePieceType.MIDDLE;
        this.colors = new ArrayList<Color>(1);
        this.colors.add(color);
    }

    /**
     * This constructor creates a edge peice of two given colors.
     * 
     * @param color1
     * @param color2
    */
    public CubePiece(Color color1, Color color2) {
        this.pieceType = CubePieceType.EDGE;
        this.colors = new ArrayList<Color>(2);
        this.colors.add(color1);
        this.colors.add(color2);
    }

    /**
     * This constructor creates a corner piece of three given colors.
     * 
     * @param color1
     * @param color2
     * @param color3
     */
    public CubePiece(Color color1, Color color2, Color color3) {
        this.pieceType = CubePieceType.CORNER;
        this.colors = new ArrayList<Color>(3);
        this.colors.add(color1);
        this.colors.add(color2);
        this.colors.add(color3);
    }

    /**
     * This constructor creates a piece of given list of colors.
     * 
     * @param colors
     */
    public CubePiece(List<Color> colors) {
        if(colors.size()==1) {
            this.pieceType = CubePieceType.MIDDLE;
        } else if(colors.size()==2) {
            this.pieceType = CubePieceType.EDGE;
        } else {
            this.pieceType = CubePieceType.CORNER;
        }
        this.colors = new ArrayList<Color>(colors.size());
        this.colors.addAll(colors);
    }

    /**
     * Construct new piece from provided piece.
     * 
     * @param piece
     */
    public CubePiece(CubePiece piece) {
        this.pieceType = piece.pieceType;
        this.colors.addAll(piece.getColors());
    }

    /**
     * Returns this piece type.
     * 
     * @return
     */
    public CubePieceType getPieceType() {
        return this.pieceType;
    }

    /**
     * Returns list of colors of this piece.
     * 
     * @return
     */
    public List<Color> getColors() {
        return colors;
    }
    
    /**
     * Returns a first color of list of colors.
     * Returns a color if it is a  middle piece.  
     * 
     * @return
     */
    public Color getColor() {
        return colors.get(0);
    }

    /**
     * Returns other color of the edge piece.
     * 
     * @param color
     * @return
     */
    public Color getOtherColor(Color color) {
        if(colors.size()==2) {
            if(colors.get(0) == color) {
                return colors.get(1);
            } else {
                return colors.get(0);
            }
        }
        return null;
    }

    /**
     * Sets color list of this piece
     * 
     * @param colors
     */
    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    /**
     * Sets color of the piece and sets its type as MIDDLE.
     * 
     * @param color
     */
    public void setColor(Color color) {
        colors = new ArrayList<>(1);
        this.colors.add(color);
        this.pieceType = CubePieceType.MIDDLE;
    }

    /**
     * Sets colors of the piece and make it as edge piece.
     * 
     * @param color1
     * @param color2
     */
    public void setColor(Color color1, Color color2) {
        colors = new ArrayList<>(2);
        this.colors.add(color1);
        this.colors.add(color2);
        this.pieceType = CubePieceType.EDGE;
    }

    /**
     * Sets colors of the piece and make it as Corner piece.
     * 
     * @param color1
     * @param color2
     * @param color3
     */
    public void setColor(Color color1, Color color2, Color color3) {
        colors = new ArrayList<>(3);
        this.colors.add(color1);
        this.colors.add(color2);
        this.colors.add(color3);
        this.pieceType = CubePieceType.CORNER;
    }

    /**
     * Returns location of this piece in its side.
     * 
     * @return
     */
    public Location getLocation() {
        return location;
    }
    
    /**
     * Sets the location of the piece in its side.
     * 
     * @param x
     * @param y
     */
    public void setLocation(int x, int y) {
        this.location = new Location(x,y);
    }

    /**
     * Returns next(Corner or Edge) Piece of this piece in the linked list (in clockwise order).
     * 
     * @return
     */
    public CubePiece next() {
        return next;
    }

    /**
     * Sets next (Corner or Edge) piece of this piece (in clockwise order).
     * 
     * @param next
     * @return
     */
    public CubePiece setNext(CubePiece next) {
        this.next = next;
        return this.next;
    }


    /**
     * Gets last piece (Corner or Edge) in the linked list of this piece (in clockwise order).
     * 
     * @return
     */
    public CubePiece getLast() {
        CubePiece currentPiece = this;

        while(currentPiece.next()!=this) {
            currentPiece = currentPiece.next();
        }

        return currentPiece;
    }

    /**
     * Gets piece (Corner or Edge) in the linked list of this piece (in clockwise order) at specified index.
     * 
     * @return
     */
    public CubePiece getAt(int index) {
        int i=0;
        CubePiece currentPiece = this;

        while(i<index && currentPiece.next()!=this) {
            currentPiece = currentPiece.next();
        }

        if(i==index) {
            return currentPiece;
        }
        return null;
    }
}
