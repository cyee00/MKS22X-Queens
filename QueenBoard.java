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

  public boolean addQueen(int r, int c){
    if (r<board.length&&c<board.length){
      if (board[r][c]!=0) return false; //false if there's already a queen there or it's marked off
      board[r][c]--;
    }
    return true;
  }

  public boolean removeQueen(int r,int c){
    board[r][c]=0;
    return true;
  }

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
  }
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
        if (solveQueen(col+1)){ //if it's possible to add queen at next column
          return true;
        }
        removeQueen(); //else backtrack and remove most recent queen
      }
    }
    return false;//nothing worked so it's not a solution
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    int ans=0;
    if (board[0][0]!=0){
      throw new IllegalStateException();
    } else if (solve()){
      for (int r=0;r<board.length;r++){
        for (int c=0;c<board[r].length;c++){
        //  if (board[r][c]){}
        }
      }
    }
    return ans;
  }

  public static void main(String[]args){
    QueenBoard qb = new QueenBoard(3);
    qb.addQueen(0,0);
    qb.addQueen(2,2);
    qb.removeQueen(0,0);
    System.out.println(qb.toString());
  }
}
