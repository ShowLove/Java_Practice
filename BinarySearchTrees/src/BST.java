/*Carlos Garzon, 
 *Mauricio Diaz 
 *Wednesdays at 2:30pm
 *
 *Binary Search Tree Assignment
 */

public class BST 
{

/*
1. Add a delete method. //

2. Add a node numbering that keeps track of the subtree size. (You will need to go back and update the delete method.)

3. Add the k time test. Have k be a variable which is initially 3.

4. Add methods to find the min and max values in the tree.
*/

	// This is the root node, which starts off as null
	//   when the BST is empty.
	BSTNode m_objRootNode;
	
	// Class constructor.
	public BST()
	{
		// Not really necessary, provided for clarity.
		m_objRootNode = null;
	}

	// Method to see if the tree is empty.
	public boolean IsEmpty()
	{
		// Return a boolean indicating whether the
		//   three is empty or not.
		return( m_objRootNode == null );
	}

	/* Functions to search for an element */
    public BSTNode Search( int nKeyValue )
    {
        return( Search( m_objRootNode, nKeyValue ) );
    }
    
    // Method to search for an element recursively.
    private BSTNode Search( BSTNode objNode, int nKeyValue )
    {
    	
    	if( objNode == null )
    	{
    		return( null );
    	}
    	
    	// First, we get the key value for this node.
    	int nThisKeyValue = objNode.GetKeyValue();
            
    	// See if the passed in key value is less. If so,
    	//   this indicates that we need to go left.
    	if( nKeyValue < nThisKeyValue )
    	{
    		// Get the left node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetLeftNode();
    	}
            
    	// See if the passed in key value is greater. If so,
    	//   this indicates that we need to go right.
    	else if( nKeyValue > nThisKeyValue )
    	{
    		// Get the right node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetRightNode();
    	}

    	// Here we have found the node with the key
    	//   value that was passed in.
    	else
    	{
    		return( objNode );
    	}
            
    	// Now call Search recursively.
    	return( Search( objNode, nKeyValue ) );
	}
    
    // This method inserts a node based on the key value.
    public void Insert( int nKeyValue ) 
    {
    	// The root node is returned to m_objRootNode from Insert()
    	m_objRootNode = Insert( nKeyValue, m_objRootNode );
    }    

    // Class protected (internal) method to insert nodes. This method
    //   will be called recursively.
    protected BSTNode Insert( int nKeyValue, BSTNode objNode ) 
    {
 
    	// This node is null and simply needs to be allocated.
        if( objNode == null )
        {
        	objNode = new BSTNode( nKeyValue );
        }
        
        // Here we need to walk left.
        else if( nKeyValue < objNode.GetKeyValue() )
        {
        	// Set the left node of this object by recursively walking left.
        	objNode.SetLeftNode( Insert( nKeyValue, objNode.GetLeftNode() ) );
        }
        
        // Here we need to talk right.
        else if( nKeyValue > objNode.GetKeyValue() )
        {
        	// Set the right node of this object by recursively walking right.
        	objNode.SetRightNode( Insert( nKeyValue, objNode.GetRightNode() ) );
        }
        
        return( objNode );
    }

/*
BSTNode objNode = Node root
objNode.m_nKeyValue 
  = objNode.m_objLeftNode , objNode.m_objRightNode  
*/

    public void inorder()
    {
        System.out.print("In-order Traversal:");
        inorder(m_objRootNode);
        System.out.println();
    }

    private void inorder(BSTNode objNode)
    {
    	 BSTNode root = objNode;
    	
        if (root == null)
            return;

        inorder(objNode.m_objLeftNode);
        System.out.print(" " + objNode.m_nKeyValue);
        inorder(objNode.m_objRightNode);
    }

    private int min(int a, int b)
    {
        return (a < b) ? a : b;
    }

    private int max(int a, int b)
    {
        return (a > b) ? a : b;
    }

    public int height(int a)
    {
        if (m_objRootNode == null)
            return -1;

        return 1 + max(height(m_objRootNode.m_objLeftNode.m_nKeyValue), height(m_objRootNode.m_objRightNode.m_nKeyValue));
    }

    public int findMax(int b)
    {
        if(m_objRootNode == null)
            return 0;

        int a = max(findMax(m_objRootNode.m_objLeftNode.m_nKeyValue), findMax(m_objRootNode.m_objRightNode.m_nKeyValue));

        return max(a, m_objRootNode.m_nKeyValue);
    }

    public int findMin(int c)
    {
         if(m_objRootNode == null)
            return 1000000000;

        int a = min(findMin(m_objRootNode.m_objLeftNode.m_nKeyValue), findMin(m_objRootNode.m_objRightNode.m_nKeyValue));

        return min(a, m_objRootNode.m_nKeyValue);       
    }

    public void delete(int data)
    {
        m_objRootNode = delete(m_objRootNode, data);
    }

    private BSTNode delete(BSTNode objNode, int data) 
    {
        if (objNode == null) //
        {
            return null;
        }
        else if (data < objNode.m_nKeyValue) //
        {
            objNode.m_objLeftNode = delete(objNode.m_objLeftNode, data);//
        }
        else if (data > objNode.m_nKeyValue) //
        {
            objNode.m_objRightNode = delete(objNode.m_objRightNode, data); //
        }
        else
        {
            if (objNode.m_objLeftNode == null && objNode.m_objRightNode == null) //
            {
                return null;
            }
            else if (objNode.m_objRightNode == null) //
            {
                return objNode.m_objLeftNode; //
            }
            else if (objNode.m_objLeftNode == null) //
            {
                return objNode.m_objRightNode;//
            }
            else
            {
                objNode.m_nKeyValue = findMax(m_objRootNode.m_objLeftNode.m_nKeyValue);
                objNode.m_objLeftNode = delete(objNode.m_objLeftNode, data);
            }
        }

        return objNode;
    }
    
}
