/***************************************
*        Author:     Jason Steadman
*        Class:      CS4110
*        Semester:   Summer 2018
***************************************/

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class TuringMachine
{
   public static void main (String[] args)
   {
      String sFileName = "";
      char[] arryTape;
      File f;
      Scanner fileIn;
      Scanner lineCount;
      String[][] arryMachine;
      String sCurrentState = "1Start";
      String sNextState;
      int iHeadLocation = 0;
      char cPrint;
      boolean bCrash = false;
      boolean bAccept = false;
      int iCount = 0;
      String sWord = "";
      
      //Create turing machine
      try
      {
         try
         {
            //Find file and word
            sFileName = args[0];
            if(sFileName.contains(".txt"))
            {
               sWord = args[1];
            }
            else
            {
               sFileName = args[1];
               if(sFileName.contains(".txt"))
               {
                  sWord = args[0];
               }
               else 
               {
                  System.out.println("No file name has been entered");
                  return;
               }
            }
         }
         catch(Exception e)
         {
            System.out.println("Error reading command line input");
            System.out.println(e);
            return;
         }
         
         f = new File(sFileName);
         
         fileIn = new Scanner(f);
         lineCount = new Scanner(f);
         
         //int used to count number of lines in file
         int iLineCount = 0;
         
         //Count lines in file
         while(lineCount.hasNextLine())
         {
            iLineCount++;
            lineCount.nextLine();
         } 
         
         //Array used to model the turing machine
         arryMachine = new String[iLineCount - 1][5];
         
         //Temp string that holds current line in file
         String sTemp;
         
         //int holding the current row
         int iRow = 0;
         
         //Holds last index and new index of ","
         int iLastIndex, iNewIndex;
         
         //Create turing machine
         while(fileIn.hasNext())
         {
            iLastIndex = iNewIndex = 0;
            sTemp = fileIn.nextLine();
         
            //Skip commented lines
            if(sTemp.charAt(0) == '#')
            {
               continue;
            }
            //Handle lines that only contain Halt
            else if (sTemp.contains("Halt") && !sTemp.contains(","))
            {
               arryMachine[iRow][0] = sTemp;
               iRow++;
            }
            else
            {
               //Current state
               iLastIndex = sTemp.indexOf(",");
               arryMachine[iRow][0] = sTemp.substring(0, iLastIndex).replaceAll(" ","");               
               
               //Read
               iNewIndex = sTemp.indexOf(",", iLastIndex + 1);
               arryMachine[iRow][1] = sTemp.substring(iLastIndex + 1, iNewIndex).replaceAll(" ","");
               
               //Write
               iLastIndex = iNewIndex;
               iNewIndex = sTemp.indexOf(",", iLastIndex + 1);
               arryMachine[iRow][2] = sTemp.substring(iLastIndex + 1, iNewIndex).replaceAll(" ","");
               
               //Direction to move head
               iLastIndex = iNewIndex;
               iNewIndex = sTemp.indexOf(",", iLastIndex + 1);
               arryMachine[iRow][3] = sTemp.substring(iLastIndex + 1, iNewIndex).replaceAll(" ","");
               
               //Next state
               iLastIndex = iNewIndex;
               arryMachine[iRow][4] = sTemp.substring(iLastIndex + 1, sTemp.length()).replaceAll(" ","");
               iRow++;
               
            }
         }
         
         //Find start state
         for (int i = 0; i < arryMachine.length; i++)
         {
            if(arryMachine[i][0].contains("Start") || arryMachine[i][0].contains("start"))
            {
               //Set begining state
               sCurrentState = arryMachine[i][0];
               break;
            }
         }
         try
         {      
            //Create tape
            arryTape = new char[10000];
            //Fill Tape
            for(int i = 0; i < 10000; i++)
            {
               arryTape[i] = '_';
            }
            
            //Place word on tape
            for(int i = 0; i < sWord.length(); i++)
            {
               arryTape[i] = sWord.charAt(i);
            }
           
            //Test Word
            while(!bCrash && !bAccept)
            { 
               for(int i = 0; i < arryMachine.length; i++)
               {
                  //Find current state
                  if(sCurrentState.equals(arryMachine[i][0]))
                  {
                     //Check read for current state
                     if(arryTape[iHeadLocation] == arryMachine[i][1].charAt(0))
                     {
                        //If match
                        
                        //Write
                        arryTape[iHeadLocation] = arryMachine[i][2].charAt(0);
                        
                        //Move head
                        if(arryMachine[i][3].equals("R"))
                        {
                           ++iHeadLocation;
                        }
                        else if (arryMachine[i][3].equals("L"))
                        {
                           --iHeadLocation;
                        }
                        
                        //Set next state
                        sCurrentState = arryMachine[i][4];
                        
                        //Check for Halt
                        if(sCurrentState.contains("Halt"))
                        {
                           bAccept = true;
                           break;
                        }
                        
                        //Increment count to avoid infinite loop
                        ++iCount;
                        
                        //If infinite loop
                        if(iCount == 1000)
                        {
                           bCrash = true;
                        }
                        
                        break;
                     }
                  }
                  
                  //No state to move to 
                  if(i == arryMachine.length - 1)
                  {
                     bCrash = true;
                  }
               }
            }
         
         }
         //Stops writer head from trying to move the the -1 index of tape
         catch (ArrayIndexOutOfBoundsException e) 
         {
            bCrash = true;
         }
         catch(Exception e)
         {
            System.out.println(e);
         }
      }
      catch(Exception e)
      {
         System.out.println(e);
      }
                  
      //Accept
      if(bAccept)
      {
         System.out.println("Accepted: " + sWord);
      }
      //Reject
      else if(bCrash)
      {
         System.out.println("Rejected: " + sWord);
      }
   }
}