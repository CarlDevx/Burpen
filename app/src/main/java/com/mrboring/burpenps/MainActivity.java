package com.mrboring.burpenps;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //variavel que sera preenchida pela função do botao de copiar
    String toClipBoard = "";
    //definindo listeners
    private View.OnClickListener pwdButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            asyncWorker();
        }
    };

    private View.OnClickListener copyButton = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            // para acessar a area de transferencia, devemos primeiro cirar um objeto ClManager
            // esse objeto recebera como parametro o casting do servico da area de transferencia
            // esse servo encontra se dentro da classe context
            // para acessa-lo, precisamos acessar o contexto atual da aplicacao, dentro dele,
            // precisamos pegar o servico de clipboard
            ClipboardManager clipboardManager = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData cpd = ClipData.newPlainText("label", toClipBoard);
            clipboardManager.setPrimaryClip(cpd);
            Toast.makeText(getApplicationContext(),"Copiado com sucesso", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button pwdGen = (Button) findViewById(R.id.generate_password_btn);
        Button copyBtn = (Button) findViewById(R.id.copyBTN);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pwdGen.setOnClickListener(pwdButtonListener);
    }
    public void asyncWorker() {
        new Thread(() -> {
            Password _password = Utils.gen(12);
            this.toClipBoard = _password.getText();
            findViewById(R.id.copyBTN).setOnClickListener(copyButton);
            runOnUiThread(()->{
                Toast.makeText(
                        this,"Senha gerada com sucesso",Toast.LENGTH_SHORT
                ).show();
                findViewById(R.id.copyBTN).setVisibility(View.VISIBLE);
            });
        }).start();
    }


}