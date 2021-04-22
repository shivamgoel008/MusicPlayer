package com.example.app

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_online.*


class OnlineFragment : Fragment() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        control.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_play))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_online, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mediaPlayer=MediaPlayer.create(requireActivity(),R.raw.one_direction)
        mediaPlayer.setOnPreparedListener{
            println("READY TO GO")
        }

//        control.setOnTouchListener{_,event ->
//            handleTouch(event)
//            true
//        }

        control.setOnClickListener {

            if(mediaPlayer.isPlaying){
                mediaPlayer.pause();
                control.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_motion_photos_paused_24))
            } else {
                mediaPlayer.start();
                control.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_play))
            }
//            mediaPlayer.start()
//            imageView.setImageDrawable(null)
//            control.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_motion_photos_paused_24))
        }

    }

    fun handleTouch(event: MotionEvent?) {
        when(event!!.action){
            MotionEvent.ACTION_DOWN->{
                println("down")
                mediaPlayer.start()
            }

            MotionEvent.ACTION_CANCEL,MotionEvent.ACTION_UP->{
                println("up or cancel")
                mediaPlayer.pause()
                mediaPlayer.seekTo(0)
            }

            else ->{
                println("other")
            }
        }
    }
}