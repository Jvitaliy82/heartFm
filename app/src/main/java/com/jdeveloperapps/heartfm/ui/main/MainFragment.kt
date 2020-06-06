package com.jdeveloperapps.heartfm.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jdeveloperapps.heartfm.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), View.OnClickListener  {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var liveData: LiveData<Boolean>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        liveData = viewModel.getData()
        liveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                play_pause.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp)
                showPB(false)
            } else {
                play_pause.setImageResource(R.drawable.ic_play_circle_outline_black_24dp)
            }
        })
        play_pause.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.play_pause -> {
                if (liveData.value!!) {
                    viewModel.stop()
                } else {
                    showPB(true)
                    viewModel.play()
                }
            }
        }
    }

    fun showPB(visible: Boolean) {
        if (visible) {
            progress_load.visibility = View.VISIBLE
            play_pause.visibility = View.GONE
        } else {
            progress_load.visibility = View.GONE
            play_pause.visibility = View.VISIBLE
        }
    }

}
