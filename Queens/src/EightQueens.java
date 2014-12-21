import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
 
public class EightQueens  extends Applet implements MouseListener, MouseMotionListener, Runnable
{
 
        Button m_objButton = new Button("Solve");
        boolean m_bSolving = false;
        String m_strStatus = "";
       
        private static final long serialVersionUID = 1L;
 
        static final int NUMROWS = 8;
        static final int NUMCOLS = 8;
        static final int SQUAREWIDTH = 50;
        static final int SQUAREHEIGHT = 50;
        static final int BOARDLEFT = 50;
        static final int BOARDTOP = 50;
       
        Thread t;
       
        int m_nBoard[][] = new int[8][8];
        void ClearBoard()
        {
                for( int i=0; i <NUMROWS; i++ )
                {
                        for( int j=0; j <NUMCOLS; j ++ )
                        {
                                m_nBoard[i][j] = 0;
                        }
                }
        }
 
        int m_nMoves[] = new int[8];
        int m_nMoveIndex = 0;
 
        boolean m_bClash = false;
       
        Image m_imgQueen;
        MediaTracker tracker = new MediaTracker(this);
       
        public void init()
        {
        setSize(1020,700);  
        addMouseListener( this );
        addMouseMotionListener( this );        
       
        try
        {
                m_imgQueen = getImage(getCodeBase()/*getDocumentBase()*/,"queen.png");
                tracker.addImage(m_imgQueen, 1);
                tracker.waitForAll();          
        }
        catch (Exception e)
        {
        }
 
        t = new Thread(this);
        t.start();
        }       
       
        void DrawSquares( Graphics canvas )
        {
                canvas.setColor(Color.BLACK);
                for( int i=0; i <NUMROWS; i ++ )
                {
                        for( int j=0; j <NUMCOLS; j ++ )
                        {
                                canvas.drawRect( BOARDLEFT + j * SQUAREWIDTH,
                                        BOARDTOP + i * SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT );
                               
                                if( m_nBoard[i][j] != 0 )
                                {
                                        canvas.drawImage( m_imgQueen,
                                                BOARDLEFT + j * SQUAREWIDTH + 8, BOARDTOP + i * SQUAREHEIGHT + 6, null );
                                }
                        }
                }
        }
       
        void CheckColumns( Graphics canvas )
        {
                // Check all columns
                for(  int j=0; j <NUMCOLS; j ++ )
                {
                        int jCount = 0;
                        for( int i=0; i <NUMROWS; i ++ )
                        {
                                if( m_nBoard[i][j] != 0 )
                                {
                                        jCount++;
                                }
                        }
                        if( jCount > 1 )
                        {
                                canvas.drawLine( BOARDLEFT + j * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                                        BOARDTOP + ( SQUAREHEIGHT / 2 ),       
                                        BOARDLEFT + j * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                                        BOARDTOP + SQUAREHEIGHT * 7 + ( SQUAREHEIGHT / 2 ) );
                               
                                m_bClash = true;
                        }
                }
        }
 
        void CheckRows( Graphics canvas )
        {
                for(  int i=0; i <NUMROWS; i ++ )
                {
                        int iCount = 0;
                        for( int j=0; j <NUMCOLS; j ++ )
                        {
                                if( m_nBoard[i][j] != 0 )
                                {
                                        iCount++;
                                }
                        }
                        if( iCount > 1 )
                        {
                                canvas.drawLine( BOARDLEFT + ( SQUAREWIDTH / 2 ),
                                        BOARDTOP + i * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ), 
                                        BOARDLEFT + 7 * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                                        BOARDTOP + i * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
                               
                                m_bClash = true;
                        }
                }
        }
       
        void CheckDiagonal1( Graphics canvas )
        {
                // Check diagonal 1
       
                for( int i=NUMROWS-1; i >=0; i -- )
                {
                        int j = 0;
                       
                        int nThisRow = i;
                        int nThisCol = j;
 
                        int jCount = 0;
                       
                        while( nThisCol < NUMCOLS &&
                                nThisRow < NUMROWS )
                        {
                                if( m_nBoard[nThisRow][nThisCol] != 0 )
                                {
                                        jCount++;
                                }
                                nThisCol++;
                                nThisRow++;
                        }
                       
                        if( jCount > 1 )
                        {
                                canvas.drawLine( BOARDLEFT + j * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                                                BOARDTOP + i * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ), 
                                                BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                                                BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
                               
                                m_bClash = true;
                        }
                }
 
                for( int j=1; j <NUMCOLS; j++)
                {
                        int i = 0;
               
                        int nThisRow = i;
                        int nThisCol = j;
 
                        int jCount = 0;
                       
                        while( nThisCol < NUMCOLS &&
                                nThisRow < NUMROWS )
                        {
                                if( m_nBoard[nThisRow][nThisCol] != 0 )
                                {
                                        jCount++;
                                }
                                nThisCol++;
                                nThisRow++;
                        }
                       
                        if( jCount > 1 )
                        {
                                canvas.drawLine( BOARDLEFT + j * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                                                BOARDTOP + i * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ), 
                                                BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                                                BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
                               
                                m_bClash = true;
                        }
                       
                }
               
        }
       
        void CheckDiagonal2( Graphics canvas )
        {
                // Check diagonal 2
               
                for( int i=NUMROWS-1; i >=0; i-- )
                {
                        int j = NUMCOLS - 1;
                       
                        int nThisRow = i;
                        int nThisCol = j;
 
                        int jCount = 0;
                       
                        while( nThisCol >= 0 &&
                                nThisRow < NUMROWS )
                        {
                                if( m_nBoard[nThisRow][nThisCol] != 0 )
                                {
                                        jCount++;
                                }
                                nThisCol--;
                                nThisRow++;
                        }
                       
                        if( jCount > 1 )
                        {
                                canvas.drawLine( BOARDLEFT + j * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                                                BOARDTOP + i * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ), 
                                                BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                                                BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
                               
                                m_bClash = true;
                        }
                }
 
                for( int j=NUMCOLS-1; j >=0; j--)
                {
                        int i = 0;
               
                        int nThisRow = i;
                        int nThisCol = j;
 
                        int jCount = 0;
                       
                        while( nThisCol >= 0 &&
                                nThisRow < NUMROWS )
                        {
                                if( m_nBoard[nThisRow][nThisCol] != 0 )
                                {
                                        jCount++;
                                }
                                nThisCol--;
                                nThisRow++;
                        }
                       
                        if( jCount > 1 )
                        {
                                canvas.drawLine( BOARDLEFT + j * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                                                BOARDTOP + i * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ), 
                                                BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                                                BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
                               
                                m_bClash = true;
                        }
                       
                }
        }
       
        public void paint (Graphics canvas)
        {
 
                m_bClash = false;
               
                DrawSquares( canvas );
               
                canvas.setColor(Color.RED);
               
                CheckColumns( canvas );
                CheckRows( canvas );
                CheckDiagonal1( canvas );
                CheckDiagonal2( canvas );
               
                canvas.setColor(Color.BLUE);
                canvas.drawString(m_strStatus, BOARDLEFT, BOARDTOP + SQUAREHEIGHT * 8 + 20);
        }
       
        @Override
        public void mouseClicked(MouseEvent e)
        {
        }
       
        @Override
        public void mouseDragged(MouseEvent arg0)
        {
        }
 
        @Override
        public void mouseMoved(MouseEvent arg0)
        {
        }
 
        @Override
        public void mouseEntered(MouseEvent arg0)
        {
        }
 
        @Override
        public void mouseExited(MouseEvent arg0)
        {
        }
 
        @Override
        public void mousePressed(MouseEvent arg0)
        {
                int j = ( arg0.getX() - BOARDLEFT ) / SQUAREWIDTH;
                int i = ( arg0.getY() - BOARDTOP ) / SQUAREHEIGHT;
                if( j >= 0 &&
                        i >= 0 &&
                        j < NUMCOLS &&
                        i < NUMROWS )
                {
                        m_nBoard[i][j] ^= 1;
                        repaint();
                }
        }
 
        @Override
        public void mouseReleased(MouseEvent arg0)
        {
        }
 
 
        boolean SolveIt( int j )
        {
               
                if( j >= NUMCOLS )
                {
                        return( true );
                }
               
                for( int i=0; i <NUMROWS; i++ )
                {
                        m_strStatus = "placed";
                        m_nBoard[i][j] = 1;
                        repaint();
                       
                        try
                        {
                                Thread.sleep(50);
                        }
                        catch (InterruptedException e)
                        {
                        	//
                        }
                       
                        if( m_bClash )
                        {
                                m_strStatus = "removing";
                                m_nBoard[i][j] = 0;
                                repaint();
                               
                                try
                                {
                                        Thread.sleep(100);
                                }
                                catch (InterruptedException e)
                                {
                                	//
                                }
                        }
                        else
                        {
                                if( !SolveIt( j + 1 ) )
                                {
                                        m_strStatus = "Backtracking";
                                        m_nBoard[i][j] = 0;
                                        repaint();
                                }
                                else
                                {
                                        return( true );
                                }
                        }
                }
               
                return( false );
        }
       
        @Override
        public void run()
        {
        	SolveIt( 0 );
        }
       
}