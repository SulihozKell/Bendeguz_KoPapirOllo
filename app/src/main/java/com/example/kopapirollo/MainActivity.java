package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView kepSajat, kepGep, btnKo, btnPapir, btnOllo, gepHp1, gepHp2, gepHp3,
            jatekosHp1, jatekosHp2, jatekosHp3;
    private ImageView[] gepEletek, jatekosEletek;
    private TextView eredmenyDontetlen;
    private Random random;
    private int gepValasztasSzam, jatekosSzam, jatekosNyeresek, gepNyeresek, dontetlenekSzama;
    private AlertDialog.Builder alertDialogVege;
    private String szoveg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnKo.setOnClickListener(view -> {
            kepSajat.setImageResource(R.drawable.rock);
            jatekosSzam = 0;
            jatek();
        });

        btnPapir.setOnClickListener(view -> {
            kepSajat.setImageResource(R.drawable.paper);
            jatekosSzam = 1;
            jatek();
        });

        btnOllo.setOnClickListener(view -> {
            kepSajat.setImageResource(R.drawable.scissors);
            jatekosSzam = 2;
            jatek();
        });
    }

    private void jatek() {
        int gepSzam = gepValasztas();
        if (jatekosSzam == gepValasztasSzam) {
            // Döntetlen
            dontetlenekSzama++;
            eredmenyDontetlen.setText("Döntetlenek száma: " + dontetlenekSzama);
        }
        else if ((jatekosSzam == 0 && gepSzam == 2) || (jatekosSzam == 1 && gepSzam == 0) ||
                (jatekosSzam == 2 && gepSzam == 1)) {
            // Játékos Nyert
            jatekosNyeresek++;
            Toast.makeText(getApplicationContext(), "Játékos Nyert!", Toast.LENGTH_SHORT)
                    .show();
            gepEletek[jatekosNyeresek - 1].setImageResource(R.drawable.heart1);
        }
        else if ((jatekosSzam == 0 && gepSzam == 1) || (jatekosSzam == 1 && gepSzam == 2) ||
                (jatekosSzam == 2 && gepSzam == 0)) {
            // Gép Nyert
            gepNyeresek++;
            Toast.makeText(getApplicationContext(), "Gép Nyert!", Toast.LENGTH_SHORT)
                    .show();
            jatekosEletek[gepNyeresek - 1].setImageResource(R.drawable.heart1);
        }

        if (jatekosNyeresek >= 3 || gepNyeresek >= 3) {
            vege();
        }
    }

    private int gepValasztas() {
        gepValasztasSzam = random.nextInt(3);
        if (gepValasztasSzam == 0) {
            kepGep.setImageResource(R.drawable.rock);
        }
        else if (gepValasztasSzam == 1) {
            kepGep.setImageResource(R.drawable.paper);
        }
        else {
            kepGep.setImageResource(R.drawable.scissors);
        }
        return gepValasztasSzam;
    }

    private void vege() {
        szoveg = "";
        if (jatekosNyeresek > gepNyeresek) {
            szoveg = "Győzelem";
        }
        else {
            szoveg = "Vereség";
        }
        alertDialogVege = new AlertDialog.Builder(MainActivity.this);

        alertDialogVege.setCancelable(false).setTitle(szoveg)
                .setMessage("Szeretne új játékot játszani?")
                .setPositiveButton("Igen", (dialog, which) -> ujJatek())
                .setNegativeButton("Nem", (dialog, which) -> finish());

        AlertDialog dialog = alertDialogVege.create();
        dialog.show();
    }

    private void ujJatek() {
        jatekosNyeresek = 0;
        gepNyeresek = 0;
        dontetlenekSzama = 0;
        eredmenyDontetlen.setText("Döntetlenek száma: " + dontetlenekSzama);
        kepSajat.setImageResource(R.drawable.rock);
        kepGep.setImageResource(R.drawable.rock);
        for (ImageView elet: jatekosEletek) {
            elet.setImageResource(R.drawable.heart2);
        }
        for (ImageView elet: gepEletek) {
            elet.setImageResource(R.drawable.heart2);
        }
    }

    private void init() {
        btnKo = findViewById(R.id.btn_ko);
        btnPapir = findViewById(R.id.btn_papir);
        btnOllo = findViewById(R.id.btn_ollo);
        kepSajat = findViewById(R.id.sajat_valasztas_kep);
        kepGep = findViewById(R.id.gep_valasztas_kep);
        eredmenyDontetlen = findViewById(R.id.eredmeny_dontetlen);
        random = new Random();
        jatekosNyeresek = 0;
        gepNyeresek = 0;
        dontetlenekSzama = 0;
        jatekosSzam = 0;
        gepHp1 = findViewById(R.id.gep_elet1);
        gepHp2 = findViewById(R.id.gep_elet2);
        gepHp3 = findViewById(R.id.gep_elet3);
        jatekosHp1 = findViewById(R.id.jatekos_elet1);
        jatekosHp2 = findViewById(R.id.jatekos_elet2);
        jatekosHp3 = findViewById(R.id.jatekos_elet3);
        gepEletek = new ImageView[]{gepHp1, gepHp2, gepHp3};
        jatekosEletek = new ImageView[]{jatekosHp1, jatekosHp2, jatekosHp3};
    }
}
