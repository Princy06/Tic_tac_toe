package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button[][] buttons= new Button[3][3];
    private boolean player1turn=true;
    private int roundVount;
    private int player1Points;
    private int player2Points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewPlayer1=findViewById(R.id.text_view_p1);
        textViewPlayer2=findViewById(R.id.text_view_p2);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String buttonID="button_" + i + j;
                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j]= findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset=findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetBoard();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals(""))
        {
            return;
        }
        if(player1turn)
        {
            ((Button) v).setText("X");
        }
        else
        {
            ((Button) v).setText("0");
        }
        roundVount++;
        if(checkForwin())
        {
            if(player1turn)
            {
                player1Wins();
            }
            else
            {
                player2wins();
            }
        }
        else if(roundVount==9)
        {
            draw();
        }
        else
        {
            player1turn= !player1turn;
        }

    }
    private boolean checkForwin()
    {
        String[][] field= new String[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                field[i][j]=buttons[i][j].getText().toString();
            }
        }
        for(int i=0;i<3;i++)
        {
            if(field[i][0].equals(field[i][1])&&field[i][0].equals(field[i][2])
                    && !field[i][0].equals(""))
            {
                return true;
            }
        }
        for(int i=0;i<3;i++)
        {
            if(field[0][i].equals(field[1][i])&&field[0][i].equals(field[2][i])
                    && !field[0][i].equals(""))
            {
                return true;
            }
        }
        if(field[0][0].equals(field[1][1])&&field[0][0].equals(field[2][2])
                && !field[0][0].equals(""))
        {
            return true;
        }
        if(field[0][2].equals(field[1][1])&&field[0][2].equals(field[2][0])
                && !field[0][2].equals(""))
        {
            return true;
        }
        return false;
    }
    private void player1Wins()
    {
        player1Points++;
        Toast.makeText(this, "player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();

    }
    private void player2wins()
    {
        player2Points++;
        Toast.makeText(this, "player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void draw()
    {
        Toast.makeText(this,"draw!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void updatePointsText()
    {
        textViewPlayer1.setText("player 1:" + player1Points);
        textViewPlayer2.setText("player 2:" + player2Points);
    }
    private void resetBoard()
    {
        for(int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                buttons[i][j].setText(" ");
            }
        }
        roundVount=0;
        player1turn=true;
    }
}
