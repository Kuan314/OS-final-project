import java.util.*;

public class Sudoku {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
        int [][] matrix = new int[9][9];
	System.out.println("This app is help you to solve the Sudoku");
	System.out.println("Please enter the number, if it is blank, just type 0:");
	System.out.println("Each line only can enter 9 number");
	System.out.println("Example:");
	System.out.println("004000768");

	for(int i = 0; i < 9; i++){
		char[] temp = sc.next().toCharArray();
		if(temp.length > 9){
			System.out.println("You entered too much number, please try again");
			i--;
		}
		else if(temp.length < 9){
			System.out.println("You entered less than 9 number, please try again");
			i--;
		}
		else{
			for(int j = 0; j < 9; j++){
				if(temp[j] >= '0'&& temp[j] <= '9'){
					matrix[i][j] = temp[j] - '0';
				}
				else{
					System.out.println("You entered a invalid word, please enter only number");
					i--;
					break;
				}
			}
		}
	}

	/* 	example
	 * { 0, 0, 4, 0, 0, 0, 7, 6, 8 },          
	 * { 3, 0, 0, 4, 5, 0, 0, 2, 0 },
	 * { 0, 0, 0, 0, 9, 8, 0, 0, 0 },
	 * { 1, 6, 9, 0, 0, 0, 0, 0, 0 },
	 * { 0, 5, 0, 9, 0, 7, 0, 8, 0 },
         * { 0, 0, 0, 0, 0, 0, 1, 9, 5 },
         * { 0, 0, 0, 5, 4, 0, 0, 0, 0 },
         * { 0, 4, 0, 0, 6, 3, 0, 0, 9 },
         * { 2, 1, 3, 0, 0, 0, 6, 0, 0 }
         */ 


	if(SolveSuduko(matrix, 9)){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(matrix[i][j]+"\t");
                }
                System.out.println();
            }
        }

    }

    public static boolean SolveSuduko(int [][]matrix, int n){
        int rowIndex = -1;
        int columnIndex = -1;
        int i = 0;
        int j = 0;

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if(matrix[i][j] == 0){
                    rowIndex = i;
                    columnIndex = j;
                    break;
                }
            }
            if(rowIndex != -1){
                break;
            }

        }
        if(i == n && j == n){
            return  true;
        }
        else {
            for (int value = 1; value < 10; value++) {
                if(IsSafe(matrix, value, rowIndex, columnIndex, n)){
                    matrix[rowIndex][columnIndex] = value;
                    if(!SolveSuduko(matrix,n)){
                        matrix[rowIndex][columnIndex] = 0;
                    }
                    else {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static boolean IsSafe(int [][] matrix, int value, int rowIndex, int columnIndex, int n){
        //row check
        for (int j = 0; j < 9; j++) {
            if(matrix[rowIndex][j] == value){
                return  false;
            }
        }
        //column check
        for (int i = 0; i < 9; i++) {
            if(matrix[i][columnIndex] == value){
                return  false;
            }
        }
        //submatrix check
        int baseRowIndex = rowIndex - (rowIndex % 3);
        int baseColumnIndex = columnIndex - (columnIndex % 3);
        for (int i = baseRowIndex; i < baseRowIndex + 3; i++) {
            for (int j = baseColumnIndex; j < baseColumnIndex + 3; j++) {
                if(matrix[i][j] == value){
                    return  false;
                }

            }
        }
        return true;
    }
}


