public class QueenBoard{
  private int[][] board;

  //Constructor
  public QueenBoard(int size){
    board = new int[size][size];
    for (int i=0;i<board.length;i++){
      for (int n=0;n<board[i].length;n++){
        board[i][n]=0;
      }
    }
  }

  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _
  *_ _ _ Q
  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String ans="";
    for (int i=0;i<board.length;i++){
      for (int n=0;n<board[i].length;n++){
        if (board[i][n]>-1){
          ans+="_";
        } else if(board[i][n]==-1){
          ans+="Q";
        }
        ans+=" ";
      }
      ans+="\n";
    }
    return ans;
  }

  private void mark(int r,int c){
    for (int i=1;i<board.length-c;i++){
      board[r][c+i]++;//add a mark
      if (i<board.length-r){
        board[r+i][c+i]++;
      }
      if (i<=r){
        board[r-i][c+i]++;
      }
    }
  }

  private boolean addQueen(int r, int c){
    if (r<board.length&&c<board.length){
      if (board[r][c]!=0) return false; //false if there's already a queen there or it's marked off
      board[r][c]=-1;
      mark(r,c); //mark off threatened squares
    }
    return true;
  }

  private void unmark(int r,int c){
    for (int i=1;i<board.length-c;i++){
      board[r][c+i]--;//remove a mark
      if (i<board.length-r){
        board[r+i][c+i]--;
      }
      if (i<=r){
        board[r-i][c+i]--;
      }
    }
  }

  private boolean removeQueen(int r,int c){
    if (r<board.length&&c<board.length){
      if (board[r][c]!=-1) return false; //false if there's no queen there
      board[r][c]=0;
      unmark(r,c);
    }
    return true;
  }

  /*
  private boolean maxQueens(int[][] nums){
    int count = 0;
    for (int r=0;r<nums.length;r++){
      for (int c=0;c<nums[r].length;c++){
        if (nums[r][c]==-1){
          count++;
          c=nums[r].length;
        }
      }
      if (count==nums.length){
        return true;
      }
    }
    if (count==nums.length) return true;
    return false;
  }*/

  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){
    if (board[0][0]!=0) throw new IllegalStateException(); //checking for non-zero value
    return solve(0); //call to recursive fxn
  }

  private boolean solve (int col){
    if (col < board.length) return true; //reached end of board, therefore this is a solution
    for (int r=0;r<board.length;r++){
      if (addQueen(r,col)){ // if it's possible to add queen at this spot
        if (solve(col+1)){ //if it's possible to add queen at next column
          return true;
        }
        removeQueen(r,col); //else backtrack and remove most recent queen
      }
    }
    return false;//nothing worked so it's not a solution
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    if (board[0][0]!=0){
      throw new IllegalStateException();
    }
    if (board.length==2||board.length==3){//exception for boards of size 2 or 3 (Source: Wikipedia)
      return 0;
    }
      int ans=countSolutions(0);
      //clear the board
      for (int r=0;r<board.length;r++){
        for (int c=0;c<board[r].length;c++){
          board[r][c]=0;
        }
      }
      return ans;
  }

  private int countSolutions(int col){
    int ans=0;
    if (col>=board.length){
      return 1; //if you reach end of row, add 1 to # of solutions
    } else for (int r=0;r<board.length;r++){
      if (addQueen(r,col)){//try to add queen at this spot, if possible then
        ans+=countSolutions(col+1); //try for the next column
      }
      removeQueen(r,col);//else backtrack, remove most recent queen
    }
    return ans;
  }

  public static void main(String[]args){
    QueenBoard qb1 = new QueenBoard(1);
      QueenBoard qb3 = new QueenBoard(3);
    QueenBoard qb7 = new QueenBoard(7);
    QueenBoard qb8 = new QueenBoard(8);
    QueenBoard qb10 = new QueenBoard(10);
    /*qb.addQueen(0,0);
    qb.addQueen(2,2);
    qb.addQueen(2,2);
    qb.removeQueen(0,0);
    qb.removeQueen(1,1);
    System.out.println(qb.toString());*/
    System.out.println(""+qb1.countSolutions());
    System.out.println(""+qb3.countSolutions());
    System.out.println(""+qb7.countSolutions());
    System.out.println(""+qb8.countSolutions());
    System.out.println(""+qb10.countSolutions());
  }
}
