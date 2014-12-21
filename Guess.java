package guess;
import java.applet.Applet;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;

public class Guess extends Applet implements MouseListener, MouseMotionListener 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void init() 
	{
        setSize(1020,700);  
        
        GenerateGame();
       
        addMouseListener( this );
        addMouseMotionListener( this );        
	}
	
	void GenerateGame()
	{
		m_nRandomNumbers = new int[20];
		m_nSecretNumber = -1;
		m_nSecretIndex = -1;
		m_nGuessNumbers = new int[20];
		m_nGuessIndexes = new int[20];
		m_nNumGuesses = 0;
		m_nRowSolved = -1;
		m_bGameIsOver = false;
		
        Random rnd = new Random();
        
        int nStart = rnd.nextInt(5) + 2;
        int nMid = nStart + 25;
        int tmp;
        
        //Now it only iterates length/2 as opposed to length
        // from O(n) to O(n/2)
        for( int i=0; i<m_nRandomNumbers.length/2; i++ )
        {
        	if( m_nSecretNumber == -1 &&
        		rnd.nextInt(m_nRandomNumbers.length/2) == 0 )
        	{
        		m_nSecretNumber = nStart;
        		m_nSecretIndex = i;
        	}
        	
        	m_nRandomNumbers[i] = nStart;
        	m_nRandomNumbers[m_nRandomNumbers.length/2 + i] = nMid;
        	tmp = (rnd.nextInt(2)+1);
        	nStart += tmp;
        	nMid += tmp;
        }
        
        if( m_nSecretNumber == -1 )
        {
        	m_nSecretIndex = ( m_nRandomNumbers.length * 3 ) / 4;
        	m_nSecretNumber = m_nRandomNumbers[m_nSecretIndex];
        }
	}
	
	int m_nRandomNumbers[];
	int m_nSecretNumber, m_nSecretIndex;
	int m_nGuessNumbers[];
	int m_nGuessIndexes[];
	int m_nNumGuesses, m_nRowSolved;
	boolean m_bGameIsOver;
	
	public void paint (Graphics page)
	{
		page.drawString("Please click on the square with your guess where the magic number is hiding", 10, 10);
		
		for( int nGuessRow=0; nGuessRow<=m_nNumGuesses; nGuessRow++ )
		{
			for( int nGuessCol=0; nGuessCol<m_nRandomNumbers.length; nGuessCol++ )
			{
				page.setColor(Color.CYAN);

				if( nGuessRow < m_nNumGuesses && 
					m_nGuessIndexes[nGuessRow] == nGuessCol )
				{
					page.setColor(Color.LIGHT_GRAY);
				}
				else if( nGuessRow < m_nNumGuesses &&
					// Guess was too low.
					m_nGuessNumbers[nGuessRow] > m_nSecretNumber &&
					// Are we above the guess column?a
					m_nGuessIndexes[nGuessRow] < nGuessCol )
				{
					page.setColor(Color.GREEN);
				}
				else if( nGuessRow < m_nNumGuesses &&
					// Guess was too high.
					m_nGuessNumbers[nGuessRow] < m_nSecretNumber &&
					// Are we below the guess column?
					m_nGuessIndexes[nGuessRow] > nGuessCol )
				{
					page.setColor(Color.MAGENTA);
				}
				
				if( m_bGameIsOver &&
					nGuessRow == m_nNumGuesses)
				{
					page.setColor(Color.YELLOW);
				}
				
				page.fillRect( 10 + nGuessCol * 50, 30 + nGuessRow * 50, 45, 45 );
				page.setColor(Color.BLACK);
				page.drawRect( 10 + nGuessCol * 50, 30 + nGuessRow * 50, 45, 45 );
				
				if( m_bGameIsOver ||
					( nGuessRow < m_nNumGuesses && m_nGuessIndexes[nGuessRow] == nGuessCol )
				   )
				{
					page.drawString( "" + m_nRandomNumbers[nGuessCol], 10 + nGuessCol * 50 + 15, 30 + nGuessRow * 50 + 30 );
				}
			}
		}
	}
	
	void MakeAGuess( int nGuessIndex )
	{
		m_nGuessIndexes[m_nNumGuesses] = nGuessIndex; 
		int nGuessNumber = m_nRandomNumbers[nGuessIndex];
		m_nGuessNumbers[m_nNumGuesses] = nGuessNumber;
		m_nNumGuesses++;
		if( nGuessNumber == m_nSecretNumber )
		{
			m_bGameIsOver = true;
		}
		repaint();
	}
	
	/*
	 *  Originally this function ran in time O(n)
	 *  I improved it by removing worst case (they did not click the correct row)
	 *  I also improved it's average case to O(log(n) ) by implementing binary search
	 */
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if( m_bGameIsOver )
		{
			GenerateGame();
			repaint();
			return;
		}
		
		int nGuessRow = m_nNumGuesses;
		int lo = 0, hi = m_nRandomNumbers.length - 1, nGuessCol;
		while(hi >= lo)
		{
			nGuessCol = lo + (hi  - lo) / 2;

			//Jump out if you did not click the correct row
			//This can potentially improve performance 
			//from O(n) to O(1) in the best case (original)
			//or from log(n) to O(1) in the best case (binary search)
			if(	!(e.getY() < 30 + nGuessRow * 50 + 45 &&
				e.getY() >= 30 + nGuessRow * 50) )
			{
				break;
			}

			//If we found the Col call MakeAGuess
			//Else continue the binary search
			if( e.getX() >= 10 + nGuessCol * 50 &&
				e.getX() < 10 + nGuessCol * 50 + 45)
			{
				MakeAGuess( nGuessCol );
				break;
			}
			else if(e.getX() < 10 + nGuessCol * 50 + 45)
			{
				hi = nGuessCol - 1;	
			}
			else if(e.getX() >= 10 + nGuessCol * 50)
			{
				lo = nGuessCol + 1;	
			}

		}
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}

}

