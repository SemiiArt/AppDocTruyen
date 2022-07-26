package com.tuyenhoang.appdoctruyenjsoupv2.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuyenhoang.appdoctruyenjsoupv2.Adapter.ItemTopicAdapter
import com.tuyenhoang.appdoctruyenjsoupv2.ItemTopic
import com.tuyenhoang.appdoctruyenjsoupv2.R
import com.tuyenhoang.appdoctruyenjsoupv2.databinding.ActivityMainBinding
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private var listTopic= mutableListOf<ItemTopic>()
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        readData("https://truyencotich.vn/")
    }

    private fun readData(links:String) {
        thread {
            val doc= Jsoup.connect(links).get()
            val listItemTopic=doc.getElementsByClass("nav-menu clear")
            val listItemTopics=listItemTopic[0].getElementsByTag("li")
            for (list in listItemTopics){
                val title=list.getElementsByTag("a").text()
                val link=list.getElementsByTag("a")[0].attr("href").toString()
                listTopic.add(ItemTopic(link,title))
            }
            this.runOnUiThread {
                val adapter=ItemTopicAdapter(listTopic)
                binding.rv.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                binding.rv.adapter=adapter
            }
        }
    }
}