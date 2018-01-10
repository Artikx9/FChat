package com.example.artik.fastchat;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.session.QBSession;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class SignUpActivity extends AppCompatActivity {

    Button btnSignUp, btnCancel;
    EditText edtUser, edtPassword, edtFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        registerSession();

        btnSignUp = findViewById(R.id.singup_btnSingUp);
        btnCancel = findViewById(R.id.singup_btnCancel);

        edtPassword = findViewById(R.id.singup_editPassword);
        edtUser = findViewById(R.id.singup_editLogin);
        edtFullName = findViewById(R.id.singup_editFullName);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user = edtUser.getText().toString();
                final String password = edtPassword.getText().toString();
                if(edtFullName.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"Заполните все поля", Toast.LENGTH_SHORT);
                    Log.d("ERROR", "Null edit");
                }
                else {
                    QBUser qbUser = new QBUser(user, password);
                    qbUser.setFullName(edtFullName.getText().toString());
                    QBUsers.signUp(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                        @Override
                        public void onSuccess(QBUser qbUser, Bundle bundle) {
                            Toast.makeText(getBaseContext(), "Регистрация успешна", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, ChatDialogsActivity.class);
                            intent.putExtra("user", user);
                            intent.putExtra("password", password);
                            startActivity(intent);
                        }

                        @Override
                        public void onError(QBResponseException e) {
                            Toast.makeText(getBaseContext(), "Ошибка регистрации " + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
    }

    private void registerSession() {
        QBAuth.createSession().performAsync(new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession qbSession, Bundle bundle) {

            }

            @Override
            public void onError(QBResponseException e) {

            }
        });
    }
}
