package com.example.android.pi2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android.adapters.ScheduleAdapter;
import com.example.android.models.Alarm;
import com.example.android.models.BasicTime;
import com.example.android.models.Medication;
import com.example.android.models.Schedule;
import com.example.android.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MedSchedule extends AppCompatActivity {

    FirebaseDatabase database;
    FirebaseAuth mAuth;

    String uid;

    List<User> depList;

    RecyclerView RecViewSched;

    ScheduleAdapter adapter;

    List<Schedule> listSched;
    Button novaRotina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_schedule);

        listSched = new ArrayList<>();

        RecViewSched = (RecyclerView) findViewById(R.id.RecViewRotinas);
        RecViewSched.setHasFixedSize(true);
        RecViewSched.setLayoutManager(new LinearLayoutManager(this));



        /*DADOS DE TESTE*/
        /*

        User user1 = new User("Rodrigo", "Wehbe", "Masculino", "7:00", "22:00");
        User user2 = new User("Rodrigo", "Wehbe", "Masculino", "7:00", "22:00");

        Medication med1 = new Medication("CANCIDAS", "ACETATO DE CASPOFUNGINA", "ERCK SHARP & DOHME FARMACEUTICA  LTDA", "70 MG PO LIOF SOL INJ CT FA VD INC", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 4247.26);
        Medication med2 = new Medication("NOLUPRON DEPOTME", "ACETATO DE LEUPRORRELINA", "ABBVIE FARMACÊUTICA LTDA", "5,0 MG/ML SOL INJ PT PLA…SER + 15 SACHETS ALCOOL", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 725.23);

        List<String> hrs = new ArrayList<>();
        hrs.add("07:00");
        hrs.add("17:00");
        hrs.add("22:00");

        */

        //listSched.add(new Schedule(med1,hrs, 4));
       // listSched.add(new Schedule(med2,hrs , 4));

        /*DADOS DE TESTE*/





        novaRotina = findViewById(R.id.btnNovaRotina);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        uid = mAuth.getUid();

        DatabaseReference depenentes = database.getReference("/Users/"+uid+"/Dependentes");

        depenentes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    User user = ds.getValue(User.class);

                    for(DataSnapshot schedSnapshot : ds.child("Rotinas").getChildren()){

                        String sr = schedSnapshot.getKey();

                        String freq = (String) schedSnapshot.child("Frequencia").getValue();

                        List<BasicTime> listBT = new ArrayList<>();

                        for(DataSnapshot hrSnapshot : schedSnapshot.child("Horários").getChildren()){
                            String t1 = (String) hrSnapshot.child("formatedTime").getValue();
                            BasicTime bt1 = BasicTime.stringToBasicTime(t1);
                            listBT.add(bt1);


                        }

                        List hrList = (List) schedSnapshot.child("Horários").getValue();
                       // HashMap<String, String> hm = (HashMap<String, String>) hrList.get(0);
                        //String formatedTime = hm.get("formatedTime");

                       String medname = (String) schedSnapshot.child("Medication").child("nome").getValue();
                       String meddesc = (String) schedSnapshot.child("Medication").child("desc").getValue();
                       String medlab = (String) schedSnapshot.child("Medication").child("lab").getValue();
                       String medativo = (String) schedSnapshot.child("Medication").child("pAtivo").getValue();
                       String medpreco = schedSnapshot.child("Medication").child("preco").getValue().toString();
                       String medrestrict = (String) schedSnapshot.child("Medication").child("resctric").getValue();
                       String medClass = (String) schedSnapshot.child("Medication").child("tClass").getValue();

                       Medication med = new Medication(medname, medativo, medlab, meddesc, medClass, medrestrict, Double.parseDouble(medpreco));

                       Schedule rotina = new Schedule(med, listBT, user, freq);
                       rotina.setSchedId(sr);

                       listSched.add(rotina);

                       int x  = 0;
                       //listSched.add(new Schedule(med,hrList, user, String.valueOf(freq)));
                    }
                }
                adapter = new ScheduleAdapter(MedSchedule.this, listSched);
                RecViewSched.setAdapter(adapter);


                for(Schedule sc : listSched){
                    for(BasicTime bt : sc.getHorarios()){
                        setAlarm(getIngestionTimes(bt.getHour(), bt.getMinute()), sc);
                    }
                }

                /*TESTES*/
                User user_teste = new User("Rodrigo", "Wehbe", "Masculino", "7:00", "22:00");

                Medication med1 = new Medication("CANCIDA", "ACETATO DE CASPOFUNGINA", "ERCK SHARP & DOHME FARMACEUTICA  LTDA", "70 MG PO LIOF SOL INJ CT FA VD INC", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 4247.26);

                List<BasicTime> hrs = new ArrayList<>();
                hrs.add(new BasicTime(7, 50));

                Schedule sce = new Schedule(med1, hrs, user_teste, "4");

                Calendar test = Calendar.getInstance();

                test.add(Calendar.SECOND, 2);






                setAlarm(test, sce);


                /*TESTES*/

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        adapter = new ScheduleAdapter(this, listSched);
        RecViewSched.setAdapter(adapter);


        novaRotina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedSchedule.this, ChooseUser.class));
            }
        });




    }

    public Calendar getIngestionTimes(int hour, int minute){
        Calendar calendar = Calendar.getInstance();

        calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                hour,
                minute,
                0);

        return calendar;
    }

    private void setAlarm(Calendar cal, Schedule s){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ingestDate = sdf.format(cal.getTime());

        Intent it = new Intent(this, Alarm.class);
        it.putExtra("username", s.getUser().getNome());
        it.putExtra("userid", s.getUser().getUserId());
        it.putExtra("medname", s.getMed().getNome());
        it.putExtra("schedId", s.getSchedId());
        it.putExtra("ingestTime", cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE));
        it.putExtra("ingestDate", ingestDate);

        int tste = Integer.parseInt(String.valueOf(cal.get(Calendar.MONTH)+1));

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, it, 0);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), alarmManager.INTERVAL_DAY, pendingIntent);

        //Toast.makeText(this, cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE), Toast.LENGTH_LONG).show();

        Log.d("QuickNotesMainActivity", cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE));
    }




}
