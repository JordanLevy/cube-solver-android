package com.jlevy.cubesolver;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CubeSolverActivity extends Activity{

    String[][][][] crossCases;
    String[] F2LCases;
    String[] OLLCases;
    String[] PLLCases;
    public static Cube cube;
    TextView solutionText;
    String finalSolution = "";

    public CubeSolverActivity()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube_solver);
        solutionText = (TextView) findViewById(R.id.solution);
        solveCube();
    }

    public void checkValidCube()
    {
        //check if the cube is possible
    }

    public void doMoves(Cube c, String m)
    {
        String[] moves = m.split(" ");
        int modifier = 0;
        for(int i = 0; i < moves.length; i++)
        {
            if(moves[i].length() == 0)
            {
                continue;
            }
            if(moves[i].length() == 1)
            {
                modifier = 1;
            }
            else
            {
                if(moves[i].charAt(1) == '\'')
                {
                    modifier = -1;
                }
                else if(moves[i].charAt(1) == '2')
                {
                    modifier = 2;
                }
            }
            switch(moves[i].charAt(0))
            {
                case 'U':
                    c = c.U(modifier);
                    break;
                case 'D':
                    c = c.D(modifier);
                    break;
                case 'R':
                    c = c.R(modifier);
                    break;
                case 'L':
                    c = c.L(modifier);
                    break;
                case 'F':
                    c = c.F(modifier);
                    break;
                case 'B':
                    c = c.B(modifier);
                    break;
                case 'M':
                    c = c.M(modifier);
                    break;
                case 'E':
                    c = c.E(modifier);
                    break;
                case 'S':
                    c = c.S(modifier);
                    break;
                case 'u':
                    c = c.u(modifier);
                    break;
                case 'd':
                    c = c.d(modifier);
                    break;
                case 'r':
                    c = c.r(modifier);
                    break;
                case 'l':
                    c = c.l(modifier);
                    break;
                case 'f':
                    c = c.f(modifier);
                    break;
                case 'b':
                    c = c.b(modifier);
                    break;
                case 'x':
                    c = c.X(modifier);
                    break;
                case 'y':
                    c = c.Y(modifier);
                    break;
                case 'z':
                    c = c.Z(modifier);
                    break;
            }
        }
    }

    public String getInverseAlg(String a)
    {
        String inv = "";
        String[] moves = a.split(" ");
        int modifier = 0;
        for(int i = moves.length - 1; i >= 0; i--)
        {
            modifier = 0;
            if(moves[i].length() == 0)
            {
                continue;
            }
            if(moves[i].length() == 1)
            {
                modifier = -1;
            }
            else
            {
                if(moves[i].charAt(1) == '\'')
                {
                    modifier = 1;
                }
                else if(moves[i].charAt(1) == '2')
                {
                    modifier = 2;
                }
            }
            inv += moves[i].charAt(0);
            if(modifier == -1)
            {
                inv += "\'";
            }
            else if(modifier == 2)
            {
                inv += "2";
            }
            inv += " ";
        }
        return inv;
    }

    public void initCrossCases()
    {
        crossCases = new String[3][3][3][2];
        //Edge on top, oriented
        crossCases[0][1][0][0] = "U2 F2 ";
        crossCases[0][0][1][0] = "U' F2 ";
        crossCases[0][1][2][0] = "F2 ";
        crossCases[0][2][1][0] = "U F2 ";
        //Edge on top, unoriented
        crossCases[0][1][0][1] = "U R' F R ";
        crossCases[0][0][1][1] = "L F' L' ";
        crossCases[0][1][2][1] = "U' R' F R ";
        crossCases[0][2][1][1] = "R' F R ";
        //Edge on side, oriented
        crossCases[1][0][2][0] = "D' L D ";
        crossCases[1][2][2][0] = "D R' D' ";
        crossCases[1][2][0][0] = "D R D' ";
        crossCases[1][0][0][0] = "D' L' D ";
        //Edge on side, unoriented
        crossCases[1][0][2][1] = "F' ";
        crossCases[1][2][2][1] = "F ";
        crossCases[1][2][0][1] = "R2 F R2 ";
        crossCases[1][0][0][1] = "L2 F' L2 ";
        //Edge on bottom, oriented
        crossCases[2][1][2][0] = "";
        crossCases[2][2][1][0] = "R D R' D' ";
        crossCases[2][1][0][0] = "B D2 B' D2 ";
        crossCases[2][0][1][0] = "L' D' L D ";
        //Edge on bottom, unoriented
        crossCases[2][1][2][1] = "D R D' F ";
        crossCases[2][2][1][1] = "R F ";
        crossCases[2][1][0][1] = "B D R D' ";
        crossCases[2][0][1][1] = "L' F' ";
    }

    public void initF2LCases()
    {
        F2LCases = new String[42];
        F2LCases[0] = "";
        F2LCases[1] = "U R U' R' "; //Case 1
        F2LCases[2] = "U' F' U F ";
        F2LCases[3] = "F' U' F ";
        F2LCases[4] = "R U R' ";
        F2LCases[5] = "F2 L' U' L U F2 "; //Case 5
        F2LCases[6] = "y F2 R U R' U' F2 y'";
        F2LCases[7] = "U' R U2 R' U2 R U' R' ";
        F2LCases[8] = "y' U R' U2 R U2 R' U R y ";
        F2LCases[9] = "y F2 U R U' R' F2 y' ";
        F2LCases[10] = "U' R U R' U R U R' "; //Case 10
        F2LCases[11] = "U' R U2 R' U F' U' F ";
        F2LCases[12] = "R' U2 R2 U R2 U R ";
        F2LCases[13] = "y' U R' U R U' R' U' R y ";
        F2LCases[14] = "U' R U' R' U R U R' ";
        F2LCases[15] = "R B L U' L' B' R' "; //Case 15
        F2LCases[16] = "R U' R' U2 F' U' F ";
        F2LCases[17] = "R U2 R' U' R U R' ";
        F2LCases[18] = "F' U2 F U F' U' F ";
        F2LCases[19] = "U R U2 R2 F R F' ";
        F2LCases[20] = "U' F' U2 F2 R' F' R "; //Case 20
        F2LCases[21] = "R U' R' U2 R U R' ";
        F2LCases[22] = "F' L' U2 L F ";
        F2LCases[23] = "U2 R2 U2 R' U' R U' R2 ";
        F2LCases[24] = "U F' L' U L F R U R' ";
        F2LCases[25] = "y U' L' U L U F U' F' y' "; //Case 25
        F2LCases[26] = "U R U' R' F R' F' R ";
        F2LCases[27] = "R U' R' U R U' R' ";
        F2LCases[28] = "R U R' U2 F' U F ";
        F2LCases[29] = "R' F R F' U R U' R' ";
        F2LCases[30] = "R U R' U' R U R' "; //Case 30
        F2LCases[31] = "R U' R' U F' U F ";
        F2LCases[32] = "R U R' U' R U R' U' R U R' ";
        F2LCases[33] = "U' R U' R' U2 R U' R' ";
        F2LCases[34] = "U R U R' U2 R U R' ";
        F2LCases[35] = "U2 R U' R' U' F' U' F "; //Case 35
        F2LCases[36] = "U F' U' F U' R U R' ";
        F2LCases[37] = "R2 U2 F R2 F' U2 R' U R' ";
        F2LCases[38] = "R2 U2 R' U' R U' R' U2 R' ";
        F2LCases[39] = "R U2 R U R' U R U2 R2 ";
        F2LCases[40] = "F' L' U2 L F R U R' "; //Case 40
        F2LCases[41] = "R U F R U R' U' F' R' "; //Case 41
    }

    public void initOLLCases()
    {
        OLLCases = new String[58];
        OLLCases[0] = "";
        OLLCases[1] = "R U2 R2' F R F' U2 R' F R F' ";
        OLLCases[2] = "F R U R' U' F' f R U R' U' f' ";
        OLLCases[3] = "f R U R' U' f' U' F R U R' U' F' ";
        OLLCases[4] = "f R U R' U' f' U F R U R' U' F' ";
        OLLCases[5] = "r' U2 R U R' U r "; //Case 5
        OLLCases[6] = "r U2 R' U' R U' r' ";
        OLLCases[7] = "r U R' U R U2 r' ";
        OLLCases[8] = "l' U' L U' L' U2 l ";
        OLLCases[9] = "R U R' U' R' F R2 U R' U' F' ";
        OLLCases[10] = "R U R' U R' F R F' R U2 R' "; //Case 10
        OLLCases[11] = "M R U R' U R U2 R' U M' ";
        OLLCases[12] = "M U2 R' U' R U' R' U2 R U M' ";
        OLLCases[13] = "r U' r' U' r U r' F' U F ";
        OLLCases[14] = "R' F R U R' F' R F U' F' ";
        OLLCases[15] = "l' U' l L' U' L U l' U l "; //Case 15
        OLLCases[16] = "r U r' R U R' U' r U' r' ";
        OLLCases[17] = "R U R' U R' F R F' U2 R' F R F' ";
        OLLCases[18] = "r U R' U R U2 r2 U' R U' R' U2 r ";
        OLLCases[19] = "M U R U R' U' M' R' F R F' ";
        OLLCases[20] = "M U R U R' U' M2 U R U' r' "; //Case 20
        OLLCases[21] = "R' U' R U' R' U R U' R' U2 R ";
        OLLCases[22] = "R U2 R2 U' R2 U' R2 U2 R ";
        OLLCases[23] = "R2 D R' U2 R D' R' U2 R' ";
        OLLCases[24] = "r U R' U' r' F R F' ";
        OLLCases[25] = "F R' F' r U R U' r' "; //Case 25
        OLLCases[26] = "R U2 R' U' R U' R' ";
        OLLCases[27] = "R U R' U R U2 R' ";
        OLLCases[28] = "r U R' U' M U R U' R' ";
        OLLCases[29] = "r2 D' r U r' D r2 U' r' U' r ";
        OLLCases[30] = "R2 U R' B' R U' R2 U R B R' "; //Case 30
        OLLCases[31] = "R' U' F U R U' R' F' R ";
        OLLCases[32] = "x' R U R' D R U' R U' R' U R' D' x ";
        OLLCases[33] = "R U R' U' R' F R F' ";
        OLLCases[34] = "R U R2 U' R' F R U R U' F' ";
        OLLCases[35] = "R U2 R2' F R F' R U2 R' "; //Case 35
        OLLCases[36] = "L' U' L U' L' U L U L F' L' F ";
        OLLCases[37] = "F R U' R' U' R U R' F' ";
        OLLCases[38] = "R U R' U R U' R' U' R' F R F' ";
        OLLCases[39] = "L F' L' U' L U F U' L' ";
        OLLCases[40] = "R' F R U R' U' F' U R "; //Case 40
        OLLCases[41] = "R U' R' U2 R U y R U' R' U' F' ";
        OLLCases[42] = "L' U L U2 L' U' y' L' U L U F ";
        OLLCases[43] = "F' U' L' U L F ";
        OLLCases[44] = "F U R U' R' F' ";
        OLLCases[45] = "F R U R' U' F' "; //Case 45
        OLLCases[46] = "R' U' R' F R F' U R ";
        OLLCases[47] = "F' L' U' L U L' U' L U F ";
        OLLCases[48] = "F R U R' U' R U R' U' F' ";
        OLLCases[49] = "R B' R2 F R2 B R2 F' R ";
        OLLCases[50] = "R' F R2 B' R2 F' R2 B R' "; //Case 50
        OLLCases[51] = "f R U R' U' R U R' U' f' ";
        OLLCases[52] = "R U R' U R d' R U' R' F' ";
        OLLCases[53] = "r' U' R U' R' U R U' R' U2 r ";
        OLLCases[54] = "r U R' U R U' R' U R U2 r' ";
        OLLCases[55] = "R U2 R2 U' R U' R' U2 F R F' "; //Case 55
        OLLCases[56] = "f R U R' U' f' F R U R' U' R U R' U' F' ";
        OLLCases[57] = "R U R' U' M' U R U' r' ";
    }

    public void initPLLCases()
    {
        PLLCases = new String[22];
        PLLCases[0] = ""; //Solved
        PLLCases[1] = "x R' U R' D2 R U' R' D2 R2 x' "; //Aa perm
        PLLCases[2] = "x R2 D2 R U R' D2 R U' R x' "; //Ab perm
        PLLCases[3] = "x' R U' R' D R U R' D' R U R' D R U' R' D' x "; //E perm
        PLLCases[4] = "R' U' F' R U R' U' R' F R2 U' R' U' R U R' U R "; //F perm
        PLLCases[5] = "R2 u R' U R' U' R u' R2 y' R' U R "; //Ga perm
        PLLCases[6] = "R' U' R y R2 u R' U R U' R u' R2 "; //Gb perm
        PLLCases[7] = "R2 u' R U' R U R' u R2 y R U' R' "; //Gc perm
        PLLCases[8] = "R U R' y' R2 u' R U' R' U R' u R2 "; //Gd perm
        PLLCases[9] = "M2 U' M2 U2 M2 U' M2 "; //H perm
        PLLCases[10] = "L U' R' U L' U2 R U' R' U2 R "; //Ja perm
        PLLCases[11] = "U R U R' F' R U R' U' R' F R2 U' R' "; //Jb perm
        PLLCases[12] = "z U R' D R2 U' R D' U R' D R2 U' R D' z' "; //Na perm
        PLLCases[13] = "z U' R D' R2 U R' D U' R D' R2 U R' D z' "; //Nb perm
        PLLCases[14] = "R U R' F' R U2 R' U2 R' F R U R U2 R' "; //Ra perm
        PLLCases[15] = "U' R' U2 R U2 R' F R U R' U' R' F' R2 "; //Rb perm
        PLLCases[16] = "R U R' U' R' F R2 U' R' U' R U R' F' "; //T perm
        PLLCases[17] = "R U' R U R U R U' R' U' R2 "; //Ua perm
        PLLCases[18] = "R2 U R U R' U' R' U' R' U R' "; //Ub perm
        PLLCases[19] = "R' U R' d' R' F' R2 U' R' U R' F R F "; //V perm
        PLLCases[20] = "F R U' R' U' R U R' F' R U R' U' R' F R F' "; //Y perm
        PLLCases[21] = "M2 U' M2 U' M' U2 M2 U2 M' "; //Z perm
    }

    public void solveCrossPiece(int col)
    {
        String result = "";
        boolean answerFound = false;
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                for(int z = 0; z < 3; z++)
                {
                    if(cube.cubie[x][y][z].type == 2) {
                        if (cube.cubie[x][y][z].sticker[0].colour == 3 && cube.cubie[x][y][z].sticker[1].colour == col) {
                            result = crossCases[x][y][z][0];
                            answerFound = true;
                        } else if (cube.cubie[x][y][z].sticker[1].colour == 3 && cube.cubie[x][y][z].sticker[0].colour == col) {
                            result = crossCases[x][y][z][1];
                            answerFound = true;
                        }
                    }
                    if(answerFound)
                    {
                        break;
                    }
                }
                if(answerFound)
                {
                    break;
                }
            }
            if(answerFound)
            {
                break;
            }
        }
        result += "y ";
        doMoves(cube, result);
        finalSolution += result;
    }

    public void solveF2LPair(int col1, int col2)
    {
        initF2LCases();
        //get edge in FR slot or in top layer
        //if edge in FL slot
        if(cube.cubie[1][0][2].hasColours(col1, col2))
        {
            doMoves(cube, "L' U' L ");
            finalSolution += "L' U' L ";
        }
        //if edge in BL slot
        else if(cube.cubie[1][0][0].hasColours(col1, col2))
        {
            doMoves(cube, "L U L' ");
            finalSolution += "L U L' ";
        }
        //if edge in BR slot
        else if(cube.cubie[1][2][0].hasColours(col1, col2))
        {
            doMoves(cube, "R' U R ");
            finalSolution += "R' U R ";
        }

        //get corner out of FL, BL, BR
        //if corner is in FL slot
        if(cube.cubie[2][0][2].hasColours(3, col1, col2))
        {
            //if edge will go into FL, move the edge
            if(cube.cubie[0][1][0].hasColours(col1, col2))
            {
                doMoves(cube, "U ");
                finalSolution += "U ";
            }
            doMoves(cube, "L' U' L ");
            finalSolution += "L' U' L ";
        }
        //if corner is in BL slot
        else if(cube.cubie[2][0][0].hasColours(3, col1, col2))
        {
            //if edge will go into BL, move the edge
            if(cube.cubie[0][1][2].hasColours(col1, col2))
            {
                doMoves(cube, "U ");
                finalSolution += "U ";
            }
            doMoves(cube, "L U L' U ");
            finalSolution += "L U L' U ";
        }
        //if corner is in BR slot
        else if(cube.cubie[2][2][0].hasColours(3, col1, col2))
        {
            //if edge will go into BR, move the edge
            if(cube.cubie[0][1][0].hasColours(col1, col2))
            {
                doMoves(cube, "U ");
                finalSolution += "U ";
            }
            doMoves(cube, "R' U R U ");
            finalSolution += "R' U R U ";
        }
        //if corner in top layer FL
        else if(cube.cubie[0][0][2].hasColours(3, col1, col2))
        {
            doMoves(cube, "U' ");
            finalSolution += "U' ";
        }
        //if corner in top layer BL
        else if(cube.cubie[0][0][0].hasColours(3, col1, col2))
        {
            doMoves(cube, "U2 ");
            finalSolution += "U2 ";
        }
        //if corner in top layer BR
        else if(cube.cubie[0][2][0].hasColours(3, col1, col2))
        {
            doMoves(cube, "U ");
            finalSolution += "U ";
        }

        for (int i = 0; i < F2LCases.length; i++) {
            doMoves(cube, F2LCases[i]);
            if (cube.cubie[1][2][2].sticker[0].colour == col1 && cube.cubie[1][2][2].sticker[1].colour == col2 && cube.cubie[2][2][2].sticker[0].colour == 3 && cube.cubie[2][2][2].sticker[1].colour == col1 && cube.cubie[2][2][2].sticker[2].colour == col2) {
                doMoves(cube, "y ");
                finalSolution += F2LCases[i] + "y ";
                return;
            }
            doMoves(cube, getInverseAlg(F2LCases[i]));
        }
    }

    public void cross()
    {
        initCrossCases();
        solveCrossPiece(1);
        solveCrossPiece(2);
        solveCrossPiece(5);
        solveCrossPiece(4);
    }

    public void F2L()
    {
        initF2LCases();
        solveF2LPair(1, 2);
        solveF2LPair(2, 5);
        solveF2LPair(5, 4);
        solveF2LPair(4, 1);
    }

    public void OLL()
    {
        initOLLCases();
        for(int j = 0; j < 4; j++) {
            for (int i = 0; i < OLLCases.length; i++) {
                doMoves(cube, OLLCases[i]);
                if (cube.cubie[0][0][0].sticker[0].matches(cube.cubie[0][0][1].sticker[0], cube.cubie[0][0][2].sticker[0]) && cube.cubie[0][1][0].sticker[0].matches(cube.cubie[0][1][1].sticker[0], cube.cubie[0][1][2].sticker[0]) && cube.cubie[0][2][0].sticker[0].matches(cube.cubie[0][2][1].sticker[0], cube.cubie[0][2][2].sticker[0])) {
                    finalSolution += OLLCases[i];
                    return;
                }
                doMoves(cube, getInverseAlg(OLLCases[i]));
            }
            doMoves(cube, "U ");
            finalSolution += "U ";
        }

    }

    public void PLL() {
        initPLLCases();
        for(int j = 0; j < 4; j++) {
            for (int i = 0; i < PLLCases.length; i++) {
                doMoves(cube, PLLCases[i]);
                if (cube.cubie[0][0][0].sticker[1].matches(cube.cubie[0][0][1].sticker[1], cube.cubie[0][0][2].sticker[2]) && cube.cubie[0][0][2].sticker[1].matches(cube.cubie[0][1][2].sticker[1], cube.cubie[0][2][2].sticker[2]) && cube.cubie[0][2][2].sticker[1].matches(cube.cubie[0][2][1].sticker[1], cube.cubie[0][2][0].sticker[2]) && cube.cubie[0][2][0].sticker[1].matches(cube.cubie[0][1][0].sticker[1], cube.cubie[0][0][0].sticker[2])) {
                    finalSolution += PLLCases[i];
                    return;
                }
                doMoves(cube, getInverseAlg(PLLCases[i]));
            }
            doMoves(cube, "U ");
            finalSolution += "U ";
        }
    }

    public void AUF()
    {
        for(int i = 0; i < 4; i++)
        {
            if(cube.cubie[0][1][2].sticker[1].matches(cube.cubie[1][1][2].sticker[0]))
            {
                return;
            }
            doMoves(cube, "U ");
            finalSolution += "U ";
        }
    }

    //Cancels out moves in the finalSolution to make it more efficient
    public void cancelMoves()
    {
        String[] moves = finalSolution.split(" ");
        for(int i = 0; i < moves.length; i++)
        {

        }
    }

    public void CFOP()
    {
        cross();
        F2L();
        OLL();
        PLL();
        AUF();
        //cancelMoves();
    }

    public void solveCube()
    {
        checkValidCube();
        CFOP();
        solutionText.setText(finalSolution);
    }

}
