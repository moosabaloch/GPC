package com.gpc.gpcsociety;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout clientInfo, eventInfo, eventTask, eventTaskCosting, teamInfo, paymentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        clientInfo = (LinearLayout) findViewById(R.id.client_info_button);
        eventInfo = (LinearLayout) findViewById(R.id.event_information_button);
        eventTask = (LinearLayout) findViewById(R.id.event_task_button);
        eventTaskCosting = (LinearLayout) findViewById(R.id.event_task_costing_button);
        teamInfo = (LinearLayout) findViewById(R.id.team_information_button);
        paymentInfo = (LinearLayout) findViewById(R.id.payment_information_button);

        clickEvent();


    }

    private void clickEvent() {
        teamInfo.setOnClickListener(this);
        eventTaskCosting.setOnClickListener(this);
        eventTask.setOnClickListener(this);
        eventInfo.setOnClickListener(this);
        clientInfo.setOnClickListener(this);
        paymentInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.client_info_button:
                Intent intent2 = new Intent(this,ClientInfoActivity.class);
                startActivity(intent2);
                break;
            case R.id.event_information_button:
                Intent intent = new Intent(this, EventInformation.class);
                startActivity(intent);

                break;
            case R.id.event_task_button:
                showMessage("Event Task Work In Progress");
                break;
            case R.id.event_task_costing_button:
                showMessage("Event Task Costing Work In Progress");
                break;
            case R.id.team_information_button:
                showMessage("Team Information Work In Progress");
                break;
            case R.id.payment_information_button:
                showMessage("Payment Information Work In Progress");
                break;
            default:
                break;
        }

    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
