package com.alejandrocorrero.pruebarxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Button btnPrueba;
    private TextView txtPrueba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPrueba = findViewById(R.id.btnPrueba);
        txtPrueba = findViewById(R.id.txtPrueba);
        btnPrueba.setOnClickListener(this::pruebas2);
    }
//subscripcion a un collable  y ejecucion de metodo
    private void pruebas2(View s) {
        observable2
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::pruebas2Test);


        txtPrueba.setText("Prueba");
    }

    private void pruebas2Test(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    private void pruebas(Integer v) {
        Toast.makeText(MainActivity.this, v.toString(), Toast.LENGTH_SHORT).show();
        txtPrueba.setText("Prueba");
    }
    //Creacion del observable de entero y pasa por datos
    Observable<Integer> observable = Observable.create(e -> {
        e.onNext(1);
        e.onNext(2);
        e.onNext(3);
        e.onNext(4);
        e.onComplete();
    });
    //Creacion del observable de string y pasa por datos
    Observable<String> observable2 = Observable.create(emitter -> {
        Thread.sleep(4000);
        emitter.onNext("Prueba");

        emitter.onNext("Prueba2");
        emitter.onNext("Prueba3");
        emitter.onComplete();
    });
    //Creacion del observable de collable y pasa por datos
    Observable<String> observavble3 = Observable.fromCallable(() -> "Test");
}
