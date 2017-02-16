package com.example.administrator.webdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText editText1;
    public static final String TAG = "MainActivity";
    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");


    String url="http://www.liuyinxin.com/json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1=(EditText)findViewById(R.id.edittext);
    }


    public void Receive(View view) {

        ReceiveThread receive=new ReceiveThread();
        receive.start();


    }

    class ReceiveThread extends Thread{
        public void run(){
            //申明给服务端传递一个json串
            //创建一个OkHttpClient对象
            OkHttpClient okHttpClient = new OkHttpClient();
            //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
            String json="{\n" +
                    "\"username\": \"xiaoli\",\n" +
                    "\"password\": \"xiaoli12345\"\n" +
                    "}";

            RequestBody requestBody = RequestBody.create(JSON, json);
            //创建一个请求对象
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            //发送请求获取响应
            try {
                Response response=okHttpClient.newCall(request).execute();
                //判断请求是否成功
                if(response.isSuccessful()){
                    //打印服务端返回结果
                    Log.i(TAG,response.body().string());

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        }

            /*OkHttpClient client = new OkHttpClient();


                RequestBody formBody = new FormEncodingBuilder()
                        .add("username", "xiaoli")
                        .add("password", "xiaoli12345")
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .post(formBody)
                        .build();

                Response response = null;
                try {
                    response = client.newCall(request).execute();

                if (response.isSuccessful()) {

                    Log.i("test","chenggong");
                }
            }catch (IOException e1) {
                    e1.printStackTrace();
                }
                }*/
            }

            /*URL url= null;
            try {
                url = new URL(Path);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.connect();
                JSONObject login=new JSONObject();
                login.put("username", "xiaoli");
                login.put("passsword","xiaoli12345");

                String jsonstr = login.toString();//把JSON对象按JSON的编码格式转换为字符串
                OutputStream out = conn.getOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(out);//缓冲字节流包装字节流
                byte[] bytes = jsonstr.getBytes("UTF-8");//把字符串转化为字节数组
                bos.write(bytes);//把这个字节数组的数据写入缓冲区中
                bos.flush();//刷新缓冲区，发送数据
                Log.d("zxy", "fasong");*/



                /*//接收
                StringBuilder str=new StringBuilder(conn.getContentLength());
                DataInputStream dis=new DataInputStream(conn.getInputStream());
                DataOutputStream dos=new DataOutputStream(conn.getOutputStream());
                int data=0;
                while((data=dis.read())!=-1){
                    dos.write(data);
                    dos.flush();
                    str.append(data);
                }
                dis.close();
                dos.close();
                String string=str.toString();
                JSONObject person=new JSONObject(string);
                String username=person.getString("username");
                String password=person.getString("password");
                editText1.append(username+"\n"+password+"\n");*/


                /*InputStream in = conn.getInputStream();//用输入流接收服务端返回的回应数据
                BufferedInputStream bis = new BufferedInputStream(in);//高效缓冲流包装它，这里用的是字节流来读取数据的，当然也可以用字符流
                byte[] b = new byte[1024];
                int len = -1;
                StringBuffer buffer = new StringBuffer();//用来接收数据的StringBuffer对象
                while((len=bis.read(b))!=-1){
                buffer.append(new String(b, 0, len));//把读取到的字节数组转化为字符串
                }
                in.close();
                bis.close();
                Log.d("zxy", buffer.toString());//{"json":true}
                JSONObject rjson = new JSONObject(buffer.toString());
                String username=rjson.getString("username");
                String password=rjson.getString("password");
                editText1.append(username+"\n"+password+"\n");
*/

/*
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }*/




