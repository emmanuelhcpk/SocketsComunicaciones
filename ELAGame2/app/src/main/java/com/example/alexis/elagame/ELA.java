package com.example.alexis.elagame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;


import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZMQException;

public class ELA extends ActionBarActivity {

    private EditText textField;
    private Button button;
    private String messsage;
    private String serverMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_el);

    textField = (EditText) findViewById(R.id.textSend); // reference to the text field
    button = (Button) findViewById(R.id.sendText); // reference to the send button


        // Button press listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messsage = textField.getText().toString(); // get the text message on the text field
				textField.setText(""); // Reset the text field to blank
                SendMessage sendMessageTask = new SendMessage();
				sendMessageTask.execute();
            }
        });

    }

    private class SendMessage extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
        try {
            ZMQ.Context cont = ZMQ.context(1);
            ZMQ.Socket socket = cont.socket(ZMQ.REQ); // Se crea el socker de tipo requeste , el servidor está seteado como reply
            socket.connect("tcp://192.168.1.6:5555"); // se conecta al server indicado en la ip [protocolo]://[dirección ip]:[puerto]
            socket.send(messsage);

            serverMessage = socket.recvStr();
            socket.close();
            cont.term();
        }catch (ZMQException e){
            e.printStackTrace();
        }
			return null;
		}

	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_el, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
