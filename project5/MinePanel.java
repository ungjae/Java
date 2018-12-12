import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.*;

/**
 * <b>Title:</b> Project 5<br>
 * <b>Filename:</b> MinePanel.java<br>
 * <b>Date Written:</b> December 9, 2018<br>
 * <b>Due Date:</b> December 9, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * Minesweeper game
 * (implemented removeAdjacentButtons method)
 * </p>
 *
 * @author Ung Jae Yun
 */

class MinePanel extends JPanel implements MouseListener
{
	boolean start,gameOver;
	MineSweepFrame parent;

	int cx, cy;				// x,y coordinates of button

	Font font;
	int numFlags;			  // number to display

	MineButton[][] button;	// represents the mine field

	int row, col, rows, cols, mines,i; 

	/**
 	* constructor using copy of parent container
	*
 	*/
	public MinePanel(MineSweepFrame p)
	{
		parent = p;			// save container that uses this MinePanel
		button = new MineButton[16][16];	// initialze button
		addMouseListener(this);			// add MouseListener to this object
		init();				// initial game variables, minefield etc.
	}

	/**
	* initialize game variables, minefield etc.
	*/
	public void init()
	{
		gameOver = false;
		parent.setGameOver(false);

		int size = 16;
		rows=size;
		cols=size;
		mines = 40;
		numFlags=0;
		start  = true;

		this.setBackground(Color.darkGray);
		parent.minesLeft.setFont(new Font("Arial", 1, 16));
		parent.minesLeft.setText("Mines: " + numFlags);
		parent.playButton.setIcon(new ImageIcon("smiley.jpg"));

		for(int i=0;i<16;i++)
		{
			for(int j=0;j<16;j++)
			{
				button[i][j] = new MineButton();
				button[i][j].setNumber(size*i+j);
				button[i][j].setColor(Color.lightGray);
				button[i][j].clicked(false);
			}
		}
		mineField();
	}

	/**
	* randomly assign mines to minefield
	*/
	public void mineField()
	{
			//Mine the field
		i = 0;
		while(i<mines)
		{
			int randnum =(int) (Math.random()*(rows*rows-1));
			row = randnum/(cols);
			col = randnum%(cols);

			// if not already mined
			if(!button[row][col].isMined())
			{
				// mine this button
				button[row][col].setMined(true);
					//update adjacent buttons
				for(int R=row-1;R<(row+2);R++)
					for(int C=col-1;C<(col+2);C++)
					{
						if((R>=0)&&(C>=0)&&(R<rows)&(C<cols))
							button[R][C].updateCount();
					}
				i++;
			}
		}
	}

	/**
	*  repaint screen using Graphics context g
	*
	*  @param g		Graphics Context
	*/
	public void paint(Graphics g)
	{
		super.paintComponent(g);

		for(cy=0;cy<320;cy+=20)
		{
			for(cx=0;cx<320;cx+=20)
			{
				if(button[cy/20][cx/20].isShowing())
					displayLeft(g,cx,cy);
				else
					displayRight(g,cx,cy);
			}
		}
	}

	/**
	*  displayRight called when right mouse button is clicked
	*  right mouse click on a button adds or removes a flag
	*
	*  @param g		Graphics Context
	*  @param cx	x coordinate of the Panel
	*  @param cy	y coordinate of the Panel
	*/
	public void displayRight(Graphics g, int cx , int cy)
	{
		g.setColor(Color.lightGray);
		if(button[cy/20][cx/20].getState() == 1)			// flag here
		{
			g.fill3DRect(button[cy/20][cx/20].getNumber()%16*20,
					button[cy/20][cx/20].getNumber()/16*20, 18, 18,true);

			g.drawImage(parent.flagPict,button[cy/20][cx/20].getNumber()%16*20+4,
					button[cy/20][cx/20].getNumber()/16*20+3,Color.lightGray,parent);
		}
		else
		{
			g.fill3DRect(button[cy/20][cx/20].getNumber()%16*20,
					button[cy/20][cx/20].getNumber()/16*20, 18, 18, true);
		}
		parent.minesLeft.setText("Mines: " + numFlags);
	}

	/**
	*  displayLeftt called when left mouse button is clicked
	*  left mouse click on a button, exposes number of mines in the area
	*     or exposes safe buttons connected to this button,
	*     if this button is a safe button
	*
	*  @param g		Graphics Context
	*  @param cx	x coordinate of the Panel
	*  @param cy	y coordinate of the Panel
	*/
	public void displayLeft(Graphics g, int cx, int cy) throws ArrayIndexOutOfBoundsException
	{
		int x = cx/20;
		int y = cy/20;

		try
		{
			// if there is no flag on the button
			if(button[y][x].getState() == 0)
			{
				// if there are no mines nearby,
				//   expose all safe buttons connected to this spot
				if(button[y][x].getNoOfMinesAround() == 0)		//no mines around

					// expose safe buttons surrounding this button
					removeAdjacentButtons(g, button[y][x].getNumber()/cols,
									button[y][x].getNumber()%rows);

				else    // if there are mines nearby
				{
					// if this is a mine... that's all folks!

						if(button[y][x].isMined()) 	// bomb here
						{
							if(!gameOver)
								button[y][x].clicked(true);

							displayAllMines(g,x,y);		// show where all the mines are

							gameOver = true;
						}
						else
							if(button[y][x].getState() == 0)	// no flag here
							{
								button[y][x].showIt(g);
							}
				}
			}

		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			// oh oh, you are outside the bounds of the minefield
		}
	}

	/**
	*  display all the mines in the mine field 'cause the game is over
	*  @param g		Graphics Context
	*  @param x		x coordinate of the Panel
	*  @param y		y coordinate of the Panel
	*/
	public void displayAllMines(Graphics g, int x, int y)
	{
//		System.out.println("Game over = " + gameOver);
//		System.out.println("x = " + x + " y = "+y);

		for(int cy=0;cy<320;cy+=20)
		{
			for(int cx=0;cx<320;cx+=20)
			{
				if(button[cy/20][cx/20].isMined())
				{
					button[cy/20][cx/20].setShowing(true);

					if(button[cy/20][cx/20].getClicked() == true)
						g.setColor(Color.red);
					else
						g.setColor(Color.lightGray);

					g.fillRect(button[cy/20][cx/20].getNumber()%16*20,
								button[cy/20][cx/20].getNumber()/16*20, 19, 19);

					g.drawImage(parent.minePict,button[cy/20][cx/20].getNumber()%16*20+4,
								button[cy/20][cx/20].getNumber()/16*20+3,Color.lightGray,parent);
				}
			}
		}
	}

	/**
 	*	 recursively expose safe buttons surrounding the current button
	*
	*  @param g		Graphics Context
	*  @param r		row of the button on the Panel
	*  @param c		column of the button on the Panel
	*/
	public void removeAdjacentButtons(Graphics g,int r, int c)
	{
		// base cases
		if (notOnBoard(r, c) || button[r][c].isShowing() || button[r][c].getState() == 1)
			return;
		
		// setShow and show every button if not base case
		button[r][c].setShowing(true);
		button[r][c].showIt(g);
		
		// if button is adjacent to a mine (when there is a number on it) do not 
		// reveal any further buttons
		if(button[r][c].getNoOfMinesAround() > 0) return;
		
		// if button does not have a number, reveal all the adjacent buttons recursively
		removeAdjacentButtons(g, r-1, c-1);
		removeAdjacentButtons(g, r-1, c);
		removeAdjacentButtons(g, r-1, c+1);
		removeAdjacentButtons(g, r, c-1);
		removeAdjacentButtons(g, r, c+1);
		removeAdjacentButtons(g, r+1, c-1);
		removeAdjacentButtons(g, r+1, c);
		removeAdjacentButtons(g, r+1, c+1);
	}
	/**
	 * helper method to tell whether a coordinate is not on the board
	 * @param r row of the coordinate
	 * @param c column of the coordinate
	 * @return <i>true</i> board[r][c] is invalid/out of bounds <i>false<i/> if valid
	 */
	private boolean notOnBoard(int r, int c) {
		return (r < 0 || r >= button.length || c < 0 || c >= button[0].length);
	}
	/**
	 *   restart the game
	 */
	public void restartGame()
	{
		init();
		repaint();
	}
	/**
	 *   check to see if player won the game
	 *@return true or false
	 */
	public boolean gameWon()
	{
		for(int i=0;i<16;i++)
		{
			for(int j=0;j<16;j++)
			{
				if((button[i][j].isMined()) && (button[i][j].getState() == 0))
					return false;
			}
		}

		if(numFlags==mines)
			return true;
		else
			return false;
	}
	/**
	 *   Event triggered when player presses the Mouse
	 *	 @param Mouse Event
	 */
	public void mousePressed(MouseEvent e)
	{
		cx = e.getX();
		cy = e.getY();
		System.out.println("button " + e.getModifiers());

		if(parent.getGameOver() == true)
			restartGame();

		if(!gameOver)
		{
			start = false;
			Graphics g = this.getGraphics();
			if(font==null)
			{
				font = g.getFont();
				font= new Font(font.getName(), 1, font.getSize()+1);
				g.setFont(font);
			}

			if(e.getModifiers() == MouseEvent.BUTTON3_MASK)  // right mouse button
			{
				if(!button[cy/20][cx/20].isShowing())		// no of mines around not showing
				{
					button[cy/20][cx/20].changeState(); // change state to flagged/normal
					if(button[cy/20][cx/20].getState() == 1)
					{
						numFlags++;
						if(gameWon())
						{
							displayRight(g,cx,cy);
							gameOver = true;
							parent.playButton.setIcon(new ImageIcon("shades.jpg"));
							parent.playSound(new File("jabba.au"));
							parent.stopThread();
							System.out.println("Game Over - You Won");
						}
					}
					else
						numFlags--;
					displayRight(g,cx,cy);
				}
			}
			else
				if(e.getModifiers() == MouseEvent.BUTTON1_MASK)  // left mouse button
				{
					displayLeft(g,cx,cy);
					if(gameOver)
					{
							parent.playButton.setIcon(new ImageIcon("sadSmiley.jpg"));
							parent.playSound(new File("bomb.au"));
							parent.stopThread();
							System.out.println("Game Over - You Stepped on a mine");
					}

				}
		}
	}

	public void mouseClicked(MouseEvent e)
	{
		System.out.println("Mouse Clicked");
	}

	public void mouseEntered(MouseEvent e)
	{
		System.out.println("Mouse Entered Panel");
	}

	public void mouseExited(MouseEvent e)
	{
		System.out.println("Mouse Exited Panel");
	}

	public void mouseReleased(MouseEvent e)
	{
		System.out.println("Mouse Released");
	}
	
	public static void main(String[] args) {
		MineSweepFrame frame = new MineSweepFrame();
		MinePanel game = new MinePanel(frame);
		frame.setVisible(true);
	}
}
