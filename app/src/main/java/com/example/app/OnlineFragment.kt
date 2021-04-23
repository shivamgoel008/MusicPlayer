package com.example.app

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_online.*


class OnlineFragment : Fragment() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var m:MediaPlayer
    private lateinit var x:MediaPlayer
    lateinit var runnable: Runnable
    private var handler=Handler()

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
        m=MediaPlayer.create(requireActivity(), R.raw.attention_charlie_puth)

//        charlie.text="Charlie Puth ${m.duration/1000}"


        mediaPlayer=MediaPlayer.create(requireActivity(), R.raw.one_direction)
        mediaPlayer.setOnPreparedListener{
            println("READY TO GO")
        }

//        control.setOnTouchListener{_,event ->
//            handleTouch(event)
//            true
//        }

        seekbar.progress=0;
        seekbar.max=mediaPlayer.duration

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

        play_attention.setOnClickListener {
            mediaPlayer.stop()
            musicTitle.text="ATTENTION"
            musicArtistName.text="Singer Name - Charlie Puth"
            mediaPlayer=MediaPlayer.create(requireActivity(), R.raw.attention_charlie_puth)
//            mediaPlayer2.setOnPreparedListener{
//                println("READY TO GO")
//            }
            imageView.setBackgroundResource(R.drawable.attension)
            interactivePlayerView.setBackgroundResource(R.drawable.attension)
            if(mediaPlayer.isPlaying){
                play_attention.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_motion_photos_paused_24))
                mediaPlayer.pause()
            }
            else{

                play_attention.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_play))
                mediaPlayer.start()
            }
        }

        play_closer.setOnClickListener {
            mediaPlayer.stop()
            musicTitle.text="CLOSER"
            musicArtistName.text="Singer Name - Chainsmokers"
            mediaPlayer=MediaPlayer.create(requireActivity(), R.raw.closer)

            imageView.setBackgroundResource(R.drawable.chainsmokers)
            interactivePlayerView.setBackgroundResource(R.drawable.wp4606687)

            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
            }

            else
                mediaPlayer.start()
        }

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if (changed) {
                    mediaPlayer.seekTo(pos)
                }

//                var minutes:Int=pos/600
//                var seconds:Int=pos%600
//
//                var secondFinal:String=""
//                if(seconds<=9){
//                    secondFinal="0"+seconds
//                }else{
//                    secondFinal=""+seconds
//                }
//
//                textView.setText(""+minutes+":"+secondFinal)


                val audioTime: String
                val dur:Int = pos
                val hrs = dur / 3600000
                val mns = dur / 60000 % 60000
                val scs = dur % 60000 / 1000

                audioTime = if (hrs > 0) {
                    String.format("%02d:%02d:%02d", hrs, mns, scs)
                } else {
                    String.format("%02d:%02d", mns, scs)
                }

                textView.text=audioTime

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })



        runnable= Runnable {
            seekbar.progress=mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }

        handler.postDelayed(runnable, 1000)
        mediaPlayer.setOnCompletionListener {
            seekbar.progress=0
        }



    }




}