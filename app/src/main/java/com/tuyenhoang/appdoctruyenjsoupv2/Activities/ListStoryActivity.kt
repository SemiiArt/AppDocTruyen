package com.tuyenhoang.appdoctruyenjsoupv2.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuyenhoang.appdoctruyenjsoupv2.Adapter.ItemStoryAdapter
import com.tuyenhoang.appdoctruyenjsoupv2.ItemStory
import com.tuyenhoang.appdoctruyenjsoupv2.ItemTopic
import com.tuyenhoang.appdoctruyenjsoupv2.R
import com.tuyenhoang.appdoctruyenjsoupv2.databinding.ActivityListStoryBinding
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class ListStoryActivity : AppCompatActivity() {
    private lateinit var binding:ActivityListStoryBinding
    private var listItemStory= mutableListOf<ItemStory>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_list_story)

        val itemTopic:ItemTopic=intent.getSerializableExtra("itemTopicData") as ItemTopic
        readData(itemTopic.linkHtml)
        binding.listTitle.text=itemTopic.topic
    }

    private fun readData(linkHtml:String) {
        thread {
            val doc=Jsoup.connect(linkHtml).get()
            val listItemStoryy = doc.getElementsByClass("clear")
            val listStorys = listItemStoryy[0].getElementsByTag("article")

            for (listStoris in listStorys) {

                val linkImage = listStoris.getElementsByClass("post-thumb")[0]
                    .getElementsByTag("a")[0].getElementsByTag("img")[0].absUrl("src").toString()

                val title = listStoris.getElementsByClass("post-thumb")[0]
                    .getElementsByTag("a")[0].getElementsByTag("img")[0].attr("alt").toString()
                val content = listStoris.getElementsByClass("entry-content")[0]
                    .getElementsByTag("p").text()
                val linkH=listStoris.getElementsByClass("post-thumb")[0]
                    .getElementsByTag("a")[0].attr("href").toString()
                listItemStory.add(ItemStory(linkH,linkImage,title,content))
            }
            this.runOnUiThread {
                val adapter=ItemStoryAdapter(listItemStory)
                binding.rv2.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                binding.rv2.adapter=adapter
            }
        }
    }
}