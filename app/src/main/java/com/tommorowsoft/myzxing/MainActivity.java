package com.tommorowsoft.myzxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {
    TextView tvresult;
    EditText Input;
    ImageView image;
    CheckBox Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvresult= (TextView) findViewById(R.id.tvresult);
        Input= (EditText) findViewById(R.id.ed_text);
        image= (ImageView) findViewById(R.id.image);
        Login= (CheckBox) findViewById(R.id.Login);

    }
    public void scan(View view){
        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class),0);

    }
    public void make(View view){
        String input=Input.getText().toString();
        if (input.equals("")){
            Toast.makeText(MainActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
        }else {
            Bitmap bitmap= EncodingUtils.createQRCode(input,500,500,
                    Login.isChecked()? BitmapFactory.decodeResource(getResources(),R.drawable.login):null);
            image.setImageBitmap(bitmap);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Bundle bundle=data.getExtras();
            String result=bundle.getString("result");
            tvresult.setText(result);
        }
    }
}
