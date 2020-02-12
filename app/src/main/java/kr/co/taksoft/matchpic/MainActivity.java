package kr.co.taksoft.matchpic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageButton startbtn;
    ImageButton howtobtn;
    ImageButton btn01;
    ImageButton btn02;
    ImageButton btn03;
    ImageButton btn04;
    ImageButton btn05;
    ImageView board;

    Random rand = new Random();
    int[] animal = new int[5];
    int question;

    Toast t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbtn = findViewById(R.id.startbtn);
        howtobtn = findViewById(R.id.howtobtn);
        btn01 = findViewById(R.id.btn01);
        btn02 = findViewById(R.id.btn02);
        btn03 = findViewById(R.id.btn03);
        btn04 = findViewById(R.id.btn04);
        btn05 = findViewById(R.id.btn05);
        board = findViewById(R.id.board);

        startbtn.setBackgroundColor(Color.TRANSPARENT);
        howtobtn.setBackgroundColor(Color.TRANSPARENT);
        btn01.setBackgroundColor(Color.TRANSPARENT);
        btn02.setBackgroundColor(Color.TRANSPARENT);
        btn03.setBackgroundColor(Color.TRANSPARENT);
        btn04.setBackgroundColor(Color.TRANSPARENT);
        btn05.setBackgroundColor(Color.TRANSPARENT);

        startbtn.setOnClickListener(startListener);
        howtobtn.setOnClickListener(howtoListener);
        btn01.setOnClickListener(animalListener);
        btn02.setOnClickListener(animalListener);
        btn03.setOnClickListener(animalListener);
        btn04.setOnClickListener(animalListener);
        btn05.setOnClickListener(animalListener);

    }

    public void mixAnimal(){
        for(int i=0;i<animal.length;i++){
            int num = rand.nextInt(5);
            animal[i] = num;
            for(int k=0;k<i;k++){
                if(animal[k] == animal[i]){
                    i--;
                    break;
                }
            }
        }
    }

    public void setAnimal(){
        btn01.setImageResource(R.drawable.a_ele + animal[0]);
        btn02.setImageResource(R.drawable.a_ele + animal[1]);
        btn03.setImageResource(R.drawable.a_ele + animal[2]);
        btn04.setImageResource(R.drawable.a_ele + animal[3]);
        btn05.setImageResource(R.drawable.a_ele + animal[4]);

        btn01.setTag(animal[0]);
        btn02.setTag(animal[1]);
        btn03.setTag(animal[2]);
        btn04.setTag(animal[3]);
        btn05.setTag(animal[4]);
    }

    public void reset(){
        mixAnimal();
        setAnimal();
        question = rand.nextInt(5);
        board.setImageResource(R.drawable.b_ele + question);
    }

    View.OnClickListener startListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            reset();
        }
    };

    View.OnClickListener howtoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("게임설명");
            builder.setMessage("matchPic이란? \n하단에 있는 단어와 일치하는 동물을 클릭하면 되는 게임");
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };

    View.OnClickListener animalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            ImageButton btn = findViewById(id);
            String s = btn.getTag().toString();
            if(t != null){
                t.cancel();
                t = null;
            }
            if(Integer.parseInt(s) == question){
                t = Toast.makeText(MainActivity.this, "정답입니다", Toast.LENGTH_SHORT);
                t.show();
                reset();
            }else{
                t = Toast.makeText(MainActivity.this, "틀렸습니다", Toast.LENGTH_SHORT);
                t.show();
            }
        }
    };

}
