import java.awt.*;
import javax.swing.*;
import java.util.HashSet;
import java.awt.event.*;
import java.util.Random;

public class PacMan extends JPanel{

    class Block{
        int x;
        int y;
        int width;
        int height;
        Image image;

        int startX;
        int startY;

        Block(int x , int y , int w , int h , Image i){
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
            this.image = i;
            this.startX = x;
            this.startY = y;
        }
    }
    
    private int rowCount = 21;
    private int colCount = 19;
    private int tileSize = 32;
    private int boardWidth = colCount * tileSize;
    private int boardHeight = rowCount * tileSize;

    private Image wallImage;
    private Image blueGhostImage;
    private Image redGhostImage;
    private Image orangeGhostImage;
    private Image pinkGhostImage;

    private Image PacmanUpImage;
    private Image PacmanDownImage;
    private Image PacmanLeftImage;
    private Image PacmanRightImage;

    //X = wall, O = skip, P = pac man, ' ' = food
    //Ghosts: b = blue, o = orange, p = pink, r = red
    private String[] tileMap = {
        "XXXXXXXXXXXXXXXXXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X                 X",
        "X XX X XXXXX X XX X",
        "X    X       X    X",
        "XXXX XXXX XXXX XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXrXX X XXXX",
        "O       bpo       O",
        "XXXX X XXXXX X XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXXXX X XXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X  X     P     X  X",
        "XX X X XXXXX X X XX",
        "X    X   X   X    X",
        "X XXXXXX X XXXXXX X",
        "X                 X",
        "XXXXXXXXXXXXXXXXXXX" 
    };

    HashSet<Block> walls;
    HashSet<Block> foods;
    HashSet<Block> ghosts;
    Block pacman;

    PacMan(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);

        wallImage = new ImageIcon(getClass().getResource("./wall.png")).getImage();
        blueGhostImage = new ImageIcon(getClass().getResource("./blueGhost.png")).getImage();
        redGhostImage = new ImageIcon(getClass().getResource("./redGhost.png")).getImage();
        orangeGhostImage = new ImageIcon(getClass().getResource("./orangeGhost.png")).getImage();
        pinkGhostImage = new ImageIcon(getClass().getResource("./pinkGhost.png")).getImage();

        PacmanUpImage = new ImageIcon(getClass().getResource("./PacmanUp.png")).getImage();
        PacmanDownImage = new ImageIcon(getClass().getResource("./PacmanDown.png")).getImage();
        PacmanLeftImage = new ImageIcon(getClass().getResource("./PacmanLeft.png")).getImage();
        PacmanRightImage = new ImageIcon(getClass().getResource("./PacmanRight.png")).getImage();

        //load the map
        loadmap();
        System.out.println(walls.size());
        System.out.println(foods.size());
        System.out.println(ghosts.size());

    }

    private void loadmap(){
        walls = new HashSet<>();
        foods = new HashSet<>();
        ghosts = new HashSet<>();

        for(int row = 0; row < rowCount; row ++){
            String curr=tileMap[row];
            
            for(int col = 0; col < colCount; col ++){
                char currChar = curr.charAt(col);
                int x = col * tileSize;
                int y = row * tileSize;

                if(currChar == 'X'){//walls
                    Block wall = new Block(x, y, tileSize, tileSize, wallImage);
                    walls.add(wall);
                }
                else if(currChar == ' '){//foods
                    Block food = new Block(x + 14, y + 14, 4, 4, null);
                    foods.add(food);
                }
                else if(currChar == 'b'){//blueGhost
                    Block blueGhost =new Block(x, y, tileSize, tileSize, blueGhostImage);
                    ghosts.add(blueGhost);
                }
                else if(currChar == 'r'){//redGhost
                    Block redGhost = new Block(x, y, tileSize, tileSize, redGhostImage);
                    ghosts.add(redGhost);
                }
                else if(currChar == 'p'){//pinkGhost
                    Block pinkGhost =new Block(x, y, tileSize, tileSize, pinkGhostImage);
                    ghosts.add(pinkGhost);
                }
                else if(currChar == 'o'){//orangeGhost
                    Block orangeGhost = new Block(x, y, tileSize, tileSize, orangeGhostImage);
                    ghosts.add(orangeGhost);
                }
                else if(currChar == 'P'){//Pacman
                    pacman = new Block(x, y, tileSize, tileSize, PacmanRightImage);
                }
            }
        }
    }



}
