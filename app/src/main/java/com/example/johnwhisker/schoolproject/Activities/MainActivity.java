package com.example.johnwhisker.schoolproject.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import com.example.johnwhisker.schoolproject.Config;
import com.example.johnwhisker.schoolproject.Question;
import com.example.johnwhisker.schoolproject.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    Firebase dtb;
    List<Question> questionList;
    @Bind(R.id.cardViewChoice1)
    CardView cv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Firebase.setAndroidContext(this);
        dtb = new Firebase(Config.FIREBASE_URL);
        questionList = new ArrayList<>();
//        while(questionList.size()==0) {
//            final Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    // Do something after 5s = 5000ms
//                    cv1.performClick();
//
//                }
//            }, 1000);
//
//        }



    }
    public void Test(){
        String input  = "Câu 86: Lệnh nào sau đây hợp lệ?\n" +
                "\ta/ char S[20] = “Hoa Sen”;\n" +
                "\tb/ char *S = “Hoa Sen”;\n" +
                "\tc/ Cả a và b đều hợp lệ\n" +
                "\td/ Cả a và b đều không hợp lệ\n" +
                "Câu 87: Kết quả của chương trình sau là gì?\n" +
                "void main()\n" +
                "{\n" +
                "\tchar S[20];\n" +
                "\tS= \"Hoa Sen\";\n" +
                "\tprintf(S);\n" +
                "}\n" +
                "\n" +
                "\ta/ Hoa Sen\n" +
                "\tb/ S\n" +
                "\tc/ HoaSen \n" +
                "\td/ Phép gán S = “Hoa Sen” không chấp nhận \uF0E0 lỗi chương trình\n" +
                "Câu 88: Kết quả của chương trình sau là gì?\n" +
                "void main()\n" +
                "{\n" +
                "\tchar S[4] = “ABC”;\n" +
                "\tint i=0;\n" +
                "\twhile (S[i]!=NULL)\n" +
                "\t\tprintf(“%d”,S[i++]);\n" +
                "}\n" +
                "\n" +
                "\ta/ ABC\n" +
                "\tb/ ABCABCABC\n" +
                "\tc/ 656667\n" +
                "\td/ Một kết quả khác\n" +
                "Câu 89: Hãy điền vào khoảng trống ở đoạn mã sau:\n" +
                "void main()\n" +
                "{\n" +
                "\tchar S[30] = “Nam 2014 la 2 ty dong”;\n" +
                "\t//Đếm số ký tự là số trong chuỗi S\n" +
                "\tint dem=0, i=0;\n" +
                "while (S[i]!=NULL)\n" +
                "\t\tif (…………….) dem++;\n" +
                "\tprintf(“%d”,dem);\t\n" +
                "}\n" +
                "\n" +
                "\ta/ isdigit(S[++i])\n" +
                "\tb/ isdigit(S[i])\n" +
                "\ta/ isalpha([i])\n" +
                "\tb/ isdigit(S[i++])\n" +
                "Câu 90: Kết quả của chương trình sau là gì?\n" +
                "void main()\n" +
                "{\n" +
                "\tchar S[4] = “ABC”;\n" +
                "\tint i=0;\n" +
                "\twhile (S[i]!=NULL)\n" +
                "\t\tprintf(“%c”,S[i++]);\n" +
                "}\n" +
                "\n" +
                "\ta/ ABC\n" +
                "\tb/ ABCABCABC\n" +
                "\tc/ 656667\n" +
                "\td/ Một kết quả khác\n";

        int id = 86;
       for (String[] questionPack :SplitAgorithym(input)){
            Question temp = new Question();
           temp.setQuestion(questionPack[0]);
           temp.setID(id);
           temp.setA(questionPack[1]);
           temp.setB(questionPack[2]);
           if(questionPack.length>3) {
               temp.setC(questionPack[3]);
               temp.setD(questionPack[4]);
           }
           dtb.child("Question "+id).setValue(temp);
           id++;
       }
    }
    public List<String[]> SplitAgorithym(String input){
        List<String[]> retu = new ArrayList<>();
        String[] stage0 = input.split("Câu");
        for(int i =1;i<stage0.length;i++) {
            String[] stage1 = stage0[i].split(":");
            if (stage1.length > 2) {
                stage1[1] = stage1[1] + stage1[2];
            }
            String[] stage2 = stage1[1].split("a/");
            String[] stage3 = stage2[1].split("b/");

            String[] stage4 = stage3[1].split("c/");
            String[] stage5;
            if(stage4.length>1){
            stage5 = stage4[1].split("d/");
                String[] ret = new String[5];
                ret[0] = stage2[0].trim();
                ret[1] = stage3[0].trim();
                ret[2] = stage4[0].trim();
                ret[3] = stage5[0].trim();
                ret[4] = stage5[1].trim();
                retu.add(ret);
            }
            else{
                String[] ret = new String[5];
                ret[0] = stage2[0].trim();
                ret[1] = stage3[0].trim();
                ret[2] = stage4[0].trim();
                retu.add(ret);
            }
        }
        return retu;
    }
    public void getData(){

        dtb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                questionList.add(dataSnapshot.getValue(Question.class));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public void cv2(){
        while(questionList.size()==0){

            getData();
        }

        Set<Question> ls = new HashSet<>();
        ls.addAll(questionList);
        questionList.clear();
        questionList.addAll(ls);
        ls.clear();

        Log.d("Number",questionList.size()+"");
    }
    public void cvChoice1OnSelect(View v) {
        getData();
        Set<Question> ls = new HashSet<>();
        ls.addAll(questionList);
        questionList.clear();
        questionList.addAll(ls);
        ls.clear();

        Log.d("Number",questionList.size()+"");
    }

    public void cvChoice2OnSelect(View v) {
        int id = 37;
        Question temp = new Question();
        temp.setQuestion("Hãy cho biết kết quả in ra màn hình khi thực hiện các lệnh sau: \n" +
                "\tint a = 4, b = 7;\n" +
                "\tint max = (a<b)? a:b;\n" +
                "\tprintf(“%d”,max); \n");
        temp.setA("7");
        temp.setB("4");
        temp.setC("11");
        temp.setD("3");
        temp.setID(id);

        dtb.child("Question "+id).setValue(temp);
    }
}
