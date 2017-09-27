package com.oscar.cleta;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.text.Html;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link fragment_speed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_speed extends Fragment {

    TextView aceleration, speed;
    Random r = new Random();
    int i=0;
    int rpm;
    final Handler myHandler = new Handler();
    final Timer myTimer = new Timer();
    public fragment_speed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment fragment_speed.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_speed newInstance() {
        fragment_speed fragment = new fragment_speed();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myInflatedView = inflater.inflate(R.layout.fragment_fragment_speed, container, false);

        aceleration = (TextView) myInflatedView.findViewById(R.id.editAceler);
        speed = (TextView) myInflatedView.findViewById(R.id.editSpeed);
        String formulaString = "<body>km/h<sup>2</sub></body>";
        TextView formula = (TextView) myInflatedView.findViewById(R.id.math);
        formula.setText(Html.fromHtml(formulaString));
        // Timer
        TimerTask myTask = new TimerTask() {
            public void run() {
                updateUI(); // updateUI method
            }
        };
        myTimer.schedule(myTask,0,1000); // TimerTask, delay, period

        aceleration.setText("Nueva aceleracion");
        return myInflatedView;
    }

    //TIMER y Handler -> https://stackoverflow.com/questions/23071411/how-to-update-textview-text-in-loop

    // TODO: Rename method, update argument and hook method into UI event

    private float CalcularSpeed(int i){
        double radio=0.60;
        double velocidad = (2*3.14*i*radio/60)*3.6;
        return (float) velocidad;
    }

    // Runnable method
    final Runnable myRunnable = new Runnable() {
        public void run() {
            aceleration.setText(String.valueOf(i)); // update your text
            speed.setText(String.valueOf(CalcularSpeed(rpm)));
        }
    };

    // updateUI method related to a Runnable
    private void updateUI() {
        //if(i < 100) {
        i=r.nextInt(10 - 0) + 0;
        rpm = r.nextInt(180 - 0) + 0;
        // num.setText(String.valueOf(i)); = avoid the RunTime error
        myHandler.post(myRunnable); // relate this to a Runnable
        //} else {
        //   myTimer.cancel(); // stop the timer
        //  return;
        // }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}