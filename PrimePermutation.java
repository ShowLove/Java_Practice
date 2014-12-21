package entropy;

/*
The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.

There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.

What 12-digit number do you form by concatenating the three terms in this sequence?
 */

import java.util.Arrays;

public class PrimePermutations
   implements Runnable
{
   int mPrimeDigits;
   boolean[] mPrimes;

   /**
    * Default constructor
    */
   public PrimePermutations()
   {
      mPrimeDigits = 10000;
      mPrimes = new boolean[10000];
      Arrays.fill(mPrimes, true);
   }

   private void sievePrimes()
   {
      for (int i = 2; i < (int) Math.sqrt(mPrimeDigits); i++)
      {
         if (mPrimes[i])
         {
            for (int j = i*i; j < mPrimeDigits; j = j+i)
            {
               mPrimes[j] = false;
            }
         }
      }
   }

   private boolean isPermutation(int pFirst, int pSecond)
   {
      int[] array = new int[10];
      int temp = pSecond;
      while (temp > 0)
      {
         array[temp % 10]++;
         temp /= 10;
      }
      temp = pFirst;
      while (temp > 0)
      {
         array[temp % 10]--;
         temp /= 10;
      }
      for (int i = 0; i < 10; i++)
      {
         if (array[i] != 0)
         {
            return false;
         }
      }
      return true;
   }

   /**
    * Run all the code in a thread.
    */
   public void run()
   {
      sievePrimes();
      for (int i = 1000; i < 10000; i++)
      {
         if (mPrimes[i])
         {
            //System.out.println("i = " + i);
            for (int j = i + 1; j < 10000; j++)
            {
               if (mPrimes[j])
               {
                  //System.out.println("j = " + j);
                  int k = j + (j - i);
                  if (k < 9999)
                  {
                     if (mPrimes[k])
                     {
                        //System.out.println("k = " + k);
                        if (isPermutation(i, j) && isPermutation(j, k))
                        {
                           System.out.println(i + " " + j + " " + k);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   /**
    * Main
    * @param args command-line input
    */
   public static void main(String[] args)
   {
      new PrimePermutations().run();
   }

}
