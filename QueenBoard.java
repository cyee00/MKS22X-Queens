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
    board[r][c]=-1;
    return true;
  }

  public boolean removeQueen(int r,int c){
    board[r][c]=0;
    return true;
  }

  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){
    return true;
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    return 0;
  }

  public static void main(String[]args){
    QueenBoard qb = new QueenBoard(3);
    qb.addQueen(0,0);
    qb.addQueen(2,2);
    qb.removeQueen(0,0);
    System.out.println(qb.toString());
  }
}
