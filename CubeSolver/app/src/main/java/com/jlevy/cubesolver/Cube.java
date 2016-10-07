package com.jlevy.cubesolver;

public class Cube {

    Cubie[][][] cubie;

    public Cube()
    {
        this.cubie = new Cubie[3][3][3];
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                for(int z = 0; z < 3; z++)
                {
                    if((x == 0 || x == 2) && (y == 0 || y == 2) && (z == 0 || z == 2)) {
                        cubie[x][y][z] = new Cubie(3);
                    }
                    else if((x == 0 && y == 1 && z == 1) || (x ==1 && y == 1 && z == 2) || (x == 1 && y == 2 && z == 1) || (x == 2 && y == 1 && z == 1) || (x == 1 && y == 0 && z == 1) || (x == 1 && y == 1 && z == 0)) {
                        cubie[x][y][z] = new Cubie(1);
                    }
                    else {
                        cubie[x][y][z] = new Cubie(2);
                    }
                }
            }
        }
    }

    public Cube U(int a)
    {
        Cube cube = this;
        if(a == 1)
        {
            Cubie temp = cubie[0][0][0];
            cube.cubie[0][0][0] = cubie[0][0][2];
            cubie[0][0][2] = cubie[0][2][2];
            cubie[0][2][2] = cubie[0][2][0];
            cubie[0][2][0] = temp;

            temp = cubie[0][1][0];
            cubie[0][1][0] = cubie[0][0][1];
            cubie[0][0][1] = cubie[0][1][2];
            cubie[0][1][2] = cubie[0][2][1];
            cubie[0][2][1] = temp;
        }
        else if(a == -1)
        {
            Cubie temp = cubie[0][0][0];
            cubie[0][0][0] = cubie[0][2][0];
            cubie[0][2][0] = cubie[0][2][2];
            cubie[0][2][2] = cubie[0][0][2];
            cubie[0][0][2] = temp;

            temp = cubie[0][1][0];
            cubie[0][1][0] = cubie[0][2][1];
            cubie[0][2][1] = cubie[0][1][2];
            cubie[0][1][2] = cubie[0][0][1];
            cubie[0][0][1] = temp;
        }
        else if(a == 2)
        {
            Cubie temp = cubie[0][0][0];
            cubie[0][0][0] = cubie[0][0][2];
            cubie[0][0][2] = cubie[0][2][2];
            cubie[0][2][2] = cubie[0][2][0];
            cubie[0][2][0] = temp;

            temp = cubie[0][1][0];
            cubie[0][1][0] = cubie[0][0][1];
            cubie[0][0][1] = cubie[0][1][2];
            cubie[0][1][2] = cubie[0][2][1];
            cubie[0][2][1] = temp;

            temp = cubie[0][0][0];
            cubie[0][0][0] = cubie[0][0][2];
            cubie[0][0][2] = cubie[0][2][2];
            cubie[0][2][2] = cubie[0][2][0];
            cubie[0][2][0] = temp;

            temp = cubie[0][1][0];
            cubie[0][1][0] = cubie[0][0][1];
            cubie[0][0][1] = cubie[0][1][2];
            cubie[0][1][2] = cubie[0][2][1];
            cubie[0][2][1] = temp;
        }
        return cube;
    }

    public Cube D(int a)
    {
        Cube cube = this;
        if(a == 1)
        {
            Cubie temp = cubie[2][2][2];
            cubie[2][2][2] = cubie[2][0][2];
            cubie[2][0][2] = cubie[2][0][0];
            cubie[2][0][0] = cubie[2][2][0];
            cubie[2][2][0] = temp;

            temp = cubie[2][2][1];
            cubie[2][2][1] = cubie[2][1][2];
            cubie[2][1][2] = cubie[2][0][1];
            cubie[2][0][1] = cubie[2][1][0];
            cubie[2][1][0] = temp;
        }
        else if(a == -1)
        {
            Cubie temp = cubie[2][2][2];
            cubie[2][2][2] = cubie[2][2][0];
            cubie[2][2][0] = cubie[2][0][0];
            cubie[2][0][0] = cubie[2][0][2];
            cubie[2][0][2] = temp;

            temp = cubie[2][2][1];
            cubie[2][2][1] = cubie[2][1][0];
            cubie[2][1][0] = cubie[2][0][1];
            cubie[2][0][1] = cubie[2][1][2];
            cubie[2][1][2] = temp;
        }
        else if(a == 2)
        {
            Cubie temp = cubie[2][2][2];
            cubie[2][2][2] = cubie[2][0][2];
            cubie[2][0][2] = cubie[2][0][0];
            cubie[2][0][0] = cubie[2][2][0];
            cubie[2][2][0] = temp;

            temp = cubie[2][2][1];
            cubie[2][2][1] = cubie[2][1][2];
            cubie[2][1][2] = cubie[2][0][1];
            cubie[2][0][1] = cubie[2][1][0];
            cubie[2][1][0] = temp;

            temp = cubie[2][2][2];
            cubie[2][2][2] = cubie[2][0][2];
            cubie[2][0][2] = cubie[2][0][0];
            cubie[2][0][0] = cubie[2][2][0];
            cubie[2][2][0] = temp;

            temp = cubie[2][2][1];
            cubie[2][2][1] = cubie[2][1][2];
            cubie[2][1][2] = cubie[2][0][1];
            cubie[2][0][1] = cubie[2][1][0];
            cubie[2][1][0] = temp;
        }
        return cube;
    }

    public Cube F(int a)
    {
        Cube cube = this;
        if(a == 1)
        {
            Cubie temp = cube.cubie[0][0][2];
            cube.cubie[0][0][2] = cube.cubie[2][0][2];
            cube.cubie[2][0][2] = cube.cubie[2][2][2];
            cube.cubie[2][2][2] = cube.cubie[0][2][2];
            cube.cubie[0][2][2] = temp;

            cube.cubie[0][0][2].twist(-1);
            cube.cubie[2][0][2].twist(1);
            cube.cubie[2][2][2].twist(-1);
            cube.cubie[0][2][2].twist(1);

            temp = cube.cubie[0][1][2];
            cube.cubie[0][1][2] = cube.cubie[1][0][2];
            cube.cubie[1][0][2] = cube.cubie[2][1][2];
            cube.cubie[2][1][2] = cube.cubie[1][2][2];
            cube.cubie[1][2][2] = temp;

            cube.cubie[0][1][2].twist(1);
            cube.cubie[1][0][2].twist(1);
            cube.cubie[2][1][2].twist(1);
            cube.cubie[1][2][2].twist(1);
        }
        else if(a == -1)
        {
            Cubie temp = cube.cubie[0][0][2];
            cube.cubie[0][0][2] = cube.cubie[0][2][2];
            cube.cubie[0][2][2] = cube.cubie[2][2][2];
            cube.cubie[2][2][2] = cube.cubie[2][0][2];
            cube.cubie[2][0][2] = temp;

            cube.cubie[0][0][2].twist(-1);
            cube.cubie[0][2][2].twist(1);
            cube.cubie[2][2][2].twist(-1);
            cube.cubie[2][0][2].twist(1);

            temp = cube.cubie[0][1][2];
            cube.cubie[0][1][2] = cube.cubie[1][2][2];
            cube.cubie[1][2][2] = cube.cubie[2][1][2];
            cube.cubie[2][1][2] = cube.cubie[1][0][2];
            cube.cubie[1][0][2] = temp;

            cube.cubie[0][1][2].twist(1);
            cube.cubie[1][0][2].twist(1);
            cube.cubie[2][1][2].twist(1);
            cube.cubie[1][2][2].twist(1);
        }
        else if(a == 2)
        {
            Cubie temp = cube.cubie[0][0][2];
            cube.cubie[0][0][2] = cube.cubie[2][0][2];
            cube.cubie[2][0][2] = cube.cubie[2][2][2];
            cube.cubie[2][2][2] = cube.cubie[0][2][2];
            cube.cubie[0][2][2] = temp;

            cube.cubie[0][0][2].twist(-1);
            cube.cubie[2][0][2].twist(1);
            cube.cubie[2][2][2].twist(-1);
            cube.cubie[0][2][2].twist(1);

            temp = cube.cubie[0][1][2];
            cube.cubie[0][1][2] = cube.cubie[1][0][2];
            cube.cubie[1][0][2] = cube.cubie[2][1][2];
            cube.cubie[2][1][2] = cube.cubie[1][2][2];
            cube.cubie[1][2][2] = temp;

            cube.cubie[0][1][2].twist(1);
            cube.cubie[1][0][2].twist(1);
            cube.cubie[2][1][2].twist(1);
            cube.cubie[1][2][2].twist(1);

            temp = cube.cubie[0][0][2];
            cube.cubie[0][0][2] = cube.cubie[2][0][2];
            cube.cubie[2][0][2] = cube.cubie[2][2][2];
            cube.cubie[2][2][2] = cube.cubie[0][2][2];
            cube.cubie[0][2][2] = temp;

            cube.cubie[0][0][2].twist(-1);
            cube.cubie[2][0][2].twist(1);
            cube.cubie[2][2][2].twist(-1);
            cube.cubie[0][2][2].twist(1);

            temp = cube.cubie[0][1][2];
            cube.cubie[0][1][2] = cube.cubie[1][0][2];
            cube.cubie[1][0][2] = cube.cubie[2][1][2];
            cube.cubie[2][1][2] = cube.cubie[1][2][2];
            cube.cubie[1][2][2] = temp;

            cube.cubie[0][1][2].twist(1);
            cube.cubie[1][0][2].twist(1);
            cube.cubie[2][1][2].twist(1);
            cube.cubie[1][2][2].twist(1);
        }
        return cube;
    }

    public Cube B(int a)
    {
        Cube cube = this;
        if(a == 1)
        {
            Cubie temp = cube.cubie[2][0][0];
            cube.cubie[2][0][0] = cube.cubie[0][0][0];
            cube.cubie[0][0][0] = cube.cubie[0][2][0];
            cube.cubie[0][2][0] = cube.cubie[2][2][0];
            cube.cubie[2][2][0] = temp;

            cube.cubie[2][0][0].twist(-1);
            cube.cubie[0][0][0].twist(1);
            cube.cubie[0][2][0].twist(-1);
            cube.cubie[2][2][0].twist(1);

            temp = cube.cubie[2][1][0];
            cube.cubie[2][1][0] = cube.cubie[1][0][0];
            cube.cubie[1][0][0] = cube.cubie[0][1][0];
            cube.cubie[0][1][0] = cube.cubie[1][2][0];
            cube.cubie[1][2][0] = temp;

            cube.cubie[2][1][0].twist(1);
            cube.cubie[1][0][0].twist(1);
            cube.cubie[0][1][0].twist(1);
            cube.cubie[1][2][0].twist(1);
        }
        else if(a == -1)
        {
            Cubie temp = cube.cubie[2][0][0];
            cube.cubie[2][0][0] = cube.cubie[2][2][0];
            cube.cubie[2][2][0] = cube.cubie[0][2][0];
            cube.cubie[0][2][0] = cube.cubie[0][0][0];
            cube.cubie[0][0][0] = temp;

            cube.cubie[2][0][0].twist(-1);
            cube.cubie[2][2][0].twist(1);
            cube.cubie[0][2][0].twist(-1);
            cube.cubie[0][0][0].twist(1);

            temp = cube.cubie[2][1][0];
            cube.cubie[2][1][0] = cube.cubie[1][2][0];
            cube.cubie[1][2][0] = cube.cubie[0][1][0];
            cube.cubie[0][1][0] = cube.cubie[1][0][0];
            cube.cubie[1][0][0] = temp;

            cube.cubie[2][1][0].twist(1);
            cube.cubie[1][2][0].twist(1);
            cube.cubie[0][1][0].twist(1);
            cube.cubie[1][0][0].twist(1);
        }
        else if(a == 2)
        {
            Cubie temp = cube.cubie[2][0][0];
            cube.cubie[2][0][0] = cube.cubie[0][0][0];
            cube.cubie[0][0][0] = cube.cubie[0][2][0];
            cube.cubie[0][2][0] = cube.cubie[2][2][0];
            cube.cubie[2][2][0] = temp;

            cube.cubie[2][0][0].twist(-1);
            cube.cubie[0][0][0].twist(1);
            cube.cubie[0][2][0].twist(-1);
            cube.cubie[2][2][0].twist(1);

            temp = cube.cubie[2][1][0];
            cube.cubie[2][1][0] = cube.cubie[1][0][0];
            cube.cubie[1][0][0] = cube.cubie[0][1][0];
            cube.cubie[0][1][0] = cube.cubie[1][2][0];
            cube.cubie[1][2][0] = temp;

            cube.cubie[2][1][0].twist(1);
            cube.cubie[1][0][0].twist(1);
            cube.cubie[0][1][0].twist(1);
            cube.cubie[1][2][0].twist(1);

            temp = cube.cubie[2][0][0];
            cube.cubie[2][0][0] = cube.cubie[0][0][0];
            cube.cubie[0][0][0] = cube.cubie[0][2][0];
            cube.cubie[0][2][0] = cube.cubie[2][2][0];
            cube.cubie[2][2][0] = temp;

            cube.cubie[2][0][0].twist(-1);
            cube.cubie[0][0][0].twist(1);
            cube.cubie[0][2][0].twist(-1);
            cube.cubie[2][2][0].twist(1);

            temp = cube.cubie[2][1][0];
            cube.cubie[2][1][0] = cube.cubie[1][0][0];
            cube.cubie[1][0][0] = cube.cubie[0][1][0];
            cube.cubie[0][1][0] = cube.cubie[1][2][0];
            cube.cubie[1][2][0] = temp;

            cube.cubie[2][1][0].twist(1);
            cube.cubie[1][0][0].twist(1);
            cube.cubie[0][1][0].twist(1);
            cube.cubie[1][2][0].twist(1);
        }
        return cube;
    }

    public Cube R(int a)
    {
        Cube cube = this;
        if(a == 1)
        {
            Cubie temp = cube.cubie[0][2][2];
            cube.cubie[0][2][2] = cube.cubie[2][2][2];
            cube.cubie[2][2][2] = cube.cubie[2][2][0];
            cube.cubie[2][2][0] = cube.cubie[0][2][0];
            cube.cubie[0][2][0] = temp;

            cube.cubie[0][2][2].twist(-1);
            cube.cubie[2][2][2].twist(1);
            cube.cubie[2][2][0].twist(-1);
            cube.cubie[0][2][0].twist(1);

            temp = cube.cubie[0][2][1];
            cube.cubie[0][2][1] = cube.cubie[1][2][2];
            cube.cubie[1][2][2] = cube.cubie[2][2][1];
            cube.cubie[2][2][1] = cube.cubie[1][2][0];
            cube.cubie[1][2][0] = temp;
        }
        else if(a == -1)
        {
            Cubie temp = cube.cubie[0][2][2];
            cube.cubie[0][2][2] = cube.cubie[0][2][0];
            cube.cubie[0][2][0] = cube.cubie[2][2][0];
            cube.cubie[2][2][0] = cube.cubie[2][2][2];
            cube.cubie[2][2][2] = temp;

            cube.cubie[0][2][2].twist(-1);
            cube.cubie[0][2][0].twist(1);
            cube.cubie[2][2][0].twist(-1);
            cube.cubie[2][2][2].twist(1);

            temp = cube.cubie[0][2][1];
            cube.cubie[0][2][1] = cube.cubie[1][2][0];
            cube.cubie[1][2][0] = cube.cubie[2][2][1];
            cube.cubie[2][2][1] = cube.cubie[1][2][2];
            cube.cubie[1][2][2] = temp;
        }
        else if(a == 2)
        {
            Cubie temp = cube.cubie[0][2][2];
            cube.cubie[0][2][2] = cube.cubie[2][2][2];
            cube.cubie[2][2][2] = cube.cubie[2][2][0];
            cube.cubie[2][2][0] = cube.cubie[0][2][0];
            cube.cubie[0][2][0] = temp;

            cube.cubie[0][2][2].twist(-1);
            cube.cubie[2][2][2].twist(1);
            cube.cubie[2][2][0].twist(-1);
            cube.cubie[0][2][0].twist(1);

            temp = cube.cubie[0][2][1];
            cube.cubie[0][2][1] = cube.cubie[1][2][2];
            cube.cubie[1][2][2] = cube.cubie[2][2][1];
            cube.cubie[2][2][1] = cube.cubie[1][2][0];
            cube.cubie[1][2][0] = temp;

            temp = cube.cubie[0][2][2];
            cube.cubie[0][2][2] = cube.cubie[2][2][2];
            cube.cubie[2][2][2] = cube.cubie[2][2][0];
            cube.cubie[2][2][0] = cube.cubie[0][2][0];
            cube.cubie[0][2][0] = temp;

            cube.cubie[0][2][2].twist(-1);
            cube.cubie[2][2][2].twist(1);
            cube.cubie[2][2][0].twist(-1);
            cube.cubie[0][2][0].twist(1);

            temp = cube.cubie[0][2][1];
            cube.cubie[0][2][1] = cube.cubie[1][2][2];
            cube.cubie[1][2][2] = cube.cubie[2][2][1];
            cube.cubie[2][2][1] = cube.cubie[1][2][0];
            cube.cubie[1][2][0] = temp;
        }
        return cube;
    }

    public Cube L(int a)
    {
        Cube cube = this;
        if(a == 1)
        {
            Cubie temp = cube.cubie[2][0][2];
            cube.cubie[2][0][2] = cube.cubie[0][0][2];
            cube.cubie[0][0][2] = cube.cubie[0][0][0];
            cube.cubie[0][0][0] = cube.cubie[2][0][0];
            cube.cubie[2][0][0] = temp;

            cube.cubie[2][0][2].twist(-1);
            cube.cubie[0][0][2].twist(1);
            cube.cubie[0][0][0].twist(-1);
            cube.cubie[2][0][0].twist(1);

            temp = cube.cubie[2][0][1];
            cube.cubie[2][0][1] = cube.cubie[1][0][2];
            cube.cubie[1][0][2] = cube.cubie[0][0][1];
            cube.cubie[0][0][1] = cube.cubie[1][0][0];
            cube.cubie[1][0][0] = temp;
        }
        else if(a == -1)
        {
            Cubie temp = cube.cubie[2][0][2];
            cube.cubie[2][0][2] = cube.cubie[2][0][0];
            cube.cubie[2][0][0] = cube.cubie[0][0][0];
            cube.cubie[0][0][0] = cube.cubie[0][0][2];
            cube.cubie[0][0][2] = temp;

            cube.cubie[2][0][2].twist(-1);
            cube.cubie[2][0][0].twist(1);
            cube.cubie[0][0][0].twist(-1);
            cube.cubie[0][0][2].twist(1);

            temp = cube.cubie[2][0][1];
            cube.cubie[2][0][1] = cube.cubie[1][0][0];
            cube.cubie[1][0][0] = cube.cubie[0][0][1];
            cube.cubie[0][0][1] = cube.cubie[1][0][2];
            cube.cubie[1][0][2] = temp;
        }
        else if(a == 2)
        {
            Cubie temp = cube.cubie[2][0][2];
            cube.cubie[2][0][2] = cube.cubie[0][0][2];
            cube.cubie[0][0][2] = cube.cubie[0][0][0];
            cube.cubie[0][0][0] = cube.cubie[2][0][0];
            cube.cubie[2][0][0] = temp;

            cube.cubie[2][0][2].twist(-1);
            cube.cubie[0][0][2].twist(1);
            cube.cubie[0][0][0].twist(-1);
            cube.cubie[2][0][0].twist(1);

            temp = cube.cubie[2][0][1];
            cube.cubie[2][0][1] = cube.cubie[1][0][2];
            cube.cubie[1][0][2] = cube.cubie[0][0][1];
            cube.cubie[0][0][1] = cube.cubie[1][0][0];
            cube.cubie[1][0][0] = temp;

            temp = cube.cubie[2][0][2];
            cube.cubie[2][0][2] = cube.cubie[0][0][2];
            cube.cubie[0][0][2] = cube.cubie[0][0][0];
            cube.cubie[0][0][0] = cube.cubie[2][0][0];
            cube.cubie[2][0][0] = temp;

            cube.cubie[2][0][2].twist(-1);
            cube.cubie[0][0][2].twist(1);
            cube.cubie[0][0][0].twist(-1);
            cube.cubie[2][0][0].twist(1);

            temp = cube.cubie[2][0][1];
            cube.cubie[2][0][1] = cube.cubie[1][0][2];
            cube.cubie[1][0][2] = cube.cubie[0][0][1];
            cube.cubie[0][0][1] = cube.cubie[1][0][0];
            cube.cubie[1][0][0] = temp;
        }
        return cube;
    }

    public Cube u(int a)
    {
        Cube cube = this;
        return cube.U(a).E(-a);
    }

    public Cube d(int a)
    {
        Cube cube = this;
        return cube.D(a).E(a);
    }

    public Cube f(int a)
    {
        Cube cube = this;
        return cube.F(a).S(a);
    }

    public Cube b(int a)
    {
        Cube cube = this;
        return cube.B(a).S(-a);
    }

    public Cube r(int a)
    {
        Cube cube = this;
        return cube.R(a).M(-a);
    }

    public Cube l(int a)
    {
        Cube cube = this;
        return cube.L(a).M(a);
    }

    public Cube M(int a)
    {
        Cube cube = this;
        if(a == 1)
        {
            Cubie temp = cubie[0][1][1];
            cube.cubie[0][1][1] = cubie[1][1][0];
            cubie[1][1][0] = cubie[2][1][1];
            cubie[2][1][1] = cubie[1][1][2];
            cubie[1][1][2] = temp;

            temp = cubie[0][1][0];
            cubie[0][1][0] = cubie[2][1][0];
            cubie[2][1][0] = cubie[2][1][2];
            cubie[2][1][2] = cubie[0][1][2];
            cubie[0][1][2] = temp;

            cubie[0][1][0].twist(1);
            cubie[2][1][0].twist(1);
            cubie[2][1][2].twist(1);
            cubie[0][1][2].twist(1);

        }
        else if(a == -1)
        {
            Cubie temp = cubie[0][1][1];
            cube.cubie[0][1][1] = cubie[1][1][2];
            cubie[1][1][2] = cubie[2][1][1];
            cubie[2][1][1] = cubie[1][1][0];
            cubie[1][1][0] = temp;

            temp = cubie[0][1][0];
            cubie[0][1][0] = cubie[0][1][2];
            cubie[0][1][2] = cubie[2][1][2];
            cubie[2][1][2] = cubie[2][1][0];
            cubie[2][1][0] = temp;

            cubie[0][1][0].twist(1);
            cubie[2][1][0].twist(1);
            cubie[2][1][2].twist(1);
            cubie[0][1][2].twist(1);
        }
        else if(a == 2)
        {
            Cubie temp = cubie[0][1][1];
            cube.cubie[0][1][1] = cubie[1][1][0];
            cubie[1][1][0] = cubie[2][1][1];
            cubie[2][1][1] = cubie[1][1][2];
            cubie[1][1][2] = temp;

            temp = cubie[0][1][0];
            cubie[0][1][0] = cubie[2][1][0];
            cubie[2][1][0] = cubie[2][1][2];
            cubie[2][1][2] = cubie[0][1][2];
            cubie[0][1][2] = temp;

            temp = cubie[0][1][1];
            cube.cubie[0][1][1] = cubie[1][1][0];
            cubie[1][1][0] = cubie[2][1][1];
            cubie[2][1][1] = cubie[1][1][2];
            cubie[1][1][2] = temp;

            temp = cubie[0][1][0];
            cubie[0][1][0] = cubie[2][1][0];
            cubie[2][1][0] = cubie[2][1][2];
            cubie[2][1][2] = cubie[0][1][2];
            cubie[0][1][2] = temp;
        }
        return cube;
    }

    public Cube E(int a)
    {
        Cube cube = this;
        if(a == 1)
        {
            Cubie temp = cubie[1][1][2];
            cube.cubie[1][1][2] = cubie[1][0][1];
            cubie[1][0][1] = cubie[1][1][0];
            cubie[1][1][0] = cubie[1][2][1];
            cubie[1][2][1] = temp;

            temp = cubie[1][0][2];
            cubie[1][0][2] = cubie[1][0][0];
            cubie[1][0][0] = cubie[1][2][0];
            cubie[1][2][0] = cubie[1][2][2];
            cubie[1][2][2] = temp;

            cubie[1][0][2].twist(1);
            cubie[1][0][0].twist(1);
            cubie[1][2][0].twist(1);
            cubie[1][2][2].twist(1);
        }
        else if(a == -1)
        {
            Cubie temp = cubie[1][1][2];
            cube.cubie[1][1][2] = cubie[1][2][1];
            cubie[1][2][1] = cubie[1][1][0];
            cubie[1][1][0] = cubie[1][0][1];
            cubie[1][0][1] = temp;

            temp = cubie[1][0][2];
            cubie[1][0][2] = cubie[1][2][2];
            cubie[1][2][2] = cubie[1][2][0];
            cubie[1][2][0] = cubie[1][0][0];
            cubie[1][0][0] = temp;

            cubie[1][0][2].twist(1);
            cubie[1][0][0].twist(1);
            cubie[1][2][0].twist(1);
            cubie[1][2][2].twist(1);
        }
        else if(a == 2)
        {
            Cubie temp = cubie[1][1][2];
            cube.cubie[1][1][2] = cubie[1][0][1];
            cubie[1][0][1] = cubie[1][1][0];
            cubie[1][1][0] = cubie[1][2][1];
            cubie[1][2][1] = temp;

            temp = cubie[1][0][2];
            cubie[1][0][2] = cubie[1][0][0];
            cubie[1][0][0] = cubie[1][2][0];
            cubie[1][2][0] = cubie[1][2][2];
            cubie[1][2][2] = temp;

            temp = cubie[1][1][2];
            cube.cubie[1][1][2] = cubie[1][0][1];
            cubie[1][0][1] = cubie[1][1][0];
            cubie[1][1][0] = cubie[1][2][1];
            cubie[1][2][1] = temp;

            temp = cubie[1][0][2];
            cubie[1][0][2] = cubie[1][0][0];
            cubie[1][0][0] = cubie[1][2][0];
            cubie[1][2][0] = cubie[1][2][2];
            cubie[1][2][2] = temp;
        }
        return cube;
    }

    public Cube S(int a)
    {
        Cube cube = this;
        if(a == 1)
        {
            Cubie temp = cubie[0][1][1];
            cube.cubie[0][1][1] = cubie[1][0][1];
            cubie[1][0][1] = cubie[2][1][1];
            cubie[2][1][1] = cubie[1][2][1];
            cubie[1][2][1] = temp;

            temp = cubie[0][0][1];
            cubie[0][0][1] = cubie[2][0][1];
            cubie[2][0][1] = cubie[2][2][1];
            cubie[2][2][1] = cubie[0][2][1];
            cubie[0][2][1] = temp;

            cubie[0][0][1].twist(1);
            cubie[2][0][1].twist(1);
            cubie[2][2][1].twist(1);
            cubie[0][2][1].twist(1);
        }
        else if(a == -1)
        {
            Cubie temp = cubie[0][1][1];
            cube.cubie[0][1][1] = cubie[1][2][1];
            cubie[1][2][1] = cubie[2][1][1];
            cubie[2][1][1] = cubie[1][0][1];
            cubie[1][0][1] = temp;

            temp = cubie[0][0][1];
            cubie[0][0][1] = cubie[0][2][1];
            cubie[0][2][1] = cubie[2][2][1];
            cubie[2][2][1] = cubie[2][0][1];
            cubie[2][0][1] = temp;

            cubie[0][0][1].twist(1);
            cubie[2][0][1].twist(1);
            cubie[2][2][1].twist(1);
            cubie[0][2][1].twist(1);
        }
        else if(a == 2)
        {
            Cubie temp = cubie[0][1][1];
            cube.cubie[0][1][1] = cubie[1][0][1];
            cubie[1][0][1] = cubie[2][1][1];
            cubie[2][1][1] = cubie[1][2][1];
            cubie[1][2][1] = temp;

            temp = cubie[0][0][1];
            cubie[0][0][1] = cubie[2][0][1];
            cubie[2][0][1] = cubie[2][2][1];
            cubie[2][2][1] = cubie[0][2][1];
            cubie[0][2][1] = temp;

            temp = cubie[0][1][1];
            cube.cubie[0][1][1] = cubie[1][0][1];
            cubie[1][0][1] = cubie[2][1][1];
            cubie[2][1][1] = cubie[1][2][1];
            cubie[1][2][1] = temp;

            temp = cubie[0][0][1];
            cubie[0][0][1] = cubie[2][0][1];
            cubie[2][0][1] = cubie[2][2][1];
            cubie[2][2][1] = cubie[0][2][1];
            cubie[0][2][1] = temp;
        }
        return cube;
    }

    public Cube X(int a)
    {
        Cube cube = this;
        return cube.R(a).M(-a).L(-a);
    }

    public Cube Y(int a)
    {
        Cube cube = this;
        return cube.U(a).E(-a).D(-a);
    }

    public Cube Z(int a)
    {
        Cube cube = this;
        return cube.F(a).S(a).B(-a);
    }

}
