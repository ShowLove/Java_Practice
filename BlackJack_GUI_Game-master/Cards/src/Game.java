import java.applet.Applet;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Random;

//this is me

public class Game extends Applet implements MouseListener, ActionListener
{
	
	int m_nDeck[];
	void Shuffle( int nDecks )
	{
		m_nDeck = new int[nDecks*52];
		
		Random rnd = new Random();
		int nUsage[] = new int[52];
		
		for( int i=0; i<nDecks*52; i++ )
		{
			int nCard = 0;
			do
			{
				nCard = rnd.nextInt(52);
			}
			while( nUsage[nCard] >= nDecks );
			nUsage[nCard]++;
			m_nDeck[i] = nCard;
		}
	}
	
	int m_nNumberOfPlayers = 4;
	int m_nDeckIndex = 0;
	int m_nPlayerCardIndex[] = new int[6];					//Number of cards each player has
	int m_nHand[][] = new int[6][11];						//Holds the players hand
	boolean m_nPlayer_Bust[][] = new boolean[6][11];		//Holds weather the player has busted or not
	boolean m_nPlayer_Win[] = new boolean[6];
	int m_nCurrentPlayer = 1;								//Represents the player we are currently using
	
	void Deal()
	{
		m_bTurnOverHoleCard = false;
		m_nDeckIndex = 0;
		m_nPlayerCardIndex = new int[6];
		m_nHand = new int[6][11];
		m_nCurrentPlayer = 1;
		
		for( int i=0; i<m_nNumberOfPlayers; i++ )
		{
			for( int j=0; j<2; j++ )
			{
				m_nHand[i][j] = m_nDeck[m_nDeckIndex++];	//Deal the player a random card
				m_nPlayer_Bust[i][j] = false;				//Nobody has busted yet
				m_nPlayerCardIndex[i]++;					//the amount of cards each player has
			}
		}
		
		repaint();
	}
	
	void Hit()
	{
		m_nHand[m_nCurrentPlayer][m_nPlayerCardIndex[m_nCurrentPlayer]] = m_nDeck[m_nDeckIndex++];
		m_nPlayerCardIndex[m_nCurrentPlayer]++;

		if( checkForBust() )
		{
			System.out.println("Player "+m_nCurrentPlayer+" has busted!");
			//If current player is not the dealer move to next player
			if( m_nCurrentPlayer != 0 )
			{
				// Checks if player 3 busted then moves to the dealer by calling Stand method
				if(m_nCurrentPlayer == 3)
				{
					Stand();
				}
				// Else go to next player
				else
					m_nCurrentPlayer++;
			}
			//If dealer busted calculate the score and deal again
			if( m_nCurrentPlayer == 0 )
			{
				//changeScore() <-- TODO: Write this function
				//delay or prompt to deal again <-- TODO: Write this function
			}

		}
		
		// Check for Blackjack
		if(checkForBlackJack())
			Stand();

		repaint();
	}
	
	boolean checkForBlackJack()
	{
		if(Score(m_nCurrentPlayer) == 21)
			return true;
		else
			return false;
	}
	
	boolean checkForBlackJack(int checkPlayer)
	{
		if(Score(checkPlayer) == 21)
			return true;
		else
			return false;
	}

	boolean checkForBust()
	{
		if( Score(m_nCurrentPlayer) > 21 )
			return true;
		else
			return false;
	}

	boolean checkForBust(int checkPlayer)
	{
		if( Score(checkPlayer) > 21 )
			return true;
		else
			return false;
	}
	
	void whatPlayerWon()
	{
		int dealer = 0;
		
		for( int i = 1; i < 3; i++ )
		{
			//If the score of the player is higher than the score 
			//of the dealer than he won.
			if( Score(dealer) < Score(i) )
			{
				m_nPlayer_Win[i] = true;
			}
			else
			{
				m_nPlayer_Win[i] = false;
			}
		}	
	}
	
	boolean m_bTurnOverHoleCard = false;
	void Stand()
	{		
		// If player 3, go to the dealer. Else, go to next player
		if(m_nCurrentPlayer == 3)
		{
			m_bTurnOverHoleCard = true;
			m_nCurrentPlayer = 0;
			while( Score(0) <= 16 )
			{
				Hit();
			}
			//TODO
			repaint();
		}
		else
			m_nCurrentPlayer++;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static String m_strCardName[] =
	{
		"Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
		"Nine", "Ten", "Jack", "Queen", "King", "Ace"
	};
	
	static String m_strSuitName[] =
	{
		"Clubs", "Diamonds", "Hearts", "Spades"
	};
	
	private static final int TWO = 0;
	private static final int THREE = 1;
	private static final int FOUR = 2;
	private static final int FIVE = 3;
	private static final int SIX = 4;
	private static final int SEVEN = 5;
	private static final int EIGHT = 6;
	private static final int NINE = 7;
	private static final int TEN = 8;
	private static final int JACK = 9;
	private static final int QUEEN = 10;
	private static final int KING = 11;
	private static final int ACE = 12;
	
	private static final int CLUBS = 0;
	private static final int DIAMONDS = 1;
	private static final int HEARTS = 2;
	private static final int SPADES = 3;
	
	Image m_imgCards[] = new Image[52];
	Image m_imgBust;
	Image m_imgBack;
	Image m_imgBender;
	Image m_imgSteewie;
	Image m_imgVegita;
	MediaTracker tracker = new MediaTracker(this);

	Button m_objShuffleButton = new Button("Shuffle");	
	Button m_objDealButton = new Button("Deal");	
	Button m_objHitButton = new Button("Hit");	
	Button m_objStandButton = new Button("Stand");	
	
	void LoadCardImages()
	{
        try 
        {
        	for( int i=0; i<52; i++ )
        	{
        		m_imgCards[i] = getImage(getCodeBase(),"Standard"+i+".png");
        		tracker.addImage(m_imgCards[i], i + 1);
        	}
        	m_imgBack = getImage(getCodeBase(),"Default.png");
        	m_imgBust = getImage(getCodeBase(),"Bust.png");
        	m_imgBender = getImage(getCodeBase(),"Bender.png");
        	m_imgSteewie = getImage(getCodeBase(),"Steewie.png");
        	m_imgVegita = getImage(getCodeBase(), "Vegita.png");
        	tracker.addImage(m_imgBack,  100);
        	tracker.addImage(m_imgBust, 101);
        	tracker.addImage(m_imgBender, 102);
        	tracker.addImage(m_imgSteewie, 103);
        	tracker.addImage(m_imgVegita, 104);
        	tracker.waitForAll();  
        } 
        catch (Exception e) 
        {
        }
	}
	
	public void init()
	{
		LoadCardImages();
		setSize(1020,700);
		addMouseListener( this );
		
		add( m_objShuffleButton );
        m_objShuffleButton.addActionListener(this);		
		add( m_objDealButton );
        m_objDealButton.addActionListener(this);
		add( m_objHitButton );
        m_objHitButton.addActionListener(this);		
		add( m_objStandButton );
        m_objStandButton.addActionListener(this);		
        
        Shuffle(1);
	}
	
	int Score( int nPlayerNumber )
	{
		int nScore = 0;
		int nAceCount = 0;
		
		for( int j=0; j<m_nPlayerCardIndex[nPlayerNumber]; j++ )
		{
			int nCard = m_nHand[nPlayerNumber][j];
			int nRank = nCard % 13;
			if( nRank <= 8 )
			{
				nScore += ( nRank + 2 );
			}
			else if( nRank <= 11 )
			{
				nScore += 10;
			}
			else
			{
				nScore += 11;
				nAceCount++;
			}
		}		

		while( nScore > 21 &&
			nAceCount > 0 )
		{
			nScore -= 10;
			nAceCount--;
		}
		
		return( nScore );
	}

	public int hiLoCount(int player)
	{
		int count=0;

		for(int j=0; j< m_nPlayerCardIndex[player]; j++)
		{
			if(m_nHand[player][j] != -1)
			{
			if(m_nHand[player][j] <=6) count--;
			else if(m_nHand[player][j] >9) count++;
			}
		}
		return count;
	}
	
	public void paint(Graphics canvas)
	{	
		for( int i=0; i<m_nNumberOfPlayers; i++ )	//Player Num
		{
			//Check if dealer busted
			if( checkForBust(0) )
				canvas.drawImage(m_imgBust, 400 + 0 * 50, 100, null );

			for( int j=0; j<m_nPlayerCardIndex[i]; j++ )	//Card Num
			{
				int nCard = m_nHand[i][j];

				//Draw back of the card and second card
				if( i == 0 && j == 0 && !m_bTurnOverHoleCard && !checkForBust(0) )
				{
					canvas.drawImage(m_imgBack, 400 + j * 50, 100 , null );
					//Paint dealers visible card
					canvas.drawImage(m_imgCards[ m_nHand[i][j + 1] ], 400 + (j+1) * 50, 100 , null );
				}			
				//Dealer after player holds
				else if( i == 0 && m_bTurnOverHoleCard && !checkForBust(0) ) 
				{
					canvas.drawImage(m_imgCards[nCard], 400 + j * 50, 100 , null );
				}
				else	//Left, Middle, Right Player
				{
					//Draw bust
					if( checkForBust(i) && i != 0 )
					{
						if(i == 2)
							canvas.drawImage(m_imgBust, 100 + 300*(i - 1) , 100 + 400, null );
						else
							canvas.drawImage(m_imgBust, 100 + 300*(i - 1) , 350, null );
						
						break;
					}
					//Draw cards
					if( i != 0 )
					{
						if(i == 2)
						{
							//This draws the middle card
							canvas.drawImage(m_imgCards[nCard], 100 + 300*(i - 1) + j * 50, 100 + 400, null );
							
							//Draw Vegita
							canvas.drawImage(m_imgVegita, 100 + 300*(i - 1) , 400, null);
							
							//Draw count
							canvas.drawString("Your card count is " + hiLoCount(i), 100 + 300*(i - 1) , 650 );
							
						}
						else
						{
							//This draws the first and last player
							canvas.drawImage(m_imgCards[nCard], 100 + 300*(i - 1) + j * 50, 350, null );
							//draw Bender
							if( i == 1 )
							{
								canvas.drawImage( m_imgBender, 100 + 300*(i - 1), 225, null );
								
								//Draw count
								canvas.drawString("Your card count is " + hiLoCount(i), 100, 175 );
							}
							//draw Stweewie
							if( i == 3 )
							{	
								canvas.drawImage( m_imgSteewie, 100 + 300*(i - 1), 225, null );
								
								//Draw Count
								canvas.drawString("Your card count is " + hiLoCount(i), 100 +300*2, 175 );
							}
						}
					}
				}		
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if( arg0.getActionCommand().equals("Shuffle"))
		{
			Shuffle(1);
		}
		else if( arg0.getActionCommand().equals("Deal"))
		{
			Deal();
		}
		else if( arg0.getActionCommand().equals("Hit"))
		{
			Hit();
		}
		else if( arg0.getActionCommand().equals("Stand"))
		{
			Stand();
		}
	}
	
}
