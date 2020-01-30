public class Piece {

    protected Integer matrix[][] = new Integer[5][5];

    public Piece(Integer type) {
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                matrix[i][j] = 0;
            }
        }

        switch(type) {
            case 0:
            matrix[0][0] = 1;
            break;

            case 1:
            matrix[0][0] = 1;
            matrix[0][1] = 1;
            break;

            case 2:
            matrix[0][0] = 1;
            matrix[0][1] = 1;
            matrix[1][1] = 1;
            break;

            case 3:
            matrix[0][0] = 1;
            matrix[0][1] = 1;
            matrix[0][2] = 1;
            break;

            case 4:
            matrix[0][0] = 1;
            matrix[0][1] = 1;
            matrix[1][0] = 1;
            matrix[1][1] = 1;
            break;

            case 5:
            matrix[0][1] = 1;
            matrix[1][0] = 1;
            matrix[1][1] = 1;
            matrix[1][2] = 1;
            break;

            case 6:
            matrix[0][0] = 1;
            matrix[0][1] = 1;
            matrix[0][2] = 1;
            matrix[0][3] = 1;
            break;

            case 7:
            matrix[0][2] = 1;
            matrix[1][0] = 1;
            matrix[1][1] = 1;
            matrix[1][2] = 1;
            break;

            case 8:
            matrix[0][1] = 1;
            matrix[0][2] = 1;
            matrix[1][0] = 1;
            matrix[1][1] = 1;
            break;

            case 9:
            matrix[0][0] = 1;
            matrix[1][0] = 1;
            matrix[1][1] = 1;
            matrix[1][2] = 1;
            matrix[1][3] = 1;
            break;

            case 10:
            matrix[0][1] = 1;
            matrix[1][1] = 1;
            matrix[2][0] = 1;
            matrix[2][1] = 1;
            matrix[2][2] = 1;
            break;

            case 11:
            matrix[0][0] = 1;
            matrix[1][0] = 1;
            matrix[2][0] = 1;
            matrix[2][1] = 1;
            matrix[2][2] = 1;
            break;

            case 12:
            matrix[0][1] = 1;
            matrix[0][2] = 1;
            matrix[0][3] = 1;
            matrix[1][0] = 1;
            matrix[1][1] = 1;
            break;

            case 13:
            matrix[0][2] = 1;
            matrix[1][0] = 1;
            matrix[1][1] = 1;
            matrix[1][2] = 1;
            matrix[2][0] = 1;
            break;

            case 14:
            matrix[0][0] = 1;
            matrix[1][0] = 1;
            matrix[1][1] = 1;
            matrix[2][0] = 1;
            matrix[2][1] = 1;
            break;

            case 15:
            matrix[0][1] = 1;
            matrix[0][2] = 1;
            matrix[1][0] = 1;
            matrix[1][1] = 1;
            matrix[2][0] = 1;
            break;

            case 16:
            matrix[0][0] = 1;
            matrix[0][1] = 1;
            matrix[1][0] = 1;
            matrix[2][0] = 1;
            matrix[2][1] = 1;
            break;

            case 17:
            matrix[0][1] = 1;
            matrix[0][2] = 1;
            matrix[1][0] = 1;
            matrix[1][1] = 1;
            matrix[2][1] = 1;
            break;

            case 18:
            matrix[0][1] = 1;
            matrix[1][0] = 1;
            matrix[1][1] = 1;
            matrix[1][2] = 1;
            matrix[2][1] = 1;
            break;

            case 19:
            matrix[0][1] = 1;
            matrix[1][0] = 1;
            matrix[1][1] = 1;
            matrix[1][2] = 1;
            matrix[1][3] = 1;
            break;
            
            case 20:
            matrix[0][0] = 1;
            matrix[1][0] = 1;
            matrix[2][0] = 1;
            matrix[3][0] = 1;
            matrix[4][0] = 1;
            break;
        }
    }

    // Under Construction...

    public Integer[][] rotate() {
        Integer newMatrix[][] = new Integer[5][5];
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                newMatrix[i][j] = matrix[j][i];
            }
        }
        newMatrix = horizontalMirror(newMatrix);
        matrix = newMatrix;
        return newMatrix;
    }

    public Integer[][] horizontalMirror(Integer[][] newMatrix) {
        for(int i=0; i<5; i++) {
            for(int j=0; j<3; j++) {
                int temp = newMatrix[i][4-j];
                newMatrix[i][4-j] = newMatrix[i][j];
                newMatrix[i][j] = temp;
            }
        }
        return newMatrix;
    }

    public Integer[][] verticalMirror(Integer[][] newMatrix) {
        for(int i=0; i<3; i++) {
            for(int j=0; j<5; j++) {
                int temp = newMatrix[4-i][j];
                newMatrix[4-i][j] = newMatrix[i][j];
                newMatrix[i][j] = temp;
            }
        }
        return newMatrix;
    }
    // DEBUG print to console
    public void print() {
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }


}